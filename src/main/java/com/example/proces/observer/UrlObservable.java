package com.example.proces.observer;

import java.util.ArrayList;
import java.util.List;


//OBSERVABLE
public class UrlObservable {
    private List<Observer> observers = new ArrayList<>();
    private List<String> urls = new ArrayList<>();

    public void addObserver(Observer observer) {
        observers.add(observer);
    }

    public void addUrl(String url, String uniqueString) {
        urls.add(url + " encolado como " + uniqueString);
        notifyAllObservers(url, uniqueString);
    }

    private void notifyAllObservers(String url, String uniqueString) {
        for (Observer observer : observers) {
            observer.update(url, uniqueString);
        }
    }
}
