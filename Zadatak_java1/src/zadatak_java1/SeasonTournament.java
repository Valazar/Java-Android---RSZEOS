/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package zadatak_java1;

/**
 *
 * @author Vanja Lazarevic
 */

import java.util.ArrayList;
import java.util.Collections;

/**
 *
 * @author Vanja Lazarevic
 */

public class SeasonTournament extends Tournament {
    
    private ArrayList<Player> roundOf16;
    private ArrayList<Player> quaterFinalists;
    private ArrayList<Player> semiFinalists;
    private ArrayList<Player> finalists;
    
    public SeasonTournament() {
     
        super();
        roundOf16 = new ArrayList<>();
        quaterFinalists = new ArrayList<>();
        semiFinalists = new ArrayList<>();
        finalists = new ArrayList<>();
    }
    
    public SeasonTournament(String tourName, String tourType, String tourSurface, int numOfSets) {
        
        super(tourName, tourType, tourSurface, numOfSets);
        
        // Inicijalizacija dodatnih atributa
        roundOf16 = new ArrayList<>();
        quaterFinalists = new ArrayList<>();
        semiFinalists = new ArrayList<>();
        finalists = new ArrayList<>();
    }
      
    public ArrayList<Player> getRoundOf16() {
        return roundOf16;
    }  
    public void setRoundOf16(ArrayList<Player> roundOf16) {
        this.roundOf16 = roundOf16;
    }
    
    @Override
    public void play() {
        // Implementacija simulacije sezone turnira
        simulateRoundOf16();
        simulateQuarterFinals();
        simulateSemiFinals();
        simulateFinal();
    }
    
    
    private void simulateRoundOf16() {
        
        System.out.println("========" + " Round Of 16 " + "========");
        if (contestants.size() == 16) {
            roundOf16 = new ArrayList<>(contestants);
            Collections.shuffle(roundOf16);
            for (int i = 0; i < 16; i += 2) {
                Player player1 = roundOf16.get(i);
                Player player2 = roundOf16.get(i + 1);
                Match match = new Match(player1, player2, getNumOfSets(), getTourSurface());
                quaterFinalists.add(match.playMatch());
                match.printMatchResult();  
            }
            for (Player player : roundOf16) {
                if (!quaterFinalists.contains(player)) {
                    int atpPoints = player.getAtpPoints();
                    if(getTourType().equals("Grand Slam")){
                        player.setAtpPoints(180+atpPoints); 
                    }
                    else{
                        player.setAtpPoints(100+atpPoints);
                    }
                }
            }
        } else {
            System.out.println("Pogresan broj igrača za simulaciju osmine finala.");
        }
    }
    
    private void simulateQuarterFinals() {
        
        System.out.println("========" + " Quarter Finals " + "========");
        if (quaterFinalists.size() == 8) {
            Collections.shuffle(quaterFinalists);
            for (int i = 0; i < 8; i += 2) {
                Player player1 = quaterFinalists.get(i);
                Player player2 = quaterFinalists.get(i + 1);
                Match match = new Match(player1, player2, getNumOfSets(), getTourSurface());
                semiFinalists.add(match.playMatch());
                match.printMatchResult();
                
            }
            for (Player player : quaterFinalists) {
                if (!semiFinalists.contains(player)) {
                    int atpPoints = player.getAtpPoints();
                    if(getTourType().equals("Grand Slam")){
                    player.setAtpPoints(360+atpPoints); 
                    }
                    else{
                        player.setAtpPoints(200+atpPoints);
                    }
                }
            }
        } else {
            System.out.println("Pogresan broj igrača za simulaciju cetvrtine finala.");
        }
    }
    
    private void simulateSemiFinals() {
        
        System.out.println("========" + " Semi Finals " + "========");
        if (semiFinalists.size() == 4) {
            Collections.shuffle(semiFinalists);
            for (int i = 0; i < 4; i += 2) {
                Player player1 = semiFinalists.get(i);
                Player player2 = semiFinalists.get(i + 1);
                Match match = new Match(player1, player2, getNumOfSets(), getTourSurface());
                finalists.add(match.playMatch());
                match.printMatchResult();
                
            }
            for (Player player : semiFinalists) {
                if (!finalists.contains(player)) {
                    int atpPoints = player.getAtpPoints();
                    if(getTourType().equals("Grand Slam")){
                    player.setAtpPoints(720+atpPoints); 
                    }
                    else{
                        player.setAtpPoints(400+atpPoints);
                    }
                }
            }
        } else {
            System.out.println("Pogresan broj igrača za simulaciju polufinala finala.");
        }
    }
    
    private void simulateFinal() {
        
        System.out.println("========" + " Finals " + "========");
        if (finalists.size() == 2) {
            Player player1 = finalists.get(0);
            Player player2 = finalists.get(1);
            int atpPoints1 = player1.getAtpPoints();
            int atpPoints2 = player2.getAtpPoints();
            
            Match match = new Match(player1, player2, getNumOfSets(), getTourSurface());
            if(player1 == match.playMatch()){
                if(getTourType().equals("Grand Slam")){
                    player1.setAtpPoints(2000+atpPoints1);
                    player2.setAtpPoints(1200+atpPoints2);
                }
                else{
                    player1.setAtpPoints(1000+atpPoints1);
                    player2.setAtpPoints(650+atpPoints2);
                }  
            }
            else{
                if(getTourType().equals("Grand Slam")){
                    player2.setAtpPoints(2000+atpPoints2);
                    player1.setAtpPoints(1200+atpPoints1);
                }
                else{
                    player2.setAtpPoints(1000+atpPoints2);
                    player1.setAtpPoints(650+atpPoints1);
                } 
                
            }
            match.printMatchResult();

        } else {
            System.out.println("Pogresan broj igrača za simulaciju finala finala.");
        }
    }
}
