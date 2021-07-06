package garbageFactory.Production;

import garbageFactory.Materials.Product;
import garbageFactory.Materials.RecycleMaterialContainer;

import java.util.ArrayList;

public class Production {
    private float ratio;
    private double accumulator;
    private ArrayList<Product> arrayList;

    public Production() {
        this.ratio = 0.7F;
        this.accumulator = 0F;
        arrayList = new ArrayList<>();
    }

    public ArrayList<Product> getArrayList() {
        return arrayList;
    }

    public void setArrayList(ArrayList<Product> arrayList) {
        this.arrayList = arrayList;
    }

    @Override
    public String toString() {
        return "Production{" +
                "ratio=" + ratio +
                ", accumulator=" + accumulator +
                ", arrayList=" + arrayList +
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

    public void performed(Product product){
        double save = product.getWeightStart() * ratio;
        product.setWeightEnd(save);
        accumulator = accumulator + save;
        arrayList.add(product);
    }
}
