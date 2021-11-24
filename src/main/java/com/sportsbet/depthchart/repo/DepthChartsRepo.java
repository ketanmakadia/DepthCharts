package com.sportsbet.depthchart.repo;


import com.sportsbet.depthchart.pojo.Player;
import com.sportsbet.depthchart.pojo.Position;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.OptionalInt;
import java.util.stream.IntStream;

public interface DepthChartsRepo <T extends Position> {

    void addPlayerToDepthChart(final Player player, final T position, int positionDepth);
    void removePlayerToDepthChart(final Player player, final T position);
    Map<T, List<Player>> getFullDepthChart();
    List<Player> getPlayersUnderPlayerInDepthChart(final Player player, final T position);

    default List<Player> getPlayerListAfter(final List<Player> playerLst , final String name) {
        OptionalInt indexOpt = IntStream.range(0, playerLst.size()).filter(i -> playerLst.get(i).getName().equals(name)).findFirst();
        if (indexOpt.isPresent()) {
            return playerLst.subList((indexOpt.getAsInt() + 1 ), playerLst.size());
        }
        return Collections.emptyList();
    }

    default List<Player> addToList(final List<Player> playerLst , final Player player, final int positionDepth) {
        if (positionDepth >= 0 && positionDepth < playerLst.size()) {
            playerLst.add(positionDepth, player);
        } else {
            playerLst.add(player);
        }
        return playerLst;
    }


}
