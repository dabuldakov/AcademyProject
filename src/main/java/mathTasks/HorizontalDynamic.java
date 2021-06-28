package mathTasks;

import java.util.Scanner;

public class HorizontalDynamic {

    private static final double scale = Math.pow(10, 3);
    private static final double g = 9.80665;

    public static void main(String[] args) {

        Scanner in = new Scanner(System.in);
        System.out.print("Enter anger: ");
        int anger = getValue(in);
        System.out.println(anger);
        System.out.print("Enter speed: ");
        int speed = getValue(in);
        System.out.println(speed);

        in.close();


        double h = (Math.pow(speed,2) * Math.pow(sinus(anger),2)) / (2 * g);
        double l = Math.abs(Math.pow(speed,2) * (2 * sinus(anger) * cosinus(anger)) / g);
        double t = 2 * speed * sinus(anger) / g;

        System.out.println("Maximum height: " + round(h) + " meters");
        System.out.println("Distance: " + round(l) + " meters.");
        System.out.println("Time: " + round(t) + " seconds.");
        System.out.println("-------------------------------");


        double part = t / 20;
        double accumm = 0;

        for(int i = 0; i <=20; i++){
            double x = speed * accumm * cosinus(anger);
            double y = (speed * accumm * sinus(anger)) - (g * Math.pow(accumm, 2)/2);
            System.out.println("x: " + round(x) + " | " + "y: " + round(y) + " | time: " + round(accumm));
            accumm = accumm + part;
        }


    }

    private static int getValue(Scanner in){
        int value =  in.nextInt();
        if(value < 0) {
            System.out.println("Enter value more than 0!");
        }else {
            return value;
        }
        return getValue(in);
    }

    private static double sinus(int x){
        return Math.sin(Math.toRadians(x));
    }

    private static double cosinus(int x){
        return Math.cos(Math.toRadians(x));
    }

    private static double round(double in){
        return Math.ceil(in * scale) / scale;
    }

}
