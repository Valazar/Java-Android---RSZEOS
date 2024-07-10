/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package zadatak_java1;
import java.util.Comparator;

/**
 *
 * @author Vanja Lazarevic
 */
public class Player implements Comparable<Player>{
    
    private String name;
    private String ability;
    private String preferedSurface;
    private int atpRank;
    private int atpPoints;
    private boolean injured;
  
   //konstruktori
   public Player() { }
   
    public Player(String name, String ability, String preferedSurface, int atpRank, int atpPoints) {
        this.name = name;
        this.ability = ability;
        this.preferedSurface = preferedSurface;
        this.atpRank = atpRank;
        this.atpPoints = atpPoints;
        this.injured = false;
    }
   

  public String getName(){
    return name;
  }
  public void setName(String newName) {
    this.name = newName;
  }
  
  public String getAbility(){
    return ability;
  }
  public void setAbility(String newAbility) {
    this.ability = newAbility;
  }
  
  public String getPreferedSurface(){
    return preferedSurface;
  }
  public void setPreferedSurface(String newPreferedSurface) {
    this.preferedSurface = newPreferedSurface;
  }
  
  public int getAtpRank(){
    return atpRank;
  }
  public void setAtpRank(int newAtpRank) {
    this.atpRank = newAtpRank;
  }
  
  public int getAtpPoints(){
    return atpPoints;
  }
  public void setAtpPoints(int newAtpPoints) {
    this.atpPoints = newAtpPoints;
  }
  
  public boolean getInjured(){
    return injured;
  }
  public void setInjured(boolean newInjured) {
    this.injured = newInjured;
  }
  
  public int servePointChance(Player opponent, String surface){
      
    int probability = 50; //vrednost na pocetku
    
    int rankDifference = this.getAtpRank() - opponent.getAtpRank();
    probability = probability - rankDifference;
    
    String opponentAbility = opponent.getAbility();
    String favSurface = this.getPreferedSurface();
    String playerAbility = this.getAbility();
    
    if(favSurface.equals(surface)){
        probability += 5;
    }
    //protivnik
    switch (opponentAbility) {
        case "backhand": 
            probability -= 8;
            break;
        case "forehand":
            //probability = probability;
            break;
        case "serve":
            probability += 5;
            break;
        case "mentality":
            probability -= 10;
            break;
        default:
            System.out.println("Selected ability is not in the list");
            break;
    } 
    //serviser
    switch (playerAbility) {
        case "backhand": 
            //probability = probability;
            break;
        case "forehand":
            probability += 10;
            break;
        case "serve":
            probability += 15;
            break;
        case "mentality":
            probability += 5;
            break;
        default:
            System.out.println("Selected ability is not in the list");
            break;
    }
        return probability;   
    }

    @Override
    public String toString(){
        return name + ", Rank: " + atpRank + ", Atp Poeni: " + atpPoints;
    }
  
    @Override
    public int compareTo(Player p1){
        return Integer.compare(this.atpPoints, p1.atpPoints); //colections.sort(nizIgraca) sortiranje gdje bude potrebno
    }
  
}