package garbageFactory.Production;

import garbageFactory.Materials.Glass;
import garbageFactory.Materials.Plastic;

public class ProductionPlastic extends Production{
    private int id;

    public ProductionPlastic() {
        super.setRatio(0.5F);
        id = 0;
    }

    public Plastic createObject(double weight){
        return new Plastic(++id, weight);
    }
}
