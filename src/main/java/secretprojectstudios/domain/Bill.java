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
        generatePassAndFailOutcomes();
    }

    private void generatePassAndFailOutcomes() {
        List<Ideal> ideals = Arrays.asList(Ideal.values());
        Collections.shuffle(ideals);
        Random random = new Random();
        passEffect = new HashMap<>();
        failEffect = new HashMap<>();
        int [] weights = new int[] {
                random.nextInt(4),
                random.nextInt(2) - 2,
                randomValue(random),
                random.nextInt(4),
                random.nextInt(2) - 2,
                randomValue(random)
        };
        if (random.nextFloat() < 0.25f) {
            int from = random.nextInt(3);
            int to = random.nextInt(3) + 3;
            ideals.set(to, ideals.get(from));
            weights[to] = weights[from] * -1 + random.nextInt(2) - 1;
        } else if (random.nextFloat() < 0.33f) {
            int from = random.nextInt(3);
            int to = random.nextInt(3) + 3;
            ideals.set(to, ideals.get(from));
            weights[to] = weights[from] * -1 + random.nextInt(2) - 1;
        }
        if (random.nextFloat() < 0.1f) {
            int from = random.nextInt(3);
            int to = random.nextInt(3) + 3;
            ideals.set(to, ideals.get(from));
            weights[to] = weights[from] * -1 + random.nextInt(2) - 1;
        } else if (random.nextFloat() < 0.1f) {
            int from = random.nextInt(3);
            int to = random.nextInt(3) + 3;
            ideals.set(to, ideals.get(from));
            weights[to] = weights[from] * -1 + random.nextInt(2) - 1;
        }
        for (int i = 0; i < 3; i++) {
            if (weights[i] != 0)
                passEffect.put(ideals.get(i), weights[i]);
            if (weights[i] != 0)
                failEffect.put(ideals.get(i+3), weights[i+3]);
        }
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
