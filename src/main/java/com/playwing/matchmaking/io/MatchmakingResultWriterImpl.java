package com.playwing.matchmaking.io;

import com.google.gson.Gson;
import com.playwing.matchmaking.dto.MatchmakingResult;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class MatchmakingResultWriterImpl implements MatchmakingResultWriter {

    @Override
    public void writeResults(MatchmakingResult matchmakingResult, String resultsFileName) throws IOException {
        File resultsFile = new File(resultsFileName);
        Gson gson = new Gson();
        String json = gson.toJson(matchmakingResult);
        FileUtils.writeStringToFile(resultsFile, json, StandardCharsets.UTF_8);
    }
}
