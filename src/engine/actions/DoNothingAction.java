package engine.actions;

import engine.actors.Actor;
import engine.positions.GameMap;

/**
 * An Action that doesn't do anything.
 * 
 * Use this to implement waiting or similar actions in game clients.
 */
public class DoNothingAction extends Action {

	/**
	 * Constructor
	 */
	public DoNothingAction() {
	}

	@Override
	public String execute(Actor actor, GameMap map) {
		return menuDescription(actor);
	}

	@Override
	public String menuDescription(Actor actor) {
		return actor + " does nothing";
	}

	@Override
	public String hotkey() {
		return "5";
	}
}
