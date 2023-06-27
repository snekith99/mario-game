package game.trading;

import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.Location;
import game.Status;
import game.actions.CoinAction;
import game.resets.Resettable;

public class Coin extends Item implements Resettable {
    /**
     * Amount of coin
     */
    private final int amount;
    /**
     * name of Item (Coin)
     */
    private static final String NAME = "Coin";
    /**
     * displayChar of coin
     */
    private static final char DISPLAY_CHAR = '$';
    /**
     * Not portable
     */
    private static final boolean PORTABLE = false;

    /***
     * Constructor
     * @param amount of coin being picked up
     */
    public Coin(int amount){
        super(NAME, DISPLAY_CHAR, PORTABLE);
        this.amount = amount;

        // adding CoinAction to pick up Coin
        this.addAction(new CoinAction(this));

        // Resetting
        registerInstance();
    }

    /**
     * Getter for amount
     * @return amount of coin
     */
    public int getAmount() {
        return this.amount;
    }

    @Override
    public void tick(Location currentLocation) {
        if (this.hasCapability(Status.RESETTABLE)) {
            currentLocation.removeItem(this);
        }
    }

    @Override
    public void resetInstance() {
        this.addCapability(Status.RESETTABLE);
    }
}
