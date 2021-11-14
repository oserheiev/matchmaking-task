package com.playwing.matchmaking.service;

import com.playwing.matchmaking.dto.MatchmakingResult;
import com.playwing.matchmaking.dto.Player;

import java.util.List;

public interface MatchmakingService {

    MatchmakingResult splitIntoTeams(List<Player> players, int skillDelta, int teamSize);
}
