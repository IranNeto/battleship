package com.example.odigeo.battleship.domain;

public class Square {

    private Status status;

    public Square(){
        status = Status.WATER;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}
