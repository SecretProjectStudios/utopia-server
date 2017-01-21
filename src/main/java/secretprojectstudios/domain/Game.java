package secretprojectstudios.domain;

import org.jongo.marshall.jackson.oid.MongoId;
import org.jongo.marshall.jackson.oid.MongoObjectId;

public class Game {
    @MongoId // auto
    @MongoObjectId
    private String id;
}
