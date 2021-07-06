package garbageFactory.Production;

import garbageFactory.Materials.Glass;

import java.util.ArrayList;

public class ProductionGlass extends Production{
    private int id;

    public ProductionGlass() {
        super.setRatio(0.9F);
        id = 0;
    }

    public Glass createObject(double weight){
        return new Glass(++id, weight);
    }
}
