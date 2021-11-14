package com.playwing.matchmaking.service;

import com.playwing.matchmaking.dto.MatchmakingResult;
import com.playwing.matchmaking.dto.Player;
import com.playwing.matchmaking.match.MatchMaker;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

import static java.util.Objects.nonNull;

public class MatchmakingServiceImpl implements MatchmakingService {

    private final MatchMaker matchMaker;

    public MatchmakingServiceImpl(MatchMaker matchMaker) {
        this.matchMaker = matchMaker;
    }

    @Override
    public MatchmakingResult splitIntoTeams(List<Player> players, int skillDelta, int teamSize) {
        if (nonNull(players) && !players.isEmpty()) {
            LinkedList<Player> queuedPlayers = new LinkedList<>(players);
            queuedPlayers.sort(Comparator.comparing(Player::getSkill, Comparator.reverseOrder()));
            return matchMaker.makeMatches(queuedPlayers, skillDelta, teamSize);
        }
        return MatchmakingResult.Empty();
    }
}
