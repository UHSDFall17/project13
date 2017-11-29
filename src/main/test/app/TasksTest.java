package app;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TasksTest
{
    Tasks testTask;

    @Test
    public void Canary()
    {
        assertTrue(true);
    }

    @Test
    public void testGoodYear()
    {
        testTask = new Tasks();
        assertTrue(testTask.isValidYear(2017));
    }

    @Test
    public void testBadYear()
    {
        testTask = new Tasks();
        assertFalse(testTask.isValidYear(1000));
    }

    @Test
    public void testGoodMonth()
    {
        testTask = new Tasks();
        assertTrue(testTask.isValidMonth(12));
    }

    @Test
    public void testBadMonth()
    {
        testTask = new Tasks();
        assertFalse(testTask.isValidMonth(13));
    }

    @Test
    public void testGoodDay()
    {
        testTask = new Tasks();
        assertTrue(testTask.isValidDay(2017,12,25));
    }
    @Test
    public void testBadDay()
    {
        testTask = new Tasks();
        assertTrue(testTask.isValidDay(2017,12,31));
    }

    @Test
    public void testGoodTime()
    {
        testTask = new Tasks();
        assertTrue(testTask.isValidTime(00,00));
    }

    @Test
    public void testBadTime()
    {
        testTask = new Tasks();
        assertFalse(testTask.isValidTime(24,00));
    }

    @Test
    public void testGoodLeapYear()
    {
        testTask = new Tasks();
        assertTrue(testTask.isLeapYear(2016));
    }

    @Test
    public void testBadLeapYear()
    {
        testTask = new Tasks();
        assertFalse(testTask.isLeapYear(2017));
    }
}