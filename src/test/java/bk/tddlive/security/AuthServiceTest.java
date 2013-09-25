package bk.tddlive.security;

import bk.tddlive.domain.User;
import bk.tddlive.domain.UserRepository;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.instanceOf;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class AuthServiceTest {

    public static final String USER_PASSWORD = "userpassword";
    public static final String USER_ID = "userid";
    private AuthService authService;

    @Before
    public void setUp() throws Exception {
        UserRepository mockUserRepository = mock(UserRepository.class);
        when(mockUserRepository.findById(USER_ID)).thenReturn(new User(USER_ID, USER_PASSWORD));

        authService = new AuthService(mockUserRepository);
    }

    private void runAuthAndAssertIllegalArgumentExThrown(String id, String password) {
        runAuthAndAssertEx(id, password, IllegalArgumentException.class);
    }

    private void runAuthAndAssertEx(String id, String password, Class<? extends RuntimeException> exceptionType) {
        Exception thrownEx = null;
        try {
            authService.authenticate(id, password);
            fail();
        } catch (Exception ex) {
            thrownEx = ex;
        }
        assertThat(thrownEx, instanceOf(exceptionType));
    }

    //    ○ ID 값이 비정상인 경우 (쉬운, 정상에서 벗어난)
    //    ○ PW 값이 비정상인 경우 (쉬운, 정상에서 벗어난)
    @Test
    public void whenInvalidId_throwEx() {
        runAuthAndAssertIllegalArgumentExThrown(null, USER_PASSWORD);
        runAuthAndAssertIllegalArgumentExThrown("", USER_PASSWORD);
        runAuthAndAssertIllegalArgumentExThrown(USER_ID, null);
        runAuthAndAssertIllegalArgumentExThrown(USER_ID, "");
    }

    //    ○ User가 존재하지 않는 경우 (정상에서 벗어난)
    @Test
    public void whenUserNotFound_throwEx() {
        runAuthAndAssertEx("nonUserId", USER_PASSWORD, NonExistingUserException.class);
    }

    //    ○ ID에 해당하는 User가 존재하는데, PW가 일치하지 않는 경우 (정상에서 벗어난)
    @Test
    public void whenUserFoundAndPwNotMatch_throwEx() throws Exception {
        runAuthAndAssertEx(USER_ID, "wrongPassword", WrongPasswordException.class);
    }

    //    ○ ID와 PW가 일치하는 경우 (정상) ■ 인증 정보를 리턴
    @Test
    public void whenUserFoundAndPwMatch_return() throws Exception {
        Authentication auth = authService.authenticate(USER_ID, USER_PASSWORD);
        assertThat(auth.getId(), equalTo(USER_ID));
    }

}
