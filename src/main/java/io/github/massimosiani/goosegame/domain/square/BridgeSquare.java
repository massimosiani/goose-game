package io.github.massimosiani.goosegame.domain.square;

import io.github.massimosiani.goosegame.domain.Game;
import io.github.massimosiani.goosegame.domain.Player;
import io.github.massimosiani.goosegame.domain.Roll;
import io.github.massimosiani.goosegame.domain.Square;

public class BridgeSquare extends Square {

    public BridgeSquare(int position) {
        super(position);
    }

    @Override
    public Game.PlayerMove move(Player player, Roll roll) {
        return Game.PlayerMove.of(player, 12);
    }
}
