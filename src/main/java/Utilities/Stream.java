package Utilities;

import setup.FileOutstream;

import java.util.InputMismatchException;
import java.util.NoSuchElementException;
import java.util.Scanner;
import static java.lang.System.*;

// class that handles input and output to user

public class Stream {

    private Scanner scan;

    public Stream()
    {
        scan = new Scanner(in);
    }

    public boolean writeToConsole(String output)  //use when writing to console
    {
        out.print(output);
        return true;
    }

    public String readLineFromConsole()  //use when reading single string line from user
    {
        String input = "";

        try
        {
            input = scan.nextLine();
        }
        catch(Exception e)
        {
            input = "*~$";  //default error return, check for this when checking
        }


        return input;
    }

    public int readIntFromConsole()  //use when reading an integer from user
    {
        int input = 0;
        boolean cont = true;

        do
        {
            try {
                input = scan.nextInt();
                cont = false;
            }
            catch (Exception e) {
                writeToConsole("Invalid Input: " + '"' + scan.nextLine() + '"' + " is not an integer. Try again: ");
            }
        }while(cont);
        scan.nextLine();

        return input;
    }

    public double readDoubleFromConsole()  //use when reading a double from user
    {
        double input = 0;
        boolean cont = true;

        do {
            try {
                input = scan.nextDouble();
                cont = false;
            } catch (Exception e) {
                writeToConsole("Input is not a decimal number. Try again: ");
            }
        }while(cont);

        return input;
    }
}