package exception.ban;

public class BanLoadFailedException extends Throwable {
    String msg = "반을 불러올 수 없습니다.";
    public BanLoadFailedException() {
    }

    public BanLoadFailedException(String msg) {
        this.msg = msg;
    }
}
