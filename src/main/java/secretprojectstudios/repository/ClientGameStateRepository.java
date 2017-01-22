package secretprojectstudios.repository;

import com.google.inject.Inject;
import secretprojectstudios.domain.*;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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
        List<Player> allPlayers = playerRepository.getAll(player.getGameId());
        Bill bill = null;
        Votes votes = null;

        if (game.getCurrentBill() != null) {
            bill = billRepository.get(game.getCurrentBill());
            List<PlayerVote> playerVotes = playerVoteRepository.getAll(bill.getId());

            Map<Player, Vote> playerVotesMap = playerVotes.parallelStream()
                    .collect(toMap(v -> playerRepository.get(v.getPlayerId()), PlayerVote::getVote));

            votes = new Votes(playerVotesMap);

            if (allPlayers.size() == votes.getCount() && gameRepository.endRound(game)) {
                bill = new Bill();
                billRepository.save(bill);
                game.setNewBill(bill);
                gameRepository.save(game);
            }
        }


        final Votes finalVotes = votes;
        List<SimplePlayer> simplePlayers = allPlayers.stream()
                .map(p -> new SimplePlayer(p.getId(), p.getName(), finalVotes != null && finalVotes.hasVoted(p)))
                .collect(Collectors.toList());
        return new ClientGameState(player, simplePlayers, game, bill, votes);
    }
}
