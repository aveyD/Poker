import java.util.ArrayList;
import java.util.List;

public class Player {
	private String name;
	private List<Card> cards;
	private List<Card> flopTurnRiver;
	private List<PokerHand> allPossibleHoldemHands;
	private PokerHandRank pokerHandRank;
	private Rank highPair;
	private boolean winner = false;

	public Player() {
	}

	public Player(int playerNum) {
		this.setName("Player " + playerNum);
		this.setCards(new ArrayList<Card>());
		this.setFlopTurnRiver(new ArrayList<Card>());
		this.setAllPossibleHoldemHands(new ArrayList<PokerHand>());
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Card> getCards() {
		return cards;
	}

	public void setCards(List<Card> cards) {
		this.cards = cards;
	}

	public PokerHandRank getPokerHandRank() {
		return pokerHandRank;
	}

	public void setPokerHandRank(PokerHandRank pokerHandRank) {
		this.pokerHandRank = pokerHandRank;
	}

	public Rank getHighPair() {
		return highPair;
	}

	public void setHighPair(Rank highPair) {
		this.highPair = highPair;
	}

	public boolean isWinner() {
		return winner;
	}

	public void setWinner(boolean winner) {
		this.winner = winner;
	}

	public String printPlayerHand() {
		return getName() + ": " + getCards();
	}

	public List<PokerHand> getAllPossibleHoldemHands() {
		return allPossibleHoldemHands;
	}

	public void setAllPossibleHoldemHands(List<PokerHand> arrayList) {
		this.allPossibleHoldemHands = arrayList;
	}

	public List<Card> getFlopTurnRiver() {
		return flopTurnRiver;
	}

	public void setFlopTurnRiver(List<Card> flopTurnRiver) {
		this.flopTurnRiver = flopTurnRiver;
	}

	@Override
	public String toString() {
		return getName();
	}
}
