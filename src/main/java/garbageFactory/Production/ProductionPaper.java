package garbageFactory.Production;

import garbageFactory.Materials.Glass;
import garbageFactory.Materials.Paper;

public class ProductionPaper extends Production{
    private int id;

    public ProductionPaper() {
        super.setRatio(0.7F);
        id = 0;
    }

    public Paper createObject(double weight){
        return new Paper(++id, weight);
    }
}
