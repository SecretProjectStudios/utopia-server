package secretprojectstudios;

import com.hubspot.dropwizard.guice.GuiceBundle;
import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import org.eclipse.jetty.servlets.CrossOriginFilter;
import secretprojectstudios.setup.configuration.UtopiaConfiguration;
import secretprojectstudios.setup.module.ServiceModule;

import javax.servlet.DispatcherType;
import javax.servlet.FilterRegistration;
import java.util.EnumSet;

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
        final FilterRegistration.Dynamic cors =
                environment.servlets().addFilter("CORS", CrossOriginFilter.class);
//        cors.setInitParameter("allowedOrigins", "http://utopia-client.s3-website-ap-southeast-2.amazonaws.com,http://localhost");
        cors.setInitParameter("allowedOrigins", "*");
        cors.setInitParameter("allowedHeaders", "X-Requested-With,Content-Type,Accept,Origin");
        cors.setInitParameter("allowedMethods", "OPTIONS,GET,PUT,POST,DELETE,HEAD");

        cors.addMappingForUrlPatterns(EnumSet.allOf(DispatcherType.class), true, "/*");
    }
}
