package game.waters;

public class Water {

    private final Fountain fountainType;

    /**
     * Constructor for Water
     * @param fountain
     */
    public Water(Fountain fountain) {
        this.fountainType = fountain;
    }

    /**
     * Method for returning the actor drinking water from the fountain
     * @param actor
     * @return
     */
    public String drunkBy(Effect actor){
        return fountainType.waterEffect(actor);
    }

    /**
     * Method to return fountain type
     * @return fountainType
     */
    public Fountain getFountainType() {
        return fountainType;
    }

    @Override
    public String toString() {
        return fountainType.toString();
    }
}