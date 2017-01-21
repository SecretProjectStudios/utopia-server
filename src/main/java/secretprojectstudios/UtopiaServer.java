package secretprojectstudios;

import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import secretprojectstudios.resources.IndexResource;
import secretprojectstudios.setup.configuration.UtopiaConfiguration;

public class UtopiaServer extends Application<UtopiaConfiguration> {

    public static void main(String[] args) throws Exception {
        new UtopiaServer().run(args);
    }

    @Override
    public String getName() {
        return "utopia-server";
    }

    @Override
    public void initialize(Bootstrap<UtopiaConfiguration> bootstrap) {

    }

    public void run(UtopiaConfiguration utopiaConfiguration, Environment environment) throws Exception {
        environment.jersey().register(IndexResource.class);
    }
}
