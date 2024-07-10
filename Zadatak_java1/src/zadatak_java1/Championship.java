/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package zadatak_java1;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

/**
 *
 * @author Vanja Lazarevic
 */
public class Championship {
    
    private ArrayList<Player> players;
    private ArrayList<SeasonTournament> tournaments;
    
    public Championship() {
        
        players = new ArrayList<>();
        tournaments = new ArrayList<>();
    }
    
    public Championship(ArrayList<Player> players, ArrayList<SeasonTournament> tournaments) {
        this.players = players;
        this.tournaments = tournaments;
    }
    
    public ArrayList<Player> getPlayers() {
        return players;
    }
    public void setPlayers(ArrayList<Player> players) {
        this.players = players;
    }
    
    public ArrayList<SeasonTournament> getTournaments() {
        return tournaments;
    }
    public void setTournaments(ArrayList<SeasonTournament> tournaments) {
        this.tournaments = tournaments;
    }
    
    
    public void updateAtpRanks() {
        // Sortiraj igrace u opadajućem redosledu na osnovu osvojenih ATP poena
        Collections.sort(players, Collections.reverseOrder());

        // Ažuriraj ATP rangove igraca nakon sortiranja
        for (int i = 0; i < players.size(); i++) {
            players.get(i).setAtpRank(i + 1);
        }
    
        // Ispis rang liste igrača
        System.out.println("Rang lista igraca nakon azuriranja:");
        for (Player player : players) {
            System.out.println(player);
        }
    }
    
    public void recoverPlayers(){
         for (Player player : players) {
            if (player.getInjured()) {
                player.setInjured(false);
                System.out.println(player.getName() + " se oporavio od povrede.");
            }
        }
    }
    
    public void loadFiles(){
       
        File tournaments_file = new File("tournaments.txt");
        File players_file = new File("players.txt");
     
        if(tournaments_file.exists() && players_file.exists()){
        try (BufferedReader br = new BufferedReader(new FileReader("players.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] tokens = line.split(",");
                if (tokens.length != 5) {
                    System.out.println("Greska pri citanju players reda : " + line);
                    continue; // Preskoci ovaj red i nastavi s čitanjem sljedeceg
                }
                int playerAtpRank = Integer.parseInt(tokens[0]);
                String playerName = tokens[1];
                String playerAbility = tokens[2];
                String playerSurface = tokens[3];
                int playerAtpPoints = Integer.parseInt(tokens[4]);
                
                Player player = new Player(playerName, playerAbility, playerSurface, playerAtpRank, playerAtpPoints);
                players.add(player);
            }
            System.out.println("Podaci uspjesno ucitani iz datoteke players.");
        } catch (IOException e) {
            System.out.println("Greska pri citanju datoteke players: " + e.getMessage());
        } catch (NumberFormatException e) {
            System.out.println("Greska pri konverziji podataka players: " + e.getMessage());
        }
        
                
        try (BufferedReader br = new BufferedReader(new FileReader("tournaments.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] tokens = line.split(",");
                if (tokens.length != 3) {
                    System.out.println("Greska pri citanju tournaments reda: " + line);
                    continue; // Preskoci ovaj red i nastavi s čitanjem sljedeceg
                }
                String tourName = tokens[0];
                String tourSurface = tokens[1];
                String tourType = tokens[2];
                int numOfSets;
                if(tourType.equals("Grand Slam")){
                    numOfSets = 3; 
                }
                else{
                    numOfSets = 2;
                }
                SeasonTournament SeasonTournament = new SeasonTournament(tourName, tourType, tourSurface, numOfSets);
                SeasonTournament.setContestants(players);
                tournaments.add(SeasonTournament);
            }
            System.out.println("Podaci uspjesno ucitani iz datoteke tournaments.");
        } catch (IOException e) {
            System.out.println("Greska pri citanju datoteke tournaments: " + e.getMessage());
        } catch (NumberFormatException e) {
            System.out.println("Greska pri konverziji podataka tournaments: " + e.getMessage());
        }
      }
    }  
     
     //dodatna metoda za unos turnira
    public boolean inputTournamentName(String tournamentName) {
        boolean found = false;

        // Provera da li se turnir nalazi na listi mogucih turnira i da li je playable
        for (SeasonTournament tournament : tournaments) {
            if (tournament.getTourName().equalsIgnoreCase(tournamentName)) {
                if (!tournament.isPlayable()) {
                    System.out.println("Greska: Turnir '" + tournamentName + "' je vec odigran.");
                    return false;
                }
                found = true;
                tournament.setPlayable(false); // Oznacavanje da turnir nije vise playable
                System.out.println("Turnir '" + tournamentName + "' uspesno unet za igranje.");
                tournament.play();
                break;
            }
        }

        if (!found) {
            System.out.println("Greska: Turnir '" + tournamentName + "' se ne nalazi na listi mogucih turnira.");
            return false;
        }

        return true;
    }
}


    



