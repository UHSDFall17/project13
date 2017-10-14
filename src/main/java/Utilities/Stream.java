package Utilities;

import java.util.Scanner;
import static java.lang.System.*;

// class that handles input and output to user

public class Stream {

    private Scanner scan;

    public Stream()
    {
        scan = new Scanner(in);
    }

    public void WriteToConsole(String output)  //use when writing to console
    {
        out.print(output);
    }

    public String ReadLineFromConsole()  //use when reading single string line from user
    {
        return scan.nextLine();
    }

    public int ReadIntFromConsole()  //use when reading an integer from user
    {
        return scan.nextInt();
    }

    public double ReadDoubleFromConsole()  //use when reading a double from user
    {
        return scan.nextDouble();
    }
}
