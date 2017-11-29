package setup;

import org.junit.Test;

import static org.junit.Assert.*;

public class UserTest {
    private User user;

    @Test
    public void Canary(){ //verifies good env
        assertTrue(true);
    }

    @Test
    public void testGetUserEmail(){
        user = new User("userTest2@gmail.com", "password123!","USER'S NAME","0","1","MOM","5","HTX");

        assertEquals("userTest2@gmail.com", user.getUsername());
    }

    @Test
    public void testGetPassword(){
        user = new User("userTest2@gmail.com", "password123!","USER'S NAME","0","1","MOM","5","HTX");

        assertEquals("password123!", user.getPassword());
    }

    @Test
    public void testGetUsersName(){
        user = new User("userTest2@gmail.com", "password123!","USER'S NAME","0","1","MOM","5","HTX");

        assertEquals("USER'S NAME", user.getName());
    }

    @Test
    public void testGetCorporate(){
        user = new User("userTest2@gmail.com", "password123!","USER'S NAME","0","1","MOM","5","HTX");

        assertEquals("0", user.getCorporate());
    }

    @Test
    public void testSecurityQuestion1(){
        user = new User("userTest2@gmail.com", "password123!","USER'S NAME","0","1","MOM","5","HTX");

        assertEquals(1, user.getQuestion1());
    }

    @Test
    public void testSecurityQuestion1AsString(){
        user = new User("userTest2@gmail.com", "password123!","USER'S NAME","0","1","MOM","5","HTX");

        assertNotEquals("1", user.getQuestion1());
    }

    @Test
    public void testSecurityAnswer1(){
        user = new User("userTest2@gmail.com", "password123!","USER'S NAME","0","1","MOM","5","HTX");

        assertEquals("MOM", user.getAnswer1());
    }


    @Test
    public void testSecurityQuestion2(){
        user = new User("userTest2@gmail.com", "password123!","USER'S NAME","0","1","MOM","5","HTX");

        assertEquals(5, user.getQuestion2());
    }

    @Test
    public void testSecurityQuestion2AsString(){
        user = new User("userTest2@gmail.com", "password123!","USER'S NAME","0","1","MOM","5","HTX");

        assertNotEquals("5", user.getQuestion2());
    }

    @Test
    public void testSecurityAnswer2(){
        user = new User("userTest2@gmail.com", "password123!","USER'S NAME","0","1","MOM","5","HTX");

        assertEquals("HTX", user.getAnswer2());
    }

}
