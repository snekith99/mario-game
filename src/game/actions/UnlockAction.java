package game.actions;

import engine.actions.Action;
import engine.actors.Actor;
import engine.positions.GameMap;

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
