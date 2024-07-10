/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package consoleserver;

/**
 *
 * @author valazar
 */

import java.util.ArrayList;
import java.util.List;


public class Administrator extends User {
    
    private ArrayList<User> users;
    private List<Administrator> administrators;
    private List<Contestant> contestants;
    private List<List<Question>> questionSets;
    
    
    public Administrator() {
        super();
        this.users = new ArrayList<>();
        this.administrators = new ArrayList<>();
        this.contestants = new ArrayList<>();
        this.questionSets = new ArrayList<>();
        
    }
    
    public Administrator(String  username, String  password) {
        super(username, password, "admin");
        this.users = new ArrayList<>();
        this.administrators = new ArrayList<>();
        this.contestants = new ArrayList<>();
        this.questionSets = new ArrayList<>();
    }
    

    // Prikazi sve korisnike
    public List<User> getUsers() {
        return new ArrayList<>(users);
    }

    // Dodaj takmičara
    public void addContestant(Contestant contestant) {
        contestants.add(contestant);
    }

    // Prikazi sve takmičare
    public List<Contestant> getContestants() {
        return new ArrayList<>(contestants);
    }

}
