package game.enemies;

import game.behaviours.Monologue;
import game.*;
import game.actions.AttackAction;
import game.behaviours.*;
import game.waters.Effect;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import engine.actions.Action;
import engine.actions.ActionList;
import engine.actions.DoNothingAction;
import engine.actors.Actor;
import engine.displays.Display;
import engine.positions.GameMap;
import engine.weapons.IntrinsicWeapon;

import java.util.ArrayList;

/**
 * A little fungus guy.
 */
public class Goomba extends Enemy implements Effect {
	private static final String name = "Goomba";
	private static final char displayChar = 'g';
	private static final int hitPoints = 20;
	private int damage = 10;

	/**
	 * Constructor.
	 */
	public Goomba() {
		super(name, displayChar, hitPoints);
		this.addCapability(Status.ENEMY_DRINK);
		behaviours.put(10, new WanderBehaviour());
		behaviours.put(6, new DrinkBehaviour());
	}

	/**
	 * Allows an actor to gain actions that can be performed on this actor. Also
	 * contains behaviours that this actor can use.
	 * 
	 * @param otherActor the Actor that might perform an action.
	 * @param direction  String representing the direction of the other Actor
	 * @param map        current GameMap
	 * @return list of actions
	 * @see Status#HOSTILE_TO_ENEMY
	 */
	@Override
	public ActionList allowableActions(Actor otherActor, String direction, GameMap map) {
		// new action list that contains actions that the other actor can utilise
		ActionList actions = new ActionList();
		// adds behaviours that this actor can use
		behaviours.put(1, new AttackBehaviour(otherActor));
		behaviours.put(5, new FollowBehaviour(otherActor));
		// gives attack action to other actors that only are hostile to this actor
		if (otherActor.hasCapability(Status.HOSTILE_TO_ENEMY)) {
			actions.add(new AttackAction(this, direction));
		}
		return actions;
	}

	/**
	 * Implements resettable features, iterates through the behaviours and chooses
	 * what Goomba should do next.
	 * 
	 * @param actions    collection of possible Actions for this Actor
	 * @param lastAction The Action this Actor took last turn. Can do interesting
	 *                   things in conjunction with Action.getNextAction()
	 * @param map        the map containing the Actor
	 * @param display    the I/O object to which messages may be written
	 */
	@Override
	public Action playTurn(ActionList actions, Action lastAction, GameMap map, Display display) {

		if (this.hasCapability(Status.RESETTABLE)) {
			map.removeActor(this);
			return new DoNothingAction();
		}

		// increment turn counter when player takes a turn
		turnCounter++;

		// if conditional to have Goomba speak every 2 turns
		if (turnCounter % 2 == 0) {

			List<Monologue> goombaMonologue = this.generateMonologue();

			// utils random number
			int selection = Utils.actorMonologueRandomize(goombaMonologue.size());

			// return Goomba monologue
			display.println(goombaMonologue.get(selection).printDetails());
		}

		for (Behaviour Behaviour : behaviours.values()) {
			Action action = Behaviour.getAction(this, map);

			if (Utils.chance() <= 10) {
				map.removeActor(this);
				return new DoNothingAction();
			}
			if (action != null)
				return action;
		}

		return new DoNothingAction();
	}

	/**
	 * the complete monologue of Goomba
	 * 
	 * @return monologue
	 */
	public List<Monologue> generateMonologue() {

		sentences.add(new Monologue("Goomba: \"Mugga mugga!\""));
		sentences.add(new Monologue("Goomba: \"Ugha ugha... (Never gonna run around and desert you...)\""));
		sentences.add(new Monologue("Goomba: \"Ooga-Chaka Ooga-Ooga!\""));
		return sentences;
	}

	@Override
	protected IntrinsicWeapon getIntrinsicWeapon() {
		return new IntrinsicWeapon(damage, "kicks");
	}

	@Override
	public void healing(int healingPoints) {
		this.heal(healingPoints);
	}

	@Override
	public void attacking(int damage) {
		this.damage += damage;
	}
}
