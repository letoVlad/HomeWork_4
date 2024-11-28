package service.exception;

public class InsufficientFundsException extends Exception {
    public InsufficientFundsException() {
        super("Недостаточно средств на счету.");
    }
}
