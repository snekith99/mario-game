package game.waters;

import edu.monash.fit2099.engine.actors.Actor;
import game.Status;
import game.consumables.ConsumableItem;


public class Bottle extends ConsumableItem {
    /**
     * Name of ConsumableItem (Bottle)
     */
    private static final String NAME = "Bottle";
    /**
     * displayChar of Bottle
     */
    private static final char DISPLAY_CHAR = 'b';
    /**
     * cannot be carried
     */
    private static final boolean PORTABLE = false;

    public Bottle() {
        super(NAME, DISPLAY_CHAR, PORTABLE);

        // adding drink capability
        this.addCapability(Status.DRINK);
    }
    /**
     * Consumable features override
     * @param actor actor to receive these perks
     * @return string of actor receiving perks
     */
    @Override
    public String consumableFeatures(Actor actor) {
        // checking if empty
        if (BottleManager.getInstance().getBottle().isEmpty()){
            return "Bottle is empty";
        }
        // otherwise drink the last water in fountain
        return BottleManager.getInstance().drink().drunkBy((Effect) actor);
    }
}
