package garbageFactory;

import garbageFactory.DB.DBInputMaterials;
import garbageFactory.Production.Production;

import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {
        ResolveService resolveService = new ResolveService();
        ArrayList<Production> productions = resolveService.sort();
        System.out.println(productions);


    }
    public static DBInputMaterials dbInputMaterials = new DBInputMaterials();




}
