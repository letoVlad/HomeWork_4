package service.impl;

import service.exception.InsufficientFundsException;
import service.exception.InvalidAmountException;


public class TerminalImpl {
    PinValidatorImpl pinValidator = new PinValidatorImpl();
    TerminalServerImpl terminalServer = new TerminalServerImpl();


    public boolean checkAccountStatus() {
        return pinValidator.isValid();
    }


    public void withdrawMoney() {
        try {
            terminalServer.withdrawMoney();
        } catch (InvalidAmountException | InsufficientFundsException e) {
            System.out.println(e.getMessage());
        }
    }

    public void depositMoney() {
        try {
            terminalServer.depositMoney();
        } catch (InvalidAmountException e) {
            System.out.println(e.getMessage());
        }
    }


}
