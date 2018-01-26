package org.launchcode.dispatchtransfer.models;

public class Patient {

    private String name;

    private String pickup;

    private String destination;

    private String time;

    public Patient() {}

    public Patient(String name, String pickup, String destination, String time) {
        this.name = name;
        this.pickup = pickup;
        this.destination = destination;
        this.time = time;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPickup() {
        return pickup;
    }

    public void setPickup(String pickup) {
        this.pickup = pickup;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
