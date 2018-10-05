package io.github.massimosiani.goosegame;

import io.github.massimosiani.goosegame.adapter.ConsoleGamePlayerService;
import io.github.massimosiani.goosegame.domain.Game;
import io.github.massimosiani.goosegame.adapter.GameBuilder;
import io.github.massimosiani.goosegame.domain.Player;
import io.github.massimosiani.goosegame.domain.Roll;
import org.junit.Assert;
import org.junit.Test;

public class MovePlayerTest {
    private ConsoleGamePlayerService service = new ConsoleGamePlayerService();

    @Test
    public void shouldMoveThePlayer() {
        var pippo = new Player("Pippo");
        var pluto = new Player("Pluto");
        var game = new GameBuilder()
                .addPlayer(pippo)
                .addPlayer(pluto)
                .start();
        Game.PlayerMove pippoPosition = service.move(game, pippo, new Roll(3, 2));
        Assert.assertEquals(5, pippoPosition.square);
        Assert.assertFalse(pippoPosition.bounced);
        Game.PlayerMove plutoPosition = service.move(game, pluto, new Roll(2, 2));
        Assert.assertEquals(4, plutoPosition.square);
        Assert.assertFalse(plutoPosition.bounced);
        pippoPosition = service.move(game, pippo, new Roll(2, 3));
        Assert.assertEquals(10, pippoPosition.square);
        Assert.assertFalse(pippoPosition.bounced);
    }

    @Test
    public void shouldWinOn63() {
        var pippo = new Player("Pippo").setCurrentPosition(60);
        var game = new Game(63)
                .addPlayer(pippo);
        Game.PlayerMove pippoPosition = service.move(game, pippo, new Roll(1, 2));
        Assert.assertEquals(63, pippoPosition.square);
        Assert.assertTrue(pippoPosition.hasWon());
    }

    @Test
    public void shouldBounceOver63() {
        var pippo = new Player("Pippo").setCurrentPosition(60);
        var game = new Game(63)
                .addPlayer(pippo);
        Game.PlayerMove pippoPosition = service.move(game, pippo, new Roll(3, 2));
        Assert.assertEquals(61, pippoPosition.square);
        Assert.assertFalse(pippoPosition.hasWon());
    }

    @Test
    public void shouldTreat6AsTheBridge() {
        var pippo = new Player("Pippo").setCurrentPosition(4);
        var game = new Game(63)
                .addPlayer(pippo);
        Game.PlayerMove pippoPosition = service.move(game, pippo, new Roll(1, 1));
        Assert.assertEquals(12, pippoPosition.square);
    }

    @Test
    public void shouldMoveAgainOnTheGoose() {
        var pippo = new Player("Pippo").setCurrentPosition(3);
        var game = new Game(63)
                .addPlayer(pippo);
        Game.PlayerMove pippoPosition = service.move(game, pippo, new Roll(1, 1));
        Assert.assertEquals(7, pippoPosition.square);
    }

    @Test
    public void shouldMoveMultipleTimesOnTheGoose() {
        var pippo = new Player("Pippo").setCurrentPosition(10);
        var game = new Game(63)
                .addPlayer(pippo);
        Game.PlayerMove pippoPosition = service.move(game, pippo, new Roll(2, 2));
        Assert.assertEquals(22, pippoPosition.square);
    }
}
