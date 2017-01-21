package secretprojectstudios.resources.requests;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

@JsonDeserialize
public class GameCreateRequest {
    private String playerName;

    public GameCreateRequest(@JsonProperty("playerName") String playerName) {
        this.playerName = playerName;
    }

    public String getPlayerName() {
        return playerName;
    }
}
