package exception.user;

import java.sql.SQLException;

public class UserJoinFailureException extends Throwable {

    String msg = "가입할 수 없습니다.";
    public UserJoinFailureException() {
    }

    public UserJoinFailureException(String msg) {
        this.msg = msg;
    }
    @Override
    public String getMessage() {
        return msg;
    }
}
