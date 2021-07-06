package garbageFactory;

import garbageFactory.DB.DBInputMaterials;
import garbageFactory.Production.Production;

import java.util.ArrayList;

public class Main {

    public static DBInputMaterials dbInputMaterials = new DBInputMaterials();

    public static void main(String[] args) {

        ResolveService resolveService = new ResolveService();
        ArrayList<Production> productions = resolveService.sort();

        for (Production production : productions) {
            System.out.println("Accumulator material: " + production.getAccumulator() + ", Ratio: " + production.getRatio());
        };

        for (Production production : productions) {
            System.out.println(production);
        };


    }
}
