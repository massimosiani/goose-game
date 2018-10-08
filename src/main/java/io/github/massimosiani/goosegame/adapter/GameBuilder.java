package io.github.massimosiani.goosegame.adapter;

import io.github.massimosiani.goosegame.domain.Game;
import io.github.massimosiani.goosegame.domain.Player;

import java.util.List;

public class GameBuilder {
    private Game game = new Game(63);

    public GameBuilder addPlayer(Player player) {
        game.addPlayer(player);
        return this;
    }

    public List<Player> getPlayers() {
        return game.getPlayers();
    }

    public Game start() {
        return game.start();
    }
}
