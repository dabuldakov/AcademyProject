package shape;

public class Triangle extends Shape {

    private int base;
    private int high;

    Triangle(int base, int high) {
        this.base = base;
        this.high = high;
    }
    public int getBase() {
        return base;
    }
    public void setBase(int base) {
        this.base = base;
    }
    public int getHigh() {
        return high;
    }
    public void setHigh(int high) {
        this.high = high;
    }

    @Override
    public double area() {
        return this.base * this.high * 0.5;
    }
}
