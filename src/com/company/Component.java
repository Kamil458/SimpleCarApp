package com.company;

public abstract class Component {
    private final String name;
    private final float weight;
    private final float price;

    public Component(String name, float weight, float price){
        this.name = name;
        this.weight = weight;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public float getWeight() {
        return weight;
    }

    public float getPrice() {
        return price;
    }
}
