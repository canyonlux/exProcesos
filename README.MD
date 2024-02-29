# Gestor de Descargas y Compresor de Archivos

Este proyecto es una aplicación Java que permite a los usuarios ingresar URLs de archivos para descargarlos de manera asincrónica. Una vez recopiladas las URLs, el programa descarga cada archivo en el servidor, asignándole un nombre único generado aleatoriamente, y finalmente, comprime todos los archivos descargados en un único archivo .zip.

## Funcionalidades Principales

### Recopilación de URLs: La aplicación solicita al usuario que ingrese URLs una por una. Cada URL se asociará con una cadena única de 20 caracteres generada aleatoriamente.

### Descarga Asincrónica: Utiliza un pool de hilos para descargar archivos de manera asincrónica, mejorando la eficiencia y el rendimiento.

### Compresión de Archivos: Después de descargar todos los archivos especificados, los comprime en un único archivo .zip.

### Patrón Observer-Listener: Implementa este patrón de diseño para observar cambios en la lista de URLs y reaccionar a estos cambios (por ejemplo, iniciar el proceso de descarga y compresión).

## Implementación de Futuros

La aplicación hace uso intensivo de futuros de Java para manejar las operaciones de descarga de archivos de manera asincrónica. Esto permite que el programa continúe recopilando URLs de entrada del usuario mientras se realizan descargas en segundo plano. Los futuros representan el resultado de estas operaciones asincrónicas, permitiendo que el programa espere a que todas las descargas se completen antes de proceder a la compresión de archivos en un .zip.

## Patrón Observer-Listener

El patrón Observer-Listener se implementa a través de las interfaces personalizadas Observer y Observable. Este patrón permite a la clase DownloaderAndZipper (observador) escuchar cambios en la lista de URLs (observable). Cuando un usuario ingresa una URL nueva, el observable notifica a todos sus observadores, desencadenando la descarga asincrónica del archivo correspondiente. Al ingresar una URL vacía, se señala a los observadores que comiencen el proceso de compresión de todos los archivos descargados.

## Cómo Usar

Iniciar la Aplicación: Ejecuta la clase Main para comenzar.
Ingresar URLs: Escribe las URLs que deseas descargar cuando se solicite y presiona Enter después de cada una.
Finalizar la Entrada: Para comenzar el proceso de descarga y compresión, simplemente presiona Enter sin escribir una URL.

## Requisitos

Java JDK 21 o superior.
Gradle 8.5 (para la construcción y gestión del proyecto).

## Construcción y Ejecución

Para construir y ejecutar el proyecto, utiliza los siguientes comandos de Gradle:

bash
Copy code
./gradlew build
./gradlew run
Reemplaza ./gradlew con gradlew.bat en Windows.

## Autor
https://github.com/canyonlux

