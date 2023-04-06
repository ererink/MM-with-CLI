package exception.user;

public class UserDeleteFailureException extends Throwable {
    String msg = "삭제할 수 없습니다.";
    public UserDeleteFailureException() {
    }

    public UserDeleteFailureException(String msg) {
        this.msg = msg;
    }
}
