package ink.abalone.week8.T2;

import java.util.ArrayList;

public class ShapeTest {
    public static void main(String[] args) {
        ArrayList<Shape3D> list = new ArrayList<>();
        Cube c1 = new Cube(3.1,2.2,4.3);
        Cube c2 = new Cube(3.1,2.2,4.3);
        Sphere s1 = new Sphere(3.0);
        Sphere s2 = new Sphere(3.0);
        list.add(c1);
        list.add(s1);
        for (Shape3D shape3D : list) {
            System.out.println(shape3D);
            System.out.println("Volume: "+shape3D.calculateVolume());
            System.out.println("SurfaceArea: "+shape3D.calculateSurfaceArea()+"\n");
        }

        System.out.println("c1.equals(c2): "+c1.equals(c2));
        System.out.println("s1.equals(s2): "+s1.equals(s2));
        System.out.println("s1.hashCode(): "+s1.hashCode());
        System.out.println("s2.hashCode(): "+s2.hashCode());
        System.out.println("c1.hashCode(): "+c1.hashCode());
        System.out.println("c2.hashCode(): "+c2.hashCode());
    }
}
