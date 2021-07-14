package garbageFactory;

import garbageFactory.db.DBInputMaterials;
import garbageFactory.db.DBRepository;
import garbageFactory.materials.*;
import garbageFactory.production.Production;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;

@ExtendWith(MockitoExtension.class)
class ResolveServiceTest {

    @Test
    void shouldReturnProductionArrayWithResizeMaterials() {
        //given
        RecycleMaterialContainer recycleMaterialContainerGlass1= new RecycleMaterialContainer<>(5, Glass.class);
        RecycleMaterialContainer recycleMaterialContainerGlass2 = new RecycleMaterialContainer<>(10, Glass.class);
        RecycleMaterialContainer recycleMaterialContainerPaper = new RecycleMaterialContainer<>(15, Paper.class);
        RecycleMaterialContainer recycleMaterialContainerPlastic = new RecycleMaterialContainer<>(50, Plastic.class);
        RecycleMaterialContainer recycleMaterialContainerWood = new RecycleMaterialContainer<>(100, Wood.class);

        ArrayList<RecycleMaterialContainer> arrayList = new ArrayList<>();
        arrayList.add(recycleMaterialContainerGlass1);
        arrayList.add(recycleMaterialContainerPaper);
        arrayList.add(recycleMaterialContainerPlastic);
        arrayList.add(recycleMaterialContainerWood);
        arrayList.add(recycleMaterialContainerGlass2);

        DBInputMaterials dbInputMaterials = new DBInputMaterials();
        DBRepository dbRepository = new DBRepository(dbInputMaterials);
        dbRepository.getDbInputMaterials().setArrayList(arrayList);
        ResolveService resolveService = new ResolveService(dbRepository);

        //when
        ArrayList<Production> result = resolveService.sort();

        //then
        Glass glass1 = (Glass) result.get(0).getMaterialArrayList().get(0);
        Glass glass2 = (Glass) result.get(0).getMaterialArrayList().get(1);
        Paper paper = (Paper) result.get(1).getMaterialArrayList().get(0);
        Plastic plastic = (Plastic) result.get(2).getMaterialArrayList().get(0);

        assertEquals(15, result.get(0).getAmount());
        assertTrue(result.get(0).getAccumulator() < 15 && result.get(0).getAccumulator() >= 0);
        assertEquals(5, glass1.getWeightStart());
        assertEquals(10, glass2.getWeightStart());
        assertEquals(15, paper.getWeightStart());
        assertEquals(50, plastic.getWeightStart());
        assertEquals(1, dbRepository.getDbInputMaterials().getArrayList().size());
        assertEquals(Wood.class, dbRepository.getDbInputMaterials().getArrayList().get(0).getType());
    }
}