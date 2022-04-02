package ro.info.iasi.fiipractic.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class UserIdGeneratorUnitTest {

    @Test/*(expected = NullPointerException.class) ~ JUnit4 way*/
    public void whenFirstNameIsNullThenThrowNPE() {
        //given
        UserIdGenerator userIdGenerator = new UserIdGeneratorImpl(false);

        //when & then
        assertThrows(NullPointerException.class, () -> userIdGenerator.generateUserId(null, "Popescu"));
        // assert same behavior for passing lastName with null value
//        assertThrows(NullPointerException.class, () -> userIdGenerator.generateUserId("Laura", null));
    }

    @DisplayName("generate userId without signature when signature is disabled")
    @Test
    public void whenSignatureIsDisabledThenUserIdIsReturnedWithoutSignature() {
        //given
        UserIdGenerator userIdGenerator = new UserIdGeneratorImpl(false);

        //when
        String actualUserId = userIdGenerator.generateUserId("Laura", "Popescu");

        //then
        assertEquals("LPopescu", actualUserId);
    }


    @DisplayName("generate userId with signature when signature is enabled")
    @Test
    public void whenSignatureIsEnabledThenUserIdIsReturnedWithSignature() {
        //given
        UserIdGenerator userIdGenerator = new UserIdGeneratorImpl(true);

        //when
        String actualUserId = userIdGenerator.generateUserId("Laura", "Popescu");

        //then
        assertEquals("uuid:LPopescu", actualUserId);
    }
}