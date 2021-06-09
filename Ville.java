package com.estem.applicationweither;

public class Ville {
    int id;
    String Ville;
    String temperature ;


    public Ville(int id, String ville, String temperature) {
        this.id = id;
        Ville = ville;
        this.temperature = temperature;
    }

    public Ville(String ville, String temperature) {
        Ville = ville;
        this.temperature = temperature;
    }

    public Ville() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getVille() {
        return Ville;
    }

    public void setVille(String ville) {
        Ville = ville;
    }

    public String getTemperature() {
        return temperature;
    }

    public void setTemperature(String temperature) {
        this.temperature = temperature;
    }
}
