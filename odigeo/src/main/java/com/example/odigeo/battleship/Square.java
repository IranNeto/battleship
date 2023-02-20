package com.example.odigeo.battleship;

public class Square {

    private Status status;

    Square(){
        status = Status.WATER;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}
