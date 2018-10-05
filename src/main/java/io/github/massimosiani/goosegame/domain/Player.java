package io.github.massimosiani.goosegame.domain;

import java.util.Objects;

public class Player {
    private final String name;

    private int currentPosition = 0;

    public Player(String name) {
        this.name = Objects.requireNonNull(name);
    }

    public String getName() {
        return name;
    }

    public int getCurrentPosition() {
        return currentPosition;
    }

    public Player setCurrentPosition(int currentPosition) {
        this.currentPosition = currentPosition;
        return this;
    }

    @Override
    public String toString() {
        return "Player={name=" + name + ", currentPosition=" + currentPosition + "}";
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
