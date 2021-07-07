package garbageFactory.db;

import garbageFactory.materials.Material;
import garbageFactory.materials.RecycleMaterialContainer;

import java.util.ArrayList;

public class DBRepository {

    private DBInputMaterials dbInputMaterials;

    public DBRepository(DBInputMaterials dbInputMaterials) {
        this.dbInputMaterials = dbInputMaterials;
    }

    public DBInputMaterials getDbInputMaterials() {
        return dbInputMaterials;
    }

    public void setDbInputMaterials(DBInputMaterials dbInputMaterials) {
        this.dbInputMaterials = dbInputMaterials;
    }

    public void add(RecycleMaterialContainer<? extends Material> recycleMaterialContainer){
        dbInputMaterials.getArrayList().add(recycleMaterialContainer);
    }
}
