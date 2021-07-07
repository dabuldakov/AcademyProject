package garbageFactory.Production;

import garbageFactory.Materials.Glass;
import garbageFactory.Materials.RecycleMaterialContainer;

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
        Glass glass = new Glass(id, container.getWeightEnd());
        super.getMaterialArrayList().add(glass);
    }
}
