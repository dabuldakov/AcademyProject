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
        ArrayList<Material> arrayList = Main.dbInputMaterials.getArrayList();
        ArrayList<Material> arrayListLeft = new ArrayList<>();

        ProductionGlass productionGlass = new ProductionGlass();
        ProductionPaper productionPaper = new ProductionPaper();
        ProductionPlastic productionPlastic = new ProductionPlastic();

        ArrayList<Production> productions = new ArrayList<>();
        productions.add(productionGlass);
        productions.add(productionPaper);
        productions.add(productionPlastic);

        /*Map<Class<Material>, Production> materialProductionMap = new HashMap<>();
        materialProductionMap.put(, productionGlass);*/


        for (Material material : arrayList) {
            Class type = material.getType();
            if (type.equals(Glass.class)) {
                productionGlass.performed(material);
            } else if (type.equals(Paper.class)) {
                productionPaper.performed(material);
            } else if (type.equals(Plastic.class)) {
                productionPlastic.performed(material);
            } else {
                arrayListLeft.add(material);
            }
        }
        Main.dbInputMaterials.setArrayList(arrayListLeft);
        smena++;
        return productions;
    }
}
