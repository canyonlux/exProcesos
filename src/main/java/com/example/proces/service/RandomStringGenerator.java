package com.example.proces.service;

import java.security.SecureRandom;
import java.util.Random;

//Clase para generar un string aleatorio
public class RandomStringGenerator {
    private static final String CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789"; //Caracteres que se pueden usar
    private static final int LENGTH = 20; //Longitud del string
    private final Random random = new SecureRandom(); //Generador de numeros aleatorios

    public String generate() { //Metodo para generar el string
        StringBuilder sb = new StringBuilder(LENGTH);
        for (int i = 0; i < LENGTH; i++) {
            sb.append(CHARACTERS.charAt(random.nextInt(CHARACTERS.length())));
        }
        return sb.toString();
    }
}
