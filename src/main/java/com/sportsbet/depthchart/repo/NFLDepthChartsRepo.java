package com.sportsbet.depthchart.repo;

import com.sportsbet.depthchart.pojo.NFLPosition;
import com.sportsbet.depthchart.pojo.Player;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class NFLDepthChartsRepo implements DepthChartsRepo<NFLPosition> {

    private Map<NFLPosition, List<Player>> nflDepthChartStorage = new HashMap<>();


    @Override
    public void addPlayerToDepthChart(final Player player, final NFLPosition position, int positionDepth) {
        List<Player> playerList = Optional.ofNullable(nflDepthChartStorage.get(position)).orElse(new ArrayList<>());
        nflDepthChartStorage.put(position, addToList(playerList, player, positionDepth));

    }

    @Override
    public void removePlayerToDepthChart(final Player player, final NFLPosition position) {
        nflDepthChartStorage.get(position).remove(player);

    }

    @Override
    public Map<NFLPosition, List<Player>> getFullDepthChart() {
        return nflDepthChartStorage;
    }

    @Override
    public List<Player> getPlayersUnderPlayerInDepthChart(final Player player, final NFLPosition position) {
        return getPlayerListAfter(nflDepthChartStorage.get(position), player.getName());
    }
}
