package bk.tddlive.security;

import bk.tddlive.domain.User;
import bk.tddlive.domain.UserRepository;

public class AuthService {
    private UserRepository userRepository;

    public AuthService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Authentication authenticate(String id, String password) {
        assertIdPw(id, password);
        User user = findUserOrThrowEx(id);
        throwExIfPasswordNotMatched(user, password);
        return new Authentication(user.getId());
    }

    private void assertIdPw(String id, String password) {
        if (id == null || id.isEmpty()) throw new IllegalArgumentException();
        if (password == null || password.isEmpty()) throw new IllegalArgumentException();
    }

    private User findUserOrThrowEx(String id) {
        User user = findUserById(id);
        if (user == null)
            throw new NonExistingUserException();
        return user;
    }

    private User findUserById(String id) {
        return userRepository.findById(id);
    }

    private void throwExIfPasswordNotMatched(User user, String password) {
        if (! user.matchPassword(password))
            throw new WrongPasswordException();
    }

}
