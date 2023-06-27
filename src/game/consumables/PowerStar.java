package game.consumables;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.Location;
import game.Status;
import game.trading.Purchasable;


public class PowerStar extends ConsumableItem implements Purchasable {
    /**
     * Name of ConsumableItem (PowerStar)
     */
    private static final String NAME = "PowerStar";
    /**
     * displayChar of PowerStar
     */
    private static final char DISPLAY_CHAR = '*';
    /**
     * can be carried
     */
    private static final boolean PORTABLE = true;
    /**
     * Price of PowerStar to buy from toad
     */
    private final static int PRICE = 600;
    /**
     * fading turn ticker of PowerStar
     */
    private int fadeCounter = 10;

    /***
     * Constructor.
     */
    public PowerStar() {
        super(NAME, DISPLAY_CHAR, PORTABLE, PRICE);
    }

    /**
     * Tick method that contains the counter for powerstar holding item
     * @param currentLocation The location of the actor carrying this Item.
     * @param actor The actor carrying this Item.
     */
    @Override
    public void tick(Location currentLocation, Actor actor) {
        fadeCounter--;

        // checks if it has been in game for 10 turns in actor's inventory
        if (fadeCounter == 0) {

            // remove item from inventory
            actor.removeItemFromInventory(this);
        }

    }

    /**
     * Tick method that contains the counter for powerstar on the ground, which will disappear once the counter reaches 0
     * @param currentLocation The location of the ground on which we lie.
     */
    @Override
    public void tick(Location currentLocation) {
        fadeCounter--;

        // checks if it has been in game for 10 turns on map
        if (fadeCounter == 0) {

            // remove item from location
            currentLocation.removeItem(this);
        }
    }


    @Override
    public String consumableFeatures(Actor actor) {
        // Add invincibility status
        actor.addCapability(Status.INVINCIBLE);
        // Heals actor by 200 points
        actor.heal(200);
        return actor + "has consumed Powerstar";
    }

    @Override
    public int getFade(){
        return fadeCounter;
    }

    /**
     * Getter for price
     * @return price of consumableItem
     */
    @Override
    public int getPrice(){
        return PRICE;
    }

    @Override
    public void purchasedBy(Actor actor) {
        actor.addItemToInventory(this);
    }
}
