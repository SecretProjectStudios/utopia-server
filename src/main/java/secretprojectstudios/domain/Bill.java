package secretprojectstudios.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.jongo.marshall.jackson.oid.MongoId;
import org.jongo.marshall.jackson.oid.MongoObjectId;

import java.util.*;

import static org.apache.commons.lang3.RandomStringUtils.randomAlphanumeric;

public class Bill {
    @MongoObjectId
    @MongoId
    private String id;
    @JsonProperty
    private String text;
    @JsonProperty
    private Map<Ideal, Integer> passEffect;
    @JsonProperty
    private Map<Ideal, Integer> failEffect;

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

    public String getId() {
        return id;
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
}
