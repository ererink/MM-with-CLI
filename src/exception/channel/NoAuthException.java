package exception.channel;

public class NoAuthException extends Throwable {
    String message = "접근 권한이 없습니다!";

    public NoAuthException() {

    }

    public NoAuthException(String msg) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }

}
