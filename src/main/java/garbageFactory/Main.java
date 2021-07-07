package garbageFactory;

import garbageFactory.DB.DBInputMaterials;
import garbageFactory.Materials.*;
import garbageFactory.Production.Production;

import java.util.ArrayList;
import java.util.Random;

public class Main {

    public static DBInputMaterials dbInputMaterials = new DBInputMaterials();

    public static void main(String[] args) {

        ArrayList<RecycleMaterialContainer<? extends Material>> containers = new ArrayList<>();
        Random random = new Random();
        for (int i = 0; i < random.nextLong(); i++) {

        }


        generateDB();
        ResolveService resolveService = new ResolveService();
        ArrayList<Production> productions = resolveService.sort();

        for (Production production : productions) {
            System.out.println("Amount: " + production.getAmount() + " Accumulator material: " + production.getAccumulator() + ", Ratio: " + production.getRatio());
        }

        for (Production production : productions) {
            System.out.println(production);
        }

        for (Production production : productions) {
            System.out.println(production.getMaterialArrayList());
        }

        System.out.println(dbInputMaterials.getArrayList());
        System.out.println("Smena count: " + resolveService.getSmena());
    }

    private static void generateDB() {
        dbInputMaterials.add(new RecycleMaterialContainer<>(17, Glass.class));
        dbInputMaterials.add(new RecycleMaterialContainer<>(19, Glass.class));
        dbInputMaterials.add(new RecycleMaterialContainer<>(13, Glass.class));

        dbInputMaterials.add(new RecycleMaterialContainer<>(35, Plastic.class));
        dbInputMaterials.add(new RecycleMaterialContainer<>(45, Plastic.class));
        dbInputMaterials.add(new RecycleMaterialContainer<>(50, Plastic.class));

        dbInputMaterials.add(new RecycleMaterialContainer<>(150, Paper.class));
        dbInputMaterials.add(new RecycleMaterialContainer<>(200, Paper.class));
        dbInputMaterials.add(new RecycleMaterialContainer<>(300, Paper.class));
        dbInputMaterials.add(new RecycleMaterialContainer<>(220, Paper.class));
        dbInputMaterials.add(new RecycleMaterialContainer<>(270, Paper.class));

        dbInputMaterials.add(new RecycleMaterialContainer<>(1000, Wood.class));
        dbInputMaterials.add(new RecycleMaterialContainer<>(1300, Wood.class));
    }
}
