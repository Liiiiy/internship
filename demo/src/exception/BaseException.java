package exception;

public class BaseException extends RuntimeException {

    private Integer code;

    public BaseException(Integer code, String msg) {

        super(msg);
        this.code = code;
    }

    public BaseException(Integer code, String msg, Exception ex) {
        super(msg, ex);
        this.code = code;
    }

    public BaseException(Integer code, Exception ex) {
        super(ex);
        this.code = code;
    }
}
