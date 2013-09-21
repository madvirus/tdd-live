package bk.tddlive;

import bk.tddlive.ui.LoginCommand;

public class Data {
    public static final String USER_ID = "userid";
    public static final String NO_USER_PW = "nouserpw";
    public static final String USER_PW = "userpw";

    public static LoginCommand createLoginCommand(String id, String password) {
        LoginCommand command = new LoginCommand();
        command.setId(id);
        command.setPassword(password);
        return command;
    }
}
