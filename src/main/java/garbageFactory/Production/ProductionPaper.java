package garbageFactory.Production;

import garbageFactory.Materials.Paper;
import garbageFactory.Materials.RecycleMaterialContainer;

public class ProductionPaper<PAPER extends Paper> extends Production {
    private int id;
    private Class<PAPER> type;

    public ProductionPaper(Class type) {
        super(type);
        super.setRatio(0.7F);
        id = 0;
    }

    @Override
    public void performed(RecycleMaterialContainer container) {
        super.performed(container);
        Paper paper = new Paper(id, container.getWeightEnd());
        super.getMaterialArrayList().add(paper);
    }
}
