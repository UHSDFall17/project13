package setup;

import java.io.*;
import java.util.*;

public class Create{
    Scanner scanner = new Scanner(System.in);

    private String name, email, pswd, ans1, ans2;
    private int secQ_1, secQ_2;

    private char[] testPswd;
    private char testQ;

    CheckPassword checkPswd;
    CheckSecurityQA checkSQ;

    public Create() {

    }

    public Create(String welcome){
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
        saveAccount();
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
        System.out.println("\nPassword Requirements: " +
                "\n \t(1) 6-20 characters " +
                "\n \t(2) at least 1 upper-case letter " +
                "\n \t(3) at least 1 digit " +
                "\n \t(4) at least 1 special character");
        System.out.print("Password: ");
        pswd = scanner.nextLine();
        testPswd = pswd.toCharArray();

        checkPswd = new CheckPassword();

        if(!checkPswd.goodPswd(testPswd)) {
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
        if(input.equals("1")){ //Reset password process
            setPswd();
        }
        else if(!(input.equals(pswd))) { //does not match
            System.out.println("\n\nPassword does not match. Try again.");
            confirmPswd();
        }
        else{}
    }

    public String getPswd() {return pswd;}

    /*******************************
     *      SECURITY QUESTIONS      *
     *******************************/

    public void setSecurityQuestion1() { // Security Question and Answer #1
        checkSQ = new CheckSecurityQA();
        System.out.println("\nSelect Security Question #1");
        for (int i = 1; i <= 4; i++)
            System.out.println("\t" + i + " - " + checkSQ.getQuestion(i));
        testQ = scanner.nextLine().charAt(0);


        if (!checkSQ.validSelection(1, testQ)) {
            System.out.println("Input is invalid. Try again.");
            setSecurityQuestion1();
        }
        else {
            secQ_1 = Character.getNumericValue(testQ);
            System.out.println("\nPress 1 to change Security Question #1.");
            System.out.println("Question: " + checkSQ.getQuestion(secQ_1));
            System.out.print("Answer: ");
            ans1 = scanner.nextLine().toUpperCase();
            if (ans1.equals("1")) {
                setSecurityQuestion1();
            }
        }
    }

    public void setSecurityQuestion2(){
        checkSQ = new CheckSecurityQA();
        System.out.println("\nSelect Security Question #2");
        for(int i = 5; i <= 8; i++)
            System.out.println("\t" + i + " - " + checkSQ.getQuestion(i));
       testQ = scanner.nextLine().charAt(0);

        if (!checkSQ.validSelection(2, testQ)) {
            System.out.println("Input is invalid. Try again.");
            setSecurityQuestion2();
        }
        else {
            secQ_2 = Character.getNumericValue(testQ);
            System.out.println("\nPress 1 to change Security Question #2.");
            System.out.println("Question: " + checkSQ.getQuestion(secQ_2));
            System.out.print("Answer: ");
            ans2 = scanner.nextLine().toUpperCase();
            if (ans2.equals("1")) {
                setSecurityQuestion2();
            }
        }
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

            account.println(secQ_1);
            account.println(ans1);

            account.println(secQ_2);
            account.print(ans2);

            account.close();
            System.out.println("\nWELCOME, " + name + "!\nYou have successfully created an account.\n");
        }
        catch(IOException e) {
            System.out.println("Something went wrong! Please try again.");
            System.exit(1);
        }
        //return to LOGIN PAGE
    }
}