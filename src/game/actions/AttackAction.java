package game.actions;

import java.util.Random;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.Location;
import edu.monash.fit2099.engine.weapons.Weapon;
import game.Status;
import game.items.Fire;

/**
 * Special Action for attacking other Actors.
 */
public class AttackAction extends Action {
	/**
	 * The Actor that is to be attacked
	 */
	protected Actor target;

	/**
	 * The direction of incoming attack.
	 */
	protected String direction;

	/**
	 * Random number generator
	 */
	protected Random rand = new Random();
	/**
	 * Constructor.
	 *
	 * @param target the Actor to attack
	 * @param direction the direction where the target is relative to the location of actor
	 */
	public AttackAction(Actor target, String direction) {
		this.target = target;
		this.direction = direction;
	}

	/**
	 * Once attack action has been chosen, the execute will take place.
	 * @param actor The actor performing the action.
	 * @param map The map the actor is on.
	 * @return
	 */
	@Override
	public String execute(Actor actor, GameMap map) {

		Weapon weapon = actor.getWeapon();
		// hit rate of weapon
		if (!(rand.nextInt(100) <= weapon.chanceToHit())) {
			return actor + " misses " + target;
		}

		int damage = weapon.damage();

		// invincibility damage should attack and kill in one hit
		if (actor.hasCapability(Status.INVINCIBLE)){
			damage = 1000;
		}
		// other actors cannot hurt invincible target
		if (target.hasCapability(Status.INVINCIBLE)) {
			damage = 0;
		}

		String result = actor + " " + weapon.verb() + " " + target + " for " + damage + " damage.";

		if (actor.hasCapability(Status.FIRE)) {
			Location targetLocation = map.locationOf(target);
			targetLocation.addItem(new Fire());
			result = actor + " attacked " + target + " with fire!";
		}

		target.hurt(damage);

		// actors hitting a target will remove the target's super mushroom buff
		if (target.hasCapability(Status.TALL)) {
			target.removeCapability(Status.TALL);
		}

		// Drops items and removes the target if the target is not koopa and is no longer conscious, or if mario holds a wrench which will kill koopa
		if ((!target.isConscious() && !target.hasCapability(Status.DORMANT)) || (actor.hasCapability(Status.DORMANTKILL)) && target.hasCapability(Status.DORMANT)) {
			ActionList dropActions = new ActionList();
			// drop all items
			for (Item item : target.getInventory())
				dropActions.add(item.getDropAction(actor));
			for (Action drop : dropActions)
				drop.execute(target, map);
			// remove actor
			map.removeActor(target);
			result += System.lineSeparator() + target + " is killed.";
		}
		// actor will turn dormant if they have the status and has no hp left
		else if (!target.isConscious() && target.hasCapability(Status.DORMANT)){
			result += System.lineSeparator() + target + " turns dormant.";
		}

		return result;
	}

	@Override
	public String menuDescription(Actor actor) {
		if (actor.hasCapability(Status.FIRE)){
			return actor + " attacks " + target + " at " + direction + " with fire!";
		}
		if (target.hasCapability(Status.DORMANTKILL) && actor.hasCapability(Status.DORMANTKILL)){
			return actor + " destroys " + target + " (Dormant)";
		}
		return actor + " attacks " + target + " at " + direction;
	}
}