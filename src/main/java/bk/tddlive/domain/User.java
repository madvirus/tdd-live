package bk.tddlive.domain;

public class User {
    private String id;
    private String password;

    public User(String id, String password) {
        this.id = id;
        this.password = password;
    }

    public boolean matchPassword(String password) {
        return this.password.equals(password);
    }

    public String getId() {
        return id;
    }
}
