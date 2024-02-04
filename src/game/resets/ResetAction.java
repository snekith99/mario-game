package game.resets;

import engine.actions.Action;
import engine.actors.Actor;
import engine.positions.GameMap;

public class ResetAction extends Action {
    /**
     * Runs the Reset Manager to reset all instances that are resettable
     * 
     * @param actor The actor performing the action.
     * @param map   The map the actor is on.
     * @return
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        // run ResetManager to reset game
        ResetManager.getInstance().run();
        return "Game has been reset";
    }

    @Override
    public String hotkey() {
        // r to reset game
        return "r";
    }

    @Override
    public String menuDescription(Actor actor) {
        return "Reset the game";
    }
}
