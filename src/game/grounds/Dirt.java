package game.grounds;

import edu.monash.fit2099.engine.positions.Ground;
import game.Status;

/**
 * A class that represents bare dirt.
 */
public class Dirt extends Ground {

	public Dirt() {
		super('.');

		// Adding Fertility status to spawn sprouts
		this.addCapability(Status.FERTILE);
	}
}
