package garbageFactory;

import garbageFactory.db.DBRepository;
import garbageFactory.materials.*;
import garbageFactory.production.Production;
import garbageFactory.production.ProductionGlass;
import garbageFactory.production.ProductionPaper;
import garbageFactory.production.ProductionPlastic;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ResolveService {
    private int smena;
    private DBRepository dbRepository;
    private ArrayList<RecycleMaterialContainer> arrayList;
    private ArrayList<RecycleMaterialContainer> unproductions;

    ResolveService(DBRepository dbRepository) {
        smena = 0;
        this.dbRepository = dbRepository;
        arrayList = dbRepository.getDbInputMaterials().getArrayList();
        unproductions = new ArrayList<>();
    }

    int getSmena() {
        return smena;
    }

    public void setSmena(int smena) {
        this.smena = smena;
    }

    ArrayList<Production> sort() {

        ProductionGlass productionGlass = new ProductionGlass(Glass.class);
        ProductionPaper productionPaper = new ProductionPaper(Paper.class);
        ProductionPlastic productionPlastic = new ProductionPlastic(Plastic.class);

        ArrayList<Production> productions = new ArrayList<>();
        productions.add(productionGlass);
        productions.add(productionPaper);
        productions.add(productionPlastic);

        Map<Class<? extends Material>, Production> materialProductionMap = new HashMap<>();
        materialProductionMap.put(Glass.class, productionGlass);
        materialProductionMap.put(Paper.class, productionPaper);
        materialProductionMap.put(Plastic.class, productionPlastic);

        for (RecycleMaterialContainer container : arrayList) {
            Class type = container.getType();
            Production production = materialProductionMap.get(type);
            if(production != null) {
                production.performed(container);
            } else {
                unproductions.add(container);
            }
        }
        dbRepository.getDbInputMaterials().setArrayList(unproductions);
        smena++;
        return productions;
    }
}
