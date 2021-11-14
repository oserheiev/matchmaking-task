package com.playwing.matchmaking.argument.parser;

import org.apache.commons.lang3.StringUtils;

public class FileNameParser implements IndexParser<String> {

    @Override
    public String parse(String stringArgument) {
        if (StringUtils.isNotBlank(stringArgument)) {
            return stringArgument.trim();
        }
        throw new IllegalArgumentException(String.format("File Name is not correct: %s", stringArgument));
    }
}
