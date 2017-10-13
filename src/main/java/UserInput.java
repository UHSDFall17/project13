import java.util.Scanner;

public class UserInput {

    Scanner input = new Scanner(System.in);

    public UserInput(){}

    public String enterString(String identity){
        System.out.print(identity + ": ");
        return input.nextLine();
    }

    public int enterInteger(String identity){
        System.out.print(identity + ": ");
        return Integer.parseInt(input.nextLine());
    }

    public char enterChar(String identity){
        System.out.print(identity + ": ");
        return input.nextLine().charAt(0);
    }
}
