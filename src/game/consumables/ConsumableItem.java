package game.consumables;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import game.actions.ConsumeAction;
import game.trading.Purchasable;

abstract public class ConsumableItem extends Item {
    /***
     * Constructor.
     *  @param name the name of this Item
     * @param displayChar the character to use to represent this item if it is on the ground
     * @param portable true if and only if the Item can be picked up
     */
    public ConsumableItem(String name, char displayChar, boolean portable, int price) {
        super(name, displayChar, portable);

        // Adding consumeAction for ConsumableItem
        this.addAction(new ConsumeAction(this));
    }

    public ConsumableItem(String name, char displayChar, boolean portable) {
        super(name, displayChar, portable);

        // Adding consumeAction for ConsumableItem
        this.addAction(new ConsumeAction(this));
    }

    /**
     * Method to be overridden if consumableItem has a fading ticker (eg PowerStar)
     * @return number of turns left
     */
    public int getFade(){
        return 0;
    }

    /**
     * Method to be overridden for the consumableItem for their respective perks
     * @param actor actor to receive these perks
     */
    public abstract String consumableFeatures(Actor actor);
}
