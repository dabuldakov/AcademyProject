package shape;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

public class Main {

    public static void main(String[] args) {

        Shape square1 = new Square(4, 5);
        square1.setName("squareFirst");

        Shape square2 = new Square(4, 5);
        square2.setName("squareSecond");

        Shape square3 = new Square(4, 5);
        square3.setName("squareSecond");

        Map<Shape, Integer> myMap = new HashMap<>();
        myMap.put(square1, 15);
        myMap.put(square2, 17);
        myMap.put(square3, 17);
        myMap.put(square3, 12);


        for (Shape shape : myMap.keySet()) {
            System.out.println("key: " + shape.getName() + " value: " + myMap.get(shape));
        }

//        Shape triangle = new Triangle(15, 10);
//        triangle.setName("triangle");
//
//        Shape circle = new Circle(7);
//        circle.setName("circle");
//
//        Stream<Shape> stream = Stream.of(square, triangle, circle);
//        stream.forEach(Shape::print);
//
//        System.out.println(square.hashCode());

    }
}
