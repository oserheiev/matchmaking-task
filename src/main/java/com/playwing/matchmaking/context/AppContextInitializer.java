package com.playwing.matchmaking.context;

import com.playwing.matchmaking.AppRunner;
import com.playwing.matchmaking.argument.ArgumentParser;
import com.playwing.matchmaking.argument.ArgumentParserImpl;
import com.playwing.matchmaking.argument.parser.FileNameParser;
import com.playwing.matchmaking.argument.parser.IndexParser;
import com.playwing.matchmaking.argument.parser.PositiveNumberParser;
import com.playwing.matchmaking.io.MatchmakingResultWriter;
import com.playwing.matchmaking.io.MatchmakingResultWriterImpl;
import com.playwing.matchmaking.io.PlayerListReader;
import com.playwing.matchmaking.io.PlayerListReaderImpl;
import com.playwing.matchmaking.match.MatchMaker;
import com.playwing.matchmaking.match.MatchMakerImpl;
import com.playwing.matchmaking.service.MatchmakingService;
import com.playwing.matchmaking.service.MatchmakingServiceImpl;
import com.playwing.matchmaking.team.TeamBalancer;
import com.playwing.matchmaking.team.TeamBalancerImpl;

import java.util.HashMap;
import java.util.Map;

public class AppContextInitializer {

    public AppContext initializeContext() {
        AppContext context = new AppContext();

        ArgumentParser argumentParser = new ArgumentParserImpl(getIndexParserByIndexes(), args -> null);
        context.setBean(ArgumentParser.class.getName(), argumentParser);

        PlayerListReader playerListReader = new PlayerListReaderImpl();
        context.setBean(PlayerListReader.class.getName(), playerListReader);

        TeamBalancer teamBalancer = new TeamBalancerImpl();
        MatchMaker matchMaker = new MatchMakerImpl(teamBalancer);
        MatchmakingService matchmakingService = new MatchmakingServiceImpl(matchMaker);
        context.setBean(MatchmakingService.class.getName(), matchmakingService);

        MatchmakingResultWriter matchmakingResultWriter = new MatchmakingResultWriterImpl();
        context.setBean(MatchmakingResultWriter.class.getName(), matchmakingResultWriter);

        return context;
    }

    private Map<Integer, IndexParser> getIndexParserByIndexes() {
        FileNameParser fileNameParser = new FileNameParser();
        PositiveNumberParser positiveNumberParser = new PositiveNumberParser();

        Map<Integer, IndexParser> parserByIndexes = new HashMap<>();
        parserByIndexes.put(AppRunner.PLAYERS_FILE_ARGUMENT_INDEX, fileNameParser);
        parserByIndexes.put(AppRunner.RESULTS_FILE_ARGUMENT_INDEX, fileNameParser);
        parserByIndexes.put(AppRunner.SKILL_DELTA_ARGUMENT_INDEX, positiveNumberParser);
        parserByIndexes.put(AppRunner.TEAM_SIZE_ARGUMENT_INDEX, positiveNumberParser);
        return parserByIndexes;
    }
}
