package setup;
import java.io.*;
import java.util.*;
import java.lang.*;

public class ResetPswd{
    Scanner input = new Scanner(System.in);

    Account newPassword;
    CheckPassword checkPassword;

    private String email,savedSQ, savedAns, oldPswd;

    /* CONSTRUCTOR*/
    public ResetPswd(){
        System.out.println("Reset Password");
        checkEmail();

        /* READ */
        try {
            BufferedReader readAccount = new BufferedReader(new FileReader(System.getProperty("user.dir") + "/Accounts/" + email + "/accountInfo.txt"));
            oldPswd = readAccount.readLine(); //old password, not needed
            System.out.println("\nHI, " + readAccount.readLine() + ".");

            CheckSecurityQA verifyUser = new CheckSecurityQA();
            savedSQ = verifyUser.getQuestion(Integer.parseInt(readAccount.readLine())); //READ FROM FILE: SECURITY QUESTION #1, THEN CONVERT NUMBER TO ACTUAL QUESTION
            savedAns = readAccount.readLine(); //READ FROM FILE: ANSWER #1
            doSQ();

            savedSQ = verifyUser.getQuestion(Integer.parseInt(readAccount.readLine())); //READ FROM FILE: SECURITY QUESTION #2, THEN CONVERT NUMBER TO ACTUAL QUESTION
            savedAns = readAccount.readLine(); //READ FROM FILE: ANSWER #2
            doSQ();

            resetPswd(); //SET AND GET NEW PASSWORD
            readAccount.close(); //close BufferedReader
            input.close(); //close Scanner object
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void checkEmail(){
        System.out.print("\nRegistered Email: ");
        email = input.nextLine().toLowerCase(); //ACCEPTS EMAIL INPUT
        if(email.isEmpty()){ //CHECK FOR NULL INPUT
            System.out.println("Error: Email required to reset password.");
            checkEmail();
        }
        else if(email.matches("1")) //EXIT
            System.exit(0);
        else if(!(new File(System.getProperty("user.dir") + "/Accounts/" + email).exists())) { //EMAIL IS NOT REGISTERED - TRY AGAIN OR EXIT
            System.out.println("\nUH OH! This email is not registered. \nTry again, or press 1 to exit.");
            checkEmail();
        }
        else{}
    }

    public void doSQ(){
        System.out.println("\nSecurity Question #1:\n\t" + savedSQ); // PRINT SECURITY QUESTION
        System.out.print("Answer: ");
        String inputAns = input.nextLine().toUpperCase(); //ACCEPTS ANSWER INPUT

        if(!inputAns.matches(savedAns) || inputAns.isEmpty()) {//INCORRECT AND/OR NO ANSWER PROVIDED
            System.out.println("\nIncorrect Answer. Please try again.");
            doSQ();
        }
    }

    public void resetPswd(){
        System.out.println("\n\nSecurity Questions have been correctly answered.\nYou may now reset your password.");
        newPassword = new Account(email, oldPswd); //once user enters acceptable password, accountInfo.txt is updated
    }
}
