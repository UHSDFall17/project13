package setup;

import java.util.*;

/****
 * Change a LOGGED IN user's security questions
 * User must FIRST verify himself
 * by correctly entering his current login password.
 */

public class SecurityQuestions {
    Scanner input = new Scanner(System.in);

    CheckSecurityQA newSQ;
    WriteToFile updateSQ;

    private char inputSecQuestion; //FOR verifying that selection is in given range
    private String userEmail, userPassword; //FOR verifying user
    private String inputPswd ; //FOR changing security questions

    protected int newSQ1,newSQ2;
    protected String newAns1,newAns2;

    protected String[] questions = {null, "What is your mother's maiden name?",
            "What is the name of the street that you lived on as a child?",
            "What was the make and model of your first car?",
            "What was the name of your first pet?",
            "In what city or town did your mother and father meet?",
            "What is the last name of your favorite childhood teacher?",
            "What is the first name of the person you first kissed?",
            "What elementary/ primary school did you go to?"};

    public SecurityQuestions(){}

    public void setQA1() {
        do {
            System.out.println("\nSelect Security Question #1");
            for (int i = 1; i <= 4; i++)
                System.out.println("\t" + i + " - " + questions[i]);
            inputSecQuestion = input.nextLine().charAt(0);
        } while (inputSecQuestion < 49 || inputSecQuestion > 52);

        newSQ1 = Character.getNumericValue(inputSecQuestion);

        do {
            System.out.println("\nPress 1 to change Security Question #1.");
            System.out.println("Question: " + questions[newSQ1]);
            System.out.print("Answer: ");
            newAns1 = input.nextLine().toUpperCase();
        }while(newAns1.isEmpty());

        if (newAns1.equals("1")) {
            setQA1();
        }
    }

    public void setQA2(){
        do {
            System.out.println("\nSelect Security Question #2");
            for (int i = 5; i <= 8; i++)
                System.out.println("\t" + i + " - " + questions[i]);
            inputSecQuestion = input.nextLine().charAt(0);
        } while (inputSecQuestion < 53 || inputSecQuestion > 56);

        newSQ2 = Character.getNumericValue(inputSecQuestion);

        do {
            System.out.println("\nPress 1 to change Security Question #2.");
            System.out.println("Question: " + questions[newSQ2]);
            System.out.print("Answer: ");
            newAns2 = input.nextLine().toUpperCase();
        }while(newAns2.isEmpty());

        if (newAns2.equals("1")) {
            setQA2();
        }
    }

    public void changeSQ(){
        userEmail = something;
        userPassword = something;

        System.out.print("Log-In Password: ");
        inputPswd = input.nextLine();

        if(!inputPswd.matches(userPassword)){
            System.out.println("Incorrect Password.");
            changeSQ();
        }
        else{
            setQA1();
            setQA2();

            /* REPLACE AND UPDATE IN FILE */
            String[] newSQA = {Integer.toString(newSQ1), newAns1, Integer.toString(newSQ2), newAns2};
            updateSQ = new WriteToFile();
            updateSQ.updateSQ(userEmail, newSQA);
        }
    }

}
