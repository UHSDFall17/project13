package setup;

import java.util.Scanner;

public class Name {
    Scanner input = new Scanner(System.in);

    protected String name;

    public Name(){}

    public void setNewName(){
        do {
            System.out.print("Name: ");
            name = input.nextLine().toUpperCase();
        }while(name.isEmpty());
    }

    public void changeName(){
        email = something;
    }
}
