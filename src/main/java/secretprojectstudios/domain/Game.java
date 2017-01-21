package secretprojectstudios.domain;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.jongo.marshall.jackson.oid.MongoId;
import org.jongo.marshall.jackson.oid.MongoObjectId;

public class Game {
    private String id;
    private final String code;

    @JsonCreator
    protected Game(@MongoId @MongoObjectId String id, String code) {
        this.id = id;
        this.code = code;
    }

    public Game(String code) {
        this(null, code);
    }

    @JsonProperty("_id")
    public String getId() {
        return id;
    }

    @JsonProperty
    public String getCode() {
        return code;
    }
}
