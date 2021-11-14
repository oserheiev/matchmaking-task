package com.playwing.matchmaking.team;

import com.playwing.matchmaking.dto.Match;
import com.playwing.matchmaking.dto.Player;

public interface TeamBalancer {

    Match balance(Player[] matchPlayers);
}
