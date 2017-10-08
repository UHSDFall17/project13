package setup;
import java.util.*;

//Dependency: createAccount class

public class forgotPswd extends createAccount{

    Scanner input = new Scanner(System.in);

/* CONSTRUCTOR*/
    public forgotPswd(){
        checkEmail();
        checkSQ1();
        checkSQ2();
        resetPwsd();
        updatePswd();

        input.close(); //close Scanner object
    }

/* ACCEPTS EMAIL INPUT */
/*CHECKS IF EMAIL IS REGISTERED*/
    //EMAIL IS NOT REGISTERED, USER MUST TRY AGAIN OR GOTO CREATE
    protected void checkEmail(){
        System.out.print("Registered Email: ");
        String temp = (input.nextLine()).toLowerCase();
        System.out.println(temp);
        if(temp.equals("1"))
            System.exit(1);
        else if(!(super.rgsdEmail(temp))) {
            System.out.println("\n\nThis email is not registered. \nTry again, or Press 1 to go to Create an Account.");
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
    protected void checkSQ1(){

    }

/* READ FROM FILE: SECURITY QUESTION #2 */
    //CONVERT NUMBER TO ACTUAL QUESTION (PULL FROM OPTIONS ARRAY FROM CREATEACCOUNT CLASS)
    //PRINT
/* ACCEPTS ANSWER INPUT*/
    //CHECKS THAT INPUT IS NOT NULL
/*READ FROM FILE: ANSWER #2*/
/*COMPARE INPUT WITH SAVED ANSWER */
    //INCORRECT ANSWER - USER TRIES AGAIN OR QUITS
    protected void checkSQ2(){

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
