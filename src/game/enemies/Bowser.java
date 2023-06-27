package game.enemies;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actions.DoNothingAction;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import edu.monash.fit2099.engine.weapons.IntrinsicWeapon;
import game.Utils;
import game.behaviours.*;
import game.Status;
import game.actions.AttackAction;
import game.items.Key;
import game.behaviours.Monologue;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Bowser extends Enemy {
    private static final String NAME = "Bowser";
    private static final char DISPLAY_CHAR = 'B';
    private static final int HIT_POINTS = 500;
    private final Location location;

    /**
     * Constructor.
     */
    public Bowser(Location location) {
        super(NAME, DISPLAY_CHAR, HIT_POINTS);
        this.addItemToInventory(new Key());
        this.location = location;
        this.addCapability(Status.CANUSEFIRE);
    }

    @Override
    public Action playTurn(ActionList actions, Action lastAction, GameMap map, Display display) {

        if (this.hasCapability(Status.RESETTABLE)){
            this.heal(HIT_POINTS);

            if (!location.containsAnActor()) {
                map.moveActor(this, location);
                this.removeCapability(Status.RESETTABLE);
                return new DoNothingAction();
            }

        }
        //increment turn counter when player takes a turn
        turnCounter++;

        //if conditional to have actor talk every alternate turn
        if (turnCounter % 2 == 0) {

            List<Monologue> bowserMonologue = this.generateMonologue();

            // utils random number
            int selection = Utils.actorMonologueRandomize(bowserMonologue.size());

            // return Bowser monologue
            display.println(bowserMonologue.get(selection).printDetails());
        }

        for(Behaviour Behaviour : behaviours.values()) {
            Action action = Behaviour.getAction(this, map);
            if (action != null)
                return action;
        }

        return new DoNothingAction();
    }

    @Override
    public ActionList allowableActions(Actor otherActor, String direction, GameMap map) {
        //new action list that contains actions that the other actor can utilise
        ActionList actions = new ActionList();
        // adds behaviours that this actor can use
        this.behaviours.put(1, new AttackBehaviour(otherActor));
        this.behaviours.put(5, new FollowBehaviour(otherActor));
        // gives attack action to other actors that only are hostile to this actor
        if (otherActor.hasCapability(Status.HOSTILE_TO_ENEMY)) {
            actions.add(new AttackAction(this, direction));
        }

        return actions;
    }

    @Override
    protected IntrinsicWeapon getIntrinsicWeapon() {
        return new IntrinsicWeapon(80,"punches");
    }

    /**
     * The complete monologue of Bowser
     * @return The monologue
     */
    public List<Monologue> generateMonologue(){
        sentences.add(new Monologue("Bowser: \"What was that sound? Oh, just a fire.\""));
        sentences.add(new Monologue("Bowser: \"Princess Peach! You are formally invited... to the creation of my new kingdom!\""));
        sentences.add(new Monologue("Bowser: \"Never gonna let you down!\""));
        sentences.add(new Monologue("Bowser: \"Wrrrrrrrrrrrrrrrryyyyyyyyyyyyyy!!!!\""));
        return sentences;
    }
}
