package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import game.allies.Toad;
import game.behaviours.Monologue;
import game.Utils;
import java.util.*;


public class ToadSpeakAction extends Action {
    /**
     * toad object
     */
    private final Toad toad;

    /**
     * Constructor
     * @param toad object to print monologue
     */
    public ToadSpeakAction(Toad toad) {
        this.toad = toad;
    }

    @Override
    public String execute(Actor actor, GameMap map) {
        List<Monologue> toadMonologue = toad.generateMonologue(actor);

        // utils random number, Toad speaks one sentence at a time
        int selection = Utils.actorMonologueRandomize(toadMonologue.size());

        // check if actor has item with while loop, will keep running until it's not null
        while (toadMonologue.get(selection).printToadDetails() == null) {
            selection = Utils.actorMonologueRandomize(toadMonologue.size());
        }

        // return correct statement
        return toadMonologue.get(selection).printDetails();
    }

    @Override
    public String menuDescription(Actor actor) {
        return actor + " talks with " + toad;
    }
}

