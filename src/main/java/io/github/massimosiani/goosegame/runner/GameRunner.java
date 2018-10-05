package io.github.massimosiani.goosegame.runner;

import io.github.massimosiani.goosegame.adapter.ConsoleGamePlayerService;
import io.github.massimosiani.goosegame.adapter.GameBuilder;
import io.github.massimosiani.goosegame.domain.Player;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class GameRunner {

    public static void main(String... args) throws IOException {
        var reader = new BufferedReader(new InputStreamReader(System.in));
        String player;
        var gameBuilder = new GameBuilder();
        while ((player = reader.readLine()) != null) {
            gameBuilder.addPlayer(new Player(player));
        }
        new ConsoleGamePlayerService().roll(gameBuilder.toGame());
    }
}
