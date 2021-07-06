package garbageFactory.DB;

import garbageFactory.Materials.*;

import java.util.ArrayList;

public class DBInputMaterials {
    private ArrayList<Material> arrayList;

    public DBInputMaterials() {
        this.arrayList = new ArrayList<>();
        arrayList.add(new Plastic(1, 14, Plastic.class));
        arrayList.add(new Plastic(2, 17, Plastic.class));
        arrayList.add(new Plastic(3, 19, Plastic.class));
        arrayList.add(new Paper(4, 45, Paper.class));
        arrayList.add(new Paper(5, 60, Paper.class));
        arrayList.add(new Paper(6, 70, Paper.class));
        arrayList.add(new Glass(7, 100, Glass.class));
        arrayList.add(new Glass(8, 150, Glass.class));
        arrayList.add(new Glass(9, 200, Glass.class));
    }

    public ArrayList<Material> getArrayList() {
        return arrayList;
    }

    public void setArrayList(ArrayList<Material> arrayList) {
        this.arrayList = arrayList;
    }

    @Override
    public String toString() {
        return "DBInputMaterials{" +
                "arrayList=" + arrayList +
                '}';
    }

}
