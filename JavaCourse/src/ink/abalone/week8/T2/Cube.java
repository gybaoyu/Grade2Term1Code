package ink.abalone.week8.T2;

import java.util.Objects;

public class Cube extends Shape3D{
    private double length, width, height;
    public Cube(double length, double width, double height) {
        this.length = length;
        this.width = width;
        this.height = height;
    }
    @Override
    public double calculateVolume() {
        return length * width * height;
    }
    @Override
    public double calculateSurfaceArea() {
        return (length*width+width*height+height*height)*2;
    }

    @Override
    public String toString() {
        return "Cube{" +
                "length=" + length +
                ", width=" + width +
                ", height=" + height +
                '}';
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cube cube = (Cube) o;
        return Double.compare(length, cube.length) == 0 && Double.compare(width, cube.width) == 0 && Double.compare(height, cube.height) == 0;
    }
    @Override
    public int hashCode() {
        return Objects.hash(length, width, height);
    }
}
