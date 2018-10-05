package io.github.massimosiani.goosegame;

import io.github.massimosiani.goosegame.domain.Player;
import static org.junit.Assert.*;
import org.junit.Test;

public class PlayerTest {

    @Test
    public void equality() {
        var base = new Player("foo");
        var same = new Player("foo");
        var different = new Player("bar");
        Player nullPlayer = null;

        assertEquals(base, same);
        assertNotEquals(base, different);
        assertNotEquals(base, nullPlayer);
    }
}
