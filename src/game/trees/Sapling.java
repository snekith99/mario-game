package game.trees;

import edu.monash.fit2099.engine.positions.Location;
import game.*;
import game.grounds.Dirt;
import game.consumables.FireFlower;
import game.resets.Resettable;
import game.trading.Coin;

public class Sapling extends Tree implements Resettable {
    /**
     * displayChar of Sapling
     */
    private static final char DISPLAY_CHAR = 't';
    /**
     * name of Tree (Sapling)
     */
    private static final String NAME = "Sapling";
    /**
     * fallDamage of Sapling
     */
    private static final int FALL_DAMAGE = 20;
    /**
     * successRate of Sapling
     */
    private static final int SUCCESS_RATE = 80;
    /**
     * Constructor
     */
    public Sapling() {
        super(DISPLAY_CHAR, NAME, FALL_DAMAGE, SUCCESS_RATE);
        counter = 0;
    }

    @Override
    public void tick(Location location){
        counter++;

        // from sapling to mature
        if(counter %  10 == 0){
            location.setGround(new Mature());
            if (Utils.chance() <= 50){
                location.addItem(new FireFlower());
            }
        }

        // spawn coins
        if (Utils.chance() <= 10) {
            location.addItem(new Coin(20));
        }

        // removing sapling if reset
        if (this.hasCapability(Status.RESETTABLE)){
            if (Utils.chance() <= 50){
                location.setGround(new Dirt());
            }
            this.removeCapability(Status.RESETTABLE);
        }
    }
}

