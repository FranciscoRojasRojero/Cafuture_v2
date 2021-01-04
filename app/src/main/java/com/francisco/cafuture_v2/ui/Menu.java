package com.francisco.cafuture_v2.ui;

public class Menu {
    private String titulo;
    private String descripcion;
    private String precio;

    public Menu(){

    }

    public Menu (String titulo, String descripcion, String precio){
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.precio = precio;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getPrecio() {
        return precio;
    }

    public void setPrecio(String precio) {
        this.precio = precio;
    }
}
