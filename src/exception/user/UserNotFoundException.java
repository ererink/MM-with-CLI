package exception.user;

public class UserNotFoundException extends Throwable {
    String message = "유저가 존재하지 않습니다.";
    public UserNotFoundException(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public UserNotFoundException() {
    }
}
