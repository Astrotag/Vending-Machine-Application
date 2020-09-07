package vendingmachine.manager;

import java.io.Serializable;

public class Coin implements Serializable
{

	private static final long serialVersionUID = 1L;
	private double coinValue;

	/**
	 * Create a coin with a given coinValue
	 * 
	 * @param coinValue - value of the coin
	 */
    public Coin(double coinValue)
    {
        this.coinValue = coinValue;
    }

    /**
     * @return the value of the coin
     */
    public double getCoinValue()
    {
        return coinValue;
    }
}
