package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;

public class UnlockAction extends Action {

    @Override
    public String execute(Actor actor, GameMap map) {
        return actor + " saved Peach :)";
    }

    @Override
    public String menuDescription(Actor actor) {
        return actor + " saves Peach :)";
    }
}
