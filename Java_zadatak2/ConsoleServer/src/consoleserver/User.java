/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package consoleserver;

import java.io.Serializable;

/**
 *
 * @author valazar
 */
public class User implements Serializable {
    
    private String username;
    private String password;
    private String role;
    private boolean loggedIn;

    
    public User() {}
    
    public User(String username, String password, String role) {
        this.username = username;
        this.password = password;
        this.role = role;
        this.loggedIn = false;
    }
    
    public String getUsername() {
        return username;
    }
    
    public String getPassword() {
        return password;
    }
    
    public String getRole() {
        return role;
    }
   
    public boolean getLoggedIn(){
        return loggedIn;
    }
    
    public void setLoggedIn(){
        this.loggedIn = true;
    }
    
    public void setNotLoggedIn(){
        this.loggedIn = false;
    }
    
    public void setUsername(String username) {
        // Validacija korisničkog imena
        if (username.matches("^[a-zA-Z][a-zA-Z0-9]*$")) {
            this.username = username;
        } else {
            System.out.println("Korisnicko ime nije validno.");
        }
    }
    

    public void setPassword(String password) {
        // Validacija lozinke
        if (password.matches("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{6,}$")) {
            this.password = password;
        } else {
            System.out.println("Lozinka nije validna.");
        }
    }
      
    public void setRole(String role) {
        // Validacija uloge
        if ("admin".equalsIgnoreCase(role) || "takmicar".equalsIgnoreCase(role)) {
            this.role = role.toLowerCase(); // Sacuvaj ulogu u malim slovima
        } else {
            System.out.println("Uloga nije validna. Dozvoljene uloge su 'admin' ili 'takmicar'.");
        }
    }
    
    @Override
    public String toString() {
        return this.getUsername() + ":" + this.getPassword()+ ":" + this.getRole();
    }
    
    
}