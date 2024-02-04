package game.items;

import engine.actors.Actor;
import engine.weapons.WeaponItem;
import game.Status;
import game.trading.Purchasable;

public class Wrench extends WeaponItem implements Purchasable {
    /**
     * Name of WeaponItem (Wrench)
     */
    private final static String NAME = "Wrench";
    /**
     * displayChar of Wrench
     */
    private final static char DISPLAY_CHAR = 'W';
    /**
     * Damage of wrench
     */
    private final static int DAMAGE = 50;
    /**
     * Verb for wrench
     */
    private final static String VERB = "hits";
    /**
     * hitRate of Wrench
     */
    private final static int HIT_RATE = 80;
    /**
     * Price of wrench to buy from toad
     */
    private final static int PRICE = 200;

    /**
     * Constructor
     */
    public Wrench() {
        super(NAME, DISPLAY_CHAR, DAMAGE, VERB, HIT_RATE);

        // adds capability to finish dormant state enemies
        this.addCapability(Status.DORMANTKILL);
    }

    /**
     * Getter for price of wrench
     * 
     * @return price of wrench
     */
    public int getPrice() {
        return PRICE;
    }

    @Override
    public void purchasedBy(Actor actor) {
        actor.addItemToInventory(this);
    }
}
