package secretprojectstudios.repository;

import com.google.inject.Inject;
import org.jongo.Jongo;
import secretprojectstudios.domain.Game;

public class GameRepository {

    private static final String GAMES_COLLECTION = "games";
    
    private final Jongo jongo;

    @Inject
    public GameRepository(Jongo jongo) {

        this.jongo = jongo;
    }

    public Game save(Game game) {
        jongo.getCollection(GAMES_COLLECTION).save(game);
        return game;
    }

    public Game get(String code) {
        return jongo.getCollection(GAMES_COLLECTION).findOne("{ _id: # }", code).as(Game.class);
    }

    public Game getByReference(String reference) {
        return jongo.getCollection(GAMES_COLLECTION).findOne("{ reference: # }", reference).as(Game.class);
    }
}
