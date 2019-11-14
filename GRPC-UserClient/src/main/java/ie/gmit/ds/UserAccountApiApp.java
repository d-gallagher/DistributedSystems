package ie.gmit.ds;

import io.dropwizard.Application;
import io.dropwizard.Configuration;
import io.dropwizard.setup.Environment;

public class UserAccountApiApp extends Application<Configuration> {

    public static void main(String[] args) throws Exception {
        new UserAccountApiApp().run(args);
    }

    @Override
    public void run(Configuration configuration, Environment environment) throws Exception {

        final UserAccountApiResource resource = new UserAccountApiResource();

        //HealthCheck criteria
        final UserAccountHealthCheck healthCheck = new UserAccountHealthCheck();
        environment.healthChecks().register("example", healthCheck);

        environment.jersey().register(resource);
    }
}
