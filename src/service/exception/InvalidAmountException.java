package service.exception;

public class InvalidAmountException extends Exception {
    public InvalidAmountException() {
        super("Сумма должна быть кратна 100.");
    }
}
