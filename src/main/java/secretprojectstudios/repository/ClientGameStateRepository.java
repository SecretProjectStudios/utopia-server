package secretprojectstudios.repository;

import com.google.inject.Inject;
import secretprojectstudios.domain.*;

import java.util.List;
import java.util.stream.Collectors;

public class ClientGameStateRepository {

    private final GameRepository gameRepository;
    private final PlayerRepository playerRepository;
    private final BillRepository billRepository;
    private final PlayerVoteRepository playerVoteRepository;

    @Inject
    public ClientGameStateRepository(GameRepository gameRepository,
                                     PlayerRepository playerRepository,
                                     BillRepository billRepository,
                                     PlayerVoteRepository playerVoteRepository) {
        this.gameRepository = gameRepository;
        this.playerRepository = playerRepository;
        this.billRepository = billRepository;
        this.playerVoteRepository = playerVoteRepository;
    }

    public ClientGameState get(Player player) {
        Game game = gameRepository.get(player.getGameId());
        List<Player> allPlayers = playerRepository.getAll(player.getGameId());
        Bill bill = null;
        List<PlayerVote> playerVotes = null;

        if (game.getCurrentBill() != null) {
            bill = billRepository.get(game.getCurrentBill());
            playerVotes = playerVoteRepository.getAll(bill.getId());

            if (allPlayers.size() == playerVotes.size() && gameRepository.endRound(game)) {
                bill = new Bill();
                billRepository.save(bill);
                game.setNewBill(bill);
                gameRepository.save(game);
                playerVotes = null;
            }
        }


        final List<PlayerVote> finalVotes = playerVotes;
        List<SimplePlayer> simplePlayers = allPlayers.stream()
                .map(p -> {
                    boolean voted = finalVotes != null && finalVotes.stream().anyMatch(v -> v.getPlayerId().equals(p.getId()));
                    return new SimplePlayer(p.getId(), p.getName(), voted);
                })
                .collect(Collectors.toList());
        return new ClientGameState(player, simplePlayers, game, bill);
    }
}
