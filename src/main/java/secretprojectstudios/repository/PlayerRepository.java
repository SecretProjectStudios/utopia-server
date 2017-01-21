package secretprojectstudios.repository;

import com.google.inject.Inject;
import org.jongo.Jongo;
import secretprojectstudios.domain.Player;

import java.util.ArrayList;
import java.util.List;

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
        return jongo.getCollection(PLAYER_COLLECTION).findOne("{ _id: #, player: # }", id).as(Player.class);
    }

    public List<Player> getAll(String reference) {
        List<Player> players = new ArrayList<>();
        jongo.getCollection(PLAYER_COLLECTION)
                .find("{ gameId: # }", reference)
                .as(Player.class)
                .forEach(players::add);
        return players;
    }

    public void remove(String id) {
        jongo.getCollection(PLAYER_COLLECTION).remove(id);
    }
}
