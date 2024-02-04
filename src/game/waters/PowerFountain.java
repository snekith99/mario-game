package game.waters;

import game.Status;
import game.actions.DrinkAction;
import game.actions.RefillAction;

import java.util.Stack;

import engine.actions.ActionList;
import engine.actors.Actor;
import engine.positions.Location;

import static game.Utils.ATTACKING_POINTS;

public class PowerFountain extends Fountain {
    /**
     * Name of Water (Power water)
     */
    private static final String NAME = "Power water";
    /**
     * displayChar of PowerWater
     */
    private static final char DISPLAY_CHAR = 'A';

    /**
     * Constructor.
     */
    public PowerFountain() {
        super(DISPLAY_CHAR);
        this.addCapability(Status.FOUNTAIN);

        for (int i = 0; i < 10; i++) {
            fountains.add(new Water(this));
        }
    }

    @Override
    public String waterEffect(Effect actor) {
        actor.attacking(15);
        return actor + " attack damage increases by " + ATTACKING_POINTS;
    }

    @Override
    public int getTurn() {
        return fountains.size();
    }

    @Override
    public Stack<Water> getFountainWaters() {
        return fountains;
    }

    @Override
    public ActionList allowableActions(Actor actor, Location location, String direction) {
        ActionList actions = new ActionList();

        if (location.containsAnActor() && !fountains.isEmpty() && actor.hasCapability(Status.DRINK)) {
            actions.add(new RefillAction(new Water(this)));
        }

        if (actor.hasCapability(Status.ENEMY_DRINK) && !fountains.isEmpty()) {
            fountains.pop();
            this.waterEffect((Effect) actor);
        }

        return actions;
    }

    @Override
    public void tick(Location location) {
        if (fountains.isEmpty()) {
            counter++;
            if (counter == 10) {
                for (int i = 0; i < 10; i++) {
                    fountains.add(new Water(this));
                }
            }
            counter = 0;
        }
    }

    @Override
    public String toString() {
        return NAME;
    }

}
