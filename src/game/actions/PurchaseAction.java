package game.actions;

import engine.actions.Action;
import engine.actors.Actor;
import engine.items.Item;
import engine.positions.GameMap;
import game.trading.CoinManager;
import game.trading.Purchasable;

public class PurchaseAction extends Action {
    /**
     * Purchasable interface
     */
    private final Purchasable purchasable;

    /**
     * Constructor
     * 
     * @param purchasable purchasable interface
     */
    public PurchaseAction(Purchasable purchasable) {
        this.purchasable = purchasable;
    }

    @Override
    public String execute(Actor actor, GameMap map) {

        // if wallet less than price, print error message
        if (CoinManager.getInstance().wallet() < purchasable.getPrice()) {
            return "Insufficient Balance!";
        }

        // deduct amount and add to inventory
        CoinManager.getInstance().removeAmount(purchasable.getPrice());
        purchasable.purchasedBy(actor);
        return actor + " obtained " + purchasable;
    }

    @Override
    public String menuDescription(Actor actor) {
        return actor + " buys " + purchasable + " ($" + purchasable.getPrice() + ")";
    }
}
