package game.behaviours;

public class Monologue {
    /**
     * text to be printed
     */
    private final String text;
    /**
     * if player interacts with ally, checks if has item
     */
    private boolean hasItem = true;

    /**
     * Constructor
     * @param text text to be printed
     * @param hasItem if player has item, toad doesn't print the statement
     */
    public Monologue(String text, boolean hasItem) {
        this.text = text;
        this.hasItem = hasItem;
    }

    public Monologue(String text){
        this.text = text;
    }

    /**
     * Method to print the text
     * @return the text
     */
    public String printToadDetails(){
        if (hasItem){
            return text;
        }
        return null;
    }

    public String printDetails(){
        return text;
    }


}
