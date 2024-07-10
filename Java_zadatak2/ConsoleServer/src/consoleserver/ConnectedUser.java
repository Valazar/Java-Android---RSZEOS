/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package consoleserver;

/**
 *
 * @author valazar
 */



import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.crypto.SecretKey;


public class ConnectedUser implements Runnable {
    
    //atributi koji se koriste za komunikaciju sa klijentom
    private Socket socket;
    private String UserName;
    
    private BufferedReader br;
    private PrintWriter pw;
    
    private String loginData;
    private ArrayList<ConnectedUser> allClients;
    private static ArrayList<Contestant> allContestants;
    private static ArrayList<Administrator> allAdmins;
    private List<Question> regularQuestionSet;
    private List<Question> bonusQuestionSet;
    private Question selectedQuestion;
    public  ArrayList <User> additionalAllUsers;
    private Integer curentState = 0;

    
    private String message;
    
    
    
    public String getMessage() {    
        return message;
    }

    //getters and setters
    public void setMessage(String message) {
        this.message = message;
    }
    
    public void setAllStudents(ArrayList<Contestant> allContestants) {
        ConnectedUser.allContestants = allContestants;
    }

    public ArrayList<Contestant> getAllContestants() {
        return allContestants;
    }
    
    public void setAllAdmins(ArrayList<Administrator> allAdmins) {
        ConnectedUser.allAdmins = allAdmins;
    }

    public ArrayList<Administrator> getAllAdmins() {
        return allAdmins;
    }

    public String getUserName() {
        return UserName;
    }

    public Integer getCurrentState() {
        return curentState;
    }

    public void setCurentState(Integer curentState) {
        this.curentState = curentState;
    }
    

    public void setUserName(String UserName) {
        this.UserName = UserName;
    }


    public String getLoginData() {
        return loginData;
    }


    public void setLoginData(String loginData) {
        this.loginData = loginData;
    }
    
    public List<Question> getRQuestionSet() {
        return regularQuestionSet;
    }


    public void setRQuestionSet(List<Question> regularQuestionSet) {
        this.regularQuestionSet = regularQuestionSet;
    }
    
    public List<Question> getBQuestionSet() {
        return bonusQuestionSet;
    }


    public void setBQuestionSet(List<Question> bonusQuestionSet) {
        this.bonusQuestionSet = bonusQuestionSet;
    }

    public ConnectedUser(ArrayList <User> additionalAllUsers)
    {
            this.additionalAllUsers = additionalAllUsers;
    }
    
