package garbageFactory;

import garbageFactory.Materials.Product;
import garbageFactory.Materials.RecycleMaterialContainer;
import garbageFactory.Materials.Type;
import garbageFactory.Production.Production;
import garbageFactory.Production.ProductionGlass;
import garbageFactory.Production.ProductionPaper;
import garbageFactory.Production.ProductionPlastic;

import java.util.ArrayList;

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
        ArrayList<RecycleMaterialContainer> arrayListLeft = new ArrayList<>();

        ProductionGlass productionGlass = new ProductionGlass();
        ProductionPaper productionPaper = new ProductionPaper();
        ProductionPlastic productionPlastic = new ProductionPlastic();

        ArrayList<Production> productions = new ArrayList<>();
        productions.add(productionGlass);
        productions.add(productionPlastic);
        productions.add(productionPaper);


        for (RecycleMaterialContainer rmc : arrayList) {
            Product product = (Product) rmc;
            Type type = product.getType();
            if (type.equals(Type.GLASS)) {
                productionGlass.performed(product);
            } else if (type.equals(Type.PAPER)) {
                productionPaper.performed(product);
            } else if (type.equals(Type.PLASTIC)) {
                productionPlastic.performed(product);
            } else {
                arrayListLeft.add(rmc);
            }
        }
        Main.dbInputMaterials.setArrayList(arrayListLeft);
        smena++;
        return productions;
    }
}
