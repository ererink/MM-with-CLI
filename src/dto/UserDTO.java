package dto;

/**
 * <유저 정보>
 * 유저 id, 유저 pw, 유저 이름, 유저 역할(회원 or 관리자), 채팅 내용, 관리자
 */
public class UserDTO {
    private String user_id;
    private String user_pw;
    private String name;
    private ROLE role;
    private int class_id;

    public String getUser_id() {
        return user_id;
    }

    public String getUser_pw() {
        return user_pw;
    }

    public String getName() {
        return name;
    }

    public ROLE getRole() {
        return role;
    }

    public int getClass_id() {
        return class_id;
    }

    public UserDTO(String user_id, String user_pw, String name, ROLE role, int class_id) {
        this.user_id = user_id;
        this.user_pw = user_pw;
        this.name = name;
        this.role = role;
        this.class_id = class_id;
    }
    public UserDTO(String user_id, String user_pw, String name) {
        this.user_id = user_id;
        this.user_pw = user_pw;
        this.name = name;
        this.role = ROLE.N;
//        this.class_id = class_id;
    }

    @Override
    public String toString() {
        return "UserDTO{" +
                "user_id='" + user_id + '\'' +
                ", user_pw='" + user_pw + '\'' +
                ", name='" + name + '\'' +
                ", role=" + role +
                ", class_id=" + class_id +
                '}';
    }

}
