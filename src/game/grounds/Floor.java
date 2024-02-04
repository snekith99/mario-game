package game.grounds;

import engine.actors.Actor;
import engine.positions.Ground;
import game.Status;

/**
 * A class that represents the floor inside a building.
 */
public class Floor extends Ground {
	public Floor() {
		super('_');
	}

	@Override
	public boolean canActorEnter(Actor actor) {

		// If actor has Jumpable capability - only players
		return actor.hasCapability(Status.JUMPABLE);
	}
}
