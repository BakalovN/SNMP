package com.company;

import javafx.scene.shape.Line;

public class Agent {
    private String name;
    private String status;
    private String access;
    private String temperature;
    private String memoryUsage;
    private String CPUUsage;
    private Line line;
    private int Ox;
    private int Oy;

    public Agent(String name){
        this.name = name;
        this.status = "ready";
        this.access = "admin only";
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getAccess() {
        return access;
    }

    public void setAccess(String access) {
        this.access = access;
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

    public String getMemoryUsage() {
        return memoryUsage;
    }

    public void setMemoryUsage(String memoryUsage) {
        this.memoryUsage = memoryUsage;
    }

    public String getCPUUsage() {
        return CPUUsage;
    }

    public void setCPUUsage(String CPUUsage) {
        this.CPUUsage = CPUUsage;
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

    public String getTemperature() {
        return temperature;
    }

    public void setTemperature(String temperature) {
        this.temperature = temperature;
    }
}
