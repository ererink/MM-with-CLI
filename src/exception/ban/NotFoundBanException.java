package exception.ban;

public class NotFoundBanException extends Throwable{

    String message = "반을 찾을 수 없습니다.";
    public NotFoundBanException() {
    }

    public NotFoundBanException(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }


}
