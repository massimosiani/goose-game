package io.github.massimosiani.goosegame.adapter;

import io.github.massimosiani.goosegame.domain.Game;
import io.github.massimosiani.goosegame.domain.Player;
import io.github.massimosiani.goosegame.domain.Roll;
import io.github.massimosiani.goosegame.domain.Square;
import io.github.massimosiani.goosegame.service.GamePlayerService;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import java.util.stream.Collectors;

public class ConsoleGamePlayerService implements GamePlayerService {

    private boolean playAutomatic;

    private BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

    private String firstMove;

    public ConsoleGamePlayerService(boolean playAutomatic) {
        this.playAutomatic = playAutomatic;
    }

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
        try {
            List<Player> players = game.getPlayers();
            int nextIndexPlayer = 0;
            while (!game.isFinished()) {
                MoveCommand mc = parseMove(reader);
                String player = mc.player;
                Roll theRoll = mc.roll;
                Player nextPlayer = players.get(nextIndexPlayer);
                nextIndexPlayer = (nextIndexPlayer + 1) % (players.size());
                if (!nextPlayer.equals(new Player(player))) {
                    System.err.println(player + " is not the next player, please try again");
                    continue;
                }
                Square startSquare = nextPlayer.getCurrentSquare();
                Game.PlayerMove move = move(game, nextPlayer, theRoll);
                System.out.print(String.format(
                        "%s rolls %d, %d. ",
                        player,
                        theRoll.first,
                        theRoll.second));
                move.getMoves().forEach(m -> {
                    System.out.print(String.format(
                            "%s moves from %s to %s. ",
                            player,
                            startSquare.toString(),
                            move.getSquare()));
                });
                System.out.println();
            }
        } catch (IOException ex) {
            System.err.println("Error while playing the game: " + ex.getMessage());
            System.exit(1);
        }
    }

    public ConsoleGamePlayerService addPlayers(GameBuilder gameBuilder) throws IOException {
        String player;
        int playersNumber = 0;
        while (!(player = parsePlayer(reader)).startsWith("first move: ")) {
            gameBuilder.addPlayer(new Player(player));
            if (gameBuilder.getPlayers().size() > playersNumber) {
                playersNumber = gameBuilder.getPlayers().size();
                String players = gameBuilder.getPlayers().stream().map(Player::getName).collect(Collectors.joining(", "));
                System.out.println("players: " + players);
            } else {
                System.out.println(player + ": already existing player");
            }
        }
        firstMove = player.substring("first move: ".length());
        return this;
    }

    private String parsePlayer(BufferedReader reader) throws IOException {
        String line = reader.readLine();
        String addPlayerCommand = "add player ";
        if (line.toLowerCase().startsWith(addPlayerCommand)) {
            return line.substring(addPlayerCommand.length()).trim();
        }
        return "first move: " + line;
    }

    private MoveCommand parseMove(BufferedReader reader) throws IOException {
        String line = firstMove.length() > 0 ? firstMove : reader.readLine();
        firstMove = "";
        String movePlayerCommand = "move ";
        if (line.toLowerCase().startsWith(movePlayerCommand)) {
            line = line.substring(movePlayerCommand.length()).trim();
            String player = line.split(" ")[0];
            String dice = line.substring(player.length());
            return new MoveCommand(player, dice);
        }
        throw new IllegalArgumentException("Command not recognized: " + line);
    }

    public static class MoveCommand {
        final String player;
        final Roll roll;

        MoveCommand(String player, String dice) {
            this.player = player;
            roll = dice != null && dice.trim().length() > 0 ?
                    new Roll(Integer.valueOf(dice.split(",")[0].trim()), Integer.valueOf(dice.split(",")[1].trim())) :
                    Roll.generate();
        }
    }
}
