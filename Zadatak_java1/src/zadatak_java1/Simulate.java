/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */

package zadatak_java1;

import java.io.IOException;
import java.util.Scanner;

/**
 *
 * @author Vanja Lazarevic
 */

public class Simulate {
    
    public static void main(String[] args) throws IOException {
        // Instanciranje objekta klase Championship
        Championship championship = new Championship();
        championship.loadFiles();
        
        Scanner sc = new Scanner(System.in);
        int tourNum;
        
        // Unos broja turnira
        do {
            System.out.print("Unesite broj turnira (od 4 do 13): ");
            while (!sc.hasNextInt()) {
                System.out.println("Greska: Unesite broj u dozvoljenom opsegu.");
                System.out.print("Unesite broj turnira (od 4 do 13): ");
                sc.next(); // cisti input stream
            }
            tourNum = sc.nextInt();
            if (tourNum < 4 || tourNum > 13) {
                System.out.println("Greska: Unesite broj iz dozvoljenog opsega (od 4 do 13).");
            }
        } while (tourNum < 4 || tourNum > 13);
        
        System.out.println("Uneli ste broj turnira: " + tourNum);
        
        // Čišćenje input stream-a
        sc.nextLine(); 
        
        
        // Unos naziva turnira i provera
        for (int i = 0; i < tourNum; i++) {
            String tourName;
            boolean validInput;
            
            do {
                System.out.print("Unesite naziv turnira: ");
                tourName = sc.nextLine().trim(); // Uklanja prazne znakove sa početka i kraja unosa
                
                if (tourName.isEmpty()) {
                    System.out.println("Greska: Naziv turnira ne moze biti prazan.");
                    validInput = false;
                    continue;
                }
                
                validInput = championship.inputTournamentName(tourName);
                // Poziv metode za azuriranje ATP rangova igraca
                championship.updateAtpRanks();
                championship.recoverPlayers();
                
                if (!validInput) {
                    System.out.println("Greska: Turnir '" + tourName + "' nije moguce uneti.");
                }
            } while (!validInput);
            
        }
        
        System.out.println("Zapocinje AtpFinals turnir. Pritisnite taster za potvrdu.");
        sc.nextLine(); // Cekanje na bilo koju taster
        
        //atp finals turnir
        AtpFinals atpFinal = new AtpFinals();
        atpFinal.dividePlayersAndPrintGroups(championship);
        atpFinal.play();
        championship.updateAtpRanks();
        //ispis tenisera na no1 poziciji
        Player topPlayer = championship.getPlayers().get(0);
        System.out.println("Teniser koji je zavrsio godinu na prvom mjestu je: " + topPlayer.getName());
        
    }

}
