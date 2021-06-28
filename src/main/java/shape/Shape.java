package shape;

public abstract class Shape {

    private String name;

    String getName() {
        return name;
    }
    void setName(String name) {
        this.name = name;
    }
    public abstract double area();

    void print(){
        System.out.println(name + " area: " + area());
    }
}
