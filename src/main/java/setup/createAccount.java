package setup;

import java.io.*;
import java.util.*;

public class createAccount{
    Scanner scanner = new Scanner(System.in);

    private String name, email, pswd;
    private int secQ_1, secQ_2;

    SecurityQA secure = new SecurityQA();

    public createAccount() { //default constructor
        clearScreen();

        System.out.println("\nCreate an Account\n");
        System.out.println();

        setName();
        System.out.println();

        setEmail();
        System.out.println();
        clearScreen();

        setPswd();
        System.out.println();
        clearScreen();

        setSecurityQuestions();
        System.out.println();
        System.out.println();
        System.out.println();
        clearScreen();

        scanner.close();
        saveAccount();
    }

    public void clearScreen(){
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    /*******************************
     *           NAME               *
     *******************************/
    public void setName() {
        System.out.print("Name: ");
        name = (scanner.nextLine()).toUpperCase();
        if(!goodName(name)) {
            System.out.println("Error: Please enter your name.\n");
            setName();
        }
    }

    protected boolean goodName(String name){
        return !name.equals("");
    }

    /*******************************
     *           EMAIL              *
     *******************************/
    protected void setEmail() {
        System.out.print("Email: ");
        email = (scanner.nextLine()).toLowerCase();

        if(!goodEmail(email)){ //checks for '@' and valid extension
            System.out.println("\nError: Invalid Email Format.");
            setEmail();
        }

        if(rgsdEmail(email)) { //email had already been registered
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
            clearScreen();
            System.out.println("Name: " + name);
            setEmail();
        }
        else{}
    }

    protected boolean goodEmail(String email){
        /** Check for ONE '@' **/
        String[] parts = email.split("@");
        if(parts.length != 2) // no "@" or too many
            return false;

        /** Check for VALID extensions **/
        String[] subParts = parts[1].split("\\.");
        if(subParts.length < 2) //no "." in domain-extension field
            return false;
        else if(subParts[subParts.length - 1].equals("com")
                || subParts[subParts.length - 1].equals("net")
                || subParts[subParts.length - 1].equals("org")
                || subParts[subParts.length - 1].equals("gov")
                || subParts[subParts.length - 1].equals("edu")
                || subParts[subParts.length - 1].equals("info")){
            return true;
        }
        else
            return false;
    }

    protected boolean rgsdEmail(String email){
        if(new File(System.getProperty("user.dir") + "/Accounts/" + email).exists())
            return true;
        return false;
    }


    /*******************************
     *           PASSWORD           *
     *******************************/
    public void setPswd(){
        Password newPswd = new Password();
        pswd = newPswd.getPswd();
    }

    /*******************************
     *      SECURITY QUESTIONS      *
     *******************************/

    public void setSecurityQuestions() { // Security Question and Answer #1
        secure.setSecureQA1();
        secure.setSecureQA2();
    }

    /*******************************
     *         SAVE ACCOUNT         *
     *******************************/

    protected void saveAccount() {
        try {
            File key = new File(System.getProperty("user.dir") + "/Accounts/" + email);
            key.mkdirs();
            PrintWriter account = new PrintWriter(System.getProperty("user.dir") + "/Accounts/" + email + "/accountInfo.txt");
            account.println(pswd);
            account.println(name);
            account.println(secure.getIndex(1));
            account.println(secure.getAnswer(1));
            account.println(secure.getIndex(2));
            account.print(secure.getAnswer(2));
            account.close();
            clearScreen();
            System.out.println("\nWELCOME, " + name + "!\nYou have successfully created an account.\n");
        }
        catch(IOException e) {
            System.out.println("Something went wrong! Please try again.");
            System.exit(1);
        }
        //return to LOGIN PAGE
    }
}