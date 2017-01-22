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
    private State state;
    private final Map<Ideal, Integer> ideals;
    private String currentBill;
    private int round;

    @JsonCreator
    protected Game(@MongoId String id,
                   @JsonProperty("reference") String reference,
                   @JsonProperty("state") State state,
                   @JsonProperty("ideals") Map<String, Integer> ideals,
                   @JsonProperty("currentBill") String currentBill,
                   @JsonProperty("round") int round) {
        this.id = id;
        this.reference = reference;
        this.state = state;
        this.ideals = ideals.entrySet().stream()
                .collect(toMap(entry -> Ideal.fromString(entry.getKey()), Map.Entry::getValue));
        this.currentBill = currentBill;
        this.round = round;
    }

    public Game(String reference) {
        this(null, reference, State.NotStarted, DEFAULT_IDEALS, null, 0);
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

    @JsonProperty
    public int getRound() {
        return round;
    }

    public void nextRound() {
        round++;
    }

    public void setNewBill(Bill bill) {
        currentBill = bill.getId();
    }

    public String getCurrentBill() {
        return currentBill;
    }

    public void start() {
        state = State.Started;
    }

    public void finish() {
        currentBill = null;
        state = State.Finished;
    }

    public void applyBill(Bill bill, Vote vote) {
        Map<Ideal, Integer> effect = vote == Vote.Aye ? bill.getPassEffect() : bill.getFailEffect();

        for (Map.Entry<Ideal, Integer> entry : effect.entrySet()) {
            ideals.compute(entry.getKey(), (ideal, value) -> Math.max(0, Math.min(value + entry.getValue(), 10)));
        }
    }
}
