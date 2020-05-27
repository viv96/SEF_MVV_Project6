package model;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserTest {

    private User user;

    @BeforeEach
    public void setUp() {
        user = new User("1", "amin", "azerty123",null);
    }

    @AfterEach
    public void tearDown() {}

    @Test
    public void testGetId() {
        System.out.println("-- Testing User ID --");
        assertEquals("1", user.getId());
    }

    @Test
    public void testGetName() {
        System.out.println("-- Testing User name --");
        assertEquals("amin", user.getName());
    }

    @Test
    public void testGetPassword() {
        System.out.println("-- Testing User password --");
        assertEquals("azerty123", user.getPassword());
    }

    @Test
    public void testSetId() {
        System.out.println("-- Testing User changing id --");
        user.setId("2");
        assertEquals("2", user.getId());
    }

    @Test
    public void testSetName() {
        System.out.println("-- Testing User changing name --");
        user.setName("max");
        assertEquals("max", user.getName());
    }

    @Test
    public void testSetPassword() {
        System.out.println("-- Testing User changing password --");
        user.setPassword("sefisgood");
        assertEquals("sefisgood", user.getPassword());
    }

}