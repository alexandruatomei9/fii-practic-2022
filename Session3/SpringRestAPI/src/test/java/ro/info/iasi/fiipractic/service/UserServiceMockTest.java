package ro.info.iasi.fiipractic.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ro.info.iasi.fiipractic.exception.UserNotFoundException;
import ro.info.iasi.fiipractic.mocks.UserObjectAndMockBuilder;
import ro.info.iasi.fiipractic.model.User;
import ro.info.iasi.fiipractic.repository.UserDAO;
import ro.info.iasi.fiipractic.util.UserUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UserServiceMockTest {

    private static final int EXISTENT_ID = 11;
    private static final String FIRST_NAME = "firstName";
    private static final String OVER_WRITTEN_FIRST_NAME_VALUE = "overWrittenFirstName";
    private static final String FIRST_NAME_VALUE = "FIRST_NAME";
    private static final String MIDDLE_NAME_VALUE = "I.";
    private static final String LAST_NAME_VALUE = "LAST_NAME";
    private static final String EMAIL_VALUE = "email@gmail.com";
    private static final String PASSWORD_VALUE = "pass";
    private static List<User> users;

    // I don't need the actual userRepository instance. I'll just use a mock for it
    @Mock
    private UserDAO userRepository;

    @Mock
    private UserUtil userUtil;

    @InjectMocks
    private UserServiceImpl unitToTest;

    @BeforeAll
    private static void setUpBeforeAll() {
        setUpMockitoData();
    }

    private static void setUpMockitoData() {
        User firstInitialUser = UserObjectAndMockBuilder.buildUser(FIRST_NAME_VALUE, "I", LAST_NAME_VALUE, EMAIL_VALUE, PASSWORD_VALUE);
        User secondInitialUser = UserObjectAndMockBuilder.buildUser(FIRST_NAME_VALUE, "E", LAST_NAME_VALUE, EMAIL_VALUE, PASSWORD_VALUE);
        users = new ArrayList<>();
        users.add(firstInitialUser);
        users.add(secondInitialUser);
    }

    @DisplayName("get all users test")
    @Test
    public void getAllUsers() {
        //given
        /*  I don't need to know what userRepository is actually doing. I'll just tell it what to do
        stubbing the userRepository.getAllUsers() method in order to define behavior.
        * Notice the wrap of the method userRepository.getAllUsers() and not just of the userRepository instance */
        when(userRepository.getAllUsers()).thenReturn(users);

        // when
        List<User> actualList = unitToTest.getAllUsers();

        //then
        Assertions.assertNotNull(actualList);
        Assertions.assertEquals(users.size(), actualList.size());
        for (int i = 0; i < users.size(); i++) {
            Assertions.assertEquals(users.get(i), actualList.get(i));
        }
    }

    @DisplayName("register a user")
    @Test
    public void registerUser() {
        //given
//        User user = UserObjectAndMockBuilder.buildUser(FIRST_NAME_VALUE, "", LAST_NAME_VALUE, EMAIL_VALUE, PASSWORD_VALUE);
//        User user = UserObjectAndMockBuilder.mockUser(FIRST_NAME_VALUE, "", LAST_NAME_VALUE, EMAIL_VALUE, PASSWORD_VALUE);
        User user = mock(User.class);

        //when
        unitToTest.registerUser(user);

        //then
//        notice the anyString() argumentMatcher
//        verify(userRepository, Mockito.times(1)).createUser(FIRST_NAME_VALUE, eq(LAST_NAME_VALUE), EMAIL_VALUE, PASSWORD_VALUE);
//        verify(userRepository, Mockito.times(1)).createUser(FIRST_NAME_VALUE, LAST_NAME_VALUE, EMAIL_VALUE, PASSWORD_VALUE);
//        verify(userRepository).createUser(eq(FIRST_NAME_VALUE), eq(LAST_NAME_VALUE), eq(EMAIL_VALUE), eq(PASSWORD_VALUE));
        verify(userRepository).createUser(isNull(), isNull(), isNull(), isNull());
    }

    @DisplayName("get non-existent user by id and throw UserNotFoundException")
    @Test
    public void getUserByNonExistentIdThrowsUserNotFoundException() {
        //given
        when(userRepository.getUserById(anyInt())).thenThrow(UserNotFoundException.class);

        //when & then
        assertThrows(UserNotFoundException.class, () -> unitToTest.getUserById(anyInt()));
    }

    @DisplayName("get existent user by id using argument capture")
    @Test
    public void getUserByExistentIdCapturesIdValue() {
        //given
        // notice we're not calling buildUser or mockUser for this user. We just want a mock with default null fields on it
        User user = mock(User.class);
        ArgumentCaptor<Integer> valueCapture = ArgumentCaptor.forClass(Integer.class);
        when(userRepository.getUserById(valueCapture.capture())).thenReturn(user);

        //when
        unitToTest.getUserById(EXISTENT_ID);

        //then
        assertEquals(EXISTENT_ID, valueCapture.getValue());
        verify(userRepository).getUserById(EXISTENT_ID);
        //maybe put more asserts here
    }

    @DisplayName("patch user throws RuntimeException")
    @Test
    public void patchUserThrowsRuntimeExceptionWhenPartialUserIsNull() {
        //given
        User user = mock(User.class);

        //when
        when(userRepository.getUserById(anyInt())).thenReturn(user);

        // Notice that no wrap is possible inside Mockito.when for void methods
//        when(unitToTest.patchUser(anyInt(), null)).thenThrow(RuntimeException.class);
//        we'll use Mockito.doThrow().when() stub with only the unitToTest instance wrapped inside the Mockito.when() method
        doThrow(new NullPointerException()).when(userUtil).patchUser(user, null);

        //when & then
        try {
            unitToTest.patchUser(anyInt(), null);
        } catch (RuntimeException ex) {
            assertInstanceOf(NullPointerException.class, ex);
            verify(userRepository).getUserById(anyInt());
            verify(userRepository, times(0)).updateUser(anyString(), anyString(), anyString(), anyString(), anyInt());
        }
    }

    @DisplayName("patch user with userUtil as mock")
    @Test
    public void patchUserSuccessfullyWithMock() {
        //given
        HashMap<String, String> partialUser = new HashMap<>();
        partialUser.put(FIRST_NAME, OVER_WRITTEN_FIRST_NAME_VALUE);

        User user = mock(User.class);

        when(userRepository.getUserById(anyInt())).thenReturn(user);
        // specify a behavior for a void method with the doAnswer().when() syntax
        doAnswer(invocation -> {
            when(user.getFirstName()).thenReturn(partialUser.get(FIRST_NAME));
            return null;
        }).when(userUtil).patchUser(user, partialUser);

        //when
        unitToTest.patchUser(EXISTENT_ID, partialUser);

        //then
        assertEquals(OVER_WRITTEN_FIRST_NAME_VALUE, user.getFirstName());
        verify(userRepository).updateUser(eq(OVER_WRITTEN_FIRST_NAME_VALUE), isNull(), isNull(), isNull(), eq(EXISTENT_ID));
    }

    @DisplayName("patch user with userUtil as a partial mock/spy")
    @Test
    public void patchUserSuccessfullyWithPartialMock() {
        //given
        HashMap<String, String> partialUser = new HashMap<>();
        partialUser.put(FIRST_NAME, OVER_WRITTEN_FIRST_NAME_VALUE);

        User user = UserObjectAndMockBuilder.buildUser(FIRST_NAME, MIDDLE_NAME_VALUE, LAST_NAME_VALUE, EMAIL_VALUE, PASSWORD_VALUE);

        when(userRepository.getUserById(anyInt())).thenReturn(user);
        // no need for a behavior specification, since we're working directly with a partial mock
//        doAnswer(invocation -> {
//            when(user.getFirstName()).thenReturn(partialUser.get(FIRST_NAME));
//            return user;
//        }).when(userUtil).patchUser(partialUser, user);
        // instead, instruct the userUtil mock to do the actual method call
        doCallRealMethod().when(userUtil).patchUser(user, partialUser);

        //when
        unitToTest.patchUser(EXISTENT_ID, partialUser);

        //then
        assertEquals(OVER_WRITTEN_FIRST_NAME_VALUE, user.getFirstName());
        verify(userRepository).updateUser(eq(OVER_WRITTEN_FIRST_NAME_VALUE), eq(LAST_NAME_VALUE), eq(EMAIL_VALUE), eq(PASSWORD_VALUE), eq(EXISTENT_ID));
    }

}