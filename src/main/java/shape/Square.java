package shape;

import java.util.Objects;

public class Square extends Shape{

    private int a;
    private int b;

    Square(int a, int b) {
        this.a = a;
        this.b = b;
    }
    public int getA() {
        return a;
    }
    public void setA(int a) {
        this.a = a;
    }
    public int getB() {
        return b;
    }
    public void setB(int b) {
        this.b = b;
    }

    @Override
    public double area() {
        return a * b;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Square square = (Square) o;
        System.out.println("I am in equals");
        return a == square.a &&
                b == square.b &&
                super.getName().equals(square.getName());
    }

    @Override
    public int hashCode() {
        int result = 31 * a;
        result = result + 31 * b;
        result = result * (super.getName() == null ? 0 : super.getName().hashCode());
        System.out.println("I am in hashCode");
        return result;
    }
}
