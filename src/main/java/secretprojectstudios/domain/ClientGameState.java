package secretprojectstudios.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.util.List;

@JsonSerialize
public class ClientGameState {
    private Game game;
    private Player player;
    private List<SimplePlayer> players;
    private Bill bill;

    public ClientGameState(Player player, List<SimplePlayer> players, Game game, Bill bill) {
        this.player = player;
        this.players = players;
        this.game = game;
        this.bill = bill;
    }

    @JsonProperty
    public Game getGame() {
        return game;
    }

    @JsonProperty
    public Player getPlayer() {
        return player;
    }

    @JsonProperty
    public List<SimplePlayer> getPlayers() {
        return players;
    }

    @JsonProperty
    public Bill getBill() {
        return bill;
    }
}
