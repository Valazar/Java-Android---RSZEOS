/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package consoleserver;


import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.crypto.SecretKey;


/**
 *
 * @author valazar
 */
public class ConsoleServer {

    private ServerSocket ssocket;
    private int port;
    private ArrayList<ConnectedUser> clients;
    

    public ServerSocket getSsocket() {
        return ssocket;
    }

    public void setSsocket(ServerSocket ssocket) {
        this.ssocket = ssocket;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }
    
    public void acceptClients() {
        Socket client = null;
        Thread thr;
        while (true) {
            try {
                
                System.out.println("Waiting for new clients..");
                client = this.ssocket.accept();
            } catch (IOException ex) {
                Logger.getLogger(ConsoleServer.class.getName()).log(Level.SEVERE, null, ex);
            }
            if (client != null) {
                //Povezao se novi klijent, kreiraj objekat klase ConnectedChatRoomClient
                //koji ce biti zaduzen za komunikaciju sa njim
                ConnectedUser clnt = new ConnectedUser(client, clients);
                //i dodaj ga na listu povezanih klijenata jer ce ti trebati kasnije
                clients.add(clnt);
                //kreiraj novu nit (konstruktoru prosledi klasu koja implementira Runnable interfejs)
                thr = new Thread(clnt);
                //..i startuj ga
                thr.start();
            } else {
                break;
            }
        }
    }
        
    public ConsoleServer(int port) {
        this.clients = new ArrayList<>();
        try {
            this.port = port;
            this.ssocket = new ServerSocket(port);
        } catch (IOException ex) {
            Logger.getLogger(ConsoleServer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
   
    public static void main(String[] args) {
        
        ConsoleServer server = new ConsoleServer(6001);

        System.out.println("Server pokrenut, slusam na portu 6001");

        //Prihvataj klijente u beskonacnoj petlji
        server.acceptClients();
        
    }
}
//        try {
//
//            // Kreiranje AES ključa i inicijalizacionog vektora
//            SecretKey key = CryptoSeril.createAESKey();
//            byte[] iv = CryptoSeril.createInitializationVector();
//            
//            ArrayList<User> users = new ArrayList<>();
//            
//            Administrator admin = new Administrator();
//            admin.setUsername("mitar");
//            admin.setPassword("Admin14$");
//            admin.setRole("admin");
//            Contestant admin1 = new Contestant();
//            admin1.setUsername("vanja");
//            admin1.setPassword("Admin14$");
//            admin1.setRole("takmicar");
//            users.add(admin);
//            users.add(admin1);
//            // Enkripcija i serijalizacija objekta Administrator
//            CryptoSeril.encryptUsersToFile(users, "users.ser");
//            ArrayList<User> decryptedUsers = CryptoSeril.decryptAllUsersFromFile("users.ser");
//            
//            for (User user : decryptedUsers) {
//                System.out.println(user.toString());
//            }
//            
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//     }
//
//}
