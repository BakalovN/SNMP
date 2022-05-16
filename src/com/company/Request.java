package com.company;

import javafx.scene.control.Label;
import javafx.scene.shape.Line;


public class Request {
    private Line requestLine;
    private Line returnLine;
    private Label requestLabel;
    private Label returnLabel;
    private Agent agent;

    public Request(Line requestLine, Line returnLine, Label requestLabel, Label returnLabel, Agent agent){
        this.requestLine = requestLine;
        this.returnLine = returnLine;
        this.requestLabel = requestLabel;
        this.returnLabel = returnLabel;
        this.agent = agent;
    }

    public Line getRequestLine() {
        return requestLine;
    }

    public void setRequestLine(Line requestLine) {
        this.requestLine = requestLine;
    }

    public Line getReturnLine() {
        return returnLine;
    }

    public void setReturnLine(Line returnLine) {
        this.returnLine = returnLine;
    }

    public Label getRequestLabel() {
        return requestLabel;
    }

    public void setRequestLabel(Label requestLabel) {
        this.requestLabel = requestLabel;
    }

    public Label getReturnLabel() {
        return returnLabel;
    }

    public void setReturnLabel(Label returnLabel) {
        this.returnLabel = returnLabel;
    }

    public Agent getAgent() {
        return agent;
    }

    public void setAgent(Agent agent) {
        this.agent = agent;
    }
}