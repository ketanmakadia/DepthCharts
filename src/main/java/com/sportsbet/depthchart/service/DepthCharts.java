package com.sportsbet.depthchart.service;


import com.sportsbet.depthchart.pojo.Player;
import com.sportsbet.depthchart.pojo.Position;

public interface DepthCharts {
    void addPlayerToDepthChart(final Player player, final Position position, int positionDepth);
    void addPlayerToDepthChart(final Player player, final Position position);
    void removePlayerFromDepthChart(final Player player, final Position position);
    void getFullDepthChart();
    void getPlayersUnderPlayerInDepthChart(final Player player, final Position position);
}
