package io.github.massimosiani.goosegame.domain;

import io.github.massimosiani.goosegame.domain.square.BouncedSquareFilter;
import io.github.massimosiani.goosegame.domain.square.CommonSquare;
import io.github.massimosiani.goosegame.domain.square.StartSquare;
import io.github.massimosiani.goosegame.domain.square.WinSquare;

import java.util.ArrayList;
import java.util.List;

public class Game {
    private Board board;
    private boolean finished = false;
    private List<Player> players = new ArrayList<>();

    public Game(int gameLength) {
        board = new Board(gameLength);
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

    public boolean isFinished() {
        return finished;
    }

    public PlayerMove move(Player player, Roll roll) {
        if (!players.contains(player))
            throw new IllegalArgumentException("Player not found: " + player.getName());
        PlayerMove playerMove = PlayerMove.of(player);
        do {
            playerMove.merge(player.move(roll));
            BouncedSquareFilter.apply(board, playerMove, roll);
            player.setCurrentSquare(board.getSquares().get(playerMove.getSquare()));
            if (player.getCurrentSquare() instanceof WinSquare) finished = true;
        } while (!(player.getCurrentSquare() instanceof CommonSquare) && !finished);
        return playerMove;
    }

    public Game start() {
        players.forEach(p -> p.setCurrentSquare(new StartSquare(0)));
        return this;
    }

    public static class PlayerMove {
        private List<Integer> moves = new ArrayList<>();
        private Player player;

        private PlayerMove(Player player) {
            this.player = player;
        }

        public static PlayerMove of(Player player) {
            return new PlayerMove(player);
        }

        public static PlayerMove of(Player player, Integer square) {
            return new PlayerMove(player).addMove(square);
        }

        public PlayerMove merge(PlayerMove playerMove) {
            this.moves.addAll(playerMove.moves);
            return this;
        }

        public PlayerMove addMove(Integer square) {
            moves.add(square);
            return this;
        }

        public List<Integer> getMoves() {
            return moves;
        }

        public Player getPlayer() {
            return player;
        }

        public int getSquare() {
            return moves.get(moves.size() - 1);
        }
    }
}
