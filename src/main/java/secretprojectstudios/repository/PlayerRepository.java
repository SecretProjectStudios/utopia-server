package secretprojectstudios.repository;

import com.google.inject.Inject;
import org.bson.types.ObjectId;
import org.jongo.Jongo;
import secretprojectstudios.domain.Player;

import java.util.List;

import static java.util.stream.Collectors.toList;
import static java.util.stream.StreamSupport.stream;

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
        return jongo.getCollection(PLAYER_COLLECTION).findOne("{ _id: # }", new ObjectId(id)).as(Player.class);
    }

    public List<Player> getAll(String id) {
        return stream(jongo.getCollection(PLAYER_COLLECTION)
                .find("{ gameId: # }", id)
                .as(Player.class)
                .spliterator(), false)
                .collect(toList());
    }

    public void remove(String id) {
        jongo.getCollection(PLAYER_COLLECTION).remove(id);
    }
}
