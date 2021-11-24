package com.sportsbet.depthchart.utils;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sportsbet.depthchart.pojo.Player;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class PlayerObjectGenerator {

    private static ObjectMapper objectMapper = new ObjectMapper();

    public static List<Player> createThreePlayer() {
        return loadPlayer("playerInput.json");
    }

    public static List<Player> createFourPlayerMLB() {
        return loadPlayer("MLB4player.json");
    }

    private static List<Player> loadPlayer(String fileName) {
        List<Player> playerList = null;
        try {
            playerList =  objectMapper.readValue(new File("src/test/resources/"+fileName), new TypeReference<List<Player>>(){});
        } catch (IOException e) {
            e.printStackTrace();
        }
        return playerList;

    }
}
