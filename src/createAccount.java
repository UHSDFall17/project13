import java.util.*;
import java.io.*;

public class createAccount {
    private String name, email,secA_1,secA_2;
    private char[] pswd;
    private char secQ_1, secQ_2;
    private static String[] options; //array of security questions offered  
    
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
        System.out.print("Name: ");
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
        System.out.print("Email: ");
        email = (console.readLine()).toLowerCase();
        
        /** CONFIRM **/
        confirmEmail(email, console);
        

        if(!goodEmail(email)){ //checks for '@' and valid extension
            System.out.println("\n\nError: Invalid Email Format.");
            setEmail(console);
        }
        else if(rgsdEmail(email)) { //email had already been registered
            System.out.println("\n\nError: Email Already Registered.\n Press ENTER to try again or '0' to log in.");
            if((console.readLine()).equals("0"))
                System.exit(1);
            setEmail(console);
        }
        else {} //good, unregistered email
    }
    
    protected void confirmEmail(String email, Console console){
        System.out.println("Confirm Email: " + email + " (Y/N)");
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
    
/*******************************
*           PASSWORD           *
*******************************/
    
    protected void setPswd(Console console) {
        System.out.print("\nPassword Requirements: \n \t(1) 6-20 characters \n \t(2) at least 1 upper-case letter \n \t(3) at least 1 digit \n \t(4) at least 1 special character\nNOTICE: Input will not be displayed but will still be recognized.\n");
        System.out.println("Password: ");
        pswd = console.readPassword();
        
        if(!goodPswd()) { //input does NOT meet all criteria
            System.out.print("Invalid Password.\n\n");
            /** CLEAR SCREEN **/
            setPswd(console); 
        }
        confirmPswd(console);
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
    
    protected void confirmPswd(Console console) {
        System.out.println("Enter 1 to change your password.");
        System.out.println("Confirm Password: ");
        String temp = String.valueOf(console.readPassword());
        if(temp.equals("1")) //Reset password process
            setPswd(console);  
        if(!(temp.equals(String.valueOf(pswd)))) { //does not match
            System.out.println("Password does not match. Try again.\n\n");
            confirmPswd(console);
        }
    }
    
    /*******************************
    *      SECURITY QUESTIONS      *
    *******************************/
    
    protected void setSecure1(Console console) {
        /** Security Question #1 OPTIONS **/
        options[0] = "What is your mother's maiden name?";
        options[1] = "What is the name of the street that you lived on as a child?";
        options[2] = "What was the make and model of your first car?";
        options[3] = "What was the name of your first pet?";

      	/** Security Question #1 **/
      	System.out.println("Select Security Question #1:");
      	System.out.println("\t1. " + options[0]);
      	System.out.println("\t2. " + options[1]);
      	System.out.println("\t3. " + options[2]);
      	System.out.println("\t4. " + options[3]);
       
        String temp = console.readLine();
        if(temp.equals("") || temp.charAt(0) < 49 || temp.charAt(0) > 52){
            //CLEAR SCREEN
            System.out.println("Invalid entry.");
            setSecure1(console);
        }
        else{
            secQ_1 = temp.charAt(0);
            System.out.println("\nPress ENTER to change Security Question #1.");            
            System.out.println("Question: " + options[secQ_1 - '0' - 1]);
            System.out.print("Answer: ");
       	    secA_1 = console.readLine();
            if(secA_1.equals("")){
              //CLEAR SCREEN
              setSecure1(console);
            }
        }      
    }
    
    protected void setSecure2(Console console) {
        /** Security Question #2 OPTIONS **/
        options[4] = "In what city or town did your mother and father meet?";
        options[5] = "What is the last name of your favorite childhood teacher?";
        options[6] = "What is the first name of the person you first kissed?";
        options[7] = "What elementary/ primary school did you go to?"; 
        
      	/** Security Question #2 **/
      	System.out.println("\nSelect Security Question #2:");
      	System.out.println("\t5. " + options[4]);       
      	System.out.println("\t6. " + options[5]);
      	System.out.println("\t7. " + options[6]);
      	System.out.println("\t8. " + options[7]);
        
        String temp = console.readLine();
        if(temp.equals("") || temp.charAt(0) < 53 || temp.charAt(0) > 56){
            //CLEAR SCREEN
            System.out.println("Invalid entry.");
            setSecure2(console);
        }
        else{
            secQ_2 = temp.charAt(0);
            System.out.println("\nPress ENTER to change Security Question #2.");
            System.out.println("Question: " + options[secQ_2 - '0' - 1]);
            System.out.print("Answer: ");
       	    secA_2 = console.readLine();
            if(secA_2.equals("")){
              //CLEAR SCREEN
              setSecure2(console);
            }
        }
    }
    
/*******************************
*         SAVE ACCOUNT         *
*******************************/
      	
    protected void saveAccount() {
      	try {
      		PrintWriter account = new PrintWriter("Accounts/" + email + ".txt");
      		account.println(pswd);
          account.println(name);
          account.println(secQ_1);
          account.println(secA_1);
          account.println(secQ_2);
          account.println(secA_2);
      		//account.println(key);
      		account.close();
      		//CLEAR SCREEN
      		System.out.println("WELCOME, " + name + "!\nYou have successfully created an account.\n");
      	}
      	catch(IOException e) {
      		System.out.println("Something went wrong! Please try again.");
          System.exit(1);
      	}
       //return to LOGIN PAGE 
    }

    
/*******************    
*   CONSTRUCTOR    *   
*******************/    
    createAccount() {
         options = new String[8];
         Console console = System.console();
         /** CLEAR SCREEN **/
         
         welcome();
         System.out.println();
         
         setName(console);
         System.out.println();
         
         setEmail(console);
         System.out.println();
         
         setPswd(console);
         System.out.println();
        
         setSecure1(console);
         System.out.println();
        
         setSecure2(console);
         System.out.println();
                  
          /**CLEAR SCREEN **/
         saveAccount();          
    }
}
