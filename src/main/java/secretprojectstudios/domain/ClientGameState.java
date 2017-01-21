package secretprojectstudios.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@JsonSerialize
public class ClientGameState {
    private Game game;
    private Player player;

    public ClientGameState(Player player, Game game) {
        this.player = player;
        this.game = game;
    }

    @JsonProperty
    public Game getGame() {
        return game;
    }

    @JsonProperty
    public Player getPlayer() {
        return player;
    }
}
