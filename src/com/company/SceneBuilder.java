package com.company;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class SceneBuilder extends Application {
    int getRequestsFlag = 0;
    int setRequestsFlag = 0;
    int monitorFlag = 0;
    List<Request> Requests = new ArrayList<>();
    List<Label> nameLabels = new ArrayList<>();
    public static void main(String[] args) {
        launch();
    }

    @Override
    public void start(Stage stage) throws Exception {
        Group root = new Group();
        List<Agent> agents = new ArrayList<>();
        List<Manager> managers = new ArrayList<>();

        commandView(root, agents, managers);

        Scene scene = new Scene(root, 1000, 800);
        stage.setTitle("SNMP");
        stage.setScene(scene);
        stage.setMaximized(true);
        stage.show();
    }

    public void commandView(Group root, List<Agent> agents, List<Manager> managers){
        List<String> fields = new ArrayList<>();
        fields.add("name");
        fields.add("status");
        fields.add("access");
        Button addAgentButton = new Button("Add Agent");
        Button addManagerButton = new Button("Add Manager");
        Label getRequestLabel = new Label("get Request:");
        Label setRequestLabel = new Label("set Request:");
        ChoiceBox<Agent> getRequestChoiceBox = new ChoiceBox();
        ChoiceBox<Agent> setRequestChoiceBox = new ChoiceBox();
        ChoiceBox<String> getRequestChoiceBoxFields = new ChoiceBox<>();
        ChoiceBox<String> setRequestChoiceBoxFields = new ChoiceBox<>();
        Button getRequestButton = new Button("Get Request");
        TextField setValue = new TextField();
        Button setRequestButton = new Button("Set request");
        Button refresh = new Button("Refresh");
        Button monitorButton = new Button("Monitor");
        addAgentButton.setPrefWidth(100);
        addAgentButton.setPrefHeight(30);
        addAgentButton.setTranslateX(0);
        addAgentButton.setTranslateY(0);
        addManagerButton.setPrefWidth(100);
        addManagerButton.setPrefHeight(30);
        addManagerButton.setTranslateX(0);
        addManagerButton.setTranslateY(30);
        getRequestLabel.setTranslateX(110);
        getRequestLabel.setTranslateY(0);
        getRequestLabel.setPrefWidth(100);
        getRequestLabel.setPrefHeight(30);
        setRequestLabel.setTranslateX(110);
        setRequestLabel.setTranslateY(30);
        setRequestLabel.setPrefWidth(100);
        setRequestLabel.setPrefHeight(30);
        getRequestChoiceBox.setTranslateX(210);
        getRequestChoiceBox.setTranslateY(0);
        setRequestChoiceBox.setTranslateX(210);
        setRequestChoiceBox.setTranslateY(30);
        getRequestChoiceBoxFields.setTranslateX(310);
        getRequestChoiceBoxFields.setTranslateY(0);
        getRequestChoiceBoxFields.getItems().addAll(fields);
        setRequestChoiceBoxFields.setTranslateX(310);
        setRequestChoiceBoxFields.setTranslateY(30);
        setRequestChoiceBoxFields.getItems().addAll(fields);
        getRequestButton.setTranslateX(410);
        getRequestButton.setTranslateY(0);
        getRequestButton.setPrefHeight(30);
        getRequestButton.setPrefWidth(100);
        setValue.setPromptText("New Value");
        setValue.setTranslateX(410);
        setValue.setTranslateY(30);
        setValue.setPrefWidth(100);
        setValue.setPrefHeight(30);
        setRequestButton.setTranslateX(510);
        setRequestButton.setTranslateY(30);
        setRequestButton.setPrefWidth(100);
        setRequestButton.setPrefHeight(30);
        refresh.setTranslateX(510);
        refresh.setTranslateY(0);
        refresh.setPrefWidth(100);
        refresh.setPrefHeight(30);
        monitorButton.setPrefHeight(60);
        monitorButton.setPrefWidth(100);
        monitorButton.setTranslateY(0);
        monitorButton.setTranslateX(610);

        monitorButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                monitorFlag = 1;
                drawingView(root, agents, managers, getRequestChoiceBox, setRequestChoiceBox, getRequestChoiceBoxFields, setRequestChoiceBoxFields, setValue);
            }
        });

        refresh.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                drawingView(root, agents, managers, getRequestChoiceBox, setRequestChoiceBox, getRequestChoiceBoxFields, setRequestChoiceBoxFields, setValue);
            }
        });

        setRequestButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                setRequestsFlag = 1;
                drawingView(root, agents, managers, getRequestChoiceBox, setRequestChoiceBox, getRequestChoiceBoxFields, setRequestChoiceBoxFields, setValue);

            }
        });

        getRequestButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                getRequestsFlag = 1;
                drawingView(root, agents, managers, getRequestChoiceBox, setRequestChoiceBox, getRequestChoiceBoxFields, setRequestChoiceBoxFields, setValue);
            }
        });
        addAgentButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                agents.add(new Agent("Agent " + String.valueOf(agents.size()+1)));
                drawingView(root, agents, managers, getRequestChoiceBox, setRequestChoiceBox, getRequestChoiceBoxFields, setRequestChoiceBoxFields, setValue);
                setRequestChoiceBox.getItems().add(agents.get(agents.size()-1));
                getRequestChoiceBox.getItems().add(agents.get(agents.size()-1));
            }
        });
        addManagerButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                managers.add(new Manager("Manager " + String.valueOf(managers.size()+1)));
                drawingView(root, agents, managers, getRequestChoiceBox, setRequestChoiceBox, getRequestChoiceBoxFields, setRequestChoiceBoxFields, setValue);

            }
        });

        root.getChildren().add(addAgentButton);
        root.getChildren().add(addManagerButton);
        root.getChildren().add(setRequestLabel);
        root.getChildren().add(getRequestLabel);
        root.getChildren().add(getRequestChoiceBox);
        root.getChildren().add(setRequestChoiceBox);
        root.getChildren().add(getRequestChoiceBoxFields);
        root.getChildren().add(setRequestChoiceBoxFields);
        root.getChildren().add(getRequestButton);
        root.getChildren().add(setValue);
        root.getChildren().add(setRequestButton);
        root.getChildren().add(refresh);
        root.getChildren().add(monitorButton);
    }

    public void drawingView(Group root, List<Agent> agents, List<Manager> managers, ChoiceBox<Agent> getRequestChoiceBox, ChoiceBox<Agent> setRequestChoiceBox, ChoiceBox<String> getRequestChoiceBoxFields, ChoiceBox<String> setRequestChoiceBoxFields, TextField setValue){
        ScrollPane drawings = new ScrollPane();
        Group drawingRoot = new Group();

        if (agents.size()>=1){
            agents.get(0).setOx(30);
            agents.get(0).setOy(90);
            int distance = 0;
            for (int i = 0; i < agents.size(); i++) {
                agents.get(i).setOy(120);
                agents.get(i).setOx(30+distance);
                Label nameLabel = new Label(agents.get(i).getName());
                nameLabel.setTranslateY(90);
                nameLabel.setTranslateX(15+distance);
                Line line = new Line();
                line.setStartX(agents.get(i).getOx());
                line.setEndX(agents.get(i).getOx());
                line.setStartY(agents.get(i).getOy());
                line.setEndY(740 + Requests.size()*100);
                agents.get(i).setLine(line);
                distance = distance + 100;
                nameLabels.add(nameLabel);
                drawingRoot.getChildren().add(nameLabel);
                drawingRoot.getChildren().add(line);
            }
        }
        if (managers.size()>=1){
            managers.get(0).setOx(agents.get(agents.size()-1).getOx() + 100);
            managers.get(0).setOy(120);
            int distance = 0;
            for (int i = 0; i < managers.size(); i++) {
                managers.get(i).setOx(agents.get(agents.size()-1).getOx() + 100+distance);
                managers.get(i).setOy(120);
                Label nameLabel = new Label(managers.get(i).getName());
                nameLabel.setTranslateY(managers.get(i).getOy()-30);
                nameLabel.setTranslateX(managers.get(i).getOx()-15);
                Line line = new Line();
                line.setStartX(managers.get(i).getOx());
                line.setEndX(managers.get(i).getOx());
                line.setStartY(managers.get(i).getOy());
                line.setEndY(740 + Requests.size()*100);
                managers.get(i).setLine(line);
                distance = distance + 100;
                drawingRoot.getChildren().add(nameLabel);
                drawingRoot.getChildren().add(line);
            }
            for (int i = 0; i < Requests.size(); i++) {
                Requests.get(i).getRequestLine().setStartX(managers.get(0).getOx());
                Requests.get(i).getReturnLine().setStartX(managers.get(0).getOx());
                Requests.get(i).getRequestLabel().setTranslateX(managers.get(0).getOx()-70);
                Requests.get(i).getReturnLabel().setTranslateX(managers.get(0).getOx()-70);
                drawingRoot.getChildren().add(Requests.get(i).getRequestLine());
                drawingRoot.getChildren().add(Requests.get(i).getRequestLabel());
                drawingRoot.getChildren().add(Requests.get(i).getReturnLine());
                drawingRoot.getChildren().add(Requests.get(i).getReturnLabel());
            }
        }
        if (getRequestsFlag == 1){
            getRequestsFlag = 0;
            Agent agent = getRequestChoiceBox.getValue();
            String getField = getRequestChoiceBoxFields.getValue();
            getRequests(agent, getField, agents, managers, drawingRoot);
            trapInformGenerator(agents, managers, drawingRoot);
        }

        if (setRequestsFlag == 1){
            setRequestsFlag = 0;
            String valueToSet = setValue.getText();
            Agent agent = setRequestChoiceBox.getValue();
            String getField = setRequestChoiceBoxFields.getValue();
            setRequests(agent, getField, agents, managers, valueToSet, drawingRoot);
            trapInformGenerator(agents, managers, drawingRoot);
        }

        if (monitorFlag == 1){
            monitorFlag = 0;
            trapInformGenerator(agents, managers, drawingRoot);
        }


        drawings.setTranslateX(0);
        drawings.setTranslateY(60);
        drawings.setPrefHeight(740);
        drawings.setPrefWidth(1531);
        drawings.setContent(drawingRoot);
        root.getChildren().add(drawings);
    }

    public void getRequests(Agent agent, String getField, List<Agent> agents, List<Manager> managers, Group drawingRoot){
        int indexOfAgent = 0;//Integer.parseInt(String.valueOf(agent.getName().charAt(agent.getName().length()-1)))-1;

        for (int i = 0; i < agents.size(); i++) {
            if (agent.hashCode()==agents.get(i).hashCode()){
                indexOfAgent = i;
                break;
            }
        }

        if (Requests.size()==0){
            Line line = new Line();
            line.setStartX(managers.get(0).getOx());
            line.setEndX(agents.get(indexOfAgent).getOx());
            line.setStartY(managers.get(0).getOy());
            line.setEndY(managers.get(0).getOy());
            Label label = new Label("get " + getField);
            label.setTranslateY(line.getEndY());
            label.setTranslateX(line.getStartX()-70);
            Line line2 = new Line();
            line2.setStartX(managers.get(0).getOx());
            line2.setEndX(agents.get(indexOfAgent).getOx());
            line2.setStartY(managers.get(0).getOy() + 50);
            line2.setEndY(managers.get(0).getOy() + 50);
            line.getStrokeDashArray().addAll(25d, 10d);
            line2.getStrokeDashArray().addAll(25d, 10d);
            Label label2 = new Label();
            if (getField.equals("name")){
                label2.setText(agents.get(indexOfAgent).getName());
            }else if (getField.equals("access")){
                label2.setText(agents.get(indexOfAgent).getAccess());
            }else if (getField.equals("status")){
                label2.setText(agents.get(indexOfAgent).getStatus());
            }
            label2.setTranslateX(line2.getStartX()-70);
            label2.setTranslateY(line2.getEndY());
            drawingRoot.getChildren().add(label2);
            drawingRoot.getChildren().add(label);
            drawingRoot.getChildren().add(line);
            drawingRoot.getChildren().add(line2);
            Request request = new Request(line, line2, label, label2, agents.get(indexOfAgent));
            Requests.add(request);
        }else if (Requests.size()>0 && agents.get(indexOfAgent).getName().equals(Requests.get(Requests.size()-1).getAgent().getName())){
            Line line = new Line();
            line.setStartX(Requests.get(Requests.size()-1).getRequestLine().getStartX());
            line.setEndX(Requests.get(Requests.size()-1).getRequestLine().getEndX());
            line.setStartY(Requests.get(Requests.size()-1).getRequestLine().getStartY()+100);
            line.setEndY(Requests.get(Requests.size()-1).getRequestLine().getEndY()+100);
            Label label = new Label("get " + getField);
            label.setTranslateY(line.getEndY());
            label.setTranslateX(Requests.get(Requests.size()-1).getRequestLabel().getTranslateX());
            Line line2 = new Line();
            line2.setStartX(Requests.get(Requests.size()-1).getReturnLine().getStartX());
            line2.setEndX(Requests.get(Requests.size()-1).getReturnLine().getEndX());
            line2.setStartY(Requests.get(Requests.size()-1).getReturnLine().getStartY()+100);
            line2.setEndY(Requests.get(Requests.size()-1).getReturnLine().getEndY()+100);
            line.getStrokeDashArray().addAll(25d, 10d);
            line2.getStrokeDashArray().addAll(25d, 10d);
            Label label2 = new Label();
            if (getField.equals("name")){
                label2.setText(agents.get(indexOfAgent).getName());
            }else if (getField.equals("access")){
                label2.setText(agents.get(indexOfAgent).getAccess());
            }else if (getField.equals("status")){
                label2.setText(agents.get(indexOfAgent).getStatus());
            }
            label2.setTranslateX(Requests.get(Requests.size()-1).getReturnLabel().getTranslateX());
            label2.setTranslateY(line2.getEndY());
            drawingRoot.getChildren().add(label2);
            drawingRoot.getChildren().add(label);
            drawingRoot.getChildren().add(line);
            drawingRoot.getChildren().add(line2);
            Request request = new Request(line, line2, label, label2, agents.get(indexOfAgent));
            Requests.add(request);
        }else {
            Line line = new Line();
            line.setStartX(managers.get(0).getOx());
            line.setEndX(agents.get(indexOfAgent).getOx());
            line.setStartY(Requests.get(Requests.size()-1).getRequestLine().getStartY()+100);
            line.setEndY(Requests.get(Requests.size()-1).getRequestLine().getEndY()+100);
            Label label = new Label("get " + getField);
            label.setTranslateY(line.getEndY());
            label.setTranslateX(Requests.get(Requests.size()-1).getRequestLabel().getTranslateX());
            Line line2 = new Line();
            line2.setStartX(managers.get(0).getOx());
            line2.setEndX(agents.get(indexOfAgent).getOx());
            line2.setStartY(Requests.get(Requests.size()-1).getReturnLine().getStartY()+100);
            line2.setEndY(Requests.get(Requests.size()-1).getReturnLine().getEndY()+100);
            line.getStrokeDashArray().addAll(25d, 10d);
            line2.getStrokeDashArray().addAll(25d, 10d);
            Label label2 = new Label();
            if (getField.equals("name")){
                label2.setText(agents.get(indexOfAgent).getName());
            }else if (getField.equals("access")){
                label2.setText(agents.get(indexOfAgent).getAccess());
            }else if (getField.equals("status")){
                label2.setText(agents.get(indexOfAgent).getStatus());
            }
            label2.setTranslateX(Requests.get(Requests.size()-1).getReturnLabel().getTranslateX());
            label2.setTranslateY(line2.getEndY());
            drawingRoot.getChildren().add(label2);
            drawingRoot.getChildren().add(label);
            drawingRoot.getChildren().add(line);
            drawingRoot.getChildren().add(line2);
            Request request = new Request(line, line2, label, label2, agents.get(indexOfAgent));
            Requests.add(request);
        }
        for (int i = 0; i < agents.size(); i++) {
            drawingRoot.getChildren().remove(agents.get(i).getLine());
            agents.get(i).getLine().setEndY(agents.get(i).getLine().getEndY()+100);
            drawingRoot.getChildren().add(agents.get(i).getLine());
        }
        for (int i = 0; i < managers.size(); i++) {
            drawingRoot.getChildren().remove(managers.get(i).getLine());
            managers.get(i).getLine().setEndY(managers.get(i).getLine().getEndY()+100);
            drawingRoot.getChildren().add(managers.get(i).getLine());
        }
    }

    public void setRequests (Agent agent, String getField, List<Agent> agents, List<Manager> managers, String valueToSet, Group drawingRoot){
        int indexOfAgent = 0;//Integer.parseInt(String.valueOf(agent.getName().charAt(agent.getName().length()-1)))-1;

        for (int i = 0; i < agents.size(); i++) {
            if (agent.hashCode()==agents.get(i).hashCode()){
                indexOfAgent = i;
                break;
            }
        }

        if (Requests.size()==0){
            Line line = new Line();
            line.setStartX(managers.get(0).getOx());
            line.setEndX(agents.get(indexOfAgent).getOx());
            line.setStartY(managers.get(0).getOy());
            line.setEndY(managers.get(0).getOy());
            Label label = new Label("set " + getField);
            label.setTranslateY(line.getEndY());
            label.setTranslateX(line.getStartX()-70);
            Line line2 = new Line();
            line2.setStartX(managers.get(0).getOx());
            line2.setEndX(agents.get(indexOfAgent).getOx());
            line2.setStartY(managers.get(0).getOy() + 50);
            line2.setEndY(managers.get(0).getOy() + 50);
            line.getStrokeDashArray().addAll(25d, 10d);
            line2.getStrokeDashArray().addAll(25d, 10d);
            Label label2 = new Label();
            if (getField.equals("name")){
                agents.get(indexOfAgent).setName(valueToSet);
                label2.setText("name set to: " + agents.get(indexOfAgent).getName());
            }else if (getField.equals("access")){
                agents.get(indexOfAgent).setAccess(valueToSet);
                label2.setText("access set to: " + agents.get(indexOfAgent).getAccess());
            }else if (getField.equals("status")){
                agents.get(indexOfAgent).setStatus(valueToSet);
                label2.setText("status set to: " + agents.get(indexOfAgent).getStatus());
            }
            label2.setTranslateX(line2.getStartX()-70);
            label2.setTranslateY(line2.getEndY());
            drawingRoot.getChildren().add(label2);
            drawingRoot.getChildren().add(label);
            drawingRoot.getChildren().add(line);
            drawingRoot.getChildren().add(line2);
            Request request = new Request(line, line2, label, label2, agents.get(indexOfAgent));
            Requests.add(request);
        }else if (Requests.size()>0 && agents.get(indexOfAgent).equals(Requests.get(Requests.size()-1).getAgent())){
            Line line = new Line();
            line.setStartX(Requests.get(Requests.size()-1).getRequestLine().getStartX());
            line.setEndX(Requests.get(Requests.size()-1).getRequestLine().getEndX());
            line.setStartY(Requests.get(Requests.size()-1).getRequestLine().getStartY()+100);
            line.setEndY(Requests.get(Requests.size()-1).getRequestLine().getEndY()+100);
            Label label = new Label("set " + getField);
            label.setTranslateY(line.getEndY());
            label.setTranslateX(Requests.get(Requests.size()-1).getRequestLabel().getTranslateX());
            Line line2 = new Line();
            line2.setStartX(Requests.get(Requests.size()-1).getReturnLine().getStartX());
            line2.setEndX(Requests.get(Requests.size()-1).getReturnLine().getEndX());
            line2.setStartY(Requests.get(Requests.size()-1).getReturnLine().getStartY()+100);
            line2.setEndY(Requests.get(Requests.size()-1).getReturnLine().getEndY()+100);
            line.getStrokeDashArray().addAll(25d, 10d);
            line2.getStrokeDashArray().addAll(25d, 10d);
            Label label2 = new Label();
            if (getField.equals("name")){
                agents.get(indexOfAgent).setName(valueToSet);
                label2.setText("name set to: " + agents.get(indexOfAgent).getName());
            }else if (getField.equals("access")){
                agents.get(indexOfAgent).setAccess(valueToSet);
                label2.setText("access set to: " + agents.get(indexOfAgent).getAccess());
            }else if (getField.equals("status")){
                agents.get(indexOfAgent).setStatus(valueToSet);
                label2.setText("status set to: " + agents.get(indexOfAgent).getStatus());
            }
            label2.setTranslateX(Requests.get(Requests.size()-1).getReturnLabel().getTranslateX());
            label2.setTranslateY(line2.getEndY());
            drawingRoot.getChildren().add(label2);
            drawingRoot.getChildren().add(label);
            drawingRoot.getChildren().add(line);
            drawingRoot.getChildren().add(line2);
            Request request = new Request(line, line2, label, label2, agents.get(indexOfAgent));
            Requests.add(request);
        }else {
            Line line = new Line();
            line.setStartX(managers.get(0).getOx());
            line.setEndX(agents.get(indexOfAgent).getOx());
            line.setStartY(Requests.get(Requests.size()-1).getRequestLine().getStartY()+100);
            line.setEndY(Requests.get(Requests.size()-1).getRequestLine().getEndY()+100);
            Label label = new Label("set " + getField);
            label.setTranslateY(line.getEndY());
            label.setTranslateX(Requests.get(Requests.size()-1).getRequestLabel().getTranslateX());
            Line line2 = new Line();
            line2.setStartX(managers.get(0).getOx());
            line2.setEndX(agents.get(indexOfAgent).getOx());
            line2.setStartY(Requests.get(Requests.size()-1).getReturnLine().getStartY()+100);
            line2.setEndY(Requests.get(Requests.size()-1).getReturnLine().getEndY()+100);
            line.getStrokeDashArray().addAll(25d, 10d);
            line2.getStrokeDashArray().addAll(25d, 10d);
            Label label2 = new Label();
            if (getField.equals("name")){
                agents.get(indexOfAgent).setName(valueToSet);
                label2.setText("name set to: " + agents.get(indexOfAgent).getName());
            }else if (getField.equals("access")){
                agents.get(indexOfAgent).setAccess(valueToSet);
                label2.setText("access set to: " + agents.get(indexOfAgent).getAccess());
            }else if (getField.equals("status")){
                agents.get(indexOfAgent).setStatus(valueToSet);
                label2.setText("status set to: " + agents.get(indexOfAgent).getStatus());
            }
            label2.setTranslateX(Requests.get(Requests.size()-1).getReturnLabel().getTranslateX());
            label2.setTranslateY(line2.getEndY());
            drawingRoot.getChildren().add(label2);
            drawingRoot.getChildren().add(label);
            drawingRoot.getChildren().add(line);
            drawingRoot.getChildren().add(line2);
            Request request = new Request(line, line2, label, label2, agents.get(indexOfAgent));
            Requests.add(request);
        }

        for (int i = 0; i < agents.size(); i++) {
            drawingRoot.getChildren().remove(agents.get(i).getLine());
            agents.get(i).getLine().setEndY(agents.get(i).getLine().getEndY()+100);
            drawingRoot.getChildren().add(agents.get(i).getLine());
        }
        for (int i = 0; i < managers.size(); i++) {
            drawingRoot.getChildren().remove(managers.get(i).getLine());
            managers.get(i).getLine().setEndY(managers.get(i).getLine().getEndY()+100);
            drawingRoot.getChildren().add(managers.get(i).getLine());
        }
    }
    public void inform(Agent agent, Group drawingRoot, String reason, List<Agent> agents, List<Manager> managers){
        int indexOfAgent = 0;//Integer.parseInt(String.valueOf(agent.getName().charAt(agent.getName().length()-1)))-1;

        for (int i = 0; i < agents.size(); i++) {
            if (agent.hashCode()==agents.get(i).hashCode()){
                indexOfAgent = i;
                break;
            }
        }

        if (Requests.size()==0){
            Line line = new Line();
            line.setStartX(managers.get(0).getOx());
            line.setEndX(agents.get(indexOfAgent).getOx());
            line.setStartY(managers.get(0).getOy());
            line.setEndY(managers.get(0).getOy());
            Label label = new Label("Seen.");

            Line line2 = new Line();
            line2.setStartX(managers.get(0).getOx());
            line2.setEndX(agents.get(indexOfAgent).getOx());
            line2.setStartY(managers.get(0).getOy() + 50);
            line2.setEndY(managers.get(0).getOy() + 50);
            line.getStrokeDashArray().addAll(25d, 10d);
            line2.getStrokeDashArray().addAll(25d, 10d);
            Label label2 = new Label();
            if (reason.equals("Temperature")){
                label2.setText("Temperature is going high: " + agent.getTemperature()+"%");
            }else if (reason.equals("CPU Usage")){
                label2.setText("CPU usage is going high: " + agent.getCPUUsage()+"%");
            }else if (reason.equals("Memory Usage")){
                label2.setText("Memory usage is going high: " + agent.getMemoryUsage()+"%");
            }
            label2.setTranslateX(line.getStartX()-70);
            label2.setTranslateY(line.getEndY());
            label.setTranslateY(line2.getEndY());
            label.setTranslateX(line2.getStartX()-70);
            drawingRoot.getChildren().add(label2);
            drawingRoot.getChildren().add(label);
            drawingRoot.getChildren().add(line);
            drawingRoot.getChildren().add(line2);
            Request request = new Request(line, line2, label, label2, agents.get(indexOfAgent));
            Requests.add(request);
        }else if (Requests.size()>0 && agents.get(indexOfAgent).getName().equals(Requests.get(Requests.size()-1).getAgent().getName())) {
            Line line = new Line();
            line.setStartX(Requests.get(Requests.size() - 1).getRequestLine().getStartX());
            line.setEndX(Requests.get(Requests.size() - 1).getRequestLine().getEndX());
            line.setStartY(Requests.get(Requests.size() - 1).getRequestLine().getStartY() + 100);
            line.setEndY(Requests.get(Requests.size() - 1).getRequestLine().getEndY() + 100);
            Label label = new Label("Seen.");

            Line line2 = new Line();
            line2.setStartX(Requests.get(Requests.size() - 1).getReturnLine().getStartX());
            line2.setEndX(Requests.get(Requests.size() - 1).getReturnLine().getEndX());
            line2.setStartY(Requests.get(Requests.size() - 1).getReturnLine().getStartY() + 100);
            line2.setEndY(Requests.get(Requests.size() - 1).getReturnLine().getEndY() + 100);
            line.getStrokeDashArray().addAll(25d, 10d);
            line2.getStrokeDashArray().addAll(25d, 10d);
            Label label2 = new Label();
            if (reason.equals("Temperature")){
                label2.setText("Temperature is going high: " + agent.getTemperature()+"%");
            }else if (reason.equals("CPU Usage")){
                label2.setText("CPU usage is going high: " + agent.getCPUUsage()+"%");
            }else if (reason.equals("Memory Usage")){
                label2.setText("Memory usage is going high: " + agent.getMemoryUsage()+"%");
            }
            label2.setTranslateX(Requests.get(Requests.size() - 1).getRequestLabel().getTranslateX());
            label2.setTranslateY(line.getEndY());
            label.setTranslateY(line2.getEndY());
            label.setTranslateX(Requests.get(Requests.size() - 1).getReturnLabel().getTranslateX());
            drawingRoot.getChildren().add(label2);
            drawingRoot.getChildren().add(label);
            drawingRoot.getChildren().add(line);
            drawingRoot.getChildren().add(line2);
            Request request = new Request(line, line2, label, label2, agents.get(indexOfAgent));
            Requests.add(request);
        }else {
            Line line = new Line();
            line.setStartX(managers.get(0).getOx());
            line.setEndX(agents.get(indexOfAgent).getOx());
            line.setStartY(Requests.get(Requests.size()-1).getRequestLine().getStartY()+100);
            line.setEndY(Requests.get(Requests.size()-1).getRequestLine().getEndY()+100);
            Label label = new Label("Seen.");
            //label.setTranslateY(line.getEndY());
            //label.setTranslateX(Requests.get(Requests.size()-1).getRequestLabel().getTranslateX());
            Line line2 = new Line();
            line2.setStartX(managers.get(0).getOx());
            line2.setEndX(agents.get(indexOfAgent).getOx());
            line2.setStartY(Requests.get(Requests.size()-1).getReturnLine().getStartY()+100);
            line2.setEndY(Requests.get(Requests.size()-1).getReturnLine().getEndY()+100);
            line.getStrokeDashArray().addAll(25d, 10d);
            line2.getStrokeDashArray().addAll(25d, 10d);
            Label label2 = new Label();
            if (reason.equals("Temperature")){
                label2.setText("Temperature is going high: " + agent.getTemperature()+"%");
            }else if (reason.equals("CPU Usage")){
                label2.setText("CPU usage is going high: " + agent.getCPUUsage()+"%");
            }else if (reason.equals("Memory Usage")){
                label2.setText("Memory usage is going high: " + agent.getMemoryUsage()+"%");
            }
            label2.setTranslateX(Requests.get(Requests.size() - 1).getRequestLabel().getTranslateX());
            label2.setTranslateY(line.getEndY());
            label.setTranslateY(line2.getEndY());
            label.setTranslateX(Requests.get(Requests.size() - 1).getReturnLabel().getTranslateX());
            drawingRoot.getChildren().add(label2);
            drawingRoot.getChildren().add(label);
            drawingRoot.getChildren().add(line);
            drawingRoot.getChildren().add(line2);
            Request request = new Request(line, line2, label, label2, agents.get(indexOfAgent));
            Requests.add(request);
        }
        for (int i = 0; i < agents.size(); i++) {
            drawingRoot.getChildren().remove(agents.get(i).getLine());
            agents.get(i).getLine().setEndY(agents.get(i).getLine().getEndY()+100);
            drawingRoot.getChildren().add(agents.get(i).getLine());
        }
        for (int i = 0; i < managers.size(); i++) {
            drawingRoot.getChildren().remove(managers.get(i).getLine());
            managers.get(i).getLine().setEndY(managers.get(i).getLine().getEndY()+100);
            drawingRoot.getChildren().add(managers.get(i).getLine());
        }
    }

    public void trap(Agent agent, Group drawingRoot, String reason, List<Agent> agents, List<Manager> managers){
        int indexOfAgent = 0;//Integer.parseInt(String.valueOf(agent.getName().charAt(agent.getName().length()-1)))-1;

        for (int i = 0; i < agents.size(); i++) {
            if (agent.hashCode()==agents.get(i).hashCode()){
                indexOfAgent = i;
                break;
            }
        }

        if (Requests.size()==0){
            Line line = new Line();
            line.setStartX(managers.get(0).getOx());
            line.setEndX(agents.get(indexOfAgent).getOx());
            line.setStartY(managers.get(0).getOy());
            line.setEndY(managers.get(0).getOy());
            line.setStroke(Color.RED);
            Label label = new Label("");

            Line line2 = new Line();
            line2.setStartX(managers.get(0).getOx());
            line2.setEndX(agents.get(indexOfAgent).getOx());
            line2.setStartY(managers.get(0).getOy() + 50);
            line2.setEndY(managers.get(0).getOy() + 50);
            line.getStrokeDashArray().addAll(25d, 10d);
            line2.getStrokeDashArray().addAll(25d, 10d);
            line2.setVisible(false);
            Label label2 = new Label();
            if (reason.equals("Temperature")){
                label2.setText("TRAP. Reason: Temperature - " + agents.get(indexOfAgent).getTemperature()+"%");
            }else if (reason.equals("CPU Usage")){
                label2.setText("TRAP. Reason: CPU Usage - " + agents.get(indexOfAgent).getCPUUsage()+"%");
            }else if (reason.equals("Memory Usage")){
                label2.setText("TRAP. Reason: Memory Usage - " + agents.get(indexOfAgent).getMemoryUsage()+"%");
            }
            label2.setTranslateX(line.getStartX()-70);
            label2.setTranslateY(line.getEndY());
            label.setTranslateY(line2.getEndY());
            label.setTranslateX(line2.getStartX()-70);
            drawingRoot.getChildren().add(label2);
            drawingRoot.getChildren().add(label);
            drawingRoot.getChildren().add(line);
            drawingRoot.getChildren().add(line2);
            Request request = new Request(line, line2, label, label2, agents.get(indexOfAgent));
            Requests.add(request);
        }else if (Requests.size()>0 && agents.get(indexOfAgent).getName().equals(Requests.get(Requests.size()-1).getAgent().getName())) {
            Line line = new Line();
            line.setStartX(Requests.get(Requests.size() - 1).getRequestLine().getStartX());
            line.setEndX(Requests.get(Requests.size() - 1).getRequestLine().getEndX());
            line.setStartY(Requests.get(Requests.size() - 1).getRequestLine().getStartY() + 100);
            line.setEndY(Requests.get(Requests.size() - 1).getRequestLine().getEndY() + 100);
            line.setStroke(Color.RED);
            Label label = new Label("");

            Line line2 = new Line();
            line2.setStartX(Requests.get(Requests.size() - 1).getReturnLine().getStartX());
            line2.setEndX(Requests.get(Requests.size() - 1).getReturnLine().getEndX());
            line2.setStartY(Requests.get(Requests.size() - 1).getReturnLine().getStartY() + 100);
            line2.setEndY(Requests.get(Requests.size() - 1).getReturnLine().getEndY() + 100);
            line.getStrokeDashArray().addAll(25d, 10d);
            line2.getStrokeDashArray().addAll(25d, 10d);
            line2.setVisible(false);
            Label label2 = new Label();
            if (reason.equals("Temperature")){
                label2.setText("TRAP. Reason: Temperature - " + agents.get(indexOfAgent).getTemperature()+"%");
            }else if (reason.equals("CPU Usage")){
                label2.setText("TRAP. Reason: CPU Usage - " + agents.get(indexOfAgent).getCPUUsage()+"%");
            }else if (reason.equals("Memory Usage")){
                label2.setText("TRAP. Reason: Memory Usage - " + agents.get(indexOfAgent).getMemoryUsage()+"%");
            }
            label2.setTranslateX(Requests.get(Requests.size() - 1).getRequestLabel().getTranslateX());
            label2.setTranslateY(line.getEndY());
            label.setTranslateY(line2.getEndY());
            label.setTranslateX(Requests.get(Requests.size() - 1).getReturnLabel().getTranslateX());
            drawingRoot.getChildren().add(label2);
            drawingRoot.getChildren().add(label);
            drawingRoot.getChildren().add(line);
            drawingRoot.getChildren().add(line2);
            Request request = new Request(line, line2, label, label2, agents.get(indexOfAgent));
            Requests.add(request);
        }else {
            Line line = new Line();
            line.setStartX(managers.get(0).getOx());
            line.setEndX(agents.get(indexOfAgent).getOx());
            line.setStartY(Requests.get(Requests.size()-1).getRequestLine().getStartY()+100);
            line.setEndY(Requests.get(Requests.size()-1).getRequestLine().getEndY()+100);
            line.setStroke(Color.RED);
            Label label = new Label("");
            //label.setTranslateY(line.getEndY());
            //label.setTranslateX(Requests.get(Requests.size()-1).getRequestLabel().getTranslateX());
            Line line2 = new Line();
            line2.setStartX(managers.get(0).getOx());
            line2.setEndX(agents.get(indexOfAgent).getOx());
            line2.setStartY(Requests.get(Requests.size()-1).getReturnLine().getStartY()+100);
            line2.setEndY(Requests.get(Requests.size()-1).getReturnLine().getEndY()+100);
            line.getStrokeDashArray().addAll(25d, 10d);
            line2.getStrokeDashArray().addAll(25d, 10d);
            line2.setVisible(false);
            Label label2 = new Label();
            if (reason.equals("Temperature")){
                label2.setText("TRAP. Reason: Temperature - " + agents.get(indexOfAgent).getTemperature()+"%");
            }else if (reason.equals("CPU Usage")){
                label2.setText("TRAP. Reason: CPU Usage - " + agents.get(indexOfAgent).getCPUUsage()+"%");
            }else if (reason.equals("Memory Usage")){
                label2.setText("TRAP. Reason: Memory Usage - " + agents.get(indexOfAgent).getMemoryUsage()+"%");
            }
            label2.setTranslateX(Requests.get(Requests.size() - 1).getRequestLabel().getTranslateX());
            label2.setTranslateY(line.getEndY());
            label.setTranslateY(line2.getEndY());
            label.setTranslateX(Requests.get(Requests.size() - 1).getReturnLabel().getTranslateX());
            drawingRoot.getChildren().add(label2);
            drawingRoot.getChildren().add(label);
            drawingRoot.getChildren().add(line);
            drawingRoot.getChildren().add(line2);
            Request request = new Request(line, line2, label, label2, agents.get(indexOfAgent));
            Requests.add(request);
        }
        for (int i = 0; i < agents.size(); i++) {
            drawingRoot.getChildren().remove(agents.get(i).getLine());
            agents.get(i).getLine().setEndY(agents.get(i).getLine().getEndY()+100);
            drawingRoot.getChildren().add(agents.get(i).getLine());
        }
        for (int i = 0; i < managers.size(); i++) {
            drawingRoot.getChildren().remove(managers.get(i).getLine());
            managers.get(i).getLine().setEndY(managers.get(i).getLine().getEndY()+100);
            drawingRoot.getChildren().add(managers.get(i).getLine());
        }
    }

    public void trapInformGenerator(List<Agent> agents, List<Manager> managers, Group drawingRoot){
        for (int i = 0; i < agents.size(); i++) {
            Random random = new Random();
            int temp = random.nextInt(100);
            int cpuUsage =  random.nextInt(100);
            int memoryUsage = random.nextInt(100);
            if (temp>=70 && temp<=85){
                agents.get(i).setTemperature(String.valueOf(temp));
                inform(agents.get(i), drawingRoot, "Temperature", agents, managers);
            }else if (temp>85){
                agents.get(i).setTemperature(String.valueOf(temp));
                trap(agents.get(i), drawingRoot, "Temperature", agents, managers);
            }
            if (cpuUsage>=70 && cpuUsage<=85){
                agents.get(i).setCPUUsage(String.valueOf(cpuUsage));
                inform(agents.get(i), drawingRoot, "CPU Usage", agents, managers);
            }else if (cpuUsage>85){
                agents.get(i).setCPUUsage(String.valueOf(cpuUsage));
                trap(agents.get(i), drawingRoot, "CPU Usage", agents, managers);
            }
            if (memoryUsage>=70 && memoryUsage<=85){
                agents.get(i).setMemoryUsage(String.valueOf(memoryUsage));
                inform(agents.get(i), drawingRoot, "Memory Usage", agents, managers);
            }else if (memoryUsage>85){
                agents.get(i).setMemoryUsage(String.valueOf(memoryUsage));
                trap(agents.get(i), drawingRoot, "Memory Usage", agents, managers);
            }
        }
    }
}
