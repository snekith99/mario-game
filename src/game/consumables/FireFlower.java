package game.consumables;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Location;
import game.Status;
import game.consumables.ConsumableItem;

public class FireFlower extends ConsumableItem {
    private static final String NAME = "Fire Flower";
    private static final char DISPLAY_CHAR = 'f';
    private static final boolean PORTABLE = false;


    public FireFlower() {
        super(NAME, DISPLAY_CHAR, PORTABLE);
    }

    @Override
    public String consumableFeatures(Actor actor) {
        // add Capability
        actor.addCapability(Status.FIRE);
        return actor + " has consumed " + this;
    }
}
