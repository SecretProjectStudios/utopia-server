package secretprojectstudios.repository;

import com.google.inject.Inject;
import org.jongo.Jongo;
import org.jongo.MongoCollection;
import org.jongo.MongoCursor;
import secretprojectstudios.domain.PlayerVote;

import java.util.List;

import static java.util.stream.Collectors.toList;
import static java.util.stream.StreamSupport.stream;

public class PlayerVoteRepository {

    private static final String PLAYER_VOTES_COLLECTION = "playerVotes";

    private final Jongo jongo;

    @Inject
    public PlayerVoteRepository(Jongo jongo) {

        this.jongo = jongo;
    }

    public PlayerVote add(PlayerVote playerVote) {
        MongoCollection collection = jongo.getCollection(PLAYER_VOTES_COLLECTION);
        if (collection.count("{ playerId: #, billId: # }", playerVote.getPlayerId(), playerVote.getBillId()) == 0)
            collection.save(playerVote);
        return playerVote;
    }

    public List<PlayerVote> getAll(String billId) {
        MongoCursor<PlayerVote> votes = jongo.getCollection(PLAYER_VOTES_COLLECTION)
                .find("{ billId: # }", billId)
                .as(PlayerVote.class);

        return stream(votes.spliterator(), false).collect(toList());
    }
}
