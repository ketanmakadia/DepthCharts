package com.sportsbet.depthchart.service;

import com.sportsbet.depthchart.pojo.MLBPosition;
import com.sportsbet.depthchart.pojo.Player;
import com.sportsbet.depthchart.repo.MLBDepthChartsRepo;
import com.sportsbet.depthchart.utils.PlayerObjectGenerator;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@ExtendWith(MockitoExtension.class)
class MLBDepthChartsServiceTest {


    private DepthChartsService depthChartsService;

    @Mock
    private MLBDepthChartsRepo mlbDepthChartsRepo;

    private List<Player> playerList;

    @BeforeEach
    void setUp() {
        depthChartsService = new DepthChartsService(mlbDepthChartsRepo);
        playerList = PlayerObjectGenerator.createThreePlayer();
    }

    @AfterEach
    void tearDown() {

    }

    @Test
    void addPlayerToDepthChart() {
        depthChartsService.addPlayerToDepthChart(playerList.get(0), MLBPosition.B1, 0);
    }

    @Test
    void addPlayerToDepthChartWithOutPositionNumber() {
        depthChartsService.addPlayerToDepthChart(playerList.get(0), MLBPosition.B1);
    }


    @Test
    void removePlayerFromDepthChart() {
        depthChartsService.removePlayerFromDepthChart(playerList.get(0), MLBPosition.B1);
    }

    @Test
    void getFullDepthChart() {
        Map<MLBPosition, List<Player>> mlbDepthChartsMap = new HashMap<>();
        mlbDepthChartsMap.put(MLBPosition.B1, playerList);
        mlbDepthChartsMap.put(MLBPosition.B2, Arrays.asList(playerList.get(0)));
        depthChartsService.getFullDepthChart();
    }

    @Test
    void getPlayersUnderPlayerInDepthChart() {
        depthChartsService.getPlayersUnderPlayerInDepthChart(playerList.get(0), MLBPosition.B1);
    }


}