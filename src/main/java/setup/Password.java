package setup;
import Utilities.Stream;

import java.io.*;
import java.util.*;
import java.lang.*;

public class Password {
    Stream stream;

    private String email,savedSQ, savedAns, oldPswd, inputAns;

    protected String newPswd;

    /* AVAILABLE FUNCTIONS:
    * setNewPassword + confirmNewPassword //CONNECTED, setNew always calls confirmNew
    * changePassword //user IS logged in
    * resetPassword //user has NOT logged in
    * */

    FileOutstream write;

    /* CONSTRUCTOR*/
    public Password(){
        stream = new Stream();
    }

/**** CREATE NEW PASSWORD ****/
    public String setAndGetNewPassword(){
        stream.writeToConsole("\nPassword Requirements: " +
                "\n \t(1) 6-20 characters " +
                "\n \t(2) at least 1 upper-case letter " +
                "\n \t(3) at least 1 digit " +
                "\n \t(4) at least 1 special character\n");
        stream.writeToConsole("Password: ");
        newPswd = stream.readLineFromConsole();
        char[] testPswd = newPswd.toCharArray();

        if(!meetsRequirements(testPswd)) {
            stream.writeToConsole("Input does not meet Password Requirements. Try Again.\n");
            return setAndGetNewPassword();
        }
        else{
            confirmNewPswd();
            return newPswd;
        }
    }

    public void confirmNewPswd() {
        stream.writeToConsole("Enter 1 to change your password.\n");
        stream.writeToConsole("Confirm Password: ");
        String inputConfirm = stream.readLineFromConsole();
        if (inputConfirm.equals("1")) { //Redo password process
            setAndGetNewPassword();
        } else if (!(inputConfirm.equals(newPswd))) { //does not match
            stream.writeToConsole("\n\nPassword does not match. Try again.\n");
            confirmNewPswd();
        } else {
        }
    }

    public boolean meetsRequirements(char[] testPswd){
        if(testPswd.length >= 6 && testPswd.length <= 20) //Criterion: MIN 6, MAX 20 characters
        {
            int countUpper = 0;    int countDigit = 0;   int countSpecial = 0;
            for(char c : testPswd){
                if(c >= 65 && c <= 90) {countUpper++;} //Upper-case letter
                else if(c >= 48 && c <= 57) {countDigit++;} //Digit
                else if(c >= 97 && c <= 122) {} //lowercase letter, do nothing
                else{countSpecial++;} //Special character
            }

            //MIN 1 special character, MIN 1 uppercase, MIN 1 digit
            if(countUpper > 0 && countDigit > 0 && countSpecial > 0)
                return true;
            return false; //missing upper, digit, or special character
        }
        return false; //too short or too long
    }

    public String getAttemptLogInPassword(){
        stream.writeToConsole("Password: ");
            return stream.readLineFromConsole();
    }

/**** CHANGE ****/
    public void changePassword(){
        try {
            BufferedReader lastLogin = new BufferedReader(new FileReader(System.getProperty("user.dir") + "/Accounts/LastLogin.txt"));
            email = lastLogin.readLine();
            lastLogin.close();

            BufferedReader userFile = new BufferedReader(new FileReader(System.getProperty("user.dir") + "/Accounts/" + email + "/accountInfo.txt"));
            oldPswd = userFile.readLine(); // grabs password
            userFile.close();

            //VERIFY - enter current password
            boolean accessGranted;
            do{
                stream.writeToConsole("\nCurrent Password: ");
                accessGranted = stream.readLineFromConsole().equals(oldPswd);
                if(!accessGranted)
                    stream.writeToConsole("\nIncorrect. Please enter your current password.\n");
            } while(!accessGranted);

            stream.writeToConsole("\n(Change Password) Access Granted.\n");
            newPswd = setAndGetNewPassword();

            write = new FileOutstream();
            write.updatePswd(email, oldPswd, newPswd);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

/**** RESET ****/
    public void resetPassword(){ //FROM OUTSIDE OF APPLICATION; HAS NOT LOGGED IN
        Email checkEmail = new Email();
        boolean registered;
        do{
            stream.writeToConsole("\n(Reset Password) Enter your Registered Email: ");
            email = stream.readLineFromConsole().toLowerCase();
            registered = checkEmail.isRegistered(email);
            if(!registered){ break; }
        } while(email.isEmpty());

        if(registered) { /* READ, TEST ACCESS, ACCESS GRANTED -- RESET PASSWORD */
            try {
                BufferedReader readAccount = new BufferedReader(new FileReader(System.getProperty("user.dir") + "/Accounts/" + email + "/accountInfo.txt"));
                oldPswd = readAccount.readLine(); //old password
                stream.writeToConsole("\nHELLO, " + readAccount.readLine() + ".\n");

                SecurityQuestions pullUserQuestions = new SecurityQuestions();
                savedSQ = pullUserQuestions.getQuestion(Integer.parseInt(readAccount.readLine())); //READ FROM FILE: SECURITY QUESTION #1, THEN CONVERT NUMBER TO ACTUAL QUESTION
                savedAns = readAccount.readLine(); //READ FROM FILE: ANSWER #1
                do {
                    stream.writeToConsole("\nSecurity Question #1: " + savedSQ + "\n"); // PRINT SECURITY QUESTION
                    stream.writeToConsole("Answer: ");
                    inputAns = stream.readLineFromConsole().toUpperCase();
                } while (!inputAns.equals(savedAns) || inputAns.isEmpty());

                savedSQ = pullUserQuestions.getQuestion(Integer.parseInt(readAccount.readLine())); //READ FROM FILE: SECURITY QUESTION #2, THEN CONVERT NUMBER TO ACTUAL QUESTION
                savedAns = readAccount.readLine(); //READ FROM FILE: ANSWER #2
                do {
                    stream.writeToConsole("\nSecurity Question #2: " + savedSQ + "\n"); // PRINT SECURITY QUESTION
                    stream.writeToConsole("Answer: ");
                    inputAns = stream.readLineFromConsole().toUpperCase();
                } while (!inputAns.equals(savedAns) || inputAns.isEmpty());

                stream.writeToConsole("\n(Reset Password) Access Granted.");
                newPswd = setAndGetNewPassword();

                write = new FileOutstream();
                write.updatePswd(email, oldPswd, newPswd);

                readAccount.close(); //close BufferedReader
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        else{
            stream.writeToConsole("OH NO! This email isn't registered. Create an Account!\n");
        }
    }

}