    //Konstruktor klase, prima kao argument socket kao vezu sa uspostavljenim klijentom
    public ConnectedUser(Socket socket, ArrayList<ConnectedUser> allClients) {
        this.socket = socket;
        this.allClients = allClients;
        this.allContestants = new ArrayList<Contestant>();
        this.allAdmins = new ArrayList<Administrator>();

        //iz socket-a preuzmi InputStream i OutputStream
        try {
            //posto se salje tekst, napravi BufferedReader i PrintWriter
            //kojim ce se lakse primati/slati poruke (bolje nego da koristimo Input/Output stream
            this.br = new BufferedReader(new InputStreamReader(this.socket.getInputStream(), "UTF-8"));
            this.pw = new PrintWriter(new OutputStreamWriter(this.socket.getOutputStream()), true);
            
            //zasad ne znamo user name povezanog klijenta

            this.loginData = "";

        } catch (IOException ex) {
            Logger.getLogger(ConnectedUser.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    
    @Override
    public void run() {
        
        while(true){
            try {
                switch (this.getCurrentState()) {
                    case 0:
                        System.out.println("State: -1 deserijalizacija i dekripcija");

                        try{
                            SecretKey key = CryptoSeril.createAESKey();
                            byte[] iv = CryptoSeril.createInitializationVector();

                            ArrayList<User> decryptedUsers = CryptoSeril.decryptAllUsersFromFile("users.ser");
                            for (User user : decryptedUsers) {
                                if (user.getRole().equals("admin")) {
                                    if (user instanceof Administrator) {
                                        allAdmins.add((Administrator) user);
                                    } else {
                                        System.out.println("Neispravno unjet korisnik");
                                    }
                                } else if (user.getRole().equals("takmicar")) {
                                    if (user instanceof Contestant) {
                                        allContestants.add((Contestant) user);
                                    } else {
                                        System.out.println("Neispravno unjet korisnik");
                                    }
                                } else {
                                    System.out.println("Neispravna uloga korisnika: " + user.getRole());
                                }
                                System.out.println(user.toString());
                            }

                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        this.setCurentState(1);
                        break;

                    case 1:
                        System.out.println("State 1.");
                        try {
                            this.loginData = this.br.readLine();
                        } catch (IOException ex) {
                            Logger.getLogger(ConnectedUser.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        System.out.println(loginData);
                        String[] whoIsLogging = loginData.split(":");

                        // Prođi kroz listu allContestants
                        for (Contestant contestant : allContestants) {
                            if(contestant.toString().equals(loginData) && !contestant.getLoggedIn()){
                                this.setCurentState(3);
                                this.pw.println("Takmicar:" + whoIsLogging[0]);
                                contestant.setLoggedIn();
                            }
                        }
                        // Prođi kroz listu allAdmins
                        for (Administrator admin : allAdmins) {
                            if(admin.toString().equals(loginData) && !admin.getLoggedIn()){
                                this.setCurentState(2);
                                this.pw.println("Admin:" + whoIsLogging[0]);
                                admin.setLoggedIn();
                            }
                        }
                        if (this.getCurrentState()== 1) {
                            System.out.println("Login failed, try again.");
                            this.setCurentState(1);
                        } 
                        else {
                            this.UserName = whoIsLogging[0]; //postavljanje imena povezanog korisnika
                            System.out.println("Login successful.");
                        }
                        
                        break;
                    case 2:
                        System.out.println("Admin just logged in, state 2.");
                        this.message = this.br.readLine();
                        String[] sentData = message.split(":");
                        if(sentData[0].equals("Dodavanje")){
                             if(sentData[3].equals("admin")){
                                 Administrator newAdmin = new Administrator(sentData[1], sentData[2]);
                                 allAdmins.add(newAdmin);
                                 System.out.println("Dodat admin " + newAdmin.getUsername());
                             }
                             else{
                                 Contestant newContestant = new Contestant(sentData[1], sentData[2]);
                                 allContestants.add(newContestant);
                                 System.out.println("Dodat takmicar " + newContestant.getUsername());

                             }
                        }
                        else if(sentData[0].equals("Uklanjanje")){
                            boolean removed = false;
                            // Uklanjanje iz allContestants
                            Iterator<Contestant> contestantIterator = allContestants.iterator();
                            while (contestantIterator.hasNext()) {
                                Contestant contestant = contestantIterator.next();
                                if (contestant.getUsername().equals(sentData[1])) {
                                    contestantIterator.remove();  // Koristite iterator za uklanjanje
                                    removed = true;
                                }
                            }

                            // Uklanjanje iz allAdmins
                            Iterator<Administrator> adminIterator = allAdmins.iterator();
                            while (adminIterator.hasNext()) {
                                Administrator admin = adminIterator.next();
                                if (admin.getUsername().equals(sentData[1]) && !(admin.toString().equals(loginData))) {
                                    adminIterator.remove();  // Koristite iterator za uklanjanje
                                    removed = true;
                                }
                            }

                            if(removed){
                                System.out.println("Korisnik: " + sentData[1] + " uklonjen" );
                            }
                            else{
                                System.out.println("Nepostojeci korisnik" );
                            }

                        }
                        else if(sentData[0].equals("Pitanja")){
                            String chosenSet = sentData[1];
                            for (ConnectedUser clnt : allClients) {
                            String clientName = clnt.getUserName(); // Pretpostavljajući da postoji metoda getUserName() koja vraća korisničko ime
                                // Prođi kroz listu allContestants
                                for (Contestant contestant : allContestants) {
                                    String contestantName = contestant.getUsername(); 
                                    if (clientName != null && clientName.equals(contestantName)) {
                                        System.out.println(chosenSet);
                                        clnt.pw.println(chosenSet);
                                    }
                                }
                            }
                            
                        }
                        else if(sentData[1].equals("Logout")){
                            for (Administrator admin : allAdmins) {
                                if(admin.toString().equals(loginData)){
                                    admin.setNotLoggedIn();
                                }
                            }
                        }
                        else{
                           System.out.println("Nepostojeci unos" ); 
                        }
                        additionalAllUsers = new ArrayList<>();
                        
                        // Dodaj sve takmičare i admine u additionalAllUsers i upisi ih u ser fajl
                        additionalAllUsers.addAll(allContestants);
                        additionalAllUsers.addAll(allAdmins);
                        try{
                        CryptoSeril.encryptUsersToFile(additionalAllUsers, "users.ser");
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        System.out.println(additionalAllUsers);
                        break;
                        
                    case 3:
                        System.out.println("Contestant just logged in, state 3.");
                        
                        String adminName = null;
                        //ko je admin kome se korisnik kasnije obraca
                        for (Administrator admin : allAdmins) {
                            adminName = admin.getUsername();
                            if (adminName != null) {
                                break; 
                            }
                        }
                        
                        this.message = this.br.readLine();
                        System.out.println(message); 
                        String[] contestantClient = message.split(":");
                        
                        if(contestantClient[0].equals("Pitanja")){
                            for (Contestant contestant : allContestants) {
                                if(contestant.toString().equals(loginData)){
                                    contestant.clearAnsweredQuestions(); //isprazni sadrzaj liste vec odgovorenih pitanja
                                    contestant.loadQuestionSets(contestantClient[1]); //ucitaj txt sa pitanjima
                                    contestant.setBonusQuestions();                  //dodaj bonus i regularna pitanja u listu
                                    contestant.setRegularQuestions();
                                    contestant.setLoaded();
                                }
                            }
                        }
                        else if (contestantClient[0].equals("Tabela")) {
                            Iterator<Contestant> iterator = allContestants.iterator();
                            while (iterator.hasNext()) {
                                Contestant contestant = iterator.next();
                                if (contestant.toString().equals(loginData)) {
                                    this.pw.println(contestant.getContestantTableStatus(allContestants));
                                }
                            }
                        }
                        else if(contestantClient[1].equals("pitanje")){
                            for (Contestant contestant : allContestants) {
                                if(contestant.toString().equals(loginData)){
                                    int questionNum = Integer.parseInt(contestantClient[0]);
                                    if(contestant.getLoaded()){
                                        if((questionNum > 0 && questionNum < 11)){
                                            if(!contestant.hasAnswered(questionNum)){
                                                Question activeQuestion = new Question();
                                                activeQuestion = contestant.getQuestionFromChosenSet(questionNum);
                                                String ispisi = activeQuestion.toString();
                                                contestant.setCurrentQuestion(activeQuestion); //trenutno pitanje
                                                this.pw.println(ispisi);
                                                contestant.setNotAnswered();
                                                contestant.getAnsweredQuestions().add(questionNum); //dodaj redni broj odgovorenog pitanja
                                            }
                                            else{
                                               System.out.println("Takmicar je vec ranije trazio to pitanje");  
                                            }
                                        }
                                        else{
                                            System.out.println("Nepostojece pitanje, opseg pitanja je od 1 do 10."); 
                                        }
                                    }
                                    else{
                                        System.out.println("Pitanja nisu jos ucitana"); 
                                    }
                                }
                            }
                        }
                        else if(contestantClient[1].equals("pomoc prijatelja")){
                            for (Contestant contestant : allContestants) {
                                if(contestant.toString().equals(loginData)){
                                    if(contestant.getHelpFromFriend()){
                                        String friend = contestantClient[2];
                                        String poruka = "Pomozi prijatelju:" + contestant.getUsername() + ":" + contestant.getCurrentQuestion().toString();

                                        for (ConnectedUser clnt : this.allClients) {
                                            if (clnt.getUserName().equals(friend)) {
                                                //prosledi poruku namenjenom korisniku
                                                clnt.pw.println(poruka);
                                                System.out.println(poruka);
                                            } else {
                                                //ispisi da je korisnik kome je namenjena poruka odsutan
                                                if (friend.equals("")) {
                                                    System.out.println("Nepoznat korisnik");
                                                }
                                            }
                                        }
                                        contestant.setHelpFromFriend();
                                    }
                                    else{
                                        System.out.println("Pomoc je vec ranije iskoriscena" ); 
                                    }
                                }
                            }
                        }
                        else if(contestantClient[1].equals("pola pola")){
                            for (Contestant contestant : allContestants) {
                                if(contestant.toString().equals(loginData)){
                                    if(contestant.getHalfAnswers()){
                                        Question activeQuestion = contestant.getCurrentQuestion();
                                        Question halfedQuestion = contestant.halfAnswers(activeQuestion);
                                        String ispisi = halfedQuestion.toString();
                                        System.out.println(ispisi);
                                        this.pw.println(ispisi);
                                    }
                                    else{
                                        System.out.println("Pomoc je vec ranije iskoriscena" ); 
                                    }
                                }
                            }
                        }
                        else if(contestantClient[1].equals("zamjena pitanja")){
                            for (Contestant contestant : allContestants) {
                                if(contestant.toString().equals(loginData)){
                                    if(contestant.getChangeQuestion()){
                                        Question activeQuestion = new Question();
                                        activeQuestion = contestant.getBonusQuestionSet();
                                        contestant.setCurrentQuestion(activeQuestion);
                                        String ispisi = activeQuestion.toString();
                                        System.out.println(ispisi);
                                        this.pw.println(ispisi);
                                        contestant.setChangeQuestion();
                                    }
                                    else{
                                        System.out.println("Pomoc je vec ranije iskoriscena" ); 
                                    } 
                                }
                            }
                        }
                        else if(contestantClient[1].equals("Odgovor")){
                            for (Contestant contestant : allContestants) {
                                if(contestant.toString().equals(loginData) && !contestant.getAnswered()){
                                    String response = contestantClient[2];
                                    String correctAnswer = contestant.getCurrentQuestion().getCorrectAnswer().trim();
                                    contestant.setAnswered();
                                    contestant.setGivenAnswers();
                                    if(response.equals(correctAnswer)){
                                        System.out.println("Tacan odgovor!");
                                        contestant.setCorrectAnswers();
                                    }
                                    else{
                                        System.out.println("Netacan odgovor!");
                                    }
                                }
                                else{
                                    System.out.println("Takmicar je vec odgovorio na pitanje");
                                }
                            }
                        }
                        else if(contestantClient[1].equals("pomozi prijatelju")){
                                for (Contestant contestant : allContestants) {
                                if(contestant.toString().equals(loginData)){
                                    String friend = contestantClient[2];
                                    String poruka = "Pomoc prijatelja:" + contestant.getUsername() + ":" + contestantClient[3];
                                        for (ConnectedUser clnt : this.allClients) {
                                            if (clnt.getUserName().equals(friend)) {
                                                //prosledi poruku namenjenom korisniku
                                                clnt.pw.println(poruka);
                                                System.out.println(poruka);
                                            } else {
                                                //ispisi da je korisnik kome je namenjena poruka odsutan
                                                if (friend.equals("")) {
                                                    this.pw.println("Korisnik " + friend + " je odsutan!");
                                                }
                                            }
                                        }
                                }
                            }
                        }
                        else if(contestantClient[1].equals("Logout")){
                            for (Contestant contestant : allContestants) {
                                if(contestant.toString().equals(loginData)){
                                    contestant.setNotLoggedIn();
                                }
                            }
                        }
                        else{
                            System.out.println("Nepostojeci unos" ); 
                        }
                        break;
                    default:
                        break;
                } 
            } catch (IOException ex) {
                System.out.println("Disconnected user: " + this.UserName);
                for (ConnectedUser cl : this.allClients) {
                    if (cl.getUserName().equals(this.UserName)) {
                        this.allClients.remove(cl);
                        
                        return;
                    }
                }
            }
        }
    }
}
 