package ink.abalone.week11.T2;

public class DangerException extends Exception {
    public DangerException(double weight) {
        super("超出最大承载重量");
        showError(weight);
    }
    private void showError(double weight){
        System.err.println("当前重量: "+weight);
    }
}
