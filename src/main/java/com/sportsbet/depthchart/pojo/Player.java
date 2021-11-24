package com.sportsbet.depthchart.pojo;


public class Player {
    private int playerId;
    private String name;
    private String position;

    public Player() {
    }

    public Player(int playerId, String name, String position) {
        this.playerId = playerId;
        this.name = name;
        this.position = position;
    }

    public int getPlayerId() {
        return playerId;
    }

    public void setPlayerId(int playerId) {
        this.playerId = playerId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }
}
