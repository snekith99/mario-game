package game.consumables;

import engine.actors.Actor;
import engine.items.Item;
import game.Status;
import game.trading.Purchasable;

public class SuperMushroom extends ConsumableItem implements Purchasable {
    /**
     * Name of ConsumableItem (SuperMushroom)
     */
    private static final String NAME = "SuperMushroom";
    /**
     * displayChar of SuperMushroom
     */
    private static final char DISPLAY_CHAR = '^';
    /**
     * portable ( can carry in inventory)
     */
    private static final boolean PORTABLE = true;
    /**
     * Price of SuperMushroom
     */
    private final static int PRICE = 400;

    /***
     * Constructor.
     */
    public SuperMushroom() {
        super(NAME, DISPLAY_CHAR, PORTABLE, PRICE);
    }

    /**
     * Features that will be given to actor once the item has been consumed
     * 
     * @param actor actor to receive these perks
     */
    @Override
    public String consumableFeatures(Actor actor) {
        // Increase max hp
        actor.increaseMaxHp(50);

        // Add tall capability
        actor.addCapability(Status.TALL);

        return actor + "has consumed Super Mushroom";
    }

    /**
     * Getter for price
     * 
     * @return price of consumableItem
     */
    @Override
    public int getPrice() {
        return PRICE;
    }

    @Override
    public void purchasedBy(Actor actor) {
        actor.addItemToInventory(this);
    }
}
