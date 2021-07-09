package garbageFactory;

import garbageFactory.db.DBInputMaterials;
import garbageFactory.db.DBRepository;
import garbageFactory.materials.*;
import garbageFactory.production.Production;

import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {

        DBInputMaterials dbInputMaterials = new DBInputMaterials();
        DBRepository dbRepository = new DBRepository(dbInputMaterials);
        generateDB(dbRepository);

/*        ArrayList<RecycleMaterialContainer<? extends Material>> containers = new ArrayList<>();
        Random random = new Random();
        for (int i = 0; i < random.nextLong(); i++) {

        }*/


        ResolveService resolveService = new ResolveService(dbRepository);
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

    private static void generateDB(DBRepository dbRepository) {
        dbRepository.addMaterial(new RecycleMaterialContainer<>(17, Glass.class));
        dbRepository.addMaterial(new RecycleMaterialContainer<>(19, Glass.class));
        dbRepository.addMaterial(new RecycleMaterialContainer<>(13, Glass.class));

        dbRepository.addMaterial(new RecycleMaterialContainer<>(35, Plastic.class));
        dbRepository.addMaterial(new RecycleMaterialContainer<>(45, Plastic.class));
        dbRepository.addMaterial(new RecycleMaterialContainer<>(50, Plastic.class));

        dbRepository.addMaterial(new RecycleMaterialContainer<>(150, Paper.class));
        dbRepository.addMaterial(new RecycleMaterialContainer<>(200, Paper.class));
        dbRepository.addMaterial(new RecycleMaterialContainer<>(300, Paper.class));
        dbRepository.addMaterial(new RecycleMaterialContainer<>(220, Paper.class));
        dbRepository.addMaterial(new RecycleMaterialContainer<>(270, Paper.class));

        dbRepository.addMaterial(new RecycleMaterialContainer<>(1000, Wood.class));
        dbRepository.addMaterial(new RecycleMaterialContainer<>(1300, Wood.class));
    }
}
