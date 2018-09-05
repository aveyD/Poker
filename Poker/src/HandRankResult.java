
public class HandRankResult {

	private PokerHandRank handRank;
	private Rank highPair;

	public PokerHandRank getHandRank() {
		return handRank;
	}

	public void setHandRank(PokerHandRank handRank) {
		this.handRank = handRank;
	}

	public Rank getHighPair() {
		return highPair;
	}

	public void setHighPair(Rank highPair) {
		this.highPair = highPair;
	}

	public String toString() {
		return handRank.name() + ":" + highPair.name();
	}
}
