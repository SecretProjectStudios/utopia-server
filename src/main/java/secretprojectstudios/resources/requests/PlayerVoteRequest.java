package secretprojectstudios.resources.requests;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import secretprojectstudios.domain.Vote;

@JsonDeserialize
public class PlayerVoteRequest {
    private Vote vote;

    public PlayerVoteRequest(@JsonProperty("vote") Vote vote) {
        this.vote = vote;
    }

    public Vote getVote() {
        return vote;
    }
}
