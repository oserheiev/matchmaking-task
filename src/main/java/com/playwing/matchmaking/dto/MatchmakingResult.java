package com.playwing.matchmaking.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Collections;
import java.util.List;

@Data
@AllArgsConstructor
public class MatchmakingResult {

    private List<Player> restPlayers;
    private List<Match> matches;

    public static MatchmakingResult Empty() {
        return new MatchmakingResult(Collections.emptyList(), Collections.emptyList());
    }
}
