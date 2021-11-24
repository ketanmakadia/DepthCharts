package com.sportsbet.depthchart.repo;


import com.sportsbet.depthchart.pojo.MLBPosition;
import com.sportsbet.depthchart.pojo.Player;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class MLBDepthChartsRepo implements DepthChartsRepo<MLBPosition> {

    private Map<MLBPosition, List<Player>> mlbDepthChartStorage = new HashMap<>();

    @Override
    public void addPlayerToDepthChart(final Player player, final MLBPosition position, int positionDepth) {
        List<Player> playerList = Optional.ofNullable(mlbDepthChartStorage.get(position)).orElse(new ArrayList<>());
        mlbDepthChartStorage.put(position, addToList(playerList, player, positionDepth));

    }

    @Override
    public void removePlayerToDepthChart(final Player player, final MLBPosition position) {
        mlbDepthChartStorage.get(position).remove(player);

    }

    @Override
    public Map<MLBPosition, List<Player>> getFullDepthChart() {
        return mlbDepthChartStorage;
    }

    @Override
    public List<Player> getPlayersUnderPlayerInDepthChart(final Player player, final MLBPosition position) {
        return getPlayerListAfter(mlbDepthChartStorage.get(position), player.getName());
    }
}
