package ink.abalone.week17.T2;

public class T2ThreadTest {
    public static void main(String[] args) {
        new Thread(new RunnableThread()).start();
        new Thread(()->{
            for (int i = 0; i < 10000; i++) {
                System.out.println("lambda thread");
            }
        }).start();
    }
}

class RunnableThread implements Runnable {
    @Override
    public void run() {
        for (int i = 0; i < 10000; i++) {
            System.out.println("runnable thread");
        }
    }
}