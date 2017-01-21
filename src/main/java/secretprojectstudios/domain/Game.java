package secretprojectstudios.domain;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.jongo.marshall.jackson.oid.MongoId;
import org.jongo.marshall.jackson.oid.MongoObjectId;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static java.util.stream.Collectors.toMap;

public class Game {
    private static final Map<String, Integer> DEFAULT_IDEALS = Arrays.stream(Ideal.values()).collect(toMap(Enum::toString, ideal -> 3));
    @MongoObjectId
    private String id;
    private final String reference;
    private final Map<Ideal, Integer> ideals;
    private final List<Bill> pastBills;
    private Bill currentBill;

    @JsonCreator
    protected Game(
            @MongoId String id,
            @JsonProperty("reference") String reference,
            @JsonProperty("ideals") Map<String, Integer> ideals,
            @JsonProperty("pastBills") List<Bill> pastBills,
            @JsonProperty("currentBill") Bill currentBill) {
        this.id = id;
        this.reference = reference;
        this.ideals = ideals
                .entrySet()
                .stream()
                .collect(toMap(
                        entry -> Ideal.fromString(entry.getKey()),
                        Map.Entry::getValue));
        this.pastBills = pastBills;
        this.currentBill = currentBill;
    }

    public Game(String reference) {
        this(null, reference, DEFAULT_IDEALS, new ArrayList<>(), null);
    }

    @JsonProperty("_id")
    public String getId() {
        return id;
    }

    @JsonProperty
    public String getReference() {
        return reference;
    }

    @JsonProperty
    public Map<Ideal, Integer> getIdeals() {
        return ideals;
    }

    @JsonProperty
    public Bill getCurrentBill() { return currentBill; }

    private void setNewBill(Bill bill)
    {
        if (currentBill != null){
            pastBills.add(currentBill);
        }
        currentBill = bill;
    }
}
