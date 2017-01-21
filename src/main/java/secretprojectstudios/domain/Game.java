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
    private String id;
    private final String code;
    private Map<Ideal, Integer> ideals;

    @JsonCreator
    protected Game(
            @MongoId @MongoObjectId String id,
            @JsonProperty("code") String code,
            @JsonProperty("ideals") Map<String, Integer> ideals) {
        this.id = id;
        this.code = code;
        this.ideals = ideals
                .entrySet()
                .stream()
                .collect(toMap(
                        entry -> Ideal.fromString(entry.getKey()),
                        Map.Entry::getValue));
    }

    public Game(String code) {
        this(null, code, DEFAULT_IDEALS);
    }

    @JsonProperty("_id")
    public String getId() {
        return id;
    }

    @JsonProperty
    public String getCode() {
        return code;
    }

    @JsonProperty
    public Map<Ideal, Integer> getIdeals() {
        return ideals;
    }
}