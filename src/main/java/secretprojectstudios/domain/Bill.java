package secretprojectstudios.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.jongo.marshall.jackson.oid.MongoId;
import org.jongo.marshall.jackson.oid.MongoObjectId;

import java.util.*;

import static org.apache.commons.lang3.RandomStringUtils.randomAlphanumeric;
import static org.apache.commons.lang3.RandomStringUtils.randomNumeric;

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
        Random random = new Random();
        Collections.shuffle(ideals, random);
        passEffect = new HashMap<>();
        failEffect = new HashMap<>();
        int [] weights = new int[] {
                randomValue(random),
                randomValue(random),
                randomValue(random),
                randomValue(random),
                randomValue(random),
                randomValue(random)
        };
        int from = -1;
        if (random.nextFloat() < 0.25f) {
            from = random.nextInt(3);
            int to = random.nextInt(3) + 3;
            ideals.set(to, ideals.get(from));
            weights[to] = weights[from] * -1 + random.nextInt(2) - 1;
        } else if (random.nextFloat() < 0.33f) {
            from = random.nextInt(3);
            int to = random.nextInt(3) + 3;
            ideals.set(to, ideals.get(from));
            weights[to] = weights[from] * -1 + random.nextInt(2) - 1;
        }
        if (random.nextFloat() < 0.1f) {
            int from2 = random.nextInt(3);
            if (from2 != from) {
                int to = random.nextInt(3) + 3;
                ideals.set(to, ideals.get(from2));
                weights[to] = weights[from2] * -1 + random.nextInt(2) - 1;
            }
        } else if (random.nextFloat() < 0.1f) {
            int from2 = random.nextInt(3);
            if (from2 != from) {
                int to = random.nextInt(3) + 3;
                ideals.set(to, ideals.get(from2));
                weights[to] = weights[from2] * -1 + random.nextInt(2) - 1;
            }
        }
        if (random.nextFloat() < 0.7f && weights[0] * weights[1] > 0)
            weights[1] *= -1;
        if (random.nextFloat() < 0.7f && weights[1] * weights[2] > 0)
            weights[2] *= -1;
        if (random.nextFloat() < 0.7f && weights[3] * weights[4] > 0)
            weights[4] *= -1;
        if (random.nextFloat() < 0.7f && weights[4] * weights[5] > 0)
            weights[5] *= -1;
        for (int i = 0; i < 3; i++) {
            if (weights[i] != 0)
                passEffect.put(ideals.get(i), Math.max(weights[i], -2));
            if (weights[i+3] != 0)
                failEffect.put(ideals.get(i+3), Math.max(weights[i+3], -2));
        }
    }

    public String getId() {
        return id;
    }

    private int randomValue(Random random) {
        int result = random.nextInt(6) - 2;
        if (result == 0) {
            result = random.nextInt(2) + 1;
        }
        return result;
    }

    private String generateName() {
        return String.format("Bill #%d", Integer.parseInt(randomNumeric(4)));
    }

    public Map<Ideal, Integer> getPassEffect() {
        return passEffect;
    }

    public Map<Ideal, Integer> getFailEffect() {
        return failEffect;
    }
}
