import java.util.*;
import java.io.*;

public class createAccount {
    private String name, email, secQ_1,secQ_2,secA_1,secA_2;
    private char[] pswd;
    
    /*******************************
    *           WELCOME            *
    *******************************/ 
    protected void welcome() {
        System.out.println("Welcome!\nCREATE AN ACCOUNT");
        //System.out.println("If you find out that you are in the wrong place, enter "Log in" at anytime.");
    }
    
    /*******************************
    *           NAME               *
    *******************************/    
    protected void setName(Console console) {
        System.out.println("Name: ");
        name = (console.readLine()).toUpperCase();
        if(!goodName(name)) {
            System.out.println("Error: Please enter your name.\n");
            setName(console);
        }
    }
    
    protected boolean goodName(String name){
      if(name.equals(""))
        return false;
      return true;
    }
    
    /*******************************
    *           EMAIL              *
    *******************************/
    protected void setEmail(Console console) {
        System.out.println("Email: ");
        email = (console.readLine()).toLowerCase();
        
        /** CONFIRM **/
        confirmEmail(email, console);
        

        if(!goodEmail(email)){ //checks for '@' and valid extension
            System.out.println("\n\nError: Invalid Email Format.");
            setEmail(console);
        }
        else if(rgsdEmail(email)) { //email had already been registered
            System.out.println("\n\nError: Email Already Registered. Log in or try again.");
            setEmail(console);
        }
        else {} //good, unregistered email
    }
    
    protected void confirmEmail(String email, Console console){
        System.out.println("Confirm Email: " + email + "(Y/N)");
        String temp = (console.readLine()).toUpperCase();
        if(temp.equals("")){
            System.out.println("Please enter Y or N.");
            confirmEmail(email, console);
        } 
        else if(temp.equals("N")){
      		//CLEAR SCREEN
      		System.out.println("Name: " + name + "\n");
      		setEmail(console);
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
        if(new File("Accounts/" + email + ".txt").exists())
          return true;
        return false;          
    }
}
