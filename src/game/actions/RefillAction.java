package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import game.waters.BottleManager;
import game.waters.Fountain;
import game.waters.HealthFountain;
import game.waters.Water;

public class RefillAction extends Action {

    private final Water water;

    /**
     * Constructor
     * @param water water to be refilled
     */

    public RefillAction(Water water) {
        this.water = water;
    }
    /**
     * @param actor The actor performing the action.
     * @param map The map the actor is on.
     * @return water filled statement
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        BottleManager.getInstance().refill(water);
        //pop water type into stack
        water.getFountainType().getFountainWaters().pop();
        return actor + " filled the bottle with " + water.getFountainType();
    }
    /**
     * @param actor The actor performing the action.
     * @return refill action
     */

    @Override
    public String menuDescription(Actor actor) {
        return actor + " refills bottle with " + water.getFountainType() + " (" + water.getFountainType().getTurn() + "/10)";
    }
}
