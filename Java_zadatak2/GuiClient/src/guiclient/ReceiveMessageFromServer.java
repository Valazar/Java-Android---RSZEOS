/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package guiclient;

/**
 *
 * @author Admin
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;


public class ReceiveMessageFromServer implements Runnable {

    GuiClient parent;
    BufferedReader br;
    String message;
    
    public ReceiveMessageFromServer(GuiClient parent) {
        //parent ce nam trebati da bismo mogli iz ovog thread-a da menjamo sadrzaj 
        //komponenti u osnovnom GUI prozoru (npr da popunjavamo Combo Box sa listom
        //korisnika
        this.parent = parent;
        //BufferedReader koristimo za prijem poruka od servera, posto su sve
        //poruke u formi Stringa i linija teksta, BufferedReader je zgodniji nego
        //da citamo poruke iz InputStream objekta
        this.br = parent.getBr();
        this.message=parent.getMessage();
    }
    
    public void firstFields(){
        
        this.parent.getBtnPitanja().setEnabled(true);
        this.parent.getTfPitanja().setEnabled(true);
        this.parent.getTfPoruka().setEnabled(true);
        this.parent.getBtnPoruka().setEnabled(true); 
        this.parent.getBtnTabela().setEnabled(true); 
        this.parent.getTa().setEnabled(true); 
        this.parent.getTfIme().setEnabled(true);
        this.parent.getBtnLogin().setEnabled(false);
        this.parent.getBtnLogout().setEnabled(true);
        this.parent.getBtnUcitaj().setEnabled(true);

        
    }
    
      
    public void secondFields(){
          
        this.parent.getTfImeKreiranje().setEnabled(true);
        this.parent.getTfSifraKreiranje().setEnabled(true); 
        this.parent.getTfUlogaKreiranje().setEnabled(true); 
        this.parent.getBtnDodaj().setEnabled(true); 
        this.parent.getBtnUkloni().setEnabled(true); 
        this.parent.getTfUkloni().setEnabled(true); 
        this.parent.getBtnSetPitanja().setEnabled(true);
        this.parent.getTfSetPitanja().setEnabled(true);
        this.parent.getBtnLogin().setEnabled(false);
        this.parent.getBtnLogout().setEnabled(true);
    }
    
    @Override
    public void run() {
        
        while (true) {
            String line;
            try {
                line = this.br.readLine();
                String[] parseMessage = line.split(":");
                
                //provjere za aktiviranje guia
                if(parseMessage[0].equals("Takmicar")){
                    this.firstFields();
                    this.parent.getTfIme().setText(parseMessage[1]);
                }
                else if(parseMessage[0].equals("Admin")){
                    secondFields();
                    
                }
                else if (parseMessage[0].equals("Tabela")) {

                    StringBuilder sb = new StringBuilder("Tabela:\n");

                    for (int i = 1; i < parseMessage.length; i++) { // pocinjemo od 1 jer parseMessage[0] sadrži "Tabela"
                        sb.append(parseMessage[i]);
                        if (i < parseMessage.length - 1) {
                            sb.append(System.lineSeparator());
                        }
                    }

                    this.parent.getTa().setText(sb.toString());
                }
                else if (line.contains("pomoc")){
                    this.parent.getTa().setText(parseMessage[0]);
                    
                }
                else if (line.contains("set")){
                    this.parent.getTa().setText(line);
                }
                else if (parseMessage[0].equals("Pitanje")){
                    StringBuilder sb = new StringBuilder("Pitanje:\n");
                    for (int i = 1; i < parseMessage.length; i++) { 
                        sb.append(parseMessage[i]);
                        if (i < parseMessage.length - 1) {
                            sb.append(System.lineSeparator());
                        }
                    }
                    this.parent.getTa().setText(sb.toString());
                }
                else if (parseMessage[0].equals("[Pitanje")){
                    StringBuilder sb = new StringBuilder("Pitanje:\n");
                    for (int i = 1; i < parseMessage.length-1; i++) { 
                        sb.append(parseMessage[i]);
                        if (i < parseMessage.length - 1) {
                            sb.append(System.lineSeparator());
                        }
                    }
                    this.parent.getTa().setText(sb.toString());
                }
                else if(parseMessage[0].equals("Pomozi prijatelju")){
                    StringBuilder sb = new StringBuilder("Pomozi prijatelju:");
                    for (int i = 1; i < parseMessage.length-1; i++) { 
                        sb.append(parseMessage[i]);
                        if (i < parseMessage.length - 1) {
                            sb.append(System.lineSeparator());
                        }
                    }
                    this.parent.getTa().setText(sb.toString());
                }
                else if(parseMessage[0].equals("Pomoc prijatelja")){
                    StringBuilder sb = new StringBuilder("Pomoc prijatelja:");
                    for (int i = 1; i < parseMessage.length; i++) { 
                        sb.append(parseMessage[i]);
                        if (i < parseMessage.length - 1) {
                            sb.append(System.lineSeparator());
                        }
                    }
                    this.parent.getTa().setText(sb.toString());
                }
                else{
                    System.out.println("Primljena nepoznata poruka");
                }

            }catch (IOException ex) {
                Logger.getLogger(ReceiveMessageFromServer.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
}

