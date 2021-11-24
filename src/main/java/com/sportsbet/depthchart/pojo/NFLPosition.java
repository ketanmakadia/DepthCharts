package com.sportsbet.depthchart.pojo;

public enum NFLPosition implements Position {
    QB("QB"), WR("WR"), RB("RB"), TE("TE"), K("K"), P("P"), KR("KR"), PR("PR");

    String position;

    NFLPosition(String position) {
        this.position = position;
    }

    @Override
    public String getPosition() {
        return position;
    }
}
