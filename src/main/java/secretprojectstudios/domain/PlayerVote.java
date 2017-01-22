package secretprojectstudios.domain;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.jongo.marshall.jackson.oid.MongoId;
import org.jongo.marshall.jackson.oid.MongoObjectId;

public class PlayerVote {
    @MongoObjectId
    @MongoId
    private String id;
    @JsonProperty
    private String playerId;
    @JsonProperty
    private String billId;
    @JsonProperty
    private Vote vote;

    @JsonCreator
    protected PlayerVote(@MongoId String id,
                         @JsonProperty("playerId") String playerId,
                         @JsonProperty("billId") String billId,
                         @JsonProperty("vote") Vote vote) {
        this.id = id;
        this.billId = billId;
        this.playerId = playerId;
        this.vote = vote;
    }

    public PlayerVote(String playerId,
                      String billId,
                      Vote vote) {
        this(null, playerId, billId, vote);
    }

    public String getPlayerId() {
        return playerId;
    }

    public Vote getVote() {
        return vote;
    }

    public String getBillId() {
        return billId;
    }
}
