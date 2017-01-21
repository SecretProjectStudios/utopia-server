package secretprojectstudios.resources;

import com.google.inject.Inject;
import secretprojectstudios.domain.ClientGameState;
import secretprojectstudios.domain.Game;
import secretprojectstudios.domain.Player;
import secretprojectstudios.repository.GameRepository;
import secretprojectstudios.repository.PlayerRepository;
import secretprojectstudios.resources.requests.PlayerVoteRequest;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/player/{id}")
public class PlayerResource {

    private final GameRepository gameRepository;
    private final PlayerRepository playerRepository;

    @Inject
    public PlayerResource(GameRepository gameRepository, PlayerRepository playerRepository) {
        this.gameRepository = gameRepository;
        this.playerRepository = playerRepository;
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public ClientGameState getPlayer(String id) {
        Player player = playerRepository.get(id);
        Game game = gameRepository.get(player.getGameId());
        return new ClientGameState(player, game);
    }

    @DELETE
    @Produces(MediaType.APPLICATION_JSON)
    public Response leaveGame(@PathParam("id") String id) {
        playerRepository.remove(id);
        return Response.status(200).build();
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Response vote(@PathParam("id") String id, PlayerVoteRequest vote) {

        return Response.status(501).build();
    }
}
