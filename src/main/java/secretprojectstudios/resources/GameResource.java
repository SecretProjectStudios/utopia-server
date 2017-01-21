package secretprojectstudios.resources;

import com.google.inject.Inject;
import org.apache.commons.lang3.RandomStringUtils;
import org.jongo.Jongo;
import secretprojectstudios.domain.Game;
import secretprojectstudios.domain.Player;
import secretprojectstudios.repository.GameRepository;
import secretprojectstudios.repository.PlayerRepository;
import secretprojectstudios.resources.requests.GameCreateRequest;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/games")
public class GameResource {

    private final GameRepository gameRepository;
    private final PlayerRepository playerRepository;

    @Inject
    public GameResource(GameRepository gameRepository, PlayerRepository playerRepository) {
        this.gameRepository = gameRepository;
        this.playerRepository = playerRepository;
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Game createNewGame(GameCreateRequest request) {
        Game game = gameRepository.add(new Game(RandomStringUtils.randomAlphabetic(6)));
        Player player = playerRepository.add(new Player(request.getRequestedName(), game.getId()));
        return game;
    }

    @GET
    @Path("/{reference}")
    @Produces(MediaType.APPLICATION_JSON)
    public Game getGame(String reference) {
        return gameRepository.get(reference);
    }

    @GET
    @Path("/{reference}/{player}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getClientGameStateForPlayer(String reference, String player) {
        return Response.status(501).build();
    }

    @PUT
    @Path("/{reference}/{player}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response joinGame(String reference, String player) {
        return Response.status(501).build();
    }

    @DELETE
    @Path("/{reference}/{player}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response leaveGame(String reference, String player) {
        return Response.status(501).build();
    }

    @POST
    @Path("/{reference}/{player}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response vote(String reference, String player) {
        return Response.status(501).build();
    }
}
