package jpabook.jpashop.exception;

public class NotEnouthStockException extends RuntimeException {

    //오버라이드 해준다 code부분에 있음 ... exception 만들면 무조건 오버라이드 해주기!! 
    public NotEnouthStockException() {
        super();
    }

    public NotEnouthStockException(String message) {
        super(message);
    }

    public NotEnouthStockException(String message, Throwable cause) {
        super(message, cause);
    }

    public NotEnouthStockException(Throwable cause) {
        super(cause);
    }

    protected NotEnouthStockException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
