package com.sportsbet.depthchart.service;

import com.sportsbet.depthchart.pojo.Player;
import com.sportsbet.depthchart.pojo.Position;
import com.sportsbet.depthchart.repo.DepthChartsRepo;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class DepthChartsService implements DepthCharts {

    private DepthChartsRepo depthChartsRepo;

    public DepthChartsService(DepthChartsRepo depthChartsRepo) {
        this.depthChartsRepo = depthChartsRepo;
    }

    @Override
    public void addPlayerToDepthChart(final Player player, final Position position,final int positionDepth) {
        depthChartsRepo.addPlayerToDepthChart(player, position, positionDepth);

    }

    @Override
    public void addPlayerToDepthChart(final Player player,final Position position) {
        addPlayerToDepthChart(player, position, -1);

    }

    @Override
    public void removePlayerFromDepthChart(final Player player,final Position position) {
        depthChartsRepo.removePlayerToDepthChart(player, position);

    }

    @Override
    public void getFullDepthChart() {
        Map<Position, List<Player>> depthChart = depthChartsRepo.getFullDepthChart();
        depthChart.forEach((k, v) -> {
            String playerIds = v.stream().map( p -> String.valueOf(p.getPlayerId())).collect(Collectors.joining(","));
            System.out.printf("[%s] : [%s]%n", k, playerIds);
        });
    }

    @Override
    public void getPlayersUnderPlayerInDepthChart(final Player player,final Position position) {
        depthChartsRepo.getPlayersUnderPlayerInDepthChart(player, position);
    }
}
