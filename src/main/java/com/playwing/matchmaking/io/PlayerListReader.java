package com.playwing.matchmaking.io;

import com.playwing.matchmaking.dto.Player;

import java.io.IOException;
import java.util.List;

public interface PlayerListReader {

    List<Player> readPlayers(String fileName) throws IOException;
}
