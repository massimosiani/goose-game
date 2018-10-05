package io.github.massimosiani.goosegame.adapter;

import io.github.massimosiani.goosegame.domain.Game;
import io.github.massimosiani.goosegame.domain.Player;
import io.github.massimosiani.goosegame.domain.Roll;
import io.github.massimosiani.goosegame.service.GamePlayerService;

import java.util.List;

public class ConsoleGamePlayerService implements GamePlayerService {

    @Override
    public List<Player> addPlayer(Game game, Player player) {
        return game.addPlayer(player).getPlayers();
    }

    @Override
    public Game.PlayerMove move(Game game, Player player, Roll roll) {
        return game.move(player, roll);
    }

    @Override
    public void roll(Game game) {
        System.out.println(game.getPlayers());
        System.out.println("AAA");
    }
}
