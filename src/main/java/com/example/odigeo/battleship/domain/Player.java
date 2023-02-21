package com.example.odigeo.battleship.domain;

import com.example.odigeo.battleship.controller.PlayerController;

public class Player {
    private String nickname;
    private final Field field = new Field();
    private final Field map = new Field();
    private final PlayerController playerController;

    public Player(String nickname){
        this.nickname = nickname;
        playerController = new PlayerController();
    }

    public String getNickname() {
        return nickname;
    }

    public Field getField() {
        return field;
    }

    public Field getMap() {
        return map;
    }
}
