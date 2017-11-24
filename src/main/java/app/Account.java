package app;

import Utilities.Stream;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import setup.*;

import java.io.*;
import java.util.Scanner;

public class Account {
    private Stream stream;

    public Account() {
        stream = new Stream();
    }

    public static User getUserInfo(String userEmail)
    {
        User user = null;

        try(BufferedReader fileReader = new BufferedReader(new FileReader(System.getProperty("user.dir") + "/Accounts/" + userEmail + "/accountInfo.txt")))
        {
            user = new User(userEmail, fileReader.readLine(), fileReader.readLine(), fileReader.readLine(), fileReader.readLine(), fileReader.readLine(), fileReader.readLine());
        } catch (IOException e) {
            e.printStackTrace();
        }

        return user;
    }

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

        /* JSON FILE SETUP */
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        Dashboard dashboard = new Dashboard();
        // SET DEFAULT LISTS
        dashboard.storeNewList("Personal");
        dashboard.storeNewList("Work");
        dashboard.storeNewList("Grocery List");
        // WRITE TO JSON FILE
        try(FileWriter writer = new FileWriter("Accounts/" + email + "/data.json")){
            gson.toJson(dashboard, writer);
            writer.close();
            stream.writeToConsole("\nWELCOME, " + name + "!\nYou have successfully created an account.\n");
        }catch(IOException e){
            e.printStackTrace();
        }
    }

    public User logIn(){
        stream = new Stream();
        Email email = new Email();
        String userEmail = "";
        while(userEmail.isEmpty()) {
         userEmail = email.getAttemptLogInEmail();
        }

        stream.writeToConsole("\nForgot your password? That's okay, just Press 1!\n");
        Password password = new Password();
        String userPassword = "";
        while(userPassword.isEmpty()) {
            userPassword = password.getAttemptLogInPassword();
        }

        if(userPassword.equals("1")){
            resetForgottenPassword();
        }
        else {
            String fileName = "Accounts/" + userEmail + "/accountInfo.txt";
            try {
                FileReader fileReader = new FileReader(fileName);
                BufferedReader bufferedReader = new BufferedReader(fileReader);

                if (bufferedReader.readLine().equals(userPassword)) {
                    bufferedReader.close();
                    stream.writeToConsole("Login successful.\n\n");
                    return getUserInfo(userEmail);
                } else {

                    stream.writeToConsole("Incorrect Password. ");
                    bufferedReader.close();
                    stream.writeToConsole("Login failed.\n");

                }
            } catch (FileNotFoundException e) {
                stream.writeToConsole("User not found: '" + userEmail + "'.\n");
            } catch (IOException e) {
                stream.writeToConsole("Error reading file: '" + fileName + "'.\n");
            }
        }
        return null;
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