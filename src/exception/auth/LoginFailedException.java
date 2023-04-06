package exception.auth;

import java.sql.SQLException;

public class LoginFailedException extends Throwable {
    String message = "로그인에 실패했습니다.";
    public LoginFailedException(SQLException e) {
    }

    public LoginFailedException(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
