package ink.abalone.week8.T2;

import java.util.Objects;

public class Sphere extends Shape3D{
    private static final double pi = 3.1415926;
    private double radius;
    public Sphere(double radius) {
        this.radius = radius;
    }
    @Override
    public double calculateVolume() {
        return 4.0/3.0*pi*Math.pow(radius, 3);
    }
    @Override
    public double calculateSurfaceArea() {
        return 4*pi*Math.pow(radius, 2);
    }

    @Override
    public String toString() {
        return "Sphere{" +
                "radius=" + radius +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Sphere sphere = (Sphere) o;
        return Double.compare(radius, sphere.radius) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(radius);
    }
}
