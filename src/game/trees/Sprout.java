package game.trees;

import edu.monash.fit2099.engine.positions.Location;
import game.Status;
import game.enemies.Goomba;
import game.Utils;
import game.grounds.Dirt;
import game.consumables.FireFlower;
import game.resets.Resettable;

public class Sprout extends Tree implements Resettable {
    /**
     * displayChar of Sprout
     */
    private static final char DISPLAY_CHAR = '+';
    /**
     * name of Tree (Sprout)
     */
    private static final String NAME = "Sprout";
    /**
     * fallDamage of Sprout
     */
    private static final int FALL_DAMAGE = 10;
    /**
     * successRate of Sprout
     */
    private static final int SUCCESS_RATE = 90;
    /**
     * Constructor.
     */
    public Sprout() {
        super(DISPLAY_CHAR, NAME, FALL_DAMAGE, SUCCESS_RATE);
        counter = 0;
    }

    @Override
    public void tick(Location location){
        counter++;

        // from sprout to sapling
        if (counter %  10 == 0){
            location.setGround(new Sapling());
            if (Utils.chance() <= 50){
                location.addItem(new FireFlower());
            }
        }

        // spawn goomba
        if (Utils.chance() <= 10 && !location.containsAnActor()) {
            location.addActor(new Goomba());
        }

        // removing sprout if reset
        if (this.hasCapability(Status.RESETTABLE)){
            if (Utils.chance() <= 50){
                location.setGround(new Dirt());
            }

            this.removeCapability(Status.RESETTABLE);
        }
    }
}
