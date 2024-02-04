package game.enemies;

import game.Status;
import game.Utils;
import game.actions.AttackAction;
import game.behaviours.*;
import game.consumables.SuperMushroom;
import game.waters.Effect;

import java.util.ArrayList;
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

public class Koopa extends Enemy implements Effect {
    private static final String NAME = "Koopa";
    private static final char DISPLAY_CHAR = 'K';
    private static final int HIT_POINTS = 100;
    private int damage = 30;

    /**
     * Constructor.
     */
    public Koopa() {
        super(NAME, DISPLAY_CHAR, HIT_POINTS);
        // Koopa has wander behaviour
        behaviours.put(10, new WanderBehaviour());

        // capabilities
        this.addCapability(Status.DORMANT);
        this.addCapability(Status.ENEMY_DRINK);

        // adding supermushrooom to inventory
        this.addItemToInventory(new SuperMushroom());

        // drink behaviour
        behaviours.put(6, new DrinkBehaviour());
    }

    /**
     * Implements resettable features, iterates through the behaviours and chooses
     * what Koopa should do next.
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
        if (!(this.isConscious())) {
            this.setDisplayChar('D');
        }

        // increment turn counter when player takes a turn
        turnCounter++;

        // if conditional to have Koopa speak every 2 turns
        if (turnCounter % 2 == 0) {

            List<Monologue> koopaMonologue = this.generateMonologue();

            // utils random number
            int selection = Utils.actorMonologueRandomize(koopaMonologue.size());

            // return Koopa monologue
            display.println(koopaMonologue.get(selection).printDetails());
        }

        for (Behaviour Behaviour : behaviours.values()) {
            Action action = Behaviour.getAction(this, map);
            if (action != null)
                return action;
        }

        return new DoNothingAction();
    }

    /**
     * Allows an actor to gain actions that can be performed on this actor
     * 
     * @param otherActor the Actor that might perform an action.
     * @param direction  String representing the direction of the other Actor
     * @param map        current GameMap
     * @return list of actions
     * @see Status#HOSTILE_TO_ENEMY
     */
    @Override
    public ActionList allowableActions(Actor otherActor, String direction, GameMap map) {
        ActionList actions = new ActionList();
        if (otherActor.hasCapability(Status.HOSTILE_TO_ENEMY)) {
            behaviours.put(1, new AttackBehaviour(otherActor));
            behaviours.put(5, new FollowBehaviour(otherActor));

            if (!(this.isConscious()) && !(otherActor.hasCapability(Status.DORMANTKILL))) {
                behaviours.remove(10);
                behaviours.remove(5);
                behaviours.remove(1);
                return actions;
            }

            actions.add(new AttackAction(this, direction));

        }
        return actions;
    }

    @Override
    protected IntrinsicWeapon getIntrinsicWeapon() {
        return new IntrinsicWeapon(damage, "punches");
    }

    /**
     * the complete monologue of Koopa
     * 
     * @return monologue
     */
    public List<Monologue> generateMonologue() {
        sentences.add(new Monologue("Koopa: \"Never gonna make you cry!\""));
        sentences.add(new Monologue("Koopa: \"Koopi koopi koopii~!\""));
        return sentences;
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
