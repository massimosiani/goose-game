package io.github.massimosiani.goosegame.domain;

import io.github.massimosiani.goosegame.domain.square.BouncedSquareFilter;
import io.github.massimosiani.goosegame.domain.square.BridgeSquare;
import io.github.massimosiani.goosegame.domain.square.CommonSquare;
import io.github.massimosiani.goosegame.domain.square.GooseSquare;
import io.github.massimosiani.goosegame.domain.square.StartSquare;
import io.github.massimosiani.goosegame.domain.square.WinSquare;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Board {

    private final List<Integer> BRIDGE_SQUARES = Arrays.asList(6);

    private final List<Integer> GOOSE_SQUARES = Arrays.asList(5, 9, 14, 18, 23, 27);

    private int gameLength;

    private List<Square> squares = new ArrayList<>();

    public Board(int gameLength) {
        this.gameLength = gameLength;
        for (int i = 0; i <= gameLength; i++) {
            if (i == 0) squares.add(new StartSquare(i));
            else if (BRIDGE_SQUARES.contains(i)) squares.add(new BridgeSquare(i));
            else if (GOOSE_SQUARES.contains(i)) squares.add(new GooseSquare(i));
            else if (i == gameLength) squares.add(new WinSquare(i));
            else squares.add(new CommonSquare(i));
        }
    }

    public int getGameLength() {
        return gameLength;
    }

    public List<Square> getSquares() {
        return new ArrayList<>(squares);
    }

    public Game.PlayerMove move(Player player, Roll roll) {
        Game.PlayerMove playerMove = Game.PlayerMove.of(player);
        do {
            playerMove.merge(player.move(roll));
            BouncedSquareFilter.apply(this, playerMove, roll);
            player.setCurrentSquare(getSquares().get(playerMove.getSquare()));
        } while (!(player.getCurrentSquare() instanceof CommonSquare) && !(player.getCurrentSquare() instanceof WinSquare));
        return playerMove;
    }
}
