package homeWork;

import java.util.LinkedList;
import java.util.List;

public class PreviousMaximumNumber {

    public static void main(String[] args) {

        List<Integer> list = new LinkedList<Integer>();
        list.add(555);
        list.add(3);
        list.add(15);
        list.add(-14);
        list.add(8);
        list.add(33);
        list.add(7);

        try {
            System.out.println("Search number: " + checkMinimal(list, 33));
        }catch (ArrayIndexOutOfBoundsException e){
            System.out.println(e.getMessage());
        }
    }

    private static int checkMinimal(List<Integer> list, int x){

        int save = 0;
        boolean flag = false;

        for (Integer integer : list) {
            if (integer < x) {
                save = integer;
                flag = true;
                break;
            }
        }

        if (flag) {
            for (Integer integer : list) {
                if (integer < x & integer > save) {
                    save = integer;
                }
            }
            return save;
        } else {
            throw new ArrayIndexOutOfBoundsException("Not found.");
        }
    }
}
