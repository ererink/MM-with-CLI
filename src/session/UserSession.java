package session;

import dto.ROLE;

public class UserSession {
    private String user_id = null;
    private String user_name = null;
    private String channel_name = null;

    public String getChannel_name() {
        return channel_name;
    }

    public void setChannel_name(String channel_name) {
        this.channel_name = channel_name;
    }

    public void setClass_id(long class_id) {
        this.class_id = class_id;
    }

    public void setChannel_id(long channel_id) {
        this.channel_id = channel_id;
    }

    public String getUser_id() {
        return user_id;
    }

    public String getUser_name() {
        return user_name;
    }

    public long getClass_id() {
        return class_id;
    }

    public long getChannel_id() {
        return channel_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public ROLE getRole() {
        return role;
    }

    public void setRole(ROLE role) {
        this.role = role;
    }

    private long class_id = -1;
    private long channel_id = -1;

    private ROLE role = null;

    private static UserSession instance = new UserSession();
    private UserSession() {

    }
    public static UserSession getInstance() {
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
