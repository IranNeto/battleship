package com.example.odigeo;

import com.example.odigeo.battleship.domain.Match;
import com.example.odigeo.battleship.domain.State;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;

public class MatchTest {

    @Test
    public void shouldAssignUUIDWhenCreated(){
        Match match = new Match();
        Assertions.assertNotNull(match.getId());
    }

    @Test
    public void shouldAssignCREATEDStateWhenMatchIsCreated(){
        Match match = new Match();
        Assertions.assertEquals(State.CREATED, match.getState());
    }
}
