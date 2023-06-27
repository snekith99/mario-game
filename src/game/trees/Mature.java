package game.trees;

import edu.monash.fit2099.engine.positions.Location;
import game.enemies.FlyingKoopa;
import game.grounds.Dirt;
import game.enemies.Koopa;
import game.Status;
import game.Utils;
import game.resets.Resettable;
import jdk.jshell.execution.Util;

public class Mature extends Tree {
    /**
     * displayChar for Mature
     */
    private static final char DISPLAY_CHAR = 'T';
    /**
     * Name of tree (Mature)
     */
    private static final String NAME = "Mature";
    /**
     * fallDamage from mature tree
     */
    private static final int FALL_DAMAGE = 30;
    /**
     * successRate for mature tree
     */
    private static final int SUCCESS_RATE = 70;

    /**
     * Constructor
     */
    public Mature() {
        super(DISPLAY_CHAR, NAME, FALL_DAMAGE, SUCCESS_RATE);
        counter = 0;


    }

    @Override
    public void tick(Location location) {
        counter++;
        boolean condition = false;

        // to spawn sapling randomly
        if (counter % 5 == 0) {

            while (!condition){

                // random exit
                int i = Utils.randSpawn(location.getExits().size());

                // checks if exits are fertile
                if (location.getExits().get(i).getDestination().getGround().hasCapability(Status.FERTILE)){
                    location.getExits().get(i).getDestination().setGround(new Sprout());
                    condition = true;
                }
            }
        }

        // removing mature if reset
        if (this.hasCapability(Status.RESETTABLE)){
            if(Utils.chance() <= 50){
                location.setGround(new Dirt());
            }
            this.removeCapability(Status.RESETTABLE);
        }

        // spawning Koopa
        if (Utils.chance() <= 15 && !location.containsAnActor()) {

            // FlyingKoopa or Koopa
            if (Utils.chance() <= 50){
                location.addActor(new FlyingKoopa());
            }
            else{
                location.addActor(new Koopa());
            }

        }

        // withers and dies (becomes dirt)
        if (Utils.chance() <= 20) {
            location.setGround(new Dirt());
        }
    }
}

