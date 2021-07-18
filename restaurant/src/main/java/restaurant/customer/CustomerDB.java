package restaurant.customer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Component;
import restaurant.ArrayList;
import restaurant.SpringConfig;

@Component
public class CustomerDB{

    private ArrayList arrayList;

    public CustomerDB() {
        arrayList = new ArrayList();
    }

    public ArrayList getArrayList() {
        return arrayList;
    }
}
