package ro.info.iasi.fiipractic.service;

import org.junit.jupiter.api.*;
import ro.info.iasi.fiipractic.mocks.UserObjectAndMockBuilder;
import ro.info.iasi.fiipractic.model.User;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class UserServiceUnitTest {

    private static List<User> users;
    private UserServiceImpl unitToTest;

    @BeforeAll
    private static void setUpBeforeAll() {
        setUpUserData();
    }

    private static void setUpUserData() {
        User firstInitialUser = UserObjectAndMockBuilder.buildUser("Laura", "", "Popescu", "email@gmail.com", "password");
        User secondInitialUser = UserObjectAndMockBuilder.buildUser("Elena", "I.", "Ionescu", "emailElly@gmail.com", "pwd");
        users = new ArrayList<>();
        users.add(firstInitialUser);
        users.add(secondInitialUser);
    }

    @BeforeEach
    private void setUp() {
        unitToTest = new UserServiceImpl(null, null);
    }

    @DisplayName("register a user")
    @Test
    public void whenRegisterUserThenFailWithNPE() {
        //given
        User user = UserObjectAndMockBuilder.buildUser("New User", "E.", "Queen", "email@gmail.com", "pwd");

        //when
        assertThrows(NullPointerException.class, () -> unitToTest.registerUser(user));
    }

    //    @Disabled
    @DisplayName("get all users test")
    @Test
    public void whenGetAllUsersThenFailWithNPE() {
        //given
        List<User> actualList = null;

        // when
        try {
            actualList = unitToTest.getAllUsers();
        } catch (NullPointerException ex) {
            System.out.println("This fails because of null userRepository field");
        } finally {
            //then
            Assertions.assertNull(actualList);
        }
    }

}