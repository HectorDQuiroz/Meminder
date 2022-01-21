package com.example.meminder;

public class datos_Add {

    private String cantidad;
    private String nameS;
    private String nameP;
    private String dia;
    private String periodo;
    private String primerPago;
    private String color;
    private String tipoPago;
    private String proximoP;

    public datos_Add() {
    }

    public datos_Add(String cantidad, String name_Sub, String nameP, String dia, String periodo, String primerPago, String color, String tipoPago, String proximoP) {
        this.cantidad = cantidad;
        this.nameS = name_Sub;
        this.nameP = nameP;
        this.dia = dia;
        this.periodo = periodo;
        this.primerPago = primerPago;
        this.color = color;
        this.tipoPago = tipoPago;
        this.proximoP = proximoP;
    }

    public String getProximoP() {
        return proximoP;
    }

    public void setProximoP(String proximoP) {
        this.proximoP = proximoP;
    }

    public String getCantidad() {
        return cantidad;
    }

    public void setCantidad(String cantidad) {
        this.cantidad = cantidad;
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

    public String getDia() {
        return dia;
    }

    public void setDia(String dia) {
        this.dia = dia;
    }

    public String getPeriodo() {
        return periodo;
    }

    public void setPeriodo(String periodo) {
        this.periodo = periodo;
    }

    public String getPrimerPago() {
        return primerPago;
    }

    public void setPrimerPago(String primerPago) {
        this.primerPago = primerPago;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getTipoPago() {
        return tipoPago;
    }

    public void setTipoPago(String tipoPago) {
        this.tipoPago = tipoPago;
    }
}
