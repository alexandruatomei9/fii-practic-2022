package ro.info.iasi.fiipractic.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import ro.info.iasi.fiipractic.mocks.UserObjectAndMockBuilder;
import ro.info.iasi.fiipractic.model.User;
import ro.info.iasi.fiipractic.repository.UserDAO;
import ro.info.iasi.fiipractic.util.UserUtil;

import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UserServiceSpyTest {

    private static final int EXISTENT_ID = 11;
    private static final String FIRST_NAME = "firstName";
    private static final String OVER_WRITTEN_FIRST_NAME_VALUE = "overWrittenFirstName";
    private static final String FIRST_NAME_VALUE = "FIRST_NAME";
    private static final String LAST_NAME_VALUE = "LAST_NAME";
    private static final String EMAIL_VALUE = "email@gmail.com";
    private static final String PASSWORD_VALUE = "pass";

    @Mock
    private UserDAO userRepository;

    @Spy
    private UserUtil userUtil;

    @InjectMocks
    private UserServiceImpl unitToTest;

    @DisplayName("patch user with userUtil as spy")
    @Test
    public void patchUserSuccessfullyWithSpy() {
        //given
        HashMap<String, String> partialUser = new HashMap<>();
        partialUser.put(FIRST_NAME, OVER_WRITTEN_FIRST_NAME_VALUE);
        // notice how user is an actual User instance here?
        // It has to be a real object in order for the codebase to actually overwrite its firstName field.
        // Otherwise, we'd have to stub the user.getFirstName() method
        User user = UserObjectAndMockBuilder.buildUser(EXISTENT_ID, FIRST_NAME_VALUE, "", LAST_NAME_VALUE, EMAIL_VALUE, PASSWORD_VALUE);

        when(userRepository.getUserById(anyInt())).thenReturn(user);
//        // no need for a behavior specification, since we're working directly with a partial mock
//        doAnswer(invocation -> {
//            User patchedUser = user;
//            patchedUser.setFirstName(partialUser.get(FIRST_NAME));
//            return patchedUser;
//        }).when(userUtil).patchUser(partialUser, user);

        //when
        unitToTest.patchUser(EXISTENT_ID, partialUser);

        //then
        assertEquals(OVER_WRITTEN_FIRST_NAME_VALUE, user.getFirstName());
        verify(userRepository).updateUser(eq(OVER_WRITTEN_FIRST_NAME_VALUE), eq(LAST_NAME_VALUE), eq(EMAIL_VALUE), eq(PASSWORD_VALUE), eq(EXISTENT_ID));
    }

}