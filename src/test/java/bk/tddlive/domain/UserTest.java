package bk.tddlive.domain;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

public class UserTest {
    @Test
    public void matchPassword() {
        String password = "1234";
        User user = new User("userid", password);
        assertThat(user.matchPassword(password), equalTo(true));
        assertThat(user.matchPassword("12345"), equalTo(false));
        assertThat(user.matchPassword(null), equalTo(false));
        assertThat(user.matchPassword(""), equalTo(false));
    }
}
