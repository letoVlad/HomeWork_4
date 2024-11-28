package service;

import service.exception.AccountIsLockedException;
import service.exception.BadPinCodeException;

public interface PinValidator {
    boolean isValid() throws AccountIsLockedException;
}
