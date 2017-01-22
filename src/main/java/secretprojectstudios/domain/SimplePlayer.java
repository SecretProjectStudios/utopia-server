package secretprojectstudios.domain;

import com.fasterxml.jackson.annotation.JsonProperty;

public class SimplePlayer {
    private String id;
    private String name;
    private boolean voted;

    public SimplePlayer(String id, String name, boolean voted) {
        this.id = id;
        this.name = name;
        this.voted = voted;
    }

    @JsonProperty("_id")
    public String getId() {
        return id;
    }

    @JsonProperty
    public String getName() {
        return name;
    }

    public boolean isVoted() {
        return voted;
    }
}
