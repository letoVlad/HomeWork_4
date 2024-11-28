package service.exception;

public class AccountIsLockedException extends Exception {

    public AccountIsLockedException(Long secondsLeft) {
        super("Аккаунт заблокирован. Попробуйте еще раз через:  " + secondsLeft + " секунд.");
    }

}
