package secretprojectstudios.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.util.List;

@JsonSerialize
public class GameState {
    private String reference;
    private List<Player> players;
    private String joinQrCode;

    public GameState(String reference,
                     List<Player> players,
                     String joinQrCode) {
        this.reference = reference;
        this.players = players;
        this.joinQrCode = joinQrCode;
    }

    @JsonProperty
    public String getReference() {
        return reference;
    }

    @JsonProperty
    public List<Player> getPlayers() {
        return players;
    }

    @JsonProperty
    public String getJoinQrCode() {
        return joinQrCode;
    }
}
