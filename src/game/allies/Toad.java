package game.allies;

import game.actions.BottleAction;
import game.actions.PurchaseAction;
import game.behaviours.Monologue;
import game.Status;
import game.actions.ToadSpeakAction;
import game.consumables.PowerStar;
import game.consumables.SuperMushroom;
import game.items.Wrench;
import game.waters.BottleManager;

import java.util.*;

import engine.actions.ActionList;
import engine.actors.Actor;
import engine.positions.GameMap;

public class Toad extends Ally {
    private static final String NAME = "Toad";
    private static final char DISPLAY_CHAR = 'O';
    private static final int HIT_POINTS = 9999;

    /**
     * Constructor.
     */
    public Toad() {
        super(NAME, DISPLAY_CHAR, HIT_POINTS);
    }

    /**
     * The complete monologue of Toad which can be accessed if certain conditions of
     * player are met.
     *
     * @param actor the actor toad is talking to
     * @return The monologue
     */
    public List<Monologue> generateMonologue(Actor actor) {

        sentences.add(new Monologue("Toad: You might need a wrench to smash Koopa's hard shells",
                !actor.hasCapability(Status.DORMANTKILL)));
        sentences.add(new Monologue("Toad: You better get back to finding Power Stars.",
                !actor.hasCapability(Status.INVINCIBLE)));
        sentences.add(new Monologue("Toad: The Princess is depending on you! You are our only hope."));
        sentences.add(new Monologue("Toad: Being imprisoned in these walls can drive a fungus crazy :("));
        return sentences;
    }

    /**
     * Allows the player to interact with toad by receiving actions to buy from him
     *
     * @param otherActor the Actor that might be performing attack
     * @param direction  String representing the direction of the other Actor
     * @param map        current GameMap
     * @return the action list of toad
     */
    @Override
    public ActionList allowableActions(Actor otherActor, String direction, GameMap map) {
        ActionList toadActions = new ActionList();
        toadActions.add(new ToadSpeakAction(this));
        toadActions.add(new PurchaseAction(new PowerStar()));
        toadActions.add(new PurchaseAction(new SuperMushroom()));
        toadActions.add(new PurchaseAction(new Wrench()));
        if (!otherActor.hasCapability(Status.DRINK)) {
            toadActions.add(new BottleAction());
        }
        return toadActions;
    }
}
