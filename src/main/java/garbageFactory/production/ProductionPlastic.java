package garbageFactory.production;

import garbageFactory.materials.Plastic;
import garbageFactory.materials.RecycleMaterialContainer;

public class ProductionPlastic<PLASTIC extends Plastic> extends Production {
    private int id;
    private Class<PLASTIC> type;

    public ProductionPlastic(Class type) {
        super(type);
        super.setRatio(0.5F);
        id = 0;
        this.type = type;
    }

    @Override
    public void performed(RecycleMaterialContainer container) {
        super.performed(container);
        Plastic plastic = new Plastic(id, container.getWeightEnd());
        super.getMaterialArrayList().add(plastic);
    }
}
