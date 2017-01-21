package secretprojectstudios.resources;

import com.google.inject.Inject;
import org.apache.commons.lang3.RandomStringUtils;
import secretprojectstudios.ResourceUtilities;
import secretprojectstudios.domain.Game;
import secretprojectstudios.domain.GameState;
import secretprojectstudios.domain.Player;
import secretprojectstudios.repository.GameRepository;
import secretprojectstudios.repository.PlayerRepository;
import secretprojectstudios.resources.requests.GameCreateRequest;

import javax.ws.rs.*;
import javax.ws.rs.core.*;
import java.net.URI;
import java.util.List;

@Path("/games")
public class GameResource {
    private final GameRepository gameRepository;
    private final PlayerRepository playerRepository;

    @Inject
    public GameResource(GameRepository gameRepository, PlayerRepository playerRepository) {
        this.gameRepository = gameRepository;
        this.playerRepository = playerRepository;
    }

    @PUT
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
//        String joinQrCode = ResourceUtilities.encodeToQr(String.format("http://utopia-client.s3-website-ap-southeast-2.amazonaws.com/join/%s", game.getReference()));
        return new GameState(game.getReference(), players, "");
    }

    @PUT
    @Path("/{reference}/{playerName}")
    @Produces(MediaType.APPLICATION_JSON)
    public Player joinGame(@PathParam("reference") String reference, @PathParam("playerName") String playerName) {
        Game game = gameRepository.getByReference(reference);
        Player player = new Player(playerName, game.getId());
        return playerRepository.add(player);
    }
}
