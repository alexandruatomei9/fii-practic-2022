package ro.info.iasi.fiipractic.mocks;

import ro.info.iasi.fiipractic.model.User;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class UserObjectAndMockBuilder {

    public static User buildUser(int id, String firstName, String middleName, String lastName, String email, String password) {
        User user = buildUser(firstName, middleName, lastName, email, password);
        user.setId(id);
        return user;
    }

    public static User buildUser(String firstName, String middleName, String lastName, String email, String password) {
        User user = new User();
        user.setFirstName(firstName);
        user.setMiddleName(middleName);
        user.setLastName(lastName);
        user.setEmail(email);
        user.setPassword(password);
        return user;
    }

    public static User mockUser(String firstName, String middleName, String lastName, String email, String password) {
        User user = mock(User.class);
        when(user.getFirstName()).thenReturn(firstName);
        when(user.getMiddleName()).thenReturn(middleName);
        when(user.getLastName()).thenReturn(lastName);
        when(user.getEmail()).thenReturn(email);
        when(user.getPassword()).thenReturn(password);
        return user;
    }

}
