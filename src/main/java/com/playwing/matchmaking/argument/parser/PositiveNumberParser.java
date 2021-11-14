package com.playwing.matchmaking.argument.parser;

public class PositiveNumberParser implements IndexParser<Integer> {

    @Override
    public Integer parse(String argument) {
        try {
            int number = Integer.parseInt(argument);

            if (number > 0) {
                return number;
            }
            throw new IllegalArgumentException("Cannot parse a number %s. The number should be more than 0.");
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(String.format("Cannot parse a number %s. See example.", argument), e);
        }
    }
}
