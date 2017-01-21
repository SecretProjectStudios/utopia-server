package secretprojectstudios.domain;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.jongo.marshall.jackson.oid.MongoId;
import org.jongo.marshall.jackson.oid.MongoObjectId;

import java.util.Map;
import java.util.UUID;

public class Player {
    private String id;

    private String name;

    private Map<Ideal, Integer> playerScore;

    @JsonCreator
    protected Player(@MongoId @MongoObjectId String id,
                     @JsonProperty("name") String name) {
        this.id = id;
        this.name = name;
    }

    public Player(String name) {
        this(null, name);
    }

    @JsonProperty("_id")
    public String getId() {
        return id;
    }

    @JsonProperty
    public String getName() {
        return name;
    }
}
