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

    public Game add(Game game) {
        jongo.getCollection(GAMES_COLLECTION).save(game);
        return game;
    }

    public Game get(String code) {
        return jongo.getCollection(GAMES_COLLECTION).findOne("{ code: # }", code).as(Game.class);
    }
}
