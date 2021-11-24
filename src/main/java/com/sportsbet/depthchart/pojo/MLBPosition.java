package com.sportsbet.depthchart.pojo;

public enum MLBPosition implements Position {
    SP("SP"), RP("RP"), C("C"), B1("1B"), B2("2B"), B3("3B"), SS("SS"), LF("LF"), RF("RF"), CF("CF"), DH("DH");


    String position;

    MLBPosition(String position) {
        this.position = position;
    }

    @Override
    public String getPosition() {
        return this.name();
    }
}
