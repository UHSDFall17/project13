import dashboard.Dashboard;
import login.Login;

import java.io.*;


public class anydo
{
    private String lastLogin;
    private Dashboard dashboard;
    private createAccount create;
    private Login login;

    public anydo()
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
