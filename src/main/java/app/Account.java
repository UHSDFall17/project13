package app;

import Utilities.Stream;
import setup.*;

import java.io.*;

public class Account {

    public Account() {}

    public void createNewAccount() {
        Name newUser = new Name();
        String name = newUser.setAndGetNewName();

        Email newEmail = new Email();
        String email = newEmail.setAndGetNewEmail();

        Password newPassword = new Password();
        String password = newPassword.setAndGetNewPassword();

        SecurityQuestions securityQuestions = new SecurityQuestions();
        String[] secQA1 = securityQuestions.setAndGetQA1(); //set both security question #1 AND its respective answer
        String[] secQA2 = securityQuestions.setAndGetQA2(); //set both security question #2 AND its respective answer

        FileOutstream write = new FileOutstream();
        write.saveNewAccount(email, password, name, secQA1[0], secQA1[1], secQA2[0], secQA2[1]);
    }

    public String[] logIn(){
        Stream stream = new Stream();
        String[] loginInfo = new String[2];
        loginInfo[0] = "";
        loginInfo[1] = "";

        Email email = new Email();
        String userEmail = email.getAttemptLogInEmail();

        Password password = new Password();
        String userPassword = password.getAttemptLogInPassword();

        String fileName = "Accounts/" + userEmail + "/accountInfo.txt";
        try
        {
            FileReader fileReader = new FileReader(fileName);
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            if(bufferedReader.readLine().equals(userPassword))
            {
                bufferedReader.close();
                stream.writeToConsole("Login successful\n");
                loginInfo[0] = userEmail;
                loginInfo[1] = userPassword;
                return loginInfo;
            }
            else
            {

                stream.writeToConsole("Password is invalid.");
                bufferedReader.close();
                stream.writeToConsole("Login failed\n");

            }
        }
        catch(FileNotFoundException e) {
            stream.writeToConsole("User not found: '" + userEmail + "'.");
        }
        catch(IOException e) {
            stream.writeToConsole("Error reading file: '" + fileName + "'.");
        }
        return loginInfo;
    }

    public void resetForgottenPassword() {
        Password password = new Password();
        password.resetPassword();
    }

    public void changeName() {
        Name name = new Name();
        name.changeName();
    }

    public void changePassword() {
        Password password = new Password();
        password.changePassword();
    }

    public void changeSQ() {
        SecurityQuestions securityQuestions = new SecurityQuestions();
        securityQuestions.changeSQ();
    }
}