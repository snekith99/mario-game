package game.waters;

import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Location;
import game.Status;
import game.actions.RefillAction;
import java.util.Stack;

import static game.Utils.HEALING_POINTS;

public class HealthFountain extends Fountain {
    /**
     * Name of Water (HealthWater)
     */
    private static final String NAME = "Healing water";
    /**
     * displayChar of HealthWater
     */
    private static final char DISPLAY_CHAR = 'H';
    /**
     * Constructor
     *
     */
    public HealthFountain() {
        super(DISPLAY_CHAR);
        this.addCapability(Status.FOUNTAIN);
        for (int i = 0; i < 10; i++){
            fountains.push(new Water(this));
        }

    }

    @Override
    public String waterEffect(Effect actor) {
        // Heal actor by 50 points
        actor.healing(HEALING_POINTS);
        return actor + " has healed " + HEALING_POINTS;
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
            return actions;
        }

        if (actor.hasCapability(Status.ENEMY_DRINK) && !fountains.isEmpty()) {
            fountains.pop();
            this.waterEffect((Effect) actor);
        }

        return actions;
    }


    @Override
    public void tick(Location location) {
        if (fountains.isEmpty()){
            counter++;
            if (counter == 10){
                for (int i = 0; i < 10; i++){
                    fountains.add(new Water(this));
                }
                counter = 0;
            }
        }
    }

    @Override
    public String toString(){
        return NAME;
    }
}
