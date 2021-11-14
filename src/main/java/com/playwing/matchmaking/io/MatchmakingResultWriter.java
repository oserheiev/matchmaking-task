package com.playwing.matchmaking.io;

import com.playwing.matchmaking.dto.MatchmakingResult;

import java.io.IOException;

public interface MatchmakingResultWriter {

    void writeResults(MatchmakingResult matchmakingResult, String resultsFileName) throws IOException;
}
