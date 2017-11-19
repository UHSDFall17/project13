package setup;

import Utilities.Stream;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

/****
 * Change a LOGGED IN user's security questions
 * User must FIRST verify himself
 * by correctly entering his current login password.
 */

public class SecurityQuestions {
    Stream stream;

    private String noInputChecker; //user did not make a selection
    private char secQuestionChecker; //FOR verifying that selection is in given range

    private String userEmail, userPassword; //FOR verifying user
    private String inputPswd; //FOR changing security questions

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

    public SecurityQuestions(){
        stream = new Stream();
    }

    public String[] setAndGetQA1() {
        String[] secQA1 = new String[2];
        secQA1[1] = "1";

        while(secQA1[1].equals("1")) {
            do {
                stream.writeToConsole("\nSelect Security Question #1\n");
                for (int i = 1; i <= 4; i++)
                    stream.writeToConsole("\t" + i + " - " + questions[i] + "\n");
                noInputChecker = stream.readLineFromConsole();
                if (noInputChecker.isEmpty()) {
                    stream.writeToConsole("Invalid: Selection required.\n");
                    secQuestionChecker = 'e';
                } else
                    secQuestionChecker = noInputChecker.charAt(0);
            } while (secQuestionChecker < 49 || secQuestionChecker > 52);


            do {
                stream.writeToConsole("\nPress 1 to change Security Question #1.\n");
                stream.writeToConsole("Question: " + questions[Character.getNumericValue(secQuestionChecker)] + "\n");
                stream.writeToConsole("Answer: ");
                secQA1[1] = stream.readLineFromConsole().toUpperCase();
            } while (secQA1[1].isEmpty());
        }
        secQA1[0] = Character.toString(secQuestionChecker);
        return secQA1;
    }

    public String[] setAndGetQA2(){
        String[] secQA2 = new String[2];
        secQA2[1] = "1";

        while(secQA2[1].equals("1")) {
            do {
                stream.writeToConsole("\nSelect Security Question #2.\n");
                for (int i = 5; i <= 8; i++)
                    stream.writeToConsole("\t" + i + " - " + questions[i] + "\n");
                noInputChecker = stream.readLineFromConsole();
                if (noInputChecker.isEmpty()) {
                    stream.writeToConsole("Invalid: Selection required.\n");
                    secQuestionChecker = 'e';
                } else
                    secQuestionChecker = noInputChecker.charAt(0);
            } while (secQuestionChecker < 53 || secQuestionChecker > 56);

            do {
                stream.writeToConsole("\nPress 1 to change Security Question #2.\n");
                stream.writeToConsole("Question: " + questions[Character.getNumericValue(secQuestionChecker)] + "\n");
                stream.writeToConsole("Answer: ");
                secQA2[1] = stream.readLineFromConsole().toUpperCase();
            } while (secQA2[1].isEmpty());
        }

        secQA2[0] = Character.toString(secQuestionChecker);
        return secQA2;
    }

    public void changeSQ(){
        try {
            BufferedReader lastLogin = new BufferedReader(new FileReader(System.getProperty("user.dir") + "/Accounts/LastLogin.txt"));
            userEmail = lastLogin.readLine();
            lastLogin.close();

            BufferedReader userFile = new BufferedReader(new FileReader(System.getProperty("user.dir") + "/Accounts/" + userEmail + "accountInfo.txt"));
            userPassword = userFile.readLine(); // grabs password
            userFile.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        stream.writeToConsole("Log-In Password: ");
        inputPswd = stream.readLineFromConsole();

        if(!inputPswd.matches(userPassword)){
            stream.writeToConsole("Incorrect Password.\n");
            changeSQ();
        }
        else{
            String[] secQA1 = setAndGetQA1();
            String[] secQA2 = setAndGetQA2();

            /* REPLACE AND UPDATE IN FILE */
            String[] newSQA = {secQA1[0], secQA1[1], secQA2[0], secQA1[1]};
            FileOutstream write = new FileOutstream();
            write.updateSQ(userEmail, newSQA);
        }
    }

    public String getQuestion(int index){
        return questions[index];
    }

}
