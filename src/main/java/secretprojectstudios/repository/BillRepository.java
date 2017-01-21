package secretprojectstudios.repository;

import com.google.inject.Inject;
import org.bson.types.ObjectId;
import org.jongo.Jongo;
import secretprojectstudios.domain.Bill;

public class BillRepository {

    private static final String BILLS_COLLECTION = "bills";

    private final Jongo jongo;

    @Inject
    public BillRepository(Jongo jongo) {

        this.jongo = jongo;
    }

    public Bill save(Bill bill) {
        jongo.getCollection(BILLS_COLLECTION).save(bill);
        return bill;
    }

    public Bill get(String id) {
        return jongo.getCollection(BILLS_COLLECTION).findOne(new ObjectId(id)).as(Bill.class);
    }
}
