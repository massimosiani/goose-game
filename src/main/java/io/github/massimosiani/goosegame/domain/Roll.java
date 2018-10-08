package io.github.massimosiani.goosegame.domain;

import java.util.Objects;

public class Roll {
    public final Integer first;
    public final Integer second;

    public Roll(Integer first, Integer second) {
        this.first = Objects.requireNonNull(first);
        this.second = Objects.requireNonNull(second);
    }

    public static Roll generate() {
        return new Roll((int)Math.round(Math.random() * 5 + 1), (int)Math.round(Math.random() * 5 + 1));
    }
}
