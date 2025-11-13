package ink.abalone.week11.T1;

public class NotATriangleException extends Exception {
    public NotATriangleException() {
        super("Can't be a triangle");
    }
    public NotATriangleException(String message) {
        super(message);
    }
}
