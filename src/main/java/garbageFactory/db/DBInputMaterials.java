package garbageFactory.db;

import garbageFactory.materials.*;

import java.util.ArrayList;

public class DBInputMaterials {
    private ArrayList<RecycleMaterialContainer> arrayList;

    public DBInputMaterials() {
        this.arrayList = new ArrayList<>();
    }

    public ArrayList<RecycleMaterialContainer> getArrayList() {
        return arrayList;
    }

    public void setArrayList(ArrayList<RecycleMaterialContainer> arrayList) {
        this.arrayList = arrayList;
    }

    public void add(RecycleMaterialContainer<? extends Material> recycleMaterialContainer){
        arrayList.add(recycleMaterialContainer);
    }

    @Override
    public String toString() {
        return "DBInputMaterials{" +
                "arrayList=" + arrayList +
                '}';
    }

}

