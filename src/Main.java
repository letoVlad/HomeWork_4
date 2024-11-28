import service.Terminal;
import service.impl.TerminalImpl;

public class Main {
    public static void main(String[] args) {

        TerminalImpl terminal = new TerminalImpl();
        terminal.depositMoney();
        terminal.withdrawMoney();
    }
}