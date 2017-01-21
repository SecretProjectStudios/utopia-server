package secretprojectstudios.setup.health;

import com.google.inject.Inject;
import com.hubspot.dropwizard.guice.InjectableHealthCheck;
import com.mongodb.CommandResult;
import org.jongo.Jongo;

public class MongoHealthCheck extends InjectableHealthCheck {

    private final Jongo jongo;

    @Inject
    public MongoHealthCheck(Jongo jongo) {

        this.jongo = jongo;
    }

    @Override
    public String getName() {
        return "mongo";
    }

    @Override
    protected Result check() throws Exception {
        try {
            CommandResult stats = jongo.getDatabase().getStats();
            if (!stats.ok()) {
                return Result.unhealthy(stats.getException());
            }
        } catch (Throwable t) {
            return Result.unhealthy(t);
        }

        return Result.healthy();
    }
}
