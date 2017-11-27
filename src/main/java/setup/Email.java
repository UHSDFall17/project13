package setup;

import Utilities.Stream;

public class Email {
    private String inputEmail = "";
    private String corporate;

    Stream stream;
    CheckEmail checkEmail;

    protected String email;

    public Email(){
        stream = new Stream();
    }

    public String setAndGetNewEmail(){
        String emailConfirmer = "1";
        while(emailConfirmer.equals("1")) {
            stream.writeToConsole("\nEmail: ");
            inputEmail = stream.readLineFromConsole().toLowerCase();

            /* CONFIRM EMAIL */
            while(!emailConfirmer.equals(inputEmail)) {
                stream.writeToConsole("Press 1 to change initial input.\n");
                stream.writeToConsole("Confirm Email: ");
                emailConfirmer = stream.readLineFromConsole().toLowerCase();
                if(emailConfirmer.equals("1"))
                    break;
            }
        }

        checkEmail = new CheckEmail();
        if(!checkEmail.goodEmail(inputEmail)|| checkEmail.isRegistered(inputEmail)){
            stream.writeToConsole("Invalid: Email entered is already registered or does not follow standard formatting rules.\n");
            return setAndGetNewEmail();
        }
        else{
           return inputEmail;
        }
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
            else
                return "1"; //corporate
        }
        return "0"; //not corporate
    }
}
