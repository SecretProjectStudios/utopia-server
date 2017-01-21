package secretprojectstudios.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.util.List;

@JsonSerialize
public class GameState {
    private List<Player> players;

    public GameState(List<Player> players) {
        this.players = players;
    }

    @JsonProperty
    public List<Player> getPlayers() {
        return players;
    }
}
