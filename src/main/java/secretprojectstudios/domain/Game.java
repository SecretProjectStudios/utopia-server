package secretprojectstudios.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.jongo.marshall.jackson.oid.MongoId;
import org.jongo.marshall.jackson.oid.MongoObjectId;

import java.util.Set;

public class Game {
    @MongoId // auto
    @MongoObjectId
    private String id;

    @JsonProperty
    private Set<Player> players;
}
