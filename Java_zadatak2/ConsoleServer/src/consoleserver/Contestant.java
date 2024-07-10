/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package consoleserver;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Comparator;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
/**
 *
 * @author valazar
 */
public class Contestant extends User { 
    
    private List<Question> chosenQuestionSet;
    private Question bonusQuestionSet;
    private List<List<Question>> questionSets;
    private int givenAnswers;
    private int correctAnswers;
    private boolean halfAnswers;
    private boolean changeQuestion;
    private boolean helpFromFriend;
    private boolean answered;
    private List<Integer> answeredQuestions;
    private Question currentQuestion;
    private boolean loaded = false;
    
    public Contestant() {
        super();
        this.chosenQuestionSet = new ArrayList<>();
        this.bonusQuestionSet = new Question();
        this.questionSets = new ArrayList<>();
        this.halfAnswers = true;
        this.changeQuestion = true;
        this.helpFromFriend = true;
        this.answered = false;
        this.answeredQuestions = new ArrayList<>();
        this.currentQuestion = new Question();
    }
    
    public Contestant(String  username, String  password) {
        super(username, password, "takmicar");
        this.chosenQuestionSet = new ArrayList<>();
        this.bonusQuestionSet = new Question();
        this.questionSets = new ArrayList<>();
        this.halfAnswers = true;
        this.changeQuestion = true;
        this.helpFromFriend = true;
        this.answered = false;
        this.answeredQuestions = new ArrayList<>();
        this.currentQuestion = new Question();
    }
    

    public List<Question> getChosenQuestionSet() {
        return chosenQuestionSet;
    }
    
    
    public Question getBonusQuestionSet() {
        return bonusQuestionSet;
    }
    
    public void setRegularQuestions() {
        if (questionSets.isEmpty()) {
            chosenQuestionSet = new ArrayList<>();
            return;
        }
        chosenQuestionSet = new ArrayList<>(questionSets.get(questionSets.size() - 1).subList(0, Math.min(10, questionSets.get(questionSets.size() - 1).size())));
    }

    public void setBonusQuestions() {
        if (questionSets.isEmpty() || questionSets.get(questionSets.size() - 1).isEmpty()) {
            bonusQuestionSet = null; // Postavi bonusQuestionSet na null ako je lista prazna
        } else {
            bonusQuestionSet = questionSets.get(questionSets.size() - 1).get(questionSets.get(questionSets.size() - 1).size() - 1);
        }
    }

    public int getGivenAnswers() {
        return givenAnswers;
    }

    public int getCorrectAnswers() {
        return correctAnswers;
    }
    
    public void setGivenAnswers() {
        this.givenAnswers += 1;
    }

    public void setCorrectAnswers() {
        this.correctAnswers += 1;
    }
    
    public boolean getHelpFromFriend() {
        return helpFromFriend;
    }
    
    public void setHelpFromFriend() {
        this.helpFromFriend = false;
    }
    
    public boolean getHalfAnswers() {
        return halfAnswers;
    }
    
    public void setHalfAnswers() {
        this.halfAnswers = false;
    }
    
    public boolean getChangeQuestion() {
        return changeQuestion;
    }
    
    public void setChangeQuestion() {
        this.changeQuestion = false;
    }
    
    public List<Integer> getAnsweredQuestions() {
        return answeredQuestions;
    }
    
    public boolean hasAnswered(int questionNumber) {
        return answeredQuestions.contains(questionNumber);
    }
    
    public void clearAnsweredQuestions() {
        answeredQuestions.clear();
    }
    
    public void addQuestionSet(List<Question> questionSet) {
        questionSets.add(questionSet);
    }
    
    public Question getCurrentQuestion() {
        return currentQuestion;
    }
    
    public void setCurrentQuestion(Question currentQuestion) {
        this.currentQuestion = currentQuestion;
    }
    
    public boolean getAnswered() {
        return answered;
    }
    
    public void setAnswered() {
        this.answered = true;
    }
    
    public void setNotAnswered() {
        this.answered = false;
    }
    
    public boolean getLoaded() {
        return loaded;
    }
    
    public void setLoaded() {
        this.loaded = true;
    }
    
    public List<String> readFileContent(String fileName) {
        try {
            return Files.readAllLines(Paths.get(fileName), StandardCharsets.UTF_8);
        } catch (IOException e) {
            System.out.println("Greška prilikom čitanja fajla: " + e.getMessage());
            return new ArrayList<>();
        }
    }
    
