package garbageFactory.DB;

import garbageFactory.Materials.*;

import java.util.ArrayList;

public class DBInputMaterials {
    private ArrayList<RecycleMaterialContainer> arrayList;

    public DBInputMaterials() {
        this.arrayList = new ArrayList<>();
        arrayList.add(new RecycleMaterialContainer(15, Glass.class));
        arrayList.add(new RecycleMaterialContainer(17, Glass.class));
        arrayList.add(new RecycleMaterialContainer(19, Glass.class));
        arrayList.add(new RecycleMaterialContainer(13, Glass.class));

        arrayList.add(new RecycleMaterialContainer(35, Plastic.class));
        arrayList.add(new RecycleMaterialContainer(45, Plastic.class));
        arrayList.add(new RecycleMaterialContainer(50, Plastic.class));

        arrayList.add(new RecycleMaterialContainer(150, Paper.class));
        arrayList.add(new RecycleMaterialContainer(200, Paper.class));
        arrayList.add(new RecycleMaterialContainer(300, Paper.class));
        arrayList.add(new RecycleMaterialContainer(220, Paper.class));
        arrayList.add(new RecycleMaterialContainer(270, Paper.class));

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

