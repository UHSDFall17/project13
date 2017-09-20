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
    void setName(Console console) {
        System.out.println("Name: ");
        name = (console.readLine()).toUpperCase();
        if(!goodName(name)) {
            System.out.println("Error: Please enter your name.\n");
            setName(console);
        }
    }
    
    boolean goodName(String name){
      if(name.equals(""))
        return false;
      return true;
    }
    
}
