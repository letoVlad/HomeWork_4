package service.exception;

public class BadPinCodeException extends RuntimeException {
    public BadPinCodeException() {
        super("Пин-код неправильный. ");
    }
}
