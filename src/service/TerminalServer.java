package service;

import service.exception.InsufficientFundsException;
import service.exception.InvalidAmountException;

public interface TerminalServer {

    Float withdrawMoney() throws InvalidAmountException, InsufficientFundsException;

    Float depositMoney() throws InvalidAmountException;

    void displayBalance();
}
