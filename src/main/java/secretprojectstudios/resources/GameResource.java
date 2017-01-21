package secretprojectstudios.resources;

import com.google.inject.Inject;
import org.apache.commons.lang3.RandomStringUtils;
import org.jongo.Jongo;
import secretprojectstudios.domain.Game;
import secretprojectstudios.domain.Player;
import secretprojectstudios.repository.GameRepository;
import secretprojectstudios.resources.requests.GameCreateRequest;

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
    public Game createNewGame(GameCreateRequest request) {
        Game game = gameRepository.add(new Game(RandomStringUtils.randomAlphabetic(6)));
        Player player = new Player(request.getRequestedName(), game.getId());
        return game;
    }

    @GET
    @Path("/{code}")
    @Produces(MediaType.APPLICATION_JSON)
    public Game getGame(String code) {
        return gameRepository.get(code);
    }
}
