package game.trading;

public class CoinManager {
    /**
     * Starting balance of wallet
     */
    private int sum = 1000;
    /**
     * instance of CoinManager
     */
    private static CoinManager instance;

    /**
     * Method to add currency to wallet
     * @param amount amount to be added to wallet
     */
    public void addAmount(int amount){
        this.sum += amount;
    }

    /**
     * Method to remove currency from wallet
     * @param amount amount to be removed from wallet
     */
    public void removeAmount(int amount){
        this.sum -= amount;
    }

    /**
     * Getter for wallet value
     * @return wallet value
     */
    public int wallet() {
        return this.sum;
    }

    /**
     * static method getInstance() to retrieve instance of CoinManager
     * @return instance of CoinManager
     */
    public static CoinManager getInstance() {
        if (instance == null) {
            instance = new CoinManager();
        }
        return instance;
    }
}



