package Restaurant;

public interface Registration {

    Array getArray();

    default void add(Object o) throws MyException {
        Array array = getArray();
        for (int i = 0; i < array.size(); i++) {
            if (array.getArray()[i].equals(o)){
                throw new MyException("This object exist in DB, please add new one.");
            }
        }
        System.out.println("Object added in Registration");
        array.add(o);
    }
}
