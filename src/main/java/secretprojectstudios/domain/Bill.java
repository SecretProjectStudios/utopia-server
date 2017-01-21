package secretprojectstudios.domain;

import org.apache.commons.lang3.RandomStringUtils;
import secretprojectstudios.resources.requests.PlayerVoteRequest;

import java.util.*;

import static org.apache.commons.lang3.RandomStringUtils.randomAlphanumeric;

public class Bill {
    private String text;
    private Map<Ideal, Integer> passEffect;
    private Map<Ideal, Integer> failEffect;
    private Map<String, Vote> votes;

    public Bill() {
        text = generateName();
        List<Ideal> ideals = Arrays.asList(Ideal.values());
        Collections.shuffle(ideals);
        Random random = new Random();
        passEffect = new HashMap<>();
        failEffect = new HashMap<>();
        passEffect.put(ideals.get(0), random.nextInt(4));
        passEffect.put(ideals.get(1), random.nextInt(2) - 2);
        passEffect.put(ideals.get(2), randomValue(random));
        failEffect.put(ideals.get(3), random.nextInt(4));
        failEffect.put(ideals.get(4), random.nextInt(2) - 2);
        failEffect.put(ideals.get(5), randomValue(random));
    }

    public void setVotes(String id, Vote vote)
    {
        votes.put(id, vote);
    }

    private int randomValue(Random random) {
        int result = random.nextInt(6) - 3;
        if (result == 0) {
            result = random.nextInt(2) + 1;
        }
        return result;
    }

    private String generateName() {
        return String.format("Bill %s.%s", randomAlphanumeric(3), randomAlphanumeric(1));
    }

    public void addVote(String id, Vote vote) {
        votes.put(id, vote);
    }
}
