package secretprojectstudios.resources;

import com.google.inject.Inject;
import org.apache.commons.lang3.RandomStringUtils;
import secretprojectstudios.domain.*;
import secretprojectstudios.repository.BillRepository;
import secretprojectstudios.repository.GameRepository;
import secretprojectstudios.repository.PlayerRepository;
import secretprojectstudios.resources.requests.GameCreateRequest;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/games")
public class GameResource {
    private final GameRepository gameRepository;
    private final PlayerRepository playerRepository;
    private final BillRepository billRepository;

    @Inject
    public GameResource(GameRepository gameRepository,
                        PlayerRepository playerRepository,
                        BillRepository billRepository) {
        this.gameRepository = gameRepository;
        this.playerRepository = playerRepository;
        this.billRepository = billRepository;
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Player createNewGame(GameCreateRequest request) {
        String reference = RandomStringUtils.randomAlphabetic(6).toUpperCase();
        Game game = gameRepository.save(new Game(reference));
        return playerRepository.add(new Player(request.getPlayerName(), game.getId()));
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public GameState getGame(@PathParam("id") String id) {
        Game game = gameRepository.get(id);
        List<Player> players = playerRepository.getAll(id);
        return new GameState(game.getReference(), players);
    }

    @PUT
    @Path("/{id}/start")
    @Produces(MediaType.APPLICATION_JSON)
    public GameState startGame(@PathParam("id") String id) {
        Bill bill = new Bill();
        billRepository.save(bill);
        Game game = gameRepository.get(id);
        game.setNewBill(bill);
        game.start();
        game = gameRepository.save(game);
        List<Player> players = playerRepository.getAll(id);
        return new GameState(game.getReference(), players);
    }

    @POST
    @Path("/{reference}/{playerName}")
    @Produces(MediaType.APPLICATION_JSON)
    public Player joinGame(@PathParam("reference") String reference, @PathParam("playerName") String playerName) {
        Game game = gameRepository.getByReferenceAndState(reference, State.NotStarted);
        Player player = new Player(playerName, game.getId());
        return playerRepository.add(player);
    }
}
