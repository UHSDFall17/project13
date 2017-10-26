package setup;

import java.io.File;

public class CheckEmail {

    public CheckEmail(){}

    public boolean goodEmail(String testEmail){
        /** Check for ONE '@' **/
        String[] parts = testEmail.split("@");
        if(parts[0].isEmpty() || parts.length != 2) // no name OR more or less than one "@"
            return false;

        /** Check for VALID extensions **/
        String[] subParts = parts[1].split("\\.");
        if(subParts[0].isEmpty() || subParts.length < 2) //no "." in domain-extension field
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

    public boolean isRegistered(String email){
        if(new File(System.getProperty("user.dir") + "/Accounts/" + email).exists())
            return true;
        return false;
    }

}
