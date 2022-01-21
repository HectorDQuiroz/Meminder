package com.example.meminder;

import java.io.Serializable;

public class Person implements Serializable {

    String nameS;
    String nameP;
    int imd_Dollar;
    String cantidad;
    String color;

    public Person() {

    }

    public Person(String nameS, String nameP, int imd_Dollar, String cantidad, String color) {
        this.nameS = nameS;
        this.nameP = nameP;
        this.imd_Dollar = imd_Dollar;
        this.cantidad = cantidad;
        this.color = color;
    }

    public String getNameS() {
        return nameS;
    }

    public void setNameS(String nameS) {
        this.nameS = nameS;
    }

    public String getNameP() {
        return nameP;
    }

    public void setNameP(String nameP) {
        this.nameP = nameP;
    }

    public int getImd_Dollar() {
        return imd_Dollar;
    }

    public void setImd_Dollar(int imd_Dollar) {
        this.imd_Dollar = imd_Dollar;
    }

    public String getCantidad() {
        return cantidad;
    }

    public void setCantidad(String cantidad) {
        this.cantidad = cantidad;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }
}
