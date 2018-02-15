package org.launchcode.dispatchtransfer.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

@Entity
public class Patient {

    @Id
    @GeneratedValue
    private int id;

    @NotNull
    private String name;

    @NotNull
    private String pickup;

    @NotNull
    private Integer room;

    @NotNull
    private String destination;

    @NotNull
    private String time;

    @ManyToOne
    private SocialWorker socialworker;

    public Patient() {}

    public Patient(String name, String pickup, Integer room, String destination, String time) {
        this.name = name;
        this.pickup = pickup;
        this.room = room;
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

    public int getId() {
        return id;
    }

    public Integer getRoom() {
        return room;
    }

    public void setRoom(Integer room) {
        this.room = room;
    }


}
