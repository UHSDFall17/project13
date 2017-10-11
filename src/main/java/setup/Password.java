package setup;

import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class Password {
    private Scanner scanner = new Scanner(System.in);

    private char[] testPswd;
    private String pswd;

    public Password(){
        setPswd();
        pswd = getPswd();
    }

    public Password(String email, String oldPswd, String newPswd){ //Procedure: resetting password
        updatePswd(email, oldPswd, newPswd);
    }

    public void setPswd() {
        System.out.println("\nPassword Requirements: " +
                "\n \t(1) 6-20 characters " +
                "\n \t(2) at least 1 upper-case letter " +
                "\n \t(3) at least 1 digit " +
                "\n \t(4) at least 1 special character");
        System.out.print("Password: ");

        testPswd = (scanner.nextLine()).toCharArray();

        if(!goodPswd()) { //input does NOT meet all criteria
            System.out.print("Invalid Password.\n\n");
            setPswd();
        }
        else {
            confirmPswd();
        }
    }

    public boolean goodPswd(){
        if(testPswd.length >= 6 && testPswd.length <= 20) //Criterion: MIN 6, MAX 20 characters
        {
            int countUpper = 0;    int countDigit = 0;   int countSpecial = 0;
            for(char c : testPswd){
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

    public void confirmPswd() {
        System.out.println("Enter 1 to change your password.");
        System.out.print("Confirm Password: ");
        String input = scanner.nextLine();
        if(input.equals("1")){ //Reset password process
            setPswd();
        }
        else if(!(input.equals(String.valueOf(testPswd)))) { //does not match
            System.out.println("\n\nPassword does not match. Try again.");
            confirmPswd();
        }
        else{}
    }

    public String getPswd(){
        return String.valueOf(testPswd);
    }

    public void updatePswd(String email, String oldPswd, String newPswd){
        try {
            BufferedReader inStream = new BufferedReader(new FileReader(System.getProperty("user.dir") + "/Accounts/" + email + "/accountInfo.txt"));
            StringBuffer getLine = new StringBuffer();
            String oldline;

            /* GRAB LINES */
            while((oldline = inStream.readLine()) != null){
                getLine.append(oldline);
                getLine.append("\n");
            }
            String line = getLine.toString();
            inStream.close();

            /* REPLACE OLD PSWD WITH NEW PSWD */
            line = line.replace(oldPswd, newPswd);
            FileOutputStream outStream = new FileOutputStream(System.getProperty("user.dir") + "/Accounts/" + email + "/accountInfo.txt");
            outStream.write(line.getBytes());
            outStream.close();

            System.out.println("\nSUCCESS! Your password has been updated.");//SUCCESS
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("\nFAILED: Your password has not been updated. Program Terminated.");
        }
    }
}
