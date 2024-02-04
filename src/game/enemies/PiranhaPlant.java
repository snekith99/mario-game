package game.enemies;

import game.Status;
import game.Utils;
import game.actions.AttackAction;
import game.behaviours.AttackBehaviour;
import game.behaviours.Behaviour;
import game.behaviours.Monologue;

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

public class PiranhaPlant extends Enemy {
    private final Map<Integer, Behaviour> behaviours = new HashMap<>(); // priority, behaviour
    private static final String NAME = "PiranhaPlant";
    private static final char DISPLAY_CHAR = 'Y';
    private static final int HIT_POINTS = 150;
    private int turnCounter = 0;

    /**
     * Constructor
     */
    public PiranhaPlant() {
        super(NAME, DISPLAY_CHAR, HIT_POINTS);
    }

    @Override
    public Action playTurn(ActionList actions, Action lastAction, GameMap map, Display display) {

        if (this.hasCapability(Status.RESETTABLE)) {
            this.increaseMaxHp(50);
            return new DoNothingAction();
        }
        // increment turn counter when player takes a turn
        turnCounter++;

        // if conditional to have Piranha Plant speak every 2 turns
        if (turnCounter % 2 == 0) {

            List<Monologue> piranhaPlantMonologue = this.generateMonologue();

            // utils random number
            int selection = Utils.actorMonologueRandomize(piranhaPlantMonologue.size());

            // return Piranha Plant monologue
            display.println(piranhaPlantMonologue.get(selection).printDetails());
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

        // new action list that contains actions that the other actor can utilise
        ActionList actions = new ActionList();

        // adds behaviours that this actor can use
        behaviours.put(1, new AttackBehaviour(otherActor));

        // gives attack action to other actors that only are hostile to this actor
        if (otherActor.hasCapability(Status.HOSTILE_TO_ENEMY)) {
            actions.add(new AttackAction(this, direction));
        }

        return actions;

    }

    @Override
    protected IntrinsicWeapon getIntrinsicWeapon() {
        return new IntrinsicWeapon(90, "chomps");
    }

    /**
     * the complete monologue of Piranha Plant
     * 
     * @return monologue
     */

    public List<Monologue> generateMonologue() {
        sentences.add(new Monologue("Piranha Plant: \"Slsstssthshs~! (Never gonna say goodbye~)\""));
        sentences.add(new Monologue("Piranha Plant: \"Ohmnom nom nom nom.\""));
        return sentences;
    }

}
