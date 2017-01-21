package secretprojectstudios.domain;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.jongo.marshall.jackson.oid.MongoId;
import org.jongo.marshall.jackson.oid.MongoObjectId;

import java.util.Map;

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
}
