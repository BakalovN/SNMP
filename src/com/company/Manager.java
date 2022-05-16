package com.company;

import javafx.scene.shape.Line;

public class Manager {
    private String name;
    private int Ox;
    private int Oy;
    private Line line;
    public Manager (String name){
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getOx() {
        return Ox;
    }

    public void setOx(int ox) {
        Ox = ox;
    }

    public int getOy() {
        return Oy;
    }

    public void setOy(int oy) {
        Oy = oy;
    }

    public Line getLine() {
        return line;
    }

    public void setLine(Line line) {
        this.line = line;
    }

    @Override
    public String toString() {
        return this.name;
    }
}
