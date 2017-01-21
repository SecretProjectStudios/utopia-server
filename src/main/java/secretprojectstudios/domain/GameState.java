package secretprojectstudios.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.util.List;

@JsonSerialize
public class GameState {
    private String reference;
    private List<Player> players;

    public GameState(String reference,
                     List<Player> players) {
        this.reference = reference;
        this.players = players;
    }

    @JsonProperty
    public String getReference() {
        return reference;
    }

    @JsonProperty
    public List<Player> getPlayers() {
        return players;
    }
}
