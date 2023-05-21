package com.iot.crabindoorfarming.model;

public class Controller {
    private String pH;
    private String temperature;
    private String condition;
    private String controlState;
    private String pumper1;
    private String pumper2;

    public Controller(){}

    public Controller(String pH, String temperature, String condition, String controlState, String pumper1, String pumper2) {
        this.pH = pH;
        this.temperature = temperature;
        this.condition = condition;
        this.controlState = controlState;
        this.pumper1 = pumper1;
        this.pumper2 = pumper2;
    }

    public String getPH() {
        return pH;
    }

    public void setPH(String pH) {
        this.pH = pH;
    }

    public String getTemperature() {
        return temperature;
    }

    public void setTemperature(String temperature) {
        this.temperature = temperature;
    }

    public String getCondition() {
        return condition;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }

    public String getControlState() {
        return controlState;
    }

    public void setControlState(String controlState) {
        this.controlState = controlState;
    }

    public String getPumper1() {
        return pumper1;
    }

    public void setPumper1(String pumper1) {
        this.pumper1 = pumper1;
    }

    public String getPumper2() {
        return pumper2;
    }

    public void setPumper2(String pumper2) {
        this.pumper2 = pumper2;
    }
}
