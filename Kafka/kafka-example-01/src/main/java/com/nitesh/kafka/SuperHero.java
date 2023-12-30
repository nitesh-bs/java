package com.nitesh.kafka;

import java.io.Serializable;

public class SuperHero {
    private String name;
    private String message;

    public SuperHero(){

    }
    public SuperHero(String name, String message) {
        this.name = name;
        this.message = message;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "SuperHero{" +
                "name='" + name + '\'' +
                ", message='" + message + '\'' +
                '}';
    }
}