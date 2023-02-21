package com.example.odigeo;

import com.example.odigeo.battleship.domain.Player;
import org.junit.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class PlayerTest {

    @Test
    public void shouldAddNicknameWhenCreatePlayer(){
        Player player = new Player("nickname");
        assertEquals("nickname", player.getNickname());
    }

    @Test
    public void shouldInitiateFieldAndMapWhenPlayerIsCreated(){
        Player player = new Player("nickname");
        assertNotNull(player.getField());
        assertNotNull(player.getMap());
    }
}
