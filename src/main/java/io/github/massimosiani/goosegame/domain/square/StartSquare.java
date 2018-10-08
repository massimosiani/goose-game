package io.github.massimosiani.goosegame.domain.square;

import io.github.massimosiani.goosegame.domain.Game;
import io.github.massimosiani.goosegame.domain.Player;
import io.github.massimosiani.goosegame.domain.Roll;
import io.github.massimosiani.goosegame.domain.Square;

public class StartSquare extends Square {

    public StartSquare(int position) {
        super(position);
    }

    public Game.PlayerMove move(Player player, Roll roll) {
        int sum = roll.first + roll.second;
        return Game.PlayerMove.of(player, position + sum);
    }
}
