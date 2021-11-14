package com.playwing.matchmaking.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Match {

    private final String id;
    private final float teamSkillDelta;
    private final Player[] teamA;
    private final Player[] teamB;
}
