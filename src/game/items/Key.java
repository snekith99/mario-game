package game.items;

import engine.items.Item;
import game.Status;

public class Key extends Item {
    private static final String NAME = "Key";
    private static final char DISPLAY_CHAR = 'k';
    private static final boolean PORTABLE = true;

    /***
     * Constructor
     */
    public Key() {
        super(NAME, DISPLAY_CHAR, PORTABLE);
        this.addCapability(Status.UNLOCK);
    }
}
