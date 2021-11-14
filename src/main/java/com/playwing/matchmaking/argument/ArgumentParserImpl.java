package com.playwing.matchmaking.argument;

import com.playwing.matchmaking.argument.parser.IndexParser;

import java.util.Map;

public class ArgumentParserImpl implements ArgumentParser {

    private final Map<Integer, IndexParser> parserByIndexs;
    private final IndexParser defaultIndexParser;

    public ArgumentParserImpl(Map<Integer, IndexParser> parserByIndexs, IndexParser defaultIndexParser) {
        this.parserByIndexs = parserByIndexs;
        this.defaultIndexParser = defaultIndexParser;
    }

    @SuppressWarnings("unchecked")
    @Override
    public <T> T parse(String[] args, int argumentIndex) {
        if (argumentIndex < args.length) {
            IndexParser parser = parserByIndexs.getOrDefault(argumentIndex, defaultIndexParser);
            return (T) parser.parse(args[argumentIndex]);
        }
        throw new IllegalArgumentException("Incorrect number of arguments. See example: " +
                "java -jar matchmaking.jar players.json results.json maxSkillDeltaBetweenPlayers teamSize");
    }
}
