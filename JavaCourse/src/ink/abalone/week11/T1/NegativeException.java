package ink.abalone.week11.T1;

public class NegativeException extends Exception {
    public NegativeException() {
        super("the length of a side is negative");
    }
    public NegativeException(String message) {
        super(message);
    }
}
