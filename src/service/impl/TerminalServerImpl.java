package service.impl;

import service.TerminalServer;
import service.exception.InsufficientFundsException;
import service.exception.InvalidAmountException;

import java.util.Scanner;

public class TerminalServerImpl implements TerminalServer {
    private Float moneyInAccount = 20000F;
    private final Scanner scanner = new Scanner(System.in);

    /**
     * Запрашивает у пользователя сумму для снятия и выполняет операцию, если сумма корректна.
     * Проверяет кратность суммы 100 и наличие достаточных средств на счёте.
     * В случае некорректной суммы или недостатка средств выбрасывает исключение.
     *
     * @return текущий остаток на счёте после операции.
     * @throws InvalidAmountException     если сумма не кратна 100.
     * @throws InsufficientFundsException если на счёте недостаточно средств.
     */
    @Override
    public Float withdrawMoney() throws InvalidAmountException, InsufficientFundsException {
        int sum = requestAmount("Сколько денег вы хотите снять: ");
        validateAmount(sum);
        if (sum > moneyInAccount) {
            throw new InsufficientFundsException();
        }
        moneyInAccount -= sum;
        displayBalance();
        return moneyInAccount;
    }

    /**
     * Запрашивает у пользователя сумму для пополнения счёта и выполняет операцию, если сумма корректна.
     * Проверяет, что сумма кратна 100. В случае некорректной суммы выбрасывает исключение.
     *
     * @return текущий остаток на счёте после пополнения.
     * @throws InvalidAmountException если сумма не кратна 100.
     */
    @Override
    public Float depositMoney() throws InvalidAmountException {
        int sum = requestAmount("Сколько денег вы хотите положить: ");
        validateAmount(sum);
        moneyInAccount += sum;
        displayBalance();
        return moneyInAccount;
    }

    /**
     * Запрашивает у пользователя сумму для операции, отображая перед этим заданное сообщение.
     *
     * @param message текст сообщения, отображаемого перед вводом.
     * @return введённая сумма в виде целого числа.
     */
    private int requestAmount(String message) {
        System.out.println(message);
        return scanner.nextInt();
    }

    /**
     * Проверяет, что сумма кратна 100. Если сумма некорректна, выбрасывает исключение.
     *
     * @param sum сумма для проверки.
     * @throws InvalidAmountException если сумма не кратна 100.
     */
    private void validateAmount(int sum) throws InvalidAmountException {
        if (sum % 100 != 0) {
            throw new InvalidAmountException();
        }
    }

    /**
     * Выводит на экран текущий остаток на счёте.
     */
    @Override
    public void displayBalance() {
        System.out.println("Ваш остаток: " + moneyInAccount);
    }
}
