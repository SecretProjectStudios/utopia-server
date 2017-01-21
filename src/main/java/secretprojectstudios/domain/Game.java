package secretprojectstudios.domain;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.jongo.marshall.jackson.oid.MongoId;
import org.jongo.marshall.jackson.oid.MongoObjectId;

import java.util.Arrays;
import java.util.Map;

import static java.util.stream.Collectors.toMap;

public class Game {
    private static final Map<String, Integer> DEFAULT_IDEALS = Arrays.stream(Ideal.values()).collect(toMap(Enum::toString, ideal -> 3));
    @MongoObjectId
    private String id;
    private final String reference;
    private final State state;
    private final Map<Ideal, Integer> ideals;
    private String currentBill;

    @JsonCreator
    protected Game(@MongoId String id,
                   @JsonProperty("reference") String reference,
                   @JsonProperty("state") State state,
                   @JsonProperty("ideals") Map<String, Integer> ideals,
                   @JsonProperty("currentBill") String currentBill) {
        this.id = id;
        this.reference = reference;
        this.state = state;
        this.ideals = ideals.entrySet().stream()
                .collect(toMap(entry -> Ideal.fromString(entry.getKey()), Map.Entry::getValue));
        this.currentBill = currentBill;
    }

    public Game(String reference) {
        this(null, reference, State.NotStarted, DEFAULT_IDEALS, null);
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
    public State getState() {
        return state;
    }

    @JsonProperty
    public Map<Ideal, Integer> getIdeals() {
        return ideals;
    }

    private void setNewBill(Bill bill) {
        currentBill = bill.getId();
    }

    public String getCurrentBill() {
        return currentBill;
    }
}
