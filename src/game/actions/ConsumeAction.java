package game.actions;

import engine.actions.Action;
import engine.actors.Actor;
import engine.positions.GameMap;
import game.Status;
import game.consumables.ConsumableItem;
import game.waters.BottleManager;

public class ConsumeAction extends Action {
    private final ConsumableItem consumableItem;

    public ConsumeAction(ConsumableItem consumableItem) {
        this.consumableItem = consumableItem;
    }

    /**
     * Once consume action is executed, remove item from ground or inventory and add
     * consumable features to actor
     * 
     * @param actor The actor performing the action.
     * @param map   The map the actor is on.
     * @return actor consumed the consumableItem
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        if (consumableItem.hasCapability(Status.DRINK)) {
            return consumableItem.consumableFeatures(actor);
        }
        map.locationOf(actor).removeItem(consumableItem);
        actor.removeItemFromInventory(consumableItem);
        return consumableItem.consumableFeatures(actor);
    }

    @Override
    public String menuDescription(Actor actor) {
        if (consumableItem.hasCapability(Status.DRINK)) {
            return actor + " consumes " + consumableItem + BottleManager.getInstance().getBottle();
        }
        if (consumableItem.getFade() == 0) {
            return actor + " consumes " + consumableItem;
        }
        return actor + " consumes " + consumableItem + " (" + consumableItem.getFade() + " turns left)";
    }
}
