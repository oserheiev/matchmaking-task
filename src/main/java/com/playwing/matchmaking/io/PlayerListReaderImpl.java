package com.playwing.matchmaking.io;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.playwing.matchmaking.dto.Player;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;

public class PlayerListReaderImpl implements PlayerListReader {

    @Override
    public List<Player> readPlayers(String fileName) throws IOException {
        File playerFile = new File(fileName);

        if (playerFile.exists()) {
            Gson gson = new Gson();
            String json = FileUtils.readFileToString(playerFile, StandardCharsets.UTF_8);
            return gson.fromJson(json, new TypeToken<List<Player>>(){}.getType());
        }
        throw new FileNotFoundException(String.format("Cannot find a file by a path %s", fileName));
    }
}
