package ink.abalone.week17.T1;

public class ThreadTest{
    public static void main(String[] args){
        new SimpleThread("Take it myself??").start();
        new SimpleThread("Give it to police!!").start();
    }
}
