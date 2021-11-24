package com.sportsbet.depthchart.repo;

import com.sportsbet.depthchart.pojo.MLBPosition;
import com.sportsbet.depthchart.pojo.Player;
import com.sportsbet.depthchart.utils.PlayerObjectGenerator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;


@ExtendWith(MockitoExtension.class)
class MLBDepthChartsRepoTest {


    private MLBDepthChartsRepo mlbDepthChartsRepo;

    private List<Player> playerList;

    @BeforeEach
    void setUp() {
        mlbDepthChartsRepo = new MLBDepthChartsRepo();
        playerList = PlayerObjectGenerator.createFourPlayerMLB();
        mlbDepthChartsRepo.addPlayerToDepthChart(playerList.get(0), MLBPosition.B1, 0);
        mlbDepthChartsRepo.addPlayerToDepthChart(playerList.get(1), MLBPosition.B1, 0);
        mlbDepthChartsRepo.addPlayerToDepthChart(playerList.get(2), MLBPosition.B1, 3);
        mlbDepthChartsRepo.addPlayerToDepthChart(playerList.get(3), MLBPosition.B1, 0);
    }

    @Test
    void addPlayerToDepthChart() {
        Map<MLBPosition, List<Player>> nflPlayerDepthChart = mlbDepthChartsRepo.getFullDepthChart();
        List<Player> wrPlayerList = nflPlayerDepthChart.get(MLBPosition.B1);
        assertEquals(playerList.get(3).getPlayerId() , wrPlayerList.get(0).getPlayerId());
        assertEquals(playerList.get(1).getPlayerId() , wrPlayerList.get(1).getPlayerId());
        assertEquals(playerList.get(0).getPlayerId() , wrPlayerList.get(2).getPlayerId());
        assertEquals(playerList.get(2).getPlayerId() , wrPlayerList.get(3).getPlayerId());
    }

    @Test
    void addPlayerToDepthChartWithoutPositionDepth() {
        mlbDepthChartsRepo.addPlayerToDepthChart(
                new Player(5, "player5", MLBPosition.B1.name()), MLBPosition.B1, -1);
        Map<MLBPosition, List<Player>> nflPlayerDepthChart = mlbDepthChartsRepo.getFullDepthChart();
        List<Player> wrPlayerList = nflPlayerDepthChart.get(MLBPosition.B1);
        assertEquals(playerList.get(3).getPlayerId() , wrPlayerList.get(0).getPlayerId());
        assertEquals(playerList.get(1).getPlayerId() , wrPlayerList.get(1).getPlayerId());
        assertEquals(playerList.get(0).getPlayerId() , wrPlayerList.get(2).getPlayerId());
        assertEquals(playerList.get(2).getPlayerId() , wrPlayerList.get(3).getPlayerId());
        assertEquals(5 , wrPlayerList.get(4).getPlayerId());
    }

    @Test
    void addPlayerToDepthChartPositionDepthMoreThanSize() {
        mlbDepthChartsRepo.addPlayerToDepthChart(
                new Player(5, "player5", MLBPosition.B1.name()), MLBPosition.B1, -1);
        mlbDepthChartsRepo.addPlayerToDepthChart(
                new Player(6, "player6", MLBPosition.B1.name()), MLBPosition.B1, 10);
        Map<MLBPosition, List<Player>> nflPlayerDepthChart = mlbDepthChartsRepo.getFullDepthChart();
        List<Player> wrPlayerList = nflPlayerDepthChart.get(MLBPosition.B1);
        assertEquals(playerList.get(3).getPlayerId() , wrPlayerList.get(0).getPlayerId());
        assertEquals(playerList.get(1).getPlayerId() , wrPlayerList.get(1).getPlayerId());
        assertEquals(playerList.get(0).getPlayerId() , wrPlayerList.get(2).getPlayerId());
        assertEquals(playerList.get(2).getPlayerId() , wrPlayerList.get(3).getPlayerId());
        assertEquals(5 , wrPlayerList.get(4).getPlayerId());
        assertEquals(6 , wrPlayerList.get(5).getPlayerId());
    }

    @Test
    void removePlayerToDepthChart() {
        mlbDepthChartsRepo.removePlayerToDepthChart(playerList.get(0) , MLBPosition.B1);
        Map<MLBPosition, List<Player>> nflPlayerDepthChart = mlbDepthChartsRepo.getFullDepthChart();
        List<Player> wrPlayerList = nflPlayerDepthChart.get(MLBPosition.B1);
        assertEquals(playerList.get(3).getPlayerId() , wrPlayerList.get(0).getPlayerId());
        assertEquals(playerList.get(1).getPlayerId() , wrPlayerList.get(1).getPlayerId());
        assertEquals(playerList.get(2).getPlayerId() , wrPlayerList.get(2).getPlayerId());
    }

    @Test
    void removePlayerToDepthChartPlayerNotExist() {
        mlbDepthChartsRepo.removePlayerToDepthChart(
                new Player(5, "player5", MLBPosition.B1.name()) , MLBPosition.B1);
        Map<MLBPosition, List<Player>> nflPlayerDepthChart = mlbDepthChartsRepo.getFullDepthChart();
        List<Player> wrPlayerList = nflPlayerDepthChart.get(MLBPosition.B1);
        assertEquals(playerList.get(3).getPlayerId() , wrPlayerList.get(0).getPlayerId());
        assertEquals(playerList.get(1).getPlayerId() , wrPlayerList.get(1).getPlayerId());
        assertEquals(playerList.get(0).getPlayerId() , wrPlayerList.get(2).getPlayerId());
        assertEquals(playerList.get(2).getPlayerId() , wrPlayerList.get(3).getPlayerId());
    }

    @Test
    void getPlayersUnderPlayerInDepthChart() {
        assertEquals(2,mlbDepthChartsRepo.
                getPlayersUnderPlayerInDepthChart(playerList.get(1), MLBPosition.B1).size());
    }


    @Test
    void getPlayersUnderPlayerInDepthChartLastPlayer() {
        assertEquals(0,mlbDepthChartsRepo.
                getPlayersUnderPlayerInDepthChart(playerList.get(2), MLBPosition.B1).size());
    }

    @Test
    void getPlayersUnderPlayerInDepthChartPlayerNotExist() {
        assertEquals(0,mlbDepthChartsRepo.getPlayersUnderPlayerInDepthChart(
                new Player(5, "player5", MLBPosition.B1.name()), MLBPosition.B1).size());
    }
}