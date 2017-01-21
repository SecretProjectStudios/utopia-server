package secretprojectstudios.resources.requests;

public class GameCreateRequest {
    private String playerName;

    public GameCreateRequest(String playerName) {
        this.playerName = playerName;
    }

    public String getPlayerName() {
        return playerName;
    }
}
