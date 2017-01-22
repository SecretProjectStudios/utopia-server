package secretprojectstudios.domain;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Map;

import static java.util.Arrays.stream;
import static java.util.function.Function.identity;
import static java.util.stream.Collectors.toMap;

public class Votes {
    @JsonProperty
    private final Map<Vote, Integer> counts;
    @JsonProperty
    private final Map<Player, Vote> players;

    public Votes(Map<Player, Vote> playerVotes) {
        this.players = playerVotes;
        this.counts = stream(Vote.values()).collect(toMap(identity(), v -> 0));

        playerVotes.values().forEach(v -> counts.put(v, counts.get(v) + 1));
    }

    public boolean hasVoted(Player player) {
        return players.containsKey(player);
    }

    public int getCount() {
        return players.size();
    }
}
