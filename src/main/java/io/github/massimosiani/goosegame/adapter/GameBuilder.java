package io.github.massimosiani.goosegame.adapter;

import io.github.massimosiani.goosegame.domain.Game;
import io.github.massimosiani.goosegame.domain.Player;

public class GameBuilder {
    private Game game = new Game(63);

    public GameBuilder addPlayer(Player player) {
        game.addPlayer(player);
        return this;
    }

    public Game start() {
        return game.start();
    }

    public Game toGame() {
        return game;
    }
}
