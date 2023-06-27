package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import game.waters.Effect;
import game.waters.Water;

public class DrinkAction extends Action {

    private final Water water;

    public DrinkAction(Water water) {
        this.water = water;
    }

    @Override
    public String execute(Actor actor, GameMap map) {
        water.getFountainType().getFountainWaters().pop().drunkBy((Effect) actor);
        return menuDescription(actor);
    }

    @Override
    public String menuDescription(Actor actor) {
        return actor + "has drank from" + water.getFountainType();
    }
}
