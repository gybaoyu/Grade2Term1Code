package ink.abalone.week11.T3;

public class InvalidAmountException extends RuntimeException {
    public InvalidAmountException() {
        super("取款金额需大于0");
    }
}
class InsufficientFundsException extends Exception{
    public InsufficientFundsException(double deposit) {
        super("余额不足,当前余额: "+deposit);
    }
}