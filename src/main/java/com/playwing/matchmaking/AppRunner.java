package com.playwing.matchmaking;

import com.playwing.matchmaking.argument.ArgumentParser;
import com.playwing.matchmaking.context.AppContext;
import com.playwing.matchmaking.context.AppContextInitializer;
import com.playwing.matchmaking.dto.MatchmakingResult;
import com.playwing.matchmaking.dto.Player;
import com.playwing.matchmaking.io.MatchmakingResultWriter;
import com.playwing.matchmaking.io.PlayerListReader;
import com.playwing.matchmaking.service.MatchmakingService;
import org.apache.log4j.Logger;

import java.util.List;

public class AppRunner {

    private static final Logger LOG = Logger.getLogger(AppRunner.class);

    public static final int PLAYERS_FILE_ARGUMENT_INDEX = 0;
    public static final int RESULTS_FILE_ARGUMENT_INDEX = 1;
    public static final int SKILL_DELTA_ARGUMENT_INDEX = 2;
    public static final int TEAM_SIZE_ARGUMENT_INDEX = 3;

    public static void main(String[] args) {
        AppContextInitializer initializer = new AppContextInitializer();
        AppContext context = initializer.initializeContext();
        ArgumentParser argumentParser = context.getBean(ArgumentParser.class.getName());
        PlayerListReader listReader = context.getBean(PlayerListReader.class.getName());
        MatchmakingService matchmakingService = context.getBean(MatchmakingService.class.getName());
        MatchmakingResultWriter matchmakingResultWriter = context.getBean(MatchmakingResultWriter.class.getName());

        try {
            String playersFileName = argumentParser.parse(args, PLAYERS_FILE_ARGUMENT_INDEX);
            String resultsFileName = argumentParser.parse(args, RESULTS_FILE_ARGUMENT_INDEX);
            int skillDelta = argumentParser.parse(args, SKILL_DELTA_ARGUMENT_INDEX);
            int teamSize = argumentParser.parse(args, TEAM_SIZE_ARGUMENT_INDEX);

            List<Player> players = listReader.readPlayers(playersFileName);
            MatchmakingResult matchmakingResult = matchmakingService.splitIntoTeams(players, skillDelta, teamSize);
            matchmakingResultWriter.writeResults(matchmakingResult, resultsFileName);
            System.out.printf("Done! See results in %s file", resultsFileName);
        } catch (Exception e) {
            LOG.error("Cannot finish matchmaking due to", e);
        }
    }
}
