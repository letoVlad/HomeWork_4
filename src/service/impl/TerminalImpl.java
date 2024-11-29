package service.impl;

import service.Terminal;
import service.exception.InsufficientFundsException;
import service.exception.InvalidAmountException;


public class TerminalImpl implements Terminal {
    PinValidatorImpl pinValidator = new PinValidatorImpl();
    TerminalServerImpl terminalServer = new TerminalServerImpl();

    @Override
    public boolean checkAccountStatus() {
        return pinValidator.isValid();
    }

    @Override
    public void withdrawMoney() {
        try {
            terminalServer.withdrawMoney();
        } catch (InvalidAmountException | InsufficientFundsException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void depositMoney() {
        try {
            terminalServer.depositMoney();
        } catch (InvalidAmountException e) {
            System.out.println(e.getMessage());
        }
    }


}
