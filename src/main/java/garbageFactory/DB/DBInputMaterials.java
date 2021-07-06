package garbageFactory.DB;

import garbageFactory.Materials.Product;
import garbageFactory.Materials.RecycleMaterialContainer;
import garbageFactory.Materials.Type;

import java.util.ArrayList;

public class DBInputMaterials {
    ArrayList<RecycleMaterialContainer> arrayList;

    public DBInputMaterials() {
        this.arrayList = new ArrayList<>();
        arrayList.add(new Product(1, Type.GLASS, 15));
        arrayList.add(new Product(2, Type.PLASTIC, 100));
        arrayList.add(new Product(3, Type.GLASS, 25));
        arrayList.add(new Product(4, Type.PAPER, 33));
        arrayList.add(new Product(5, Type.PLASTIC, 170));
        arrayList.add(new Product(6, Type.PAPER, 43));
        arrayList.add(new Product(7, Type.PAPER, 54));
        arrayList.add(new Product(8, Type.PLASTIC, 150));
        arrayList.add(new Product(9, Type.GLASS, 20));

    }

    public ArrayList<RecycleMaterialContainer> getArrayList() {
        return arrayList;
    }

    public void setArrayList(ArrayList<RecycleMaterialContainer> arrayList) {
        this.arrayList = arrayList;
    }

    @Override
    public String toString() {
        return "DBInputMaterials{" +
                "arrayList=" + arrayList +
                '}';
    }

}
