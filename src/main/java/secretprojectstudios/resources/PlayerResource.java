package secretprojectstudios.resources;

import com.google.inject.Inject;
import secretprojectstudios.domain.*;
import secretprojectstudios.repository.*;
import secretprojectstudios.resources.requests.PlayerVoteRequest;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/players")
public class PlayerResource {

    private final GameRepository gameRepository;
    private final PlayerRepository playerRepository;
    private final BillRepository billRepository;
    private final PlayerVoteRepository playerVoteRepository;
    private final ClientGameStateRepository clientGameStateRepository;

    @Inject
    public PlayerResource(GameRepository gameRepository,
                          PlayerRepository playerRepository,
                          BillRepository billRepository,
                          PlayerVoteRepository playerVoteRepository,
                          ClientGameStateRepository clientGameStateRepository) {
        this.gameRepository = gameRepository;
        this.playerRepository = playerRepository;
        this.billRepository = billRepository;
        this.playerVoteRepository = playerVoteRepository;
        this.clientGameStateRepository = clientGameStateRepository;
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{id}")
    public ClientGameState getPlayer(@PathParam("id") String id) {
        Player player = playerRepository.get(id);
        return clientGameStateRepository.get(player);
    }

    @DELETE
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{id}")
    public Response leaveGame(@PathParam("id") String id) {
        playerRepository.remove(id);
        return Response.status(200).build();
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{id}/vote")
    public ClientGameState vote(@PathParam("id") String id, PlayerVoteRequest vote) {
        Player player = playerRepository.get(id);
        Game game = gameRepository.get(player.getGameId());
        Bill bill = billRepository.get(game.getCurrentBill());
        PlayerVote playerVote = new PlayerVote(id, bill.getId(), vote.getVote());
        playerVoteRepository.add(playerVote);
        return clientGameStateRepository.get(player);
    }
}
