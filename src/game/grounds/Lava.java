package game.grounds;

import engine.actors.Actor;
import engine.positions.Ground;
import engine.positions.Location;
import game.Status;

public class Lava extends Ground {
    /**
     * Constructor.
     *
     * @param
     */
    public Lava() {
        super('L');
    }

    @Override
    public void tick(Location location) {
        if (location.containsAnActor()) {
            location.getActor().hurt(15);
        }
    }

    @Override
    public boolean canActorEnter(Actor actor) {
        return actor.hasCapability(Status.HOSTILE_TO_ENEMY);
    }
}
