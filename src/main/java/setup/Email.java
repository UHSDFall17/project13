package setup;

import Utilities.Stream;

public class Email {
    private String inputEmail = "";

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
}
