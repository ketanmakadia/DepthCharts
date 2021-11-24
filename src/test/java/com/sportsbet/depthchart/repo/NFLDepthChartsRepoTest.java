package com.sportsbet.depthchart.repo;

import com.sportsbet.depthchart.pojo.NFLPosition;
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
class NFLDepthChartsRepoTest {


    private NFLDepthChartsRepo nflDepthChartsRepo;

    private List<Player> playerList;


    @BeforeEach
    void setUp() {
        nflDepthChartsRepo = new NFLDepthChartsRepo();
        playerList = PlayerObjectGenerator.createFourPlayerMLB();

        nflDepthChartsRepo.addPlayerToDepthChart(playerList.get(0), NFLPosition.WR, 0);
        nflDepthChartsRepo.addPlayerToDepthChart(playerList.get(1), NFLPosition.WR, 0);
        nflDepthChartsRepo.addPlayerToDepthChart(playerList.get(2), NFLPosition.WR, 3);
        nflDepthChartsRepo.addPlayerToDepthChart(playerList.get(3), NFLPosition.WR, 0);
    }

    @Test
    void addPlayerToDepthChart() {
        Map<NFLPosition, List<Player>> nflPlayerDepthChart = nflDepthChartsRepo.getFullDepthChart();
        List<Player> wrPlayerList = nflPlayerDepthChart.get(NFLPosition.WR);
        assertEquals(playerList.get(3).getPlayerId() , wrPlayerList.get(0).getPlayerId());
        assertEquals(playerList.get(1).getPlayerId() , wrPlayerList.get(1).getPlayerId());
        assertEquals(playerList.get(0).getPlayerId() , wrPlayerList.get(2).getPlayerId());
        assertEquals(playerList.get(2).getPlayerId() , wrPlayerList.get(3).getPlayerId());
    }

    @Test
    void addPlayerToDepthChartWithoutPositionDepth() {
        nflDepthChartsRepo.addPlayerToDepthChart(
                new Player(5, "player5", NFLPosition.WR.name()), NFLPosition.WR, -1);
        Map<NFLPosition, List<Player>> nflPlayerDepthChart = nflDepthChartsRepo.getFullDepthChart();
        List<Player> wrPlayerList = nflPlayerDepthChart.get(NFLPosition.WR);
        assertEquals(playerList.get(3).getPlayerId() , wrPlayerList.get(0).getPlayerId());
        assertEquals(playerList.get(1).getPlayerId() , wrPlayerList.get(1).getPlayerId());
        assertEquals(playerList.get(0).getPlayerId() , wrPlayerList.get(2).getPlayerId());
        assertEquals(playerList.get(2).getPlayerId() , wrPlayerList.get(3).getPlayerId());
        assertEquals(5 , wrPlayerList.get(4).getPlayerId());
    }

    @Test
    void addPlayerToDepthChartPositionDepthMoreThanSize() {
        nflDepthChartsRepo.addPlayerToDepthChart(
                new Player(5, "player5", NFLPosition.WR.name()), NFLPosition.WR, -1);
        nflDepthChartsRepo.addPlayerToDepthChart(
                new Player(6, "player6", NFLPosition.WR.name()), NFLPosition.WR, 10);
        Map<NFLPosition, List<Player>> nflPlayerDepthChart = nflDepthChartsRepo.getFullDepthChart();
        List<Player> wrPlayerList = nflPlayerDepthChart.get(NFLPosition.WR);
        assertEquals(playerList.get(3).getPlayerId() , wrPlayerList.get(0).getPlayerId());
        assertEquals(playerList.get(1).getPlayerId() , wrPlayerList.get(1).getPlayerId());
        assertEquals(playerList.get(0).getPlayerId() , wrPlayerList.get(2).getPlayerId());
        assertEquals(playerList.get(2).getPlayerId() , wrPlayerList.get(3).getPlayerId());
        assertEquals(5 , wrPlayerList.get(4).getPlayerId());
        assertEquals(6 , wrPlayerList.get(5).getPlayerId());
    }

    @Test
    void removePlayerToDepthChart() {
        nflDepthChartsRepo.removePlayerToDepthChart(playerList.get(0) , NFLPosition.WR);
        Map<NFLPosition, List<Player>> nflPlayerDepthChart = nflDepthChartsRepo.getFullDepthChart();
        List<Player> wrPlayerList = nflPlayerDepthChart.get(NFLPosition.WR);
        assertEquals(playerList.get(3).getPlayerId() , wrPlayerList.get(0).getPlayerId());
        assertEquals(playerList.get(1).getPlayerId() , wrPlayerList.get(1).getPlayerId());
        assertEquals(playerList.get(2).getPlayerId() , wrPlayerList.get(2).getPlayerId());
    }

    @Test
    void removePlayerToDepthChartPlayerNotExist() {
        nflDepthChartsRepo.removePlayerToDepthChart(
                new Player(5, "player5", NFLPosition.WR.name()) , NFLPosition.WR);
        Map<NFLPosition, List<Player>> nflPlayerDepthChart = nflDepthChartsRepo.getFullDepthChart();
        List<Player> wrPlayerList = nflPlayerDepthChart.get(NFLPosition.WR);
        assertEquals(playerList.get(3).getPlayerId() , wrPlayerList.get(0).getPlayerId());
        assertEquals(playerList.get(1).getPlayerId() , wrPlayerList.get(1).getPlayerId());
        assertEquals(playerList.get(0).getPlayerId() , wrPlayerList.get(2).getPlayerId());
        assertEquals(playerList.get(2).getPlayerId() , wrPlayerList.get(3).getPlayerId());
    }

    @Test
    void getPlayersUnderPlayerInDepthChart() {
        assertEquals(2,nflDepthChartsRepo
                .getPlayersUnderPlayerInDepthChart(playerList.get(1), NFLPosition.WR).size());
    }


    @Test
    void getPlayersUnderPlayerInDepthChartLastPlayer() {
        assertEquals(0,nflDepthChartsRepo
                .getPlayersUnderPlayerInDepthChart(playerList.get(2), NFLPosition.WR).size());
    }

    @Test
    void getPlayersUnderPlayerInDepthChartPlayerNotExist() {
        assertEquals(0,nflDepthChartsRepo.getPlayersUnderPlayerInDepthChart(
                new Player(5, "player5", NFLPosition.WR.name()), NFLPosition.WR).size());
    }
}