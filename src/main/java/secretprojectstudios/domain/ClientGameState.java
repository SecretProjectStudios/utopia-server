package secretprojectstudios.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.util.Map;

@JsonSerialize
public class ClientGameState {
    private Game game;
    private Player player;
    private Bill bill;
    private Votes votes;

    public ClientGameState(Player player, Game game, Bill bill, Votes votes) {
        this.player = player;
        this.game = game;
        this.bill = bill;
        this.votes = votes;
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
    public Bill getBill() {
        return bill;
    }

    @JsonProperty
    public Votes getVotes() {
        return votes;
    }
}
