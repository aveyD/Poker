import java.util.ArrayList;
import java.util.List;

public class PokerHand {
	private List<Card> pokerHand;
	private PokerHandRank handRank;
	private Rank highPair;

	PokerHand() {
		this.setPokerHand(new ArrayList<Card>());
	}

	PokerHand(List<Card> pokerHand) {
		this.setPokerHand(pokerHand);
	}

	public List<Card> getPokerHand() {
		return pokerHand;
	}

	public void setPokerHand(List<Card> pokerHand) {
		this.pokerHand = pokerHand;
	}

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
		return pokerHand + ", " + handRank + ":" + highPair;
	}
}
