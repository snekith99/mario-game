package game.actions;

import engine.actions.Action;
import engine.actors.Actor;
import engine.positions.GameMap;
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