    public void loadQuestionSets(String fileName) {
        try{

            List<String> fileContent = readFileContent(fileName);

            List<Question> questions = new ArrayList<>();
            String questionText = "";
            String optionA = "";
            String optionB = "";
            String optionC = "";
            String optionD = "";
            String correctAnswer = "";

            for (String line : fileContent) {
                if (line.matches("^\\d+\\..*")) {
                    // Ako je ovo početak novog pitanja, dodaj prethodno pitanje u listu
                    if (!questionText.isEmpty()) {
                        addQuestionToList(questions, questionText, optionA, optionB, optionC, optionD, correctAnswer);
                        optionA = "";
                        optionB = "";
                        optionC = "";
                        optionD = "";
                        correctAnswer = "";
                    }
                    questionText = line.substring(line.indexOf(" ") + 1).trim();
                } else if (line.matches("^\\s*a\\) .*")) {
                    optionA = line.substring(3).trim();
                } else if (line.matches("^\\s*b\\) .*")) {
                    optionB = line.substring(3).trim();
                } else if (line.matches("^\\s*c\\) .*")) {
                    optionC = line.substring(3).trim();
                } else if (line.matches("^\\s*d\\) .*")) {
                    optionD = line.substring(3).trim();
                    correctAnswer = optionD;
                }
            }

            // Dodaj poslednje pitanje ako postoji
            if (!questionText.isEmpty()) {
                addQuestionToList(questions, questionText, optionA, optionB, optionC, optionD, correctAnswer);
            }

            addQuestionSet(questions);  // Dodaj set pitanja u questionSets

            System.out.println("Ucitan set pitanja iz fajla: " + fileName);
            
            this.loaded = true;
            
        } catch (Exception e) {
            System.out.println("Greska prilikom ucitavanja seta pitanja: " + e.getMessage());
        }
    }
    
    public void addQuestionToList(List<Question> questions, String questionText, String optionA, String optionB, String optionC, String optionD, String correctAnswer) {
        if (!questionText.isEmpty()) {
            Question question = new Question(questionText, optionA, optionB, optionC, optionD, correctAnswer);
            questions.add(question);
        }
    }
  
    public String getContestantTableStatus(ArrayList<Contestant> allContestants) {
        // Pravimo kopiju liste
        ArrayList<Contestant> copyOfAllContestants = new ArrayList<>(allContestants);

        // Sortiranje takmičara po broju tačnih odgovora u kopiji liste
        copyOfAllContestants.sort(Comparator.comparingInt(Contestant::getCorrectAnswers).reversed());

        StringBuilder sb = new StringBuilder();
        sb.append("Tabela:");
        
        int rank = 1;
        for (Contestant contestant : copyOfAllContestants) {
            sb.append(rank)
              .append(". ")
              .append(contestant.getUsername())
              .append(" ")
              .append(contestant.getCorrectAnswers())
              .append("/")
              .append(contestant.getGivenAnswers())
              .append(" <= ")
              .append(contestant.getCorrectAnswers())
              .append(" tacnih odgovora, od ")
              .append(contestant.getGivenAnswers())
              .append(" predjenih pitanja.")
              .append(":");  // Dodajemo novi red nakon svakog takmičara
            rank++;
        }

        return sb.toString();
    }


    public Question halfAnswers(Question question) {
        
        if (!halfAnswers) {
            System.out.println("Polovljenje odgovora je već korišćeno.");
            return null;
        }
        
        // Podesi tačan odgovor
        String correctAnswer = question.getCorrectAnswer();
        
        // Podesi netačne odgovore
        List<String> allOptions = new ArrayList<>();
        allOptions.add(question.getOptionA());
        allOptions.add(question.getOptionB());
        allOptions.add(question.getOptionC());
        Collections.shuffle(allOptions);  
        
        // Dodaj još jedan nasumični odgovor
        String optionA = allOptions.get(0);

        Question halfAnsweredQuestion = new Question(question.getQuestionText(), optionA, correctAnswer, "", "", correctAnswer);
        
        this.halfAnswers = false;
        
        return halfAnsweredQuestion;
        
    }
    
    public void printQuestionSets() {
        // Ispis chosenQuestionSet
        System.out.println("Chosen Question Set:");
        if (chosenQuestionSet != null && !chosenQuestionSet.isEmpty()) {
            for (Question question : chosenQuestionSet) {
                question.printQuestion();
            }
        } else {
            System.out.println("Empty chosen question set.");
        }

        // Ispis bonusQuestionSet
        System.out.println("\nBonus Question:");
        if (bonusQuestionSet != null) {
            bonusQuestionSet.printQuestion();            
        } else {
            System.out.println("Empty bonus question set.");
        }
    }
    
    public Question getQuestionFromChosenSet(int questionIndex) {
        // Provera da li je chosenQuestionSet null ili prazan
        if (chosenQuestionSet == null || chosenQuestionSet.isEmpty()) {
            System.out.println("Chosen question set is empty.");
            return null;
        }

        // Provera da li je prosleđeni indeks validan
        if (questionIndex < 0 || questionIndex > chosenQuestionSet.size()) {
            System.out.println("Invalid question index.");
            return null;
        }

        return chosenQuestionSet.get(questionIndex-1);
    }

}
