package com.example.odigeo.battleship.domain;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.UUID;

@Entity
@Data
public class Match {
    @Id
    @GeneratedValue
    private String id;
    private String player1;
    private String player2;
    private State state;

    public Match(){
        this.id = UUID.randomUUID().toString();
        this.state = State.CREATED;
    }
}
