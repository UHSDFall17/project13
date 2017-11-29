package Utilities;

import org.junit.Test;

import static org.junit.Assert.*;

public class CommandsTest {
    private Commands commands;

    @Test
    public void Canary(){ //verifies good env
        assertTrue(true);
    }

    @Test
    public void testGetSizeOfCommandsOffered_Correct(){
        String[] testCommandsOffered = {"Command A", "Command B", "Command C"};
        commands = new Commands(testCommandsOffered);
        assertEquals(3, commands.size());
    }

    @Test
    public void testGetSizeOfCommandsOffered_Wrong(){
        String[] testCommandsOffered = {"Command A, Command B, Command C"};
        commands = new Commands(testCommandsOffered);
        assertNotEquals(4, commands.size());
    }

}
