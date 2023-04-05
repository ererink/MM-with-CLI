package auth;

import dto.ROLE;

public class UserAuth {
    private String user_id = null;
    private String user_name = null;
    private long class_id = -1;
    private ROLE role = null;

    private static UserAuth instance = new UserAuth();
    private UserAuth() {

    }
    public static UserAuth getInstance() {
        return instance;
    }

    public void login(String user_id, String user_name, long class_id, ROLE role) {
        this.user_id = user_id;
        this.user_name = user_name;
        this.class_id = class_id;
        this.role = role;
    }

    public void logout() {
        this.user_id = null;
        this.user_name = null;
        this.class_id = -1;
        this.role = null;
    }
}
