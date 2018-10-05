package io.github.massimosiani.goosegame;

import io.github.massimosiani.goosegame.adapter.GameBuilder;
import io.github.massimosiani.goosegame.domain.Player;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class GameTest {

    @Test
    public void cannotAddTheSamePlayer() {
        var gameBuilder = new GameBuilder();
        gameBuilder.addPlayer(new Player("foo"));
        assertEquals(1, gameBuilder.toGame().getPlayers().size());
        gameBuilder.addPlayer(new Player("foo"));
        assertEquals(1, gameBuilder.toGame().getPlayers().size());
    }
}
