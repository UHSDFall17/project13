package setup;

import Utilities.Stream;

import java.io.File;

public class Email {
    private String inputEmail = "";
    private String corporate;

    Stream stream;

    protected String email;

    public Email(){
        stream = new Stream();
    }

    public String setAndGetNewEmail(){
        inputEmail = "";
        while(!goodEmail(inputEmail)|| isRegistered(inputEmail)) {
            String emailConfirmer = "1";
            while (emailConfirmer.equals("1")) {
                stream.writeToConsole("\nEmail: ");
                inputEmail = stream.readLineFromConsole().toLowerCase();

            /* CONFIRM EMAIL */
                while (!emailConfirmer.equals(inputEmail)) {
                    stream.writeToConsole("Press 1 to change initial input.\n");
                    stream.writeToConsole("Confirm Email: ");
                    emailConfirmer = stream.readLineFromConsole().toLowerCase();
                    if (emailConfirmer.equals("1"))
                        break;
                }
            }

            if(isRegistered(inputEmail)){ stream.writeToConsole("Invalid: Email entered is already registered.\n"); }
            else if(!goodEmail(inputEmail)){ stream.writeToConsole("Invalid: Email does not follow standard format."); }
            else{ return inputEmail; }
        }
        return null;
    }

    public boolean goodEmail(String testEmail){
        /** Email requires a username, at-symbol, domain, '.', and ext => MINIMUM OF 6 CHARACTERS **/
        if(testEmail.length() < 6)
            return false;

        /** Check for ONE '@' **/
        String[] parts = testEmail.split("@");
        if(parts[0].isEmpty() || parts.length != 2) // no name OR more or less than one "@"
            return false;

        /** Check for VALID extensions **/
        String[] subParts = parts[1].split("\\.");
        if(subParts.length < 2 || subParts[0].isEmpty() || subParts[subParts.length-1].isEmpty()) //no "." , or no domain, or no extension
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

    public String getAttemptLogInEmail(){
        stream.writeToConsole("User Email: ");
        return stream.readLineFromConsole().toLowerCase();
    }

    public String getCorporate(String email){
        /*PARSE EMAIL, GRAB DOMAIN*/
        String[] parts = email.split("@");
        String[] subParts = parts[1].split("\\.");
        //subParts[0] == email domain
        String[] nonCorpDomains = {"gmail","aol","yahoo","hotmail","outlook","comcast","msn","sbcglobal","version","roadrunner","optimum", "inbox","icloud","mail","zoho","yandex","live","passport","protonmail"};
        for(String e: nonCorpDomains){
            if(subParts[0].equals(e))
                return "0"; //NOT corporate
        }
        return "1"; //Corporate

        /* MORE THOROUGH BUT TOO SLOW*/
//        String line;
//        try(BufferedReader domains = new BufferedReader(new FileReader(System.getProperty("user.dir") + "/all_email_provider_domains.txt"))) {
//            line = domains.readLine();
//            while(line != null){
//                if(parts[1].equals(line)) //NOT CORPORATE USER
//                    return "0";
//            }
//            return "1"; //NO MATCHES -> CORPORATE USER
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
    }
}
