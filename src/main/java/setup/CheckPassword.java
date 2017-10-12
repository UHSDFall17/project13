package setup;

import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;

public class CheckPassword {

    public CheckPassword(){ }


    public boolean goodPswd(char[] testPswd){
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
}
