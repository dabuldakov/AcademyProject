package garbageFactory.production;

import garbageFactory.materials.Glass;
import garbageFactory.materials.RecycleMaterialContainer;

public class ProductionGlass<GLASS extends Glass> extends Production {
    private int id;
    private Class<GLASS> type;

    public ProductionGlass(Class type) {
        super(type);
        super.setRatio(0.9F);
        id = 0;
    }

    @Override
    public void performed(RecycleMaterialContainer container) {
        super.performed(container);
        Glass glass = new Glass(id, container.getWeightStart());
        glass.setWeightEnd(container.getWeightEnd());
        super.getMaterialArrayList().add(glass);
    }
}
