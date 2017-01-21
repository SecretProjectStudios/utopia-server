package secretprojectstudios.resources.requests;

public class GameCreateRequest {
    private String requestedName;

    public GameCreateRequest(String requestedName) {
        this.requestedName = requestedName;
    }

    public String getRequestedName() {
        return requestedName;
    }
}
