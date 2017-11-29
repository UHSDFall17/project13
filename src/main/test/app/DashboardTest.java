package app;

import static org.junit.Assert.*;

import Utilities.Stream;
import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;

public class DashboardTest
{
    Dashboard test;

    @Test
    public void Canary(){ //verifies good env
        assertTrue(true);
    }

    @Test
    public void testCommandCenter_Failure() throws IOException {
        test = new Dashboard();
        assertEquals(0, test.commandCenter(0, "Command A\nCommand B\n"));
    }

    @Test
    public void testCommandCenter_Case1_Success() throws IOException {
        test = new Dashboard();
        assertEquals(0, test.commandCenter(1, "Command A\nCommand B\n"));
    }

    @Test
    public void testCommandCenter_Case7_Success() throws IOException {
        test = new Dashboard();
        assertEquals(2, test.commandCenter(7, "Command A\nCommand B\n"));
    }

    @Test
    public void testCommandCenter_Case8_Success() throws IOException {
        test = new Dashboard();
        assertEquals(0, test.commandCenter(8, "Command A\nCommand B\n"));
    }

    @Test
    public void testCommandCenter_Case9_Success() throws IOException {
        test = new Dashboard();
        assertEquals(1, test.commandCenter(9, "Command A\nCommand B\n"));
    }

    @Test
    public void StoreNewListSuccess()
    {
        test = new Dashboard();
        assertTrue(test.storeNewList("Yes"));
    }

    @Test
    public void StoreNewListFailure()
    {
        test = new Dashboard();
        assertFalse(test.storeNewList(""));
    }

    @Test
    public void GetAllLists()
    {
        test = new Dashboard();
        assertEquals(test.displayLists(), "\nNo lists");
    }

    @Test
    public void GetAllListsNotEmpty()
    {
        test = new Dashboard();
        test.storeNewList("test list");

        assertNotEquals(test.displayLists(), "\nNo lists");
    }

    @Test
    public void DeleteList()
    {
        test = new Dashboard();
        test.storeNewList("test list");
        test.getLists().remove(0);

        assertEquals(test.displayLists(), "\nNo lists");
    }
}