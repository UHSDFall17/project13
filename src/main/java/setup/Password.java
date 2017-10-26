package setup;
import java.io.*;
import java.util.*;
import java.lang.*;

public class Password {
    Scanner input = new Scanner(System.in);

    private String email,savedSQ, savedAns, oldPswd;
    private String inputOldPswd, inputAns;

    protected String newPswd;

    /* AVAILABLE FUNCTIONS:
    * setNewPassword + confirmNewPassword //CONNECTED, setNew always calls confirmNew
    * changePassword //user IS logged in
    * resetPassword //user has NOT logged in
    * */

    //CheckEmail checkEmail;
    CheckPassword checkPswd;
    WriteToFile write;
    //Account newPassword;

    /* CONSTRUCTOR*/
    public Password(){}

/**** CREATE NEW PASSWORD ****/
    public void setNewPassword(){
        System.out.println("\nPassword Requirements: " +
                "\n \t(1) 6-20 characters " +
                "\n \t(2) at least 1 upper-case letter " +
                "\n \t(3) at least 1 digit " +
                "\n \t(4) at least 1 special character");
        System.out.print("Password: ");
        newPswd = input.nextLine();
        char[] testPswd = newPswd.toCharArray();

        checkPswd = new CheckPassword();

        if(!checkPswd.meetsRequirements(testPswd)) {
            System.out.println("Input does not meet Password Requirements. Try Again.");
            setNewPassword();
        }
        else{
            confirmNewPswd();
        }
    }

    public void confirmNewPswd() {
        System.out.println("Enter 1 to change your password.");
        System.out.print("Confirm Password: ");
        String inputConfirm = input.nextLine();
        if (input.equals("1")) { //Redo password process
            setNewPassword();
        } else if (!(inputConfirm.equals(newPswd))) { //does not match
            System.out.println("\n\nPassword does not match. Try again.");
            confirmNewPswd();
        } else {
        }
    }

    public boolean isGoodLogInPassword(String email){
        System.out.print("Password: ");

        checkPswd = new CheckPassword();
        return checkPswd.isCorrectPswd(email, input.nextLine());
    }

/**** CHANGE ****/
    public void changePassword(){
        try {
            BufferedReader lastLogin = new BufferedReader(new FileReader(System.getProperty("user.dir") + "/Accounts/LastLogin.txt"));
            email = lastLogin.readLine();
            lastLogin.close();

            BufferedReader userFile = new BufferedReader(new FileReader(System.getProperty("user.dir") + "/Accounts/" + email + "accountInfo.txt"));
            oldPswd = userFile.readLine(); // grabs password
            userFile.close();

            //VERIFY - enter current password
            System.out.print("Current Password: ");

            if(!input.nextLine().matches(oldPswd)) { //INPUT NOT CURRENT PASSWORD
                System.out.println("Incorrect. Please enter your current password.");
                changePassword();
            }
            else { //INPUT MATCHES CURRENT PASSWORD
                write = new WriteToFile();
                write.updatePswd(email, oldPswd, newPswd);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

/**** RESET ****/
    public void resetPassword(){ //FROM OUTSIDE OF APPLICATION; HAS NOT LOGGED IN
        CheckEmail checkEmail = new CheckEmail();
        do{
            System.out.print("\nEnter your Registered Email: ");
            email = input.nextLine().toLowerCase();
        } while(email.isEmpty() || !checkEmail.isRegistered(email));

        /* READ */
        try {
            BufferedReader readAccount = new BufferedReader(new FileReader(System.getProperty("user.dir") + "/Accounts/" + email + "/accountInfo.txt"));
            oldPswd = readAccount.readLine(); //old password
            System.out.println("\nHI, " + readAccount.readLine() + ".");

            SecurityQuestions pullUserQuestions = new SecurityQuestions();
            savedSQ = pullUserQuestions.getQuestion(Integer.parseInt(readAccount.readLine())); //READ FROM FILE: SECURITY QUESTION #1, THEN CONVERT NUMBER TO ACTUAL QUESTION
            savedAns = readAccount.readLine(); //READ FROM FILE: ANSWER #1
            do{
                System.out.println("\nSecurity Question #1:\n\t" + savedSQ); // PRINT SECURITY QUESTION
                System.out.print("Answer: ");
                inputAns = input.nextLine().toUpperCase();
            }while(!inputAns.matches(savedAns));

            savedSQ = pullUserQuestions.getQuestion(Integer.parseInt(readAccount.readLine())); //READ FROM FILE: SECURITY QUESTION #2, THEN CONVERT NUMBER TO ACTUAL QUESTION
            savedAns = readAccount.readLine(); //READ FROM FILE: ANSWER #2
            do{
                System.out.println("\nSecurity Question #2:\n\t" + savedSQ); // PRINT SECURITY QUESTION
                System.out.print("Answer: ");
                inputAns = input.nextLine().toUpperCase();
            }while(!inputAns.matches(savedAns));

            setNewPassword();

            write = new WriteToFile();
            write.updatePswd(email, oldPswd, newPswd);

            readAccount.close(); //close BufferedReader
            input.close(); //close Scanner object
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
