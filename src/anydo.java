import java.io.*;


public class anydo
{
    private String lastLogin;

    public anydo()
    {
        startUp();
    }

    public void startUp()
    {
        try {
            FileInputStream file = new FileInputStream(new File("/LastLogin/Defualt.json"));
        }
        catch(FileNotFoundException e)
        {

        }
    }
}
