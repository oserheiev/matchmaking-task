package com.playwing.matchmaking.team;

import com.playwing.matchmaking.dto.Match;
import com.playwing.matchmaking.dto.Player;
import com.playwing.matchmaking.utils.ArrayUtils;

import java.util.Arrays;
import java.util.UUID;

public class TeamBalancerImpl implements TeamBalancer {

    @Override
    public Match balance(Player[] matchPlayers) {
        int halfPlayerSize = matchPlayers.length / 2;
        Player[] teamA = new Player[halfPlayerSize];
        Player[] teamB = new Player[halfPlayerSize];

        splitRandomly(matchPlayers, teamA, teamB);
        float skillDelta = balanceTeams(teamA, teamB);

        return Match.builder()
                .id(UUID.randomUUID().toString())
                .teamA(teamA)
                .teamB(teamB)
                .teamSkillDelta(skillDelta)
                .build();
    }

    private float balanceTeams(Player[] teamA, Player[] teamB) {
        int skillSumA = sumTeamSkill(teamA);
        int skillSumB = sumTeamSkill(teamB);
        float skillDifference = Math.abs(skillSumA - skillSumB);
        float bestSkillDifference = skillDifference;

        int playerAIndex = -1;
        int playerBIndex = -1;

        for (int a = 0, teamALength = teamA.length; a < teamALength; a++) {
            Player playerA = teamA[a];
            int skillA = playerA.getSkill();

            for (int b = 0, teamBLength = teamB.length; b < teamBLength; b++) {
                int skillB = teamB[b].getSkill();
                float potentialSkillDifference = Math.abs((skillSumA - skillA + skillB) - (skillSumB - skillB + skillA));

                if (potentialSkillDifference < bestSkillDifference) {
                    bestSkillDifference = potentialSkillDifference;
                    playerAIndex = a;
                    playerBIndex = b;
                }
            }
        }

        if (bestSkillDifference < skillDifference) {
            ArrayUtils.swap(teamA, playerAIndex, teamB, playerBIndex);
            return balanceTeams(teamA, teamB);
        }
        return bestSkillDifference;
    }

    private int sumTeamSkill(Player[] team) {
        return Arrays.stream(team).map(Player::getSkill).reduce(0, Integer::sum);
    }

    private void splitRandomly(Player[] matchPlayers, Player[] teamA, Player[] teamB) {
        for (int i = 0, a = 0, b = 0; i < matchPlayers.length; i++) {
            if (i % 2 == 0) {
                teamA[a++] = matchPlayers[i];
                continue;
            }
            teamB[b++] = matchPlayers[i];
        }
    }
}
