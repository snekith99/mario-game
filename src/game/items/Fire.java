package game.items;

import engine.items.Item;
import engine.positions.Location;

public class Fire extends Item {
    private static final String NAME = "Fire";
    private static final char DISPLAY_CHAR = 'v';
    private static final boolean PORTABLE = false;
    private int counter;

    /***
     * Constructor.
     */
    public Fire() {
        super(NAME, DISPLAY_CHAR, PORTABLE);
        counter = 0;
    }

    @Override
    public void tick(Location location) {
        counter++;

        // after 3 turns, it will remove the fire from the map
        if (counter == 3) {
            location.removeItem(this);
        }

        // any actor who stands on the fire will lose 20 HP
        if (location.containsAnActor()) {
            location.getActor().hurt(20);
        }
    }
}
