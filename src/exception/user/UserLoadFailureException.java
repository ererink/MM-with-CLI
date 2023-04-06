package exception.user;

import java.sql.SQLException;

public class UserLoadFailureException extends Throwable {
    String msg = "조회할 수 없습니다.";

    public UserLoadFailureException() {
    }

    public UserLoadFailureException(String msg) {
        this.msg = msg;
    }

    @Override
    public String getMessage() {
        return msg;
    }
}
