package bk.tddlive.domain;

import org.junit.Before;
import org.junit.Test;

import java.util.Random;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertThat;

public class InMemoryUserRepositoryTest {

	private InMemoryUserRepository repository;
    private String[] randomIds;

    @Before
    public void setUp() {
        randomIds = generateRandomIds();
        String data = toData(randomIds);
        repository = new InMemoryUserRepository(data);
    }
	// 등록하지 않은 사용자 조회 시 null, 등록한 사용자 조회 됨
	@Test
	public void should_return_user_when_user_is_found() {
		assertThat(repository.findById("userA"), nullValue());
		for (String id : randomIds)
			assertUserExistsAndMatchPassword(id, "1234");
	}

	private String toData(String[] ids) {
		String result = "";
		for (String id : ids)
			result += id + "=1234\n";
		return result;
	}

	private String[] generateRandomIds() {
		String[] result = new String[50];
		Random random = new Random();
		for (int i = 0; i < result.length; i++)
			result[i] = "user" + (random.nextInt(100) + i * 100);
		return result;
	}

	private void assertUserExistsAndMatchPassword(String userId, String password) {
		User user = repository.findById(userId);
		assertThat("userid=" + userId, user, notNullValue());
		assertThat("userid=" + userId, user.getId(), equalTo(userId));
		assertThat("userid=" + userId, user.matchPassword(password), equalTo(true));
	}

}
