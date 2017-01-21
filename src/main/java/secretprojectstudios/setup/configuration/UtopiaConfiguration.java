package secretprojectstudios.setup.configuration;

import io.dropwizard.Configuration;

import javax.validation.constraints.NotNull;

public class UtopiaConfiguration extends Configuration {

    @NotNull
    private DatabaseConfiguration database;

    public DatabaseConfiguration getDatabase() {
        return database;
    }
}
