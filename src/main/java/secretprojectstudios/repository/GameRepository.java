package secretprojectstudios.repository;

import com.google.inject.Inject;
import org.bson.types.ObjectId;
import org.jongo.Jongo;
import secretprojectstudios.domain.Game;
import secretprojectstudios.domain.State;

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

    public Game get(String id) {
        return jongo.getCollection(GAMES_COLLECTION).findOne(new ObjectId(id)).as(Game.class);
    }

    public Game getByReferenceAndState(String reference, State state) {
        return jongo.getCollection(GAMES_COLLECTION).findOne("{ reference: #, state: # }", reference.toUpperCase(), state).as(Game.class);
    }

    public boolean endRound(Game game) {
        int n = jongo.getCollection(GAMES_COLLECTION)
                .update("{ _id: #, round: # }", new ObjectId(game.getId()), game.getRound())
                .with("{ $inc: { round: 1 } }")
                .getN();
        game.nextRound();
        return n > 0;
    }
}
