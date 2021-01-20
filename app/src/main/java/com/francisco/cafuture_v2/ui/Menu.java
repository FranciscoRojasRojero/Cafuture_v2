package com.francisco.cafuture_v2.ui;

public class Menu {
    //nombre, precio, tiempo, imagen, descripcion, disponibilidad
    private String name;
    private double price;
    private double time;
    private String img;
    private String description;
    private boolean available;

    public Menu(){

    }

    public Menu (String name, double price, double time, String img, String description, boolean available){
        this.name = name;
        this.price = price;
        this.time = time;
        this.img = img;
        this.description = description;
        this.available = available;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getTime() {
        return time;
    }

    public void setTime(double time) {
        this.time = time;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

}
