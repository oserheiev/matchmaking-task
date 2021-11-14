package com.playwing.matchmaking.match;

import com.playwing.matchmaking.dto.Match;
import com.playwing.matchmaking.dto.MatchmakingResult;
import com.playwing.matchmaking.dto.Player;
import com.playwing.matchmaking.team.TeamBalancer;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class MatchMakerImpl implements MatchMaker {

    private final TeamBalancer teamBalancer;

    public MatchMakerImpl(TeamBalancer teamBalancer) {
        this.teamBalancer = teamBalancer;
    }

    @Override
    public MatchmakingResult makeMatches(LinkedList<Player> players, int skillDelta, int teamSize) {
        List<Match> matches = new ArrayList<>();
        List<Player> withoutMatchPlayers = new ArrayList<>();

        while (!players.isEmpty()) {
            Player player = players.pollFirst();

            int skill = player.getSkill();
            int lowerSkillDelta = Math.max(skill - skillDelta, 0);
            int higherSkillDelta = skill + skillDelta;
            int matchSizeWithoutPlayer = teamSize * 2 - 1;

            List<Player> matchPlayers = players.stream()
                    .filter(byDeltaSkill(lowerSkillDelta, higherSkillDelta))
                    .limit(matchSizeWithoutPlayer)
                    .collect(Collectors.toList());

            if (matchPlayers.size() == matchSizeWithoutPlayer) {
                players.removeAll(matchPlayers);
                matchPlayers.add(player);
                balanceMatch(matches, matchPlayers);
            } else {
                withoutMatchPlayers.add(player);
            }
        }
        return new MatchmakingResult(withoutMatchPlayers, matches);
    }

    private void balanceMatch(List<Match> matches, List<Player> matchPlayers) {
        Player[] matchPlayersArray = matchPlayers.toArray(new Player[0]);
        Match match = teamBalancer.balance(matchPlayersArray);
        matches.add(match);
    }

    private Predicate<? super Player> byDeltaSkill(int lowerSkillDelta, int higherSkillDelta) {
        return anotherPlayer -> lowerSkillDelta <= anotherPlayer.getSkill() &&
                anotherPlayer.getSkill() <= higherSkillDelta;
    }
}
