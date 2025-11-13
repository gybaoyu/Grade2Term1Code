package ink.abalone.week11.T2;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CargoBoat {
    double totalWeight = 0;
    private List<Cargo> cargos;
    public CargoBoat() {
        cargos = new ArrayList<Cargo>();
    }
    void loading(double weight) throws DangerException {
        cargos.add(new Cargo(weight));
        totalWeight += weight;
        if (totalWeight > 1000)throw new DangerException(totalWeight);
        else System.out.println("当前载重: "+totalWeight);
    }
}
class Cargo{
    private double weight;
    public Cargo(double weight){
        this.weight = weight;
    }
}
class CargoBoatTest{
    public static void main(String[] args) {
        CargoBoat boat = new CargoBoat();
        Scanner sc = new Scanner(System.in);
        try {
            while (true) {
                System.out.println("请输入需要装载的集装箱重量(输入 # 停止装载): ");
                String s = sc.nextLine();
                if (s.equals("#"))break;
                else boat.loading(Double.parseDouble(s));
            }
        }catch (DangerException e){
            System.out.println(e.getMessage());
        }finally {
            System.out.println("启航");
        }
    }
}
