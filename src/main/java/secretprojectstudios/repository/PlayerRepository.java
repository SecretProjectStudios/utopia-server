package secretprojectstudios.repository;

import com.google.inject.Inject;
import org.jongo.Jongo;
import secretprojectstudios.domain.Game;
import secretprojectstudios.domain.Player;

public class PlayerRepository {

    private static final String PLAYER_COLLECTION = "players";

    private final Jongo jongo;

    @Inject
    public PlayerRepository(Jongo jongo) {

        this.jongo = jongo;
    }

    public Player add(Player player) {
        jongo.getCollection(PLAYER_COLLECTION).save(player);
        return player;
    }

    public Player get(String id) {
        return jongo.getCollection(PLAYER_COLLECTION).findOne("{ _id: # }", id).as(Player.class);
    }
}
