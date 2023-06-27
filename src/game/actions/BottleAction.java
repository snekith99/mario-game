package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import game.Status;
import game.waters.Bottle;

public class BottleAction extends Action {

    @Override
    public String execute(Actor actor, GameMap map) {
        actor.addCapability(Status.DRINK);
        actor.addItemToInventory(new Bottle());
        return actor + " obtained bottle";
    }

    @Override
    public String menuDescription(Actor actor) {
        return actor + " obtains bottle";
    }
}
