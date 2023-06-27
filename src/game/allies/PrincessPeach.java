package game.allies;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.GameMap;
import game.Status;
import game.Utils;
import game.actions.UnlockAction;
import game.behaviours.Monologue;

import java.util.ArrayList;
import java.util.List;

public class PrincessPeach extends Ally {
    private static final String NAME = "Princess Peach";
    private static final char DISPLAY_CHAR = 'P';
    private static final int HIT_POINTS = 9999;
    private int turnCounter = 0;
    /**
     * Constructor.
     */
    public PrincessPeach() {
        super(NAME, DISPLAY_CHAR, HIT_POINTS);
    }

    @Override
    public ActionList allowableActions(Actor otherActor, String direction, GameMap map) {
        //new action list that contains actions that the other actor can utilise
        ActionList actions = new ActionList();
        if (otherActor.hasCapability(Status.UNLOCK)){
            actions.add(new UnlockAction());;
        }
        return actions;
    }

    @Override
    public Action playTurn(ActionList actions, Action lastAction, GameMap map, Display display) {
        turnCounter++;

        if (turnCounter % 2 == 0) {

            List<Monologue> princessPeachMonologue = this.generateMonologue();

            // utils random number
            int selection = Utils.actorMonologueRandomize(princessPeachMonologue.size());

            // return Bowser monologue
            display.println(princessPeachMonologue.get(selection).printDetails());
        }

        return super.playTurn(actions, lastAction, map, display);
    }

    /**
     * the complete monologue of Princess Peach
     * @return monologue
     */

    public List<Monologue> generateMonologue(){
        sentences.add(new Monologue("Princess Peach: \"Dear Mario, I'll be waiting for you...\""));
        sentences.add(new Monologue("Princess Peach: \"Never gonna give you up!\""));
        sentences.add(new Monologue("Princess Peach: \"Release me, or I will kick you!\""));
        return sentences;
    }
}
