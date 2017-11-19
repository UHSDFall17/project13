package setup;

import java.util.Scanner;

public class Email {
    private String inputEmail = "";

    Scanner input = new Scanner(System.in);
    CheckEmail checkEmail;

    protected String email;

    public Email(){}

    public String setAndGetNewEmail(){
        String emailConfirmer = "not confirmed";
        while(!emailConfirmer.equals(inputEmail)) {
            System.out.print("Email: ");
            inputEmail = input.nextLine().toLowerCase();

            /* CONFIRM EMAIL */
            System.out.print("Confirm Email: ");
            emailConfirmer = input.nextLine().toLowerCase();
        }

        checkEmail = new CheckEmail();
        if(!checkEmail.goodEmail(inputEmail)|| checkEmail.isRegistered(inputEmail)){
            System.out.println("Invalid: Email entered is already registered or does not follow standard formatting rules.");
            return setAndGetNewEmail();
        }
        else{
           return inputEmail;
        }
    }

    public String getAttemptLogInEmail(){
        System.out.print("Email: ");
            return input.nextLine().toLowerCase();
    }
}
