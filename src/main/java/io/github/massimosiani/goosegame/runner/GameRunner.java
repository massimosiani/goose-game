package io.github.massimosiani.goosegame.runner;

import io.github.massimosiani.goosegame.adapter.ConsoleGamePlayerService;
import io.github.massimosiani.goosegame.adapter.GameBuilder;
import io.github.massimosiani.goosegame.domain.Player;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

public class GameRunner {

    public static void main(String... args) throws IOException {
        var gameBuilder = new GameBuilder();
        var gameService = new ConsoleGamePlayerService(true).addPlayers(gameBuilder);
        gameService.roll(gameBuilder.start());
    }
}
