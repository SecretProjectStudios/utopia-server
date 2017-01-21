package secretprojectstudios;

import com.hubspot.dropwizard.guice.GuiceBundle;
import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import secretprojectstudios.setup.configuration.UtopiaConfiguration;
import secretprojectstudios.setup.module.ServiceModule;

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
        GuiceBundle<UtopiaConfiguration> guiceBundle = GuiceBundle.<UtopiaConfiguration>newBuilder()
                .addModule(new ServiceModule())
                .enableAutoConfig(getClass().getPackage().getName())
                .setConfigClass(UtopiaConfiguration.class)
                .build();

        bootstrap.addBundle(guiceBundle);
    }

    public void run(UtopiaConfiguration utopiaConfiguration, Environment environment) throws Exception {
    }
}
