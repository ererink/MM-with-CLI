package exception.common;

public class InValidInputException extends Throwable{
    String msg = "유효하지 않은 입력입니다.";
    public InValidInputException(String msg) {
        this.msg = msg;
    }
    public InValidInputException() {
    }
    @Override
    public String getMessage() {
        return msg;
    }
}