/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package zadatak_java1;

import java.util.ArrayList;

/**
 *
 * @author Vanja Lazarevic
 */
public abstract class Tournament {
    
    protected String tourName;
    protected String tourType;
    protected String tourSurface;
    protected boolean playable;
    protected int numOfSets;
    protected ArrayList<Player> contestants ;
    
    public Tournament() {}
    
    public Tournament(String tourName, String tourType, String tourSurface, int numOfSets) {
        this.tourName = tourName;
        this.tourType = tourType; 
        this.tourSurface = tourSurface;
        this.playable = true;  
        this.numOfSets = numOfSets;
        this.contestants = new ArrayList<>();
    }
        
    public String getTourName() {
        return tourName;
    }
    public void setTourName(String tourName) {
        this.tourName = tourName;
    }


    // Getteri i setteri za tourType
    public String getTourType() {
        return tourType;
    }
    public void setTourType(String tourType) {
        this.tourType = tourType;
    }

    // Getteri i setteri za tourSurface
    public String getTourSurface() {
        return tourSurface;
    }
    public void setTourSurface(String tourSurface) {
        this.tourSurface = tourSurface;
    }

    // Getteri i setteri za playable
    public boolean isPlayable() {
        return playable;
    }
    public void setPlayable(boolean playable) {
        this.playable = playable;
    }

    // Getteri i setteri za numOfSet
    public int getNumOfSets() {
        return numOfSets;
    }
    public void setNumOfSets(int numOfSets) {
        this.numOfSets = numOfSets;
    }

    // Getteri i setteri za contestants
    public ArrayList<Player> getContestants() {
        return contestants;
    }
    public void setContestants(ArrayList<Player> contestants) {
        this.contestants = contestants;
    }
    
    abstract public void play();
}
