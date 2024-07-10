/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package zadatak_java1;

import java.util.ArrayList;
import java.util.Collections;
/**
 *
 * @author Vanja Lazarevic
 */
public class AtpFinals extends Tournament {
    
    private ArrayList<Player> groupA;
    private ArrayList<Player> groupB;
    private ArrayList<Player> semiFinalists;
    private ArrayList<Player> finalists;

    public AtpFinals() {
     
        super();
        groupA = new ArrayList<>();
        groupB = new ArrayList<>();
        semiFinalists = new ArrayList<>();
        finalists = new ArrayList<>();
    }
    
    public AtpFinals(String tourName, String tourType, String tourSurface, int numOfSets) {
        
        super(tourName, tourType, tourSurface, numOfSets);
        
        // Inicijalizacija dodatnih atributa
        groupA = new ArrayList<>();
        groupB = new ArrayList<>();
        semiFinalists = new ArrayList<>();
        finalists = new ArrayList<>();
    }
    
    
    @Override
    public void play() {
        
        groupA();
        groupB();
        semiFinals();
        finals();
        
    }
    
    public void groupA() {
    
        for (int i = 0; i < groupA.size(); i++) {
            for (int j = i + 1; j < groupA.size(); j++) {
                Player player1 = groupA.get(i);
                Player player2 = groupA.get(j);
                Match match = new Match(player1, player2, 2, "hard");
                Player winner = match.playMatch(); 
            
                if (winner != null) {
                    int atpPoints = winner.getAtpPoints();
                    winner.setAtpPoints(200 + atpPoints); 
                }
            }
        }
        Collections.sort(groupA, Collections.reverseOrder());
        semiFinalists.add(groupA.get(0)); // Prvi igrac 
        semiFinalists.add(groupA.get(1)); // Drugi igrac
    }
    
    public void groupB(){
        
        for (int i = 0; i < groupB.size(); i++) {
            for (int j = i + 1; j < groupB.size(); j++) {
                Player player1 = groupB.get(i);
                Player player2 = groupB.get(j);
                Match match = new Match(player1, player2, 2, "hard");
                Player winner = match.playMatch(); 
            
                if (winner != null) {
                    int atpPoints = winner.getAtpPoints();
                    winner.setAtpPoints(200 + atpPoints); 
                }
            }
        }
        Collections.sort(groupB, Collections.reverseOrder());
        semiFinalists.add(groupB.get(0)); // Prvi igrac 
        semiFinalists.add(groupB.get(1)); // Drugi igrac
        
    }
    
    public void semiFinals() {
    // Simulacija meceva polufinala
        System.out.println("Polufinalisti:");
        for (Player player : semiFinalists) {
            System.out.println(player);
        }
        System.out.println();
        for (int i = 0; i < semiFinalists.size(); i += 2) {
            Player player1 = semiFinalists.get(i);
            Player player2 = semiFinalists.get(i + 1);
        
            // Simulacija meča između igrača
            Match match = new Match(player1, player2, 3, "hard"); 
            Player winner = match.playMatch(); 
        
            // Dodavanje bodova pobjedniku
            if (winner != null) {
                int atpPoints = winner.getAtpPoints();
                winner.setAtpPoints(400 + atpPoints); 
            }
            finalists.add(winner);
        }   
    }
    
    public void finals() {
        System.out.println("Finalisti:");
        for (Player player : finalists) {
            System.out.println(player);
        }
        System.out.println();
        Player player1 = finalists.get(0);
        Player player2 = finalists.get(1);

    
        Match match = new Match(player1, player2, 2, "hard"); 
        Player winner = match.playMatch(); 

        if (winner != null) {
            int atpPoints = winner.getAtpPoints();
            winner.setAtpPoints(500 + atpPoints); 
        }
        System.out.println("Pobjednik atpFinals turnira:");
        System.out.println(winner);
        System.out.println();
    }
    
    public void dividePlayersAndPrintGroups(Championship championship) {
        ArrayList<Player> players = championship.getPlayers();

        for (int i = 0; i < players.size()/2; i++) {
            if ((i + 1) % 2 == 0) {
                groupB.add(players.get(i));
            } else {
                groupA.add(players.get(i));
            }
        }
        
        System.out.println("Grupa A:");
        for (Player player : groupA) {
            System.out.println(player);
        }

        System.out.println("Grupa B:");
        for (Player player : groupB) {
            System.out.println(player);
        }
        System.out.println();
    }
    
}
