package io.github.massimosiani.goosegame.domain;

import java.util.Objects;

public class Player {
    private final String name;

    private Square currentSquare;

    public Player(String name) {
        this.name = Objects.requireNonNull(name);
    }

    public String getName() {
        return name;
    }

    public Square getCurrentSquare() {
        return currentSquare;
    }

    public Player setCurrentSquare(Square currentSquare) {
        this.currentSquare = currentSquare;
        return this;
    }

    public Game.PlayerMove move(Roll roll) {
        return currentSquare.move(this, roll);
    }

    @Override
    public String toString() {
        return "Player={name=" + name + ", currentSquare=" + (currentSquare != null ? currentSquare.toString() : "null") + "}";
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result
                + ((name == null) ? 0 : name.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (!(obj instanceof Player))
            return false;
        Player other = (Player) obj;
        if (!name.equals(other.name))
            return false;
        return true;
    }
}
