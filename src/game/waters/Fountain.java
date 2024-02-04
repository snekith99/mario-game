package game.waters;

import java.util.Stack;

import engine.actors.Actor;
import engine.positions.Ground;

abstract public class Fountain extends Ground {

    Stack<Water> fountains = new Stack<Water>();

    int counter = 0;

    /**
     * Constructor.
     *
     * @param displayChar character to display for this type of terrain
     */
    public Fountain(char displayChar) {
        super(displayChar);
    }

    public abstract String waterEffect(Effect actor);

    public abstract int getTurn();

    public abstract Stack<Water> getFountainWaters();
}
