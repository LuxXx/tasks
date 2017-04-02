public class Player {
	
	
	/** the rating a player has, the higher the better */
	private int elo = STARTING_ELO;
	
	/** new players start with this elo rating */
	private static final int STARTING_ELO = 1200;
	
	/**
	 * new players may start with a boost this applies to players under the age of 18
	 * or players who played less than 30 games.
	 * this does not apply if their rating is above 2300
	 */
	private boolean boost;
	
	/** the default constructor */
	public Player() {}
	
	/**
	 * creates a new Player with a specific elo
	 * @param elo the elo this player should have
	 */
	public Player(int elo) {
		this.elo = elo;
	}

	/**
	 * creates a new Player with a specific elo and this player might have a boost
	 * @param elo
	 * @param boost
	 * @see #boost
	 */
	public Player(int elo, boolean boost) {
		this.elo = elo;
		this.boost = boost;
	}
	
	/**
	 * creates a new Player with the default elo and might have a boost
	 * @param boost
	 * @see #boost
	 */
	public Player(boolean boost) {
		this.boost = boost;
	}
	
	/**
	 * adjusts the elo if this Player wins vs. Player p
	 * @param p the opponent
	 */
	public void wonVersus(Player p) {
		elo = (int) (elo + getKFactor() * (1 - getWinEstimation(p)));
	}
	
	/**
	 * adjusts the elo if this Player loses to Player p
	 * @param p the opponent
	 */
	public void lostVersus(Player p) {
		elo = (int) (elo + getKFactor() * (0 - getWinEstimation(p)));
	}
	
	/**
	 * adjusts the elo if this Player plays a draw vs. Player p
	 * @param p the opponent
	 */
	public void drawVersus(Player p) {
		elo = (int) (elo + getKFactor() * (0.5 - getWinEstimation(p)));
	}
	
	/**
	 * This is a mathematically estimation with what estimated probability Player this wins versus Player p.
	 * @param p the player this player wants to fight
	 * @return the probability with this Player wins versus Player p
	 */
	public double getWinEstimation(Player p) {
		return 1 / (1 + (Math.pow(10, (p.getElo() - getElo()) / 400))); 
	}
	
	/**
	 * Returns the current elo of this player
	 * @return elo of this player
	 */
	public int getElo() {
		return elo;
	}
	
	/**
	 * The k-factor determines how fast you can increase your elo
	 * @return 10
	 * @see #boost
	 */
	private int getKFactor() {
		if (boost && elo < 2300) return 40;
		return (elo < 2400 ? 20 : 10);
	}
}
