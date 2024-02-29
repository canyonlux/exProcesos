package com.example.proces;

import com.example.proces.observer.DownloaderAndZipper;
import com.example.proces.observer.Observer;
import com.example.proces.observer.UrlObservable;
import com.example.proces.service.RandomStringGenerator;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        UrlObservable observable = new UrlObservable(); //Observable
        DownloaderAndZipper downloaderAndZipper = new DownloaderAndZipper(); //Observer
        RandomStringGenerator generator = new RandomStringGenerator(); //Service

        observable.addObserver((Observer) downloaderAndZipper);

        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("Ingrese una URL para descargar (deje en blanco y presione ENTER para finalizar):");
            String input = scanner.nextLine();

            if (input.isEmpty()) {
                observable.addUrl("", "");
                break;
            } else {
                String uniqueString = generator.generate();
                observable.addUrl(input, uniqueString);
            }
        }
    }
}
