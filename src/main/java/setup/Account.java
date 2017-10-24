package setup;

import java.awt.*;
import java.io.*;
import java.util.*;

public class Account {
    private String name, email, pswd, ans1, ans2;
    private int secQ_1, secQ_2;

    private char[] testPswd;
    private char testQ;

    CheckEmail newEmail;
    CheckPassword newPswd;
    CheckSecurityQA newSQ1;
    CheckSecurityQA newSQ2;

    public Account() {
    }

    public void createNewAccount() {
        Name newUser = new Name();
        newUser.setNewName();

        Email newEmail = new Email();
        newEmail.setNewEmail();

        Password newPassword = new Password();
        newPassword.setNewPassword();

        SecurityQuestions securityQuestions_1 = new SecurityQuestions();
        securityQuestions_1.setQA1(); //set both security question #1 AND its respective answer

        SecurityQuestions securityQuestions_2 = new SecurityQuestions();
        securityQuestions_2.setQA2(); //set both security question #2 AND its respective answer

        WriteToFile write = new WriteToFile();
        write.saveNewAccount(newEmail.email, newPassword.newPswd, newUser.name, securityQuestions_1.newSQ1, securityQuestions_1.newAns1, securityQuestions_2.newSQ2, securityQuestions_2.newAns1);
    }

    public void logIn(){
        Email email = new Email();
        String emailAttempt = email.getAttemptLogInEmail();

        Password password = new Password();

        CheckEmail checkEmail = new CheckEmail();

        if(!(checkEmail.isRegistered(emailAttempt) && password.isGoodLogInPassword(emailAttempt))){
            System.out.println("Email and/or password is incorrect. Try Again.");
            logIn();
        }
        else{} //MOVE ONTO USER'S DASHBOARD
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