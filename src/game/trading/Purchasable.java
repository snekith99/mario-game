package game.trading;

import edu.monash.fit2099.engine.actors.Actor;

public interface Purchasable {
    /**
     * default getPrice method to receive the price of the Purchasable item
     * @return price of Purchasable item
     */
    int getPrice();

    void purchasedBy(Actor actor);
}