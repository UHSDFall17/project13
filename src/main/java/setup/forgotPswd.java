package setup;
import java.io.*;
import java.util.*;
import java.lang.*;

//Dependency: createAccount class

public class forgotPswd extends createAccount{

    Scanner input = new Scanner(System.in);
    String email, oldPswd, sq1, sq2, ans1, ans2, newPswd;
    BufferedReader readAccount, replaceLine;
    FileOutputStream outStream;

    /* CONSTRUCTOR*/
    public forgotPswd(){
        System.out.println("Reset Password");
        checkEmail();

        /* READ */
        try {
            readAccount = new BufferedReader(new FileReader(System.getProperty("user.dir") + "/Accounts/" + email + "/accountInfo.txt"));
            oldPswd = readAccount.readLine(); //old password, not needed
            System.out.println("\nHI, " + readAccount.readLine() + ".");

            sq1 = super.getSecQ(Integer.parseInt(readAccount.readLine()));
            ans1 = readAccount.readLine();
            doSQ1();

            sq2 = super.getSecQ(Integer.parseInt(readAccount.readLine()));
            ans2 = readAccount.readLine();
            doSQ2();

            resetPswd();
            readAccount.close();
            input.close(); //close Scanner object
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        catch (IOException e) {
            e.printStackTrace();
        }

        /* WRITE */
        updatePswd();
    }

/* ACCEPTS EMAIL INPUT */
/*CHECKS IF EMAIL IS REGISTERED*/
    //EMAIL IS NOT REGISTERED, USER MUST TRY AGAIN OR GOTO CREATE
    protected void checkEmail(){
        System.out.print("\nRegistered Email: ");
        email = input.nextLine().toLowerCase();
        if(email.isEmpty()){
            System.out.println("Error: Email required to reset password.");
            checkEmail();
        }
        else if(email.matches("1"))
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

        if(!inputAns.matches(ans1) || inputAns.isEmpty()) {//INCORRECT includes NO ANSWER PROVIDED
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
        System.out.println("\nSecurity Question #2:\n\t" + sq2);
        System.out.print("Answer: ");
        String inputAns = input.nextLine().toUpperCase();

        if(!inputAns.matches(ans2) || inputAns.isEmpty()) {//INCORRECT includes NO ANSWER PROVIDED
            System.out.println("\nIncorrect Answer. Please try again.");
            doSQ2();
        }
    }

/* PULL PASSWORD METHODS FROM CREATEACCOUNT */
    //RESET PASSWORD
    //VERIFY VALID PASSWORD (PULL METHODS FROM CREATEACCOUNT CLASS*/
        //TRY CREATE NEW PASSWORD AGAIN
    //CONFIRM PASSWORD
        //IF DOES NOT MATCH, TRY AGAIN OR RESET PASSWORD
    protected void resetPswd(){
        System.out.println("\n\nCONGRATULATIONS,\nSecurity Questions have been correctly answered.\nYou may now reset your password.");
        newPswd = super.getPswd();
    }

/* UPDATE PASSWORD IN USER'S ACCOUNTINFO.TXT FILE */
    //SUCCESS MESSAGE
    //FAILURE MESSAGE
    protected void updatePswd(){
        try {
            BufferedReader replaceLine = new BufferedReader(new FileReader(System.getProperty("user.dir") + "/Accounts/" + email + "/accountInfo.txt"));
            StringBuffer getLine = new StringBuffer();
            String oldline;

            /* GRAB LINES */
            while((oldline = replaceLine.readLine()) != null){
                getLine.append(oldline);
                getLine.append("\n");
            }
            String line = getLine.toString();
            replaceLine.close();

            /* REPLACE OLD PSWD WITH NEW PSWD */
            line = line.replace(oldPswd, newPswd);
            FileOutputStream outStream = new FileOutputStream(System.getProperty("user.dir") + "/Accounts/" + email + "/accountInfo.txt");
            outStream.write(line.getBytes());
            outStream.close();

            System.out.println("\nSUCCESS! Your password has been updated.\nPlease try to log in again. Thank you.");//SUCCESS
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("\nFAILED: Something went wrong!\nYour password has not been updated. Program Terminated.");
        }
    }
}
