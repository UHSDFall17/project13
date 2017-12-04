package Utilities;
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

public class StreamTest {
    Stream stream;

    @Before
    public void initialize()
    {
        stream = new Stream();
    }

    @Test
    public void Canary(){ //verifies good env
        assertTrue(true);
    }

    @Test
    public void writeToConsoleTest()
    {
        assertTrue(stream.writeToConsole("Test"));
    }
}
