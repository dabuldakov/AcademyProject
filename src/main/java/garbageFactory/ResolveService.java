package garbageFactory;

import garbageFactory.Materials.*;
import garbageFactory.Production.Production;
import garbageFactory.Production.ProductionGlass;
import garbageFactory.Production.ProductionPaper;
import garbageFactory.Production.ProductionPlastic;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ResolveService {
    private int smena;

    public ResolveService() {
        smena = 0;
    }

    public int getSmena() {
        return smena;
    }

    public void setSmena(int smena) {
        this.smena = smena;
    }

    public ArrayList<Production> sort() {
        ArrayList<RecycleMaterialContainer> arrayList = Main.dbInputMaterials.getArrayList();
        ArrayList<RecycleMaterialContainer> unproductions = new ArrayList<>();

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
        Main.dbInputMaterials.setArrayList(unproductions);
        smena++;
        return productions;
    }
}
