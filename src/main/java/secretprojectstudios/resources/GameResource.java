package secretprojectstudios.resources;

import com.google.inject.Inject;
import org.jongo.Jongo;
import secretprojectstudios.domain.Game;
import secretprojectstudios.repository.GameRepository;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/games")
public class GameResource {

    private final GameRepository gameRepository;

    @Inject
    public GameResource(GameRepository gameRepository) {
        this.gameRepository = gameRepository;
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Game createNewGame() {
        return gameRepository.add(new Game("test"));
    }

    @GET
    @Path("/{code}")
    @Produces(MediaType.APPLICATION_JSON)
    public Game getGame(String code) {
        return gameRepository.get(code);
    }
}
