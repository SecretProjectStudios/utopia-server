package secretprojectstudios.resources;

import com.google.inject.Inject;
import org.apache.commons.lang3.RandomStringUtils;
import secretprojectstudios.domain.ClientGameState;
import secretprojectstudios.domain.Game;
import secretprojectstudios.domain.GameState;
import secretprojectstudios.domain.Player;
import secretprojectstudios.repository.GameRepository;
import secretprojectstudios.repository.PlayerRepository;
import secretprojectstudios.resources.requests.GameCreateRequest;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/players")
public class PlayerResource {

    private final GameRepository gameRepository;
    private final PlayerRepository playerRepository;

    @Inject
    public PlayerResource(GameRepository gameRepository, PlayerRepository playerRepository) {
        this.gameRepository = gameRepository;
        this.playerRepository = playerRepository;
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public ClientGameState getPlayer(String id) {
        Player player = playerRepository.get(id);
        Game game = gameRepository.get(player.getGameId());
        return new ClientGameState(player, game);
    }
    @DELETE
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response leaveGame(String id) {
        return Response.status(501).build();
    }

    @POST
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response vote(String id) {
        return Response.status(501).build();
    }
}
