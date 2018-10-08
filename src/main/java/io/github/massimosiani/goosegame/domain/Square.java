package io.github.massimosiani.goosegame.domain;

public abstract class Square {
    protected int position;

    public Square(int position) {
        this.position = position;
    }

    public abstract Game.PlayerMove move(Player player, Roll roll);

    @Override
    public String toString() {
        return "" + this.position;
    }
}
