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

    public Account(){ //create entire account from scratch
        //default constructor
        System.out.println("\nCreate an Account\n");
        System.out.println();

        setName();
        System.out.println();

        setEmail();
        System.out.println();

        setPswd();
        System.out.println();

        setSecurityQuestion1();
        setSecurityQuestion2();
        System.out.println();
        System.out.println();
        System.out.println();

        scanner.close();
        WriteToFile newFile = new WriteToFile();
        newFile.saveNewAccount(email, pswd, name, secQ_1, ans1, secQ_2, ans2);
    }

    public Account(String email, String oldPswd){ //reset password
        setPswd();
        WriteToFile updateFile = new WriteToFile();
        updateFile.updatePswd(email, oldPswd, pswd);
    }

    /*******************************
     *           NAME               *
     *******************************/
    public void setName() {
        System.out.print("Name: ");
        name = (scanner.nextLine()).toUpperCase();
        if(name.isEmpty()) {
            System.out.println("Error: Please enter your name.\n");
            setName();
        }
    }

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
    public void setPswd(){
        System.out.println("\nPassword Requirements: " +
                "\n \t(1) 6-20 characters " +
                "\n \t(2) at least 1 upper-case letter " +
                "\n \t(3) at least 1 digit " +
                "\n \t(4) at least 1 special character");
        System.out.print("Password: ");
        pswd = scanner.nextLine();
        testPswd = pswd.toCharArray();

        newPswd = new CheckPassword();

        if(!newPswd.meetsRequirment(testPswd)) {
            System.out.println("Input does not meet Password Requirements. Try Again.");
            setPswd();
        }
        else{
            confirmPswd();
        }
    }

    public void confirmPswd() {
        System.out.println("Enter 1 to change your password.");
        System.out.print("Confirm Password: ");
        String input = scanner.nextLine();
        if(input.equals("1")){ //Redo password process
            setPswd();
        }
        else if(!(input.equals(pswd))) { //does not match
            System.out.println("\n\nPassword does not match. Try again.");
            confirmPswd();
        }
        else{}
    }

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