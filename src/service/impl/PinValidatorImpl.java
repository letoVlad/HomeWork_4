package service.impl;

import service.PinValidator;
import service.exception.AccountIsLockedException;
import service.exception.BadPinCodeException;

import java.util.InputMismatchException;
import java.util.Scanner;

public class PinValidatorImpl implements PinValidator {
    //Пин-код для входа
    private final int pinCode = 1212;
    private static final int MAX_ATTEMPTS = 3;
    private static final int BLOCK_TIME = 10000;
    private final Scanner scanner = new Scanner(System.in);

    /**
     * Проверяет введённый пин-код на правильность.
     * Разрешает 3 попытки ввода, после чего блокирует доступ на 10 секунд и сбрасывает счётчик попыток.
     * При неверном вводе пин-кода выводится сообщение об ошибке с указанием оставшихся попыток.
     *
     * @return {@code true}, если введённый пин-код верен; {@code false}, если попытки исчерпаны.
     * @throws BadPinCodeException если пользователь ввел неверный пин-код.
     */
    @Override
    public boolean isValid() {
        int count = 0;
        boolean isSleep = true;

        while (isSleep) {
            while (count < MAX_ATTEMPTS) {
                try {
                    if (pinCode != checkPasswordForNumbers()) {
                        count++;
                        throw new BadPinCodeException();
                    } else {
                        isSleep = false;
                        return true;
                    }
                } catch (BadPinCodeException e) {
                    System.out.println(e.getMessage() + " Осталось попыток: " + (4 - count));
                }
            }
            System.out.println("Доступ заблокирован на 10сек.");
            count = 0;
            temporaryInputBlocking();
        }
        return false;
    }

    /**
     * Запрашивает у пользователя ввод пароля, состоящего только из цифр.
     * Если введено значение, не являющееся числом, выводится сообщение об ошибке.
     *
     * @return введённый пароль в виде целого числа. В случае ошибки возвращается 0.
     */
    @Override
    public Integer checkPasswordForNumbers() {
        System.out.print("Введите пароль: ");
        try {
            return scanner.nextInt();
        } catch (InputMismatchException e) {
            System.out.println("Пароль должен состоять только из цифр.");
            scanner.nextLine();
            return 0;
        }
    }

    /**
     * Блокирует ввод пароля на 10 секунд и отображает оставшееся время до разблокировки.
     * Выбрасывает исключение, если пользователь пытается ввести данные во время блокировки.
     *
     * @throws AccountIsLockedException если пользователь пытается ввести символы во время блокировки.
     */
    @Override
    public void temporaryInputBlocking() {
        long startTime = System.currentTimeMillis();

        while (System.currentTimeMillis() - startTime < BLOCK_TIME) {
            long elapsed = System.currentTimeMillis() - startTime;
            long remainingSeconds = (BLOCK_TIME - elapsed) / 1000;
            if (scannerForPassword()) {
                try {
                    throw new AccountIsLockedException(remainingSeconds);
                } catch (AccountIsLockedException e) {
                    System.out.println(e.getMessage());
                }
            }
        }
    }

    private boolean scannerForPassword() {
        Scanner scanner = new Scanner(System.in);
        return scanner.hasNextLine();
    }
}
