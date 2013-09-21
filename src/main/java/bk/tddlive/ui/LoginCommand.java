package bk.tddlive.ui;

import org.springframework.util.StringUtils;

public class LoginCommand {
    private String id;
    private String password;

    public boolean validate() {
        return ! (StringUtils.isEmpty(id) || StringUtils.isEmpty(password));
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
