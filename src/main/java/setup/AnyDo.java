package setup;
import app.*;

import java.io.*;


public class AnyDo
{
    private String lastLogin;
    private Dashboard dashboard;
    private Account create;
    private Login login;

    public AnyDo()
    {
        startUp();
    }

    //handles startup of app, will handle whether to go to login screen or dashboard
    public void startUp()
    {
        try(BufferedReader fileReader = new BufferedReader(new FileReader("LastLogin.txt"))) {

            String fileName = fileReader.readLine();

            if(fileName.length() < 10) //checking if valid file name is found
                throw new IOException();

            dashboard = new Dashboard(fileName);
        }
        catch(IOException e)
        {
            //go to login screen here
        }

    }

    //NOT DONE...
}
