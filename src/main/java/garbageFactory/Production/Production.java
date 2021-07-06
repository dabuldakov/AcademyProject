package garbageFactory.Production;

import com.ibm.crypto.provider.Mars;
import garbageFactory.Materials.Glass;
import garbageFactory.Materials.RecycleMaterialContainer;
import garbageFactory.Materials.RecycleMaterialContainer;

import java.util.ArrayList;

public class Production {
    private float ratio;
    private double accumulator;
    private ArrayList<RecycleMaterialContainer> arrayList;
    private int id;

    public Production() {
        this.ratio = 0.7F;
        this.accumulator = 0F;
        arrayList = new ArrayList<>();
    }

    public ArrayList<RecycleMaterialContainer> getArrayList() {
        return arrayList;
    }

    public void setArrayList(ArrayList<RecycleMaterialContainer> arrayList) {
        this.arrayList = arrayList;
    }

    @Override
    public String toString() {
        return "Production{" +
                "ratio=" + ratio +
                ", accumulator=" + accumulator +
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

    public void performed(RecycleMaterialContainer rmc){
        double save = rmc.getWeightStart() * ratio;
        rmc.setWeightEnd(Math.round(save));
        accumulator = Math.round(accumulator + save);
        arrayList.add(rmc);
        id++;
    }



/*    public <OB> createObject(double weight, Class<OB> obClass){
        OB ob;
        ob = new Class<OB>(++id, weight);
        return ob;
    }*/

}
