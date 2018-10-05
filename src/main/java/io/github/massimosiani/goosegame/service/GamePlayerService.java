package io.github.massimosiani.goosegame.service;

import io.github.massimosiani.goosegame.domain.Game;
import io.github.massimosiani.goosegame.domain.Player;
import io.github.massimosiani.goosegame.domain.Roll;

import java.util.List;

public interface GamePlayerService {

    List<Player> addPlayer(Game game, Player player);

    Game.PlayerMove move(Game game, Player player, Roll roll);

    void roll(Game game);
}
