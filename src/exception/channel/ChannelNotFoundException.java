package exception.channel;

public class ChannelNotFoundException extends Throwable {
    String message = "해당 채널이 발견되지 않았습니다!";
    public ChannelNotFoundException() {

    }

    public ChannelNotFoundException(String msg) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
