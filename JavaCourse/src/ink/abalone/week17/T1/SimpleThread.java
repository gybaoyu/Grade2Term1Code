package ink.abalone.week17.T1;

public class SimpleThread extends Thread{
    String name;
    public SimpleThread(String str){
        name = str;
    }

    public void run(){//定义run()方法
        for(int i = 0; i < 10; i++){
            System.out.println(i + "" + getName());
            try{
                Thread.sleep((int)(Math.random()*1000));
            } catch(InterruptedException e){
            }
        }
        System.out.println("DONE!" + getName());
    }
}