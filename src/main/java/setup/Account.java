package setup;

import java.io.*;
import java.util.*;

public class Account {
    Scanner scanner = new Scanner(System.in);

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

        SecurityQuestions newSQ1 = new SecurityQuestions();
        newSQ1.setQA1(); //set both security question #1 AND its respective answer

        SecurityQuestions newSQ2 = new SecurityQuestions();
        newSQ1.setQA2(); //set both security question #2 AND its respective answer

        WriteToFile write = new WriteToFile();
        write.saveNewAccount(newEmail.email, newPassword.newPswd, newUser.name, newSQ1.sq1, newSQ1.ans1, newSQ2.sq2, newSQ2.ans2);
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