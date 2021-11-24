package com.sportsbet.depthchart.service;

import com.sportsbet.depthchart.pojo.NFLPosition;
import com.sportsbet.depthchart.pojo.Player;
import com.sportsbet.depthchart.repo.NFLDepthChartsRepo;
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
class NFLDepthChartsServiceTest {


    private DepthChartsService depthChartsService;

    @Mock
    private NFLDepthChartsRepo nflDepthChartsRepo;

    private List<Player> playerList;

    @BeforeEach
    void setUp() {
        depthChartsService = new DepthChartsService(nflDepthChartsRepo);
        playerList = PlayerObjectGenerator.createThreePlayer();
    }

    @AfterEach
    void tearDown() {

    }

    @Test
    void addPlayerToDepthChart() {
        depthChartsService.addPlayerToDepthChart(playerList.get(0), NFLPosition.WR, 0);
    }

    @Test
    void addPlayerToDepthChartWithOutPositionNumber() {
        depthChartsService.addPlayerToDepthChart(playerList.get(0), NFLPosition.WR);
    }


    @Test
    void removePlayerFromDepthChart() {
        depthChartsService.removePlayerFromDepthChart(playerList.get(0), NFLPosition.WR);
    }

    @Test
    void getFullDepthChart() {
        Map<NFLPosition, List<Player>> mlbDepthChartsMap = new HashMap<>();
        mlbDepthChartsMap.put(NFLPosition.WR, playerList);
        mlbDepthChartsMap.put(NFLPosition.WR, Arrays.asList(playerList.get(0)));
        depthChartsService.getFullDepthChart();
    }

    @Test
    void getPlayersUnderPlayerInDepthChart() {
        depthChartsService.getPlayersUnderPlayerInDepthChart(playerList.get(0), NFLPosition.WR);
    }


}