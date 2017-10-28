package setup;

import java.io.*;

public class CheckPassword {

    public CheckPassword(){ }

    public boolean meetsRequirements(char[] testPswd){
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

    public boolean isCorrectPswd(String email, String pswdAttempt){
        try{
            BufferedReader inStream = new BufferedReader(new FileReader(System.getProperty("user.dir") + "/Accounts/" + email + "/accountInfo.txt"));
            String password = inStream.readLine(); //stored password
            inStream.close();
            return pswdAttempt.matches(password);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }
}
