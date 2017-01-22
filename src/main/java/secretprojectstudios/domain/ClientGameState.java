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
    private Votes votes;

    public ClientGameState(Player player, List<SimplePlayer> players, Game game, Bill bill, Votes votes) {
        this.player = player;
        this.players = players;
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
    public List<SimplePlayer> getPlayers() {
        return players;
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
