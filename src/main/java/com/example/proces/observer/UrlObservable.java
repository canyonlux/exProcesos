package com.example.proces.observer;

import java.util.ArrayList;
import java.util.List;


//OBSERVABLE
public class UrlObservable {
    private List<Observer> observers = new ArrayList<>(); //Lista de observadores
    private List<String> urls = new ArrayList<>(); //Lista de urls

    public void addObserver(Observer observer) {
        observers.add(observer);
    } //Metodo para agregar un observador

    public void addUrl(String url, String uniqueString) { //Metodo para agregar una url
        urls.add(url + " encolado como " + uniqueString);
        notifyAllObservers(url, uniqueString);
    }

    private void notifyAllObservers(String url, String uniqueString) { //Metodo para notificar a todos los observadores
        for (Observer observer : observers) {
            observer.update(url, uniqueString);
        }
    }
}
