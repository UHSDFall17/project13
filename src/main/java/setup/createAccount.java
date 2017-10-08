package setup;

import java.io.*;
import java.util.*;

public class createAccount {
    Scanner scanner = new Scanner(System.in);
    private String name, email,secA_1,secA_2;
    private char[] pswd;
    private char secQ_1, secQ_2;
    private static String[] options = {"What is your mother's maiden name?", "What is the name of the street that you lived on as a child?", "What was the make and model of your first car?", "What was the name of your first pet?", "In what city or town did your mother and father meet?", "What is the last name of your favorite childhood teacher?", "What is the first name of the person you first kissed?", "What elementary/ primary school did you go to?"}; //array of security questions offered

    public createAccount(){}

    public createAccount(String welcome) { //constructor
        clearScreen();

        System.out.println("Create an Account\n" + welcome);
        System.out.println();

        setName();
        System.out.println();

        setEmail();
        System.out.println();
        clearScreen();

        setPswd();
        System.out.println();
        clearScreen();

        setSecure1();
        System.out.println();
        clearScreen();

        setSecure2();
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
    protected void setName() {
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

    protected void setPswd() {
        System.out.println("Name: " + name);
        System.out.println("Email: " + email + "\n");
        System.out.print("\nPassword Requirements: \n \t(1) 6-20 characters \n \t(2) at least 1 upper-case letter \n \t(3) at least 1 digit \n \t(4) at least 1 special character\nNOTICE: Input will not be displayed but will still be recognized.\n");
        System.out.println("Password: ");

        pswd = (scanner.nextLine()).toCharArray();

        if(!goodPswd()) { //input does NOT meet all criteria
            clearScreen();
            System.out.print("Invalid Password.\n\n");
            setPswd();
        }
        else
            confirmPswd();
    }

    protected boolean goodPswd(){
        if(pswd.length >= 6 && pswd.length <= 20) //Criterion: MIN 6, MAX 20 characters
        {
            int countUpper = 0;    int countDigit = 0;   int countSpecial = 0;
            for(char c : pswd){
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

    protected void confirmPswd() {
        System.out.println("Enter 1 to change your password.");
        System.out.println("Confirm Password: ");
        String temp = scanner.nextLine();
        if(temp.equals("1")){ //Reset password process
            clearScreen();
            setPswd();
        }
        else if(!(temp.equals(String.valueOf(pswd)))) { //does not match
            System.out.println("\n\nPassword does not match. Try again.");
            confirmPswd();
        }
        else{}
    }

    /*******************************
     *      SECURITY QUESTIONS      *
     *******************************/

    protected void setSecure1() {
        /** Security Question #1 **/
        System.out.println("Select Security Question #1:");
        System.out.println("\t1. " + options[0]);
        System.out.println("\t2. " + options[1]);
        System.out.println("\t3. " + options[2]);
        System.out.println("\t4. " + options[3]);

        String temp = scanner.nextLine();
        if(temp.equals("") || temp.charAt(0) < 49 || temp.charAt(0) > 52){
            clearScreen();
            System.out.println("Invalid entry.\n");
            setSecure1();
        }
        else{
            secQ_1 = temp.charAt(0);
            System.out.println("\nPress ENTER to change Security Question #1.");
            System.out.println("Question: " + options[secQ_1 - '0' - 1]);
            System.out.print("Answer: ");
            secA_1 = (scanner.nextLine()).toUpperCase();
            if(secA_1.equals("")){
                clearScreen();
                setSecure1();
            }
        }
    }

    protected void setSecure2() {
        /** Security Question #2 **/
        System.out.println("\nSelect Security Question #2:");
        System.out.println("\t5. " + options[4]);
        System.out.println("\t6. " + options[5]);
        System.out.println("\t7. " + options[6]);
        System.out.println("\t8. " + options[7]);

        String temp = scanner.nextLine();
        if(temp.equals("") || temp.charAt(0) < 53 || temp.charAt(0) > 56){
            clearScreen();
            System.out.println("Invalid entry.");
            setSecure2();
        }
        else{
            secQ_2 = temp.charAt(0);
            System.out.println("\nPress ENTER to change Security Question #2.");
            System.out.println("Question: " + options[secQ_2 - '0' - 1]);
            System.out.print("Answer: ");
            secA_2 = (scanner.nextLine()).toUpperCase();
            if(secA_2.equals("")){
                clearScreen();
                setSecure2();
            }
        }
    }

    protected String getSecQ(int index){
        if(index > 0 && index < 9)
            return options[index - 1];
        else
            return "Error: Corrupted file. Security Question cannot be retrieved at this moment.";
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
            account.println(secA_1);
            account.println(secQ_2);
            account.print(secA_2);
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