package bk.tddlive.ui;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class LoginCommandTest {
    @Test
    public void validate() {
        String userId = "userid";
        String password = "1234";

        assertThat(createLoginCommand("", password).validate(), is(false));
        assertThat(createLoginCommand(null, password).validate(), is(false));
        assertThat(createLoginCommand(userId, "").validate(), is(false));
        assertThat(createLoginCommand(userId, null).validate(), is(false));
        assertThat(createLoginCommand(userId, password).validate(), is(true));
    }

    private LoginCommand createLoginCommand(String id, String password) {
        LoginCommand loginCommand = new LoginCommand();
        loginCommand.setId(id);
        loginCommand.setPassword(password);
        return loginCommand;
    }
}
