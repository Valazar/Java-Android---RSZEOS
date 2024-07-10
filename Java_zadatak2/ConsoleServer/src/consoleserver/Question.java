/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package consoleserver;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 *
 * @author valazar
 */
public class Question implements Serializable{
    
    private String questionText;
    private String optionA;
    private String optionB;
    private String optionC;
    private String optionD;
    private String correctAnswer;

    public Question(String questionText, String optionA, String optionB, String optionC, String optionD, String correctAnswer) {
        this.questionText = questionText;
        this.optionA = optionA;
        this.optionB = optionB;
        this.optionC = optionC;
        this.optionD = optionD;
        this.correctAnswer = optionD;
    }

    Question() {}

    // Getteri
    public String getQuestionText() {
        return questionText;
    }

    public String getOptionA() {
        return optionA;
    }

    public String getOptionB() {
        return optionB;
    }

    public String getOptionC() {
        return optionC;
    }

    public String getOptionD() {
        return optionD;
    }

    public String getCorrectAnswer() {
        return correctAnswer;
    }
    
    public void printQuestion() {
        System.out.println("Pitanje: " + questionText);
        System.out.println("a) " + optionA);
        System.out.println("b) " + optionB);
        System.out.println("c) " + optionC);
        System.out.println("d) " + optionD);
        System.out.println("Tacan odgovor: " + correctAnswer);
        System.out.println("---------------");
    }
    
    @Override
    public String toString() {
        List<String> options = new ArrayList<>(Arrays.asList(optionA, optionB, optionC, optionD));
        Collections.shuffle(options);  // Promena redoslijeda opcija

        StringBuilder sb = new StringBuilder();

        sb.append("Pitanje: ").append(questionText).append(":");
        sb.append("a) ").append(options.get(0)).append(":");
        sb.append("b) ").append(options.get(1)).append(":");
        sb.append("c) ").append(options.get(2)).append(":");
        sb.append("d) ").append(options.get(3)).append(":");
        //sb.append("Tacan odgovor: ").append(correctAnswer).append("\n");
        sb.append("---------------\n");

        return sb.toString();
    }

}