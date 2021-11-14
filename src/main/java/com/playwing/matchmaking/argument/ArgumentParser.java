package com.playwing.matchmaking.argument;

public interface ArgumentParser {

    <T> T parse(String[] args, int argumentIndex);
}
