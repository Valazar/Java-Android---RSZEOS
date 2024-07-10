/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package zadatak_java1;

import java.util.Random;

/**
 *
 * @author Vanja Lazarevic
 */
public class Match {
    
    private Player p1; 
    private Player p2; 
    private int winSetNum;
    private int p1Sets, p1Gems, p2Sets, p2Gems;
    private int p1ScorePerSet[], p2ScorePerSet[];
    private String matchSurface; //dodati atribut
    private Random rng;
    
    public Match() { }
     
    public Match(Player player1, Player player2, int winSetNum, String matchSurface) {
        this.p1 = player1;
        this.p2 = player2;
        this.winSetNum = winSetNum;
        this.p1Sets = 0;
        this.p1Gems = 0;
        this.p2Sets = 0;
        this.p2Gems = 0;
        this.p1ScorePerSet = new int[winSetNum == 3 ? 5 : 3]; 
        this.p2ScorePerSet = new int[winSetNum == 3 ? 5 : 3]; 
        this.matchSurface = matchSurface;
        this.rng = new Random();
        
    }
    
    public Player playMatch() {
        Random randomNumb = new Random();
        double injuryProbability = 0.01; // Verovatnoca povrede: 1%
        
        if (randomNumb.nextDouble() < injuryProbability) {
            Player injuredPlayer = randomNumb.nextBoolean() ? p1 : p2;
            System.out.println("Doslo je do povrede! Teniser " + injuredPlayer.getName() + " je povredjen.");
            injuredPlayer.setInjured(true); 
            Player healthyPlayer = injuredPlayer == p1 ? p2 : p1;
            if (healthyPlayer == p1) {
                p1Sets = winSetNum;
            } else {
                p2Sets = winSetNum;
            }
            
            return healthyPlayer;
        }

        playSet();
        
        //vraca pobjednika meca
        return p1Sets == winSetNum ? p1 : p2;    
        
    }
    
    private void playSet() {
    
        int setsPlayed = 0;
        
        while (p1Sets != winSetNum && p2Sets != winSetNum){
            if ((p1Gems >= 6 && p1Gems - p2Gems >= 2) || (p2Gems >= 6 && p2Gems - p1Gems >= 2)) {
                if (p1Gems > p2Gems) {
                    p1Sets++;
                    p1ScorePerSet[setsPlayed] = p1Gems;
                    p2ScorePerSet[setsPlayed] = p2Gems;
                    setsPlayed+=1;
                } else {
                    p2Sets++;
                    p1ScorePerSet[setsPlayed] = p1Gems;
                    p2ScorePerSet[setsPlayed] = p2Gems;
                    setsPlayed+=1;
                }
                p1Gems = 0;
                p2Gems = 0;
            }
            else if(p1Gems == 6 && p2Gems == 6){
                playTieBreak();
                p1ScorePerSet[setsPlayed] = p1Gems;
                p2ScorePerSet[setsPlayed] = p2Gems;
                setsPlayed+=1;
                //restart gemova za sledeci set
                p1Gems = 0;
                p2Gems = 0;
            } else {
                playGame();
             
            }
        }
    }
    
    private void playGame() {
    
        int points1 = 0;
        int points2 = 0;
        Player servingPlayer = p1; // Počinje p1 kao serviser

        boolean gemWon = false;
        while (!gemWon) {
            //Odredi sanse za serviranje za trenutnog servisera
            int chance = servingPlayer.servePointChance(servingPlayer == p1 ? p2 : p1, matchSurface);

            // Simuliraj osvajanje poena
            if (chanceEvent(chance)) {
                points1++;
            } else {
                points2++;
            }

            // Proveri da li je osvojen gem 15,30,40 i gem winer zbog toga je points==4
            if ((points1 >= 4 && points1 - points2 >= 2) || (points2 >= 4 && points2 - points1 >= 2)) {
                if (points1 > points2) {
                    p1Gems++;
                } else {
                    p2Gems++;
                }
                servingPlayer = (servingPlayer == p1) ? p2 : p1;
                points1 = 0;
                points2 = 0;
                gemWon = true;
            }
        }
    }
    
    private void playTieBreak() {
    
        Player servingPlayer = p1; // Pocinje p1 kao serviser
        int points1 = 0;
        int points2 = 0;
        boolean tieBreakWon = false;

        while (!tieBreakWon) {
            // Odredi šanse za serviranje za trenutnog servisera
            int chance = servingPlayer.servePointChance(servingPlayer == p1 ? p2 : p1, matchSurface);

            // Simuliraj osvajanje poena gdje isti igrac servira dva puta za redom nakon cega sledi rotacija
            for (int i = 0; i < 2; i++) {
                // Simuliraj osvajanje poena prvog igrača
                if (chanceEvent(chance)) {
                    points1++;
                } else {
                    points2++;
                }
            }
            
            // Proveri da li je osvojen tie-break
            if ((points1 >= 7 && points1 - points2 >= 2) || (points2 >= 7 && points2 - points1 >= 2)) {
                // Pobednik tie-breaka osvaja set
                if (points1 > points2) {
                    p1Gems++;
                    p1Sets++;
                } else {
                    p2Gems++;
                    p2Sets++;
                }
                tieBreakWon = true;
            }

            // Promeni servisera za sledeci poen
            servingPlayer = (servingPlayer == p1) ? p2 : p1;
        }
    }
    
    private boolean chanceEvent(int probability) {
        int randomNum = rng.nextInt(100) + 1; 
        return randomNum <= probability;
    }
    
    public void printMatchResult() {
    // Ispis rezultata setova i broja osvojenih setova za svakog igrača
        System.out.print(p1.getName() + " ");
        for (int i = 0; i < p1Sets + p2Sets; i++) {
            System.out.print(p1ScorePerSet[i] + " ");
        }
        System.out.println( " " + p1Sets);

        System.out.print(p2.getName() + " ");
        for (int i = 0; i < p1Sets + p2Sets; i++) {
            System.out.print(p2ScorePerSet[i] + " ");
        }
        System.out.println(" " + p2Sets);
        System.out.println();
    }
    
}
