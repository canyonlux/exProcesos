package com.example.proces.observer;

import java.io.*;
import java.net.URL;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;
import java.util.zip.*;
//OBSERVADOR
/**
 * Clase que actúa como observador para descargar archivos de URL dadas y comprimirlos en un archivo ZIP.
 * Utiliza un pool de threads para realizar descargas asincrónicas y CompletableFuture para la sincronización.
 */
public class DownloaderAndZipper implements Observer{
    private ExecutorService executor = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
    private List<Future<Path>> futureDownloads = new ArrayList<>();

    /**
     * Se invoca cuando se actualiza la lista de URLs.
     * Si la URL está vacía, inicia el proceso de compresión de los archivos descargados.
     * De lo contrario, programa la descarga del archivo.
     *
     * @param url La URL del archivo a descargar.
     * @param uniqueString Una cadena aleatoria única para el nombre del archivo.
     */
    public void update(String url, String uniqueString) {
        if (url.isEmpty()) {
            CompletableFuture<Void> allDownloads = CompletableFuture.allOf(
                    futureDownloads.stream()
                            .map(future -> CompletableFuture.runAsync(() -> {
                                try {
                                    future.get();
                                } catch (InterruptedException | ExecutionException e) {
                                    e.printStackTrace();
                                }
                            }, executor))
                            .toArray(CompletableFuture[]::new)
            );

            allDownloads.thenRunAsync(() -> {
                try {
                    compressFiles(futureDownloads);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }, executor).join();

            executor.shutdown();
        } else {
            Future<Path> futureDownload = executor.submit(() -> downloadFile(url, uniqueString));
            futureDownloads.add(futureDownload);
        }
    }

    /**
     * Descarga el archivo de la URL dada y lo guarda con el nombre proporcionado.
     *
     * @param urlString La URL del archivo a descargar.
     * @param fileName El nombre bajo el cual guardar el archivo descargado.
     * @return La ruta al archivo descargado.
     * @throws IOException Si ocurre un error durante la descarga.
     */
    private Path downloadFile(String urlString, String fileName) throws IOException {
        URL url = new URL(urlString);
        Path tempDir = Paths.get("ejercicio2descargas");
        Files.createDirectories(tempDir);
        Path filePath = tempDir.resolve(fileName + ".html");

        try (InputStream in = url.openStream()) {
            Files.copy(in, filePath, StandardCopyOption.REPLACE_EXISTING);
            System.out.println(urlString + " encolado como " + fileName);
            return filePath;
        }
    }

    /**
     * Comprime todos los archivos descargados en un único archivo ZIP. la ruta del archivo ZIP se imprime en la consola.
     *
     * @param downloadedFiles Lista de futuros que representan las rutas a los archivos descargados.
     * @throws IOException Si ocurre un error durante la compresión.
     */
    private void compressFiles(List<Future<Path>> downloadedFiles) throws IOException {
        Path zipFilePath = Paths.get("ejercicio2descargas/downloaded_files.zip");

        try (ZipOutputStream zos = new ZipOutputStream(Files.newOutputStream(zipFilePath))) {
            for (Future<Path> future : downloadedFiles) {
                Path file = future.get(); // Espera y obtiene el resultado
                ZipEntry zipEntry = new ZipEntry(file.getFileName().toString());
                zos.putNextEntry(zipEntry);

                Files.copy(file, zos);
                zos.closeEntry();

                Files.delete(file);
            }
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        System.out.println("Se va a proceder a descargar y comprimir los ficheros");
        System.out.println("Archivos comprimidos en: " + zipFilePath.toAbsolutePath());
    }
}
