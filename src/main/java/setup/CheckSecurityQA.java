package setup;

import java.util.Scanner;

public class CheckSecurityQA{

    //array of security questions offered
    private String[] questions = {null, "What is your mother's maiden name?",
            "What is the name of the street that you lived on as a child?",
            "What was the make and model of your first car?",
            "What was the name of your first pet?",
            "In what city or town did your mother and father meet?",
            "What is the last name of your favorite childhood teacher?",
            "What is the first name of the person you first kissed?",
            "What elementary/ primary school did you go to?"};

    public CheckSecurityQA(){}


    public boolean validSelection(int questionNum, char selection){
        return(!(String.valueOf(selection).isEmpty()
                || (questionNum == 1 && (selection < 49 || selection > 52))
                || (questionNum == 2 && (selection < 53 || selection > 56))));
    }

    public String getQuestion(int index){
        return questions[index];
    }

    public void updateSQA(String email, int oldSQ, String oldAns, int newSQ, String newAns){

    }
}
