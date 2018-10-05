package io.github.massimosiani.goosegame.domain;

import io.github.massimosiani.goosegame.utils.SquareUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Game {
    private final Integer gameLength;
    private List<Player> players = new ArrayList<>();

    public Game(int gameLength) {
        this.gameLength = gameLength;
    }

    public Game addPlayer(Player player) {
        if (players.contains(player)) {
            return this;
        }
        players.add(player);
        return this;
    }

    public List<Player> getPlayers() {
        return new ArrayList<>(players);
    }

    public PlayerMove move(Player player, Roll roll) {
        if (!players.contains(player))
            throw new IllegalArgumentException("Player not found: " + player.getName());
        var newPlayerPosition = SquareUtils.computeNewSquare(players.get(players.indexOf(player)), roll, gameLength);
        player.setCurrentPosition(newPlayerPosition.square);
        newPlayerPosition.won = newPlayerPosition.square == gameLength;
        return newPlayerPosition;
    }

    public Game start() {
        players.forEach(p -> p.setCurrentPosition(0));
        return this;
    }

    public static class PlayerMove {
        public final boolean bounced;
        public final boolean bridged;
        private List<Integer> moves = new ArrayList<>();
        public final int square;
        private boolean won;

        public PlayerMove(boolean bounced, boolean bridged, int square) {
            this.bounced = bounced;
            this.bridged = bridged;
            this.square = square;
        }

        public PlayerMove addMove(Integer square) {
            moves.add(square);
            return this;
        }

        public List<Integer> getMoves() {
            return moves;
        }

        public boolean hasWon() {
            return won;
        }
    }
}
