package sorts;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Random;

public class UtilsStudy {

    public static ArrayList<Integer> createArrayList(){

        ArrayList<Integer> array = new ArrayList<Integer>();
        Random random = new Random();
        int upperbound = 100000;
        for (int i = 1; i <= 100; i++){
            array.add(random.nextInt(upperbound));
        }
        return array;
    }

    public static LinkedList<Integer> createLinkedList(int size){

        LinkedList<Integer> list = new LinkedList<Integer>();
        Random random = new Random();
        int upperbound = 100000;
        for (int i = 1; i <= size; i++){
            list.add(random.nextInt(upperbound));
        }
        return list;
    }

}
