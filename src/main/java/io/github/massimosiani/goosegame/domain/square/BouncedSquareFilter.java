package io.github.massimosiani.goosegame.domain.square;

import io.github.massimosiani.goosegame.domain.Board;
import io.github.massimosiani.goosegame.domain.Game;
import io.github.massimosiani.goosegame.domain.Roll;
import io.github.massimosiani.goosegame.domain.Square;

public class BouncedSquareFilter {

    public static Square apply(Board board, Game.PlayerMove playerMove, Roll roll) {
        if (playerMove.getSquare() >= board.getSquares().size()) {
            int sum = roll.first + roll.second;
            int originalSquare = playerMove.getSquare() - sum;
            int result = 2 * board.getGameLength() - originalSquare - sum;
            playerMove.addMove(result);
        }
        return board.getSquares().get(playerMove.getSquare());
    }
}
