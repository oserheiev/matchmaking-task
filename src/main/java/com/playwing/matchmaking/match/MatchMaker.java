package com.playwing.matchmaking.match;

import com.playwing.matchmaking.dto.MatchmakingResult;
import com.playwing.matchmaking.dto.Player;

import java.util.LinkedList;

public interface MatchMaker {

    MatchmakingResult makeMatches(LinkedList<Player> players, int skillDelta, int teamSize);
}
