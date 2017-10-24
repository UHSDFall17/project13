package setup;

import java.util.Scanner;

public class Name {
    Scanner input = new Scanner(System.in);

    private String userEmail, userPassword; //FOR verifying user
    protected String name;

    public Name(){}

    public void setNewName(){
        do {
            System.out.print("Name: ");
            name = input.nextLine().toUpperCase();
        }while(name.isEmpty());
    }

    public void changeName(){
        userEmail = something;
        userPassword = something;
        String userName = something;

        System.out.print("Log-In Password: ");
        String inputPswd = input.nextLine();

        if(!inputPswd.matches(userPassword)){
            System.out.println("Incorrect Password.");
            changeName();
        }
        else{
            setNewName();

            /* REPLACE AND UPDATE IN FILE */
            WriteToFile updateName = new WriteToFile();
            updateName.updateName(userEmail, userName, name);
        }
    }
}
