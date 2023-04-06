package exception.user;

public class UserUpdateFailureException extends Throwable {
    String msg = "유저를 수정할 수 없습니다..";
    public UserUpdateFailureException(String msg) {
        this.msg = msg;
    }
    public UserUpdateFailureException() {
    }
    @Override
    public String getMessage() {
        return msg;
    }
}

