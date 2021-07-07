package garbageFactory.Production;

import garbageFactory.Materials.Material;
import garbageFactory.Materials.RecycleMaterialContainer;

import java.util.ArrayList;

public class Production<MATERIAL extends Material> {
    private float ratio;
    private double accumulator;
    private double amount;
    private ArrayList<RecycleMaterialContainer> arrayList;
    private int id;
    private final Class<MATERIAL> type;
    private ArrayList<MATERIAL> materialArrayList;

    public Production(Class<MATERIAL> type) {
        this.ratio = 0.7F;
        this.accumulator = 0F;
        this.amount = 0F;
        arrayList = new ArrayList<>();
        this.type = type;
        materialArrayList = new ArrayList<>();
    }

    public ArrayList<MATERIAL> getMaterialArrayList() {
        return materialArrayList;
    }

    public void setMaterialArrayList(ArrayList<MATERIAL> materialArrayList) {
        this.materialArrayList = materialArrayList;
    }

    public ArrayList<RecycleMaterialContainer> getArrayList() {
        return arrayList;
    }

    public void setArrayList(ArrayList<RecycleMaterialContainer> arrayList) {
        this.arrayList = arrayList;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "ProductionMain{" +
                "ratio=" + ratio +
                ", accumulator=" + accumulator +
                ", amount=" + amount +
                ", arrayList=" + arrayList +
                ", id=" + id +
                '}';
    }

    public double getAccumulator() {
        return accumulator;
    }

    public void setAccumulator(double accumulator) {
        this.accumulator = accumulator;
    }

    public float getRatio() {
        return ratio;
    }

    public void setRatio(float ratio) {
        this.ratio = ratio;
    }

    public void performed(RecycleMaterialContainer container) {
        double save = container.getWeightStart() * ratio;
        container.setWeightEnd(Math.round(save));
        accumulator = Math.round(accumulator + save);
        amount = amount + container.getWeightStart();
        arrayList.add(container);
        id++;

    }
}
