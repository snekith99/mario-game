package game.enemies;

import game.Status;
import game.behaviours.Behaviour;
import game.behaviours.Monologue;
import game.resets.Resettable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import engine.actors.Actor;

abstract public class Enemy extends Actor implements Resettable {
    Map<Integer, Behaviour> behaviours = new HashMap<>(); // priority, behaviour

    List<Monologue> sentences = new ArrayList<>();

    int turnCounter = 0;

    /**
     * Constructor.
     *
     * @param name        the name of the Actor
     * @param displayChar the character that will represent the Actor in the display
     * @param hitPoints   the Actor's starting hit points
     */
    public Enemy(String name, char displayChar, int hitPoints) {
        super(name, displayChar, hitPoints);
        registerInstance();
    }

    public void resetInstance() {
        this.addCapability(Status.RESETTABLE);
    }

    public abstract List<Monologue> generateMonologue();
}
