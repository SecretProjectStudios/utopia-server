package secretprojectstudios.setup.module;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.Singleton;
import com.mongodb.DB;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import org.jongo.Jongo;
import secretprojectstudios.setup.configuration.UtopiaConfiguration;

public class ServiceModule extends AbstractModule {
    @Override
    protected void configure() {
    }

    @Provides
    public DB getMongo(UtopiaConfiguration configuration) {
        MongoClientURI uri = new MongoClientURI(configuration.getDatabase().getHost());

        MongoClient client = new MongoClient(uri);

        return client.getDB(uri.getDatabase());
    }

    @Provides
    @Singleton
    public Jongo getJongo(DB db) {
        return new Jongo(db);
    }
}
