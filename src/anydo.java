import dashboard.Dashboard;

import java.io.*;


public class anydo
{
    private String lastLogin;
    private Dashboard dashboard;

    public anydo()
    {
        startUp();
    }

    public void startUp()
    {
        try(BufferedReader fileReader = new BufferedReader(new FileReader("LastLogin.txt"))) {

            String fileName = fileReader.readLine();

            dashboard = new Dashboard(fileName);
        }
        catch(IOException e)
        {

        }

    }
}
