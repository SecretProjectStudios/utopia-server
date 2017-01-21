package secretprojectstudios.domain;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.jongo.marshall.jackson.oid.MongoId;
import org.jongo.marshall.jackson.oid.MongoObjectId;

import java.util.*;

public class Player {
    @MongoObjectId
    private String id;

    private String name;

    private String gameId;

    private Map<Ideal, Integer> targets;

    @JsonCreator
    protected Player(@MongoId String id,
                     @JsonProperty("name") String name,
                     @JsonProperty("gameId") String gameId) {
        this.id = id;
        this.name = name;
        this.gameId = gameId;
        generateTargets();
    }

    public Player(String name, String gameId) {
        this(null, name, gameId);
    }

    @JsonProperty("_id")
    public String getId() {
        return id;
    }

    @JsonProperty
    public String getName() {
        return name;
    }

    @JsonProperty
    public String getGameId() {
        return gameId;
    }

    public boolean hasWon(Map<Ideal, Integer> ideals) {
        for (Ideal ideal: Ideal.values()) {
            if (targets.get(ideal) < ideals.get(ideal)) {
                return false;
            }
        }
        return true;
    }

    private void generateTargets() {
        targets = new HashMap<>();
        List<Ideal> ideals = Arrays.asList(Ideal.values());
        Collections.shuffle(ideals);
        int [] weights = new int[ideals.size()];
        Arrays.fill(weights, 0);
        weights[0] = 8;
        weights[1] = 5;
        weights[2] = 3;
        weights[3] = 2;
        weights[4] = 2;
        Random random = new Random();
        for (int i = 0; i < 10; i++) {
            int from = random.nextInt(weights.length-1);
            int to = from + 1;
            int adjustment = random.nextBoolean() ? 1 : -1;
            if ((weights[from] + adjustment < 10) && (weights[to] - adjustment > 1)) {
                weights[from] += adjustment;
                weights[to] -= adjustment;
            }
        }
        for (int i = 0; i < weights.length; i++) {
            if (weights[i] > 0) {
                targets.put(ideals.get(i), weights[i]);
            }
        }
    }
}
