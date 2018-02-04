package org.launchcode.dispatchtransfer.models;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class SocialWorker {

    @Id
    @GeneratedValue
    private int id;

    private String username;

    private String password;

    @OneToMany
    @JoinColumn(name = "socialworker_id")
    private List<Patient> patients = new ArrayList<>();

    public SocialWorker(){}

    public SocialWorker(String username, String password){
        this.username=username;
        this.password=password;
    }

    public int getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
