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

public class FlyingKoopa extends Enemy implements Effect {
    private static final String NAME = "FlyingKoopa";
    private static final char DISPLAY_CHAR = 'F';
    private static final int HIT_POINTS = 150;
    private int damage = 30;

    /**
     * Constructor.
     */
    public FlyingKoopa() {
        super(NAME, DISPLAY_CHAR, HIT_POINTS);
        this.addItemToInventory(new SuperMushroom());
        this.addCapability(Status.FLYING);
        this.addCapability(Status.ENEMY_DRINK);
        behaviours.put(10, new WanderBehaviour());
        behaviours.put(6, new DrinkBehaviour());
    }

    @Override
    public Action playTurn(ActionList actions, Action lastAction, GameMap map, Display display) {

        if (this.hasCapability(Status.RESETTABLE)) {
            map.removeActor(this);
            return new DoNothingAction();
        }

        // if unconscious, set to dormant mode
        if (!(this.isConscious())) {
            this.setDisplayChar('D');
        }

        // increment turn counter when player takes a turn
        turnCounter++;

        // if conditional to have Flying Koopa speak every 2 turns
        if (turnCounter % 2 == 0) {

            List<Monologue> flyingKoopa = this.generateMonologue();

            // utils random number
            int selection = Utils.actorMonologueRandomize(flyingKoopa.size());

            // return FlyingKoopa monologue
            display.println(flyingKoopa.get(selection).printDetails());
        }

        for (Behaviour Behaviour : behaviours.values()) {
            Action action = Behaviour.getAction(this, map);
            if (action != null)
                return action;
        }

        return new DoNothingAction();
    }

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

    /**
     * the complete monologue of Flying Koopa
     * 
     * @return monologue
     */

    public List<Monologue> generateMonologue() {

        sentences.add(new Monologue("Flying Koopa: \"Never gonna make you cry!\""));
        sentences.add(new Monologue("Flying Koopa: \"Koopi koopi koopii~!\""));
        sentences.add(new Monologue("Flying Koopa: \"Pam pam pam!\""));
        return sentences;
    }

    protected IntrinsicWeapon getIntrinsicWeapon() {
        return new IntrinsicWeapon(damage, "punches");
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
