package setup;
import java.io.*;
import java.util.*;
import java.lang.*;

//Dependency: createAccount class

public class forgotPswd extends createAccount{

    Scanner input = new Scanner(System.in);
    String email, sq1, sq2, ans1, ans2;
    BufferedReader account;

    /* CONSTRUCTOR*/
    public forgotPswd(){
        System.out.println("Reset Password");
        checkEmail();

        try {
            account = new BufferedReader(new FileReader(System.getProperty("user.dir") + "/Accounts/" + email + "/accountInfo.txt"));
            account.readLine(); //old password, not needed
            System.out.println("\nHI, " + account.readLine() + ".");

            sq1 = super.getSecQ(Integer.parseInt(account.readLine()));
            ans1 = account.readLine();
            doSQ1();

            sq2 = super.getSecQ(Integer.parseInt(account.readLine()));
            ans2 = account.readLine();
            doSQ2();

            resetPwsd();
            updatePswd();
            account.close();
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        catch (IOException e) {
            e.printStackTrace();
        }

        input.close(); //close Scanner object
    }

    public boolean emptyInput(String input){
        return input.equals("");
    }

/* ACCEPTS EMAIL INPUT */
/*CHECKS IF EMAIL IS REGISTERED*/
    //EMAIL IS NOT REGISTERED, USER MUST TRY AGAIN OR GOTO CREATE
    protected void checkEmail(){
        System.out.print("\nRegistered Email: ");
        email = input.nextLine().toLowerCase();
        if(emptyInput(email)){
            System.out.println("Error: Email required to reset password.");
            checkEmail();
        }
        else if(email.equals("1"))
            System.exit(1);
        else if(!(super.rgsdEmail(email))) {
            System.out.println("\nUH OH! This email is not registered. \nTry again, or press 1 to exit.");
            checkEmail();
        }
        else{}
    }

/* READ FROM FILE: SECURITY QUESTION #1 */
    //CONVERT NUMBER TO ACTUAL QUESTION (PULL FROM OPTIONS ARRAY FROM CREATEACCOUNT CLASS)
    // PRINT
/*ACCEPTS ANSWER INPUT*/
    //CHECKS THAT INPUT IS NOT NULL
/* READ FROM FILE: ANSWER #1 */
/*COMPARE INPUT WITH SAVED ANSWER*/
    //INCORRECT ANSWER - USER TRIES AGAIN OR QUITS
    protected void doSQ1(){
        System.out.println("\nSecurity Question #1:\n\t" + sq1);
        System.out.print("Answer: ");
        String inputAns = input.nextLine().toUpperCase();

        if(!inputAns.equals(ans1) || emptyInput(inputAns)) {//INCORRECT includes NO ANSWER PROVIDED
            System.out.println("\nIncorrect Answer. Please try again.");
            doSQ1();
        }
    }

/* READ FROM FILE: SECURITY QUESTION #2 */
    //CONVERT NUMBER TO ACTUAL QUESTION (PULL FROM OPTIONS ARRAY FROM CREATEACCOUNT CLASS)
    //PRINT
/* ACCEPTS ANSWER INPUT*/
    //CHECKS THAT INPUT IS NOT NULL
/*READ FROM FILE: ANSWER #2*/
/*COMPARE INPUT WITH SAVED ANSWER */
    //INCORRECT ANSWER - USER TRIES AGAIN OR QUITS
    protected void doSQ2(){

    }

/* PULL PASSWORD METHODS FROM CREATEACCOUNT */
    //RESET PASSWORD
    //VERIFY VALID PASSWORD (PULL METHODS FROM CREATEACCOUNT CLASS*/
        //TRY CREATE NEW PASSWORD AGAIN
    //CONFIRM PASSWORD
        //IF DOES NOT MATCH, TRY AGAIN OR RESET PASSWORD
    protected void resetPwsd(){

    }

/* UPDATE PASSWORD IN USER'S ACCOUNTINFO.TXT FILE */
    //SUCCESS MESSAGE
    //FAILURE MESSAGE
    protected void updatePswd(){

    }
}
