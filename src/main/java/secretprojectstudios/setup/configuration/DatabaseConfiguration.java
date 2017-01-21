package secretprojectstudios.setup.configuration;

import javax.validation.constraints.NotNull;

public class DatabaseConfiguration {

    @NotNull
    private String host;

    public String getHost() {
        return host;
    }
}
