package secretprojectstudios.repository;

import com.google.inject.Inject;
import secretprojectstudios.domain.*;
import secretprojectstudios.repository.BillRepository;
import secretprojectstudios.repository.GameRepository;
import secretprojectstudios.repository.PlayerRepository;
import secretprojectstudios.repository.PlayerVoteRepository;

import java.util.List;
import java.util.Map;

import static java.util.stream.Collectors.toMap;

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
        Bill bill = null;
        Votes votes = null;

        if (game.getCurrentBill() != null) {
            bill = billRepository.get(game.getCurrentBill());
            List<PlayerVote> playerVotes = playerVoteRepository.getAll(bill.getId());

            Map<Player, Vote> playerVotesMap = playerVotes.parallelStream()
                    .collect(toMap(v -> playerRepository.get(v.getPlayerId()), PlayerVote::getVote));

            votes = new Votes(playerVotesMap);
        }

        return new ClientGameState(player, game, bill, votes);
    }
}
