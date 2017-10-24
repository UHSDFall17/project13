package setup;

import java.util.Scanner;

public class Email {
    private String inputEmail;

    Scanner input = new Scanner(System.in);
    CheckEmail checkEmail;

    protected String email;

    public Email(){}

    public void setNewEmail(){
        System.out.print("Email: ");
        inputEmail = input.nextLine().toLowerCase();

        if(!checkEmail.goodEmail(inputEmail) || checkEmail.isRegistered(inputEmail)){
            System.out.println("Invalid: Email entered is already registered or does not follow standard formatting rules.");
            setNewEmail();
        }
        else{
            email = inputEmail;
        }
    }
}
