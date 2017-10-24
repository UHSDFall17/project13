package setup;

import java.io.*;
import java.util.*;

public class Account{
    Scanner scanner = new Scanner(System.in);

    private String name, email, pswd, ans1, ans2;
    private int secQ_1, secQ_2;

    private char[] testPswd;
    private char testQ;

    CheckEmail newEmail;
    CheckPassword newPswd;
    CheckSecurityQA newSQ1;
    CheckSecurityQA newSQ2;

    public Account(){}

    public void createNewAccount(){
        User newUser = new User();
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

    public void resetForgottenPassword(){

    }

    public void changeName(){}

    public void changePassword(){}

    public void changeSQ(){}


    /*******************************
     *           EMAIL              *
     *******************************/
    protected void setEmail() {
        System.out.print("Email: ");
        email = (scanner.nextLine()).toLowerCase();

        newEmail = new CheckEmail();
        if(!newEmail.goodEmail(email)){ //checks for '@' and valid extension
            System.out.println("\nError: Invalid Email Format.");
            setEmail();
        }
        else if(newEmail.isRegistered(email)) { //email had already been registered
            System.out.println("\nError: Email Already Registered.\n Press ENTER to try again or '0' to log in.");
            if((scanner.nextLine()).equals("0"))
                System.exit(1);
            setEmail();
        }
        else { //good, unregistered email
            confirmEmail(email);
        }
    }

    protected void confirmEmail(String email){
        System.out.println("Confirm Email: " + email + " (Y/N)");
        String temp = (scanner.nextLine()).toUpperCase();
        if(temp.equals("")){
            System.out.println("Please enter Y or N.");
            confirmEmail(email);
        }
        else if(temp.equals("N")){
            System.out.println("Name: " + name);
            setEmail();
        }
        else{}
    }

    /*******************************
     *           PASSWORD           *
     *******************************/


    /*******************************
     *      SECURITY QUESTIONS      *
     *******************************/

    public void setSecurityQuestion1() { // Security Question and Answer #1
        newSQ1 = new CheckSecurityQA();
        System.out.println("\nSelect Security Question #1");
        for (int i = 1; i <= 4; i++)
            System.out.println("\t" + i + " - " + newSQ1.getQuestion(i));
        testQ = scanner.nextLine().charAt(0);


        if (!newSQ1.validSelection(1, testQ)) {
            System.out.println("Input is invalid. Try again.");
            setSecurityQuestion1();
        }
        else {
            secQ_1 = Character.getNumericValue(testQ);
            System.out.println("\nPress 1 to change Security Question #1.");
            System.out.println("Question: " + newSQ1.getQuestion(secQ_1));
            System.out.print("Answer: ");
            ans1 = scanner.nextLine().toUpperCase();
            if (ans1.equals("1")) {
                setSecurityQuestion1();
            }
            else if(ans1.isEmpty()){
                System.out.println("Enter an answer for Security Question #1.");
            }
            else{}
        }
    }

    public void setSecurityQuestion2(){
        newSQ2 = new CheckSecurityQA();
        System.out.println("\nSelect Security Question #2");
        for(int i = 5; i <= 8; i++)
            System.out.println("\t" + i + " - " + newSQ2.getQuestion(i));
       testQ = scanner.nextLine().charAt(0);

        if (!newSQ2.validSelection(2, testQ)) {
            System.out.println("Input is invalid. Try again.");
            setSecurityQuestion2();
        }
        else {
            secQ_2 = Character.getNumericValue(testQ);
            System.out.println("\nPress 1 to change Security Question #2.");
            System.out.println("Question: " + newSQ2.getQuestion(secQ_2));
            System.out.print("Answer: ");
            ans2 = scanner.nextLine().toUpperCase();
            if (ans2.equals("1")) {
                setSecurityQuestion2();
            }
            else if(ans2.isEmpty()){
                System.out.println("Enter an answer for Security Question #2.");
            }
            else{}
        }
    }
}