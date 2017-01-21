package secretprojectstudios.resources;

import com.google.inject.Inject;
import org.jongo.Jongo;
import org.jongo.MongoCursor;
import secretprojectstudios.domain.Game;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;

import static java.util.stream.Collectors.toList;
import static java.util.stream.StreamSupport.stream;

@Path("/")
public class IndexResource {

    private final Jongo jongo;

    @Inject
    public IndexResource(Jongo jongo) {

        this.jongo = jongo;
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Game> index() {
        MongoCursor<Game> games = jongo.getCollection("games").find().as(Game.class);
        return stream(games.spliterator(), false).collect(toList());
    }

}
