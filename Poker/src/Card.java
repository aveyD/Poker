
public enum Card
{
	TWO_HEART(Rank.TWO, Suit.HEART, "2♥"),
	TWO_CLUB(Rank.TWO, Suit.CLUB, "2♣"),
	TWO_SPADE(Rank.TWO, Suit.SPADE, "2♠"),
	TWO_DIAMOND(Rank.TWO, Suit.DIAMOND, "2♦"),
	
	THREE_HEART(Rank.THREE, Suit.HEART, "3♥"),
	THREE_CLUB(Rank.THREE, Suit.CLUB, "3♣"),
	THREE_SPADE(Rank.THREE, Suit.SPADE, "3♠"),
	THREE_DIAMOND(Rank.THREE, Suit.DIAMOND, "3♦"),
	
	FOUR_HEART(Rank.FOUR, Suit.HEART, "4♥"),
	FOUR_CLUB(Rank.FOUR, Suit.CLUB, "4♣"),
	FOUR_SPADE(Rank.FOUR, Suit.SPADE, "4♠"),
	FOUR_DIAMOND(Rank.FOUR, Suit.DIAMOND, "4♦"),
	
	FIVE_HEART(Rank.FIVE, Suit.HEART, "5♥"),
	FIVE_CLUB(Rank.FIVE, Suit.CLUB, "5♣"),
	FIVE_SPADE(Rank.FIVE, Suit.SPADE, "5♠"),
	FIVE_DIAMOND(Rank.FIVE, Suit.DIAMOND, "5♦"),
	
	SIX_HEART(Rank.SIX, Suit.HEART, "6♥"),
	SIX_CLUB(Rank.SIX, Suit.CLUB, "6♣"),
	SIX_SPADE(Rank.SIX, Suit.SPADE, "6♠"),
	SIX_DIAMOND(Rank.SIX, Suit.DIAMOND, "6♦"),
	
	SEVEN_HEART(Rank.SEVEN, Suit.HEART, "7♥"),
	SEVEN_CLUB(Rank.SEVEN, Suit.CLUB, "7♣"),
	SEVEN_SPADE(Rank.SEVEN, Suit.SPADE, "7♠"),
	SEVEN_DIAMOND(Rank.SEVEN, Suit.DIAMOND, "7♦"),
	
	EIGHT_HEART(Rank.EIGHT, Suit.HEART, "8♥"),
	EIGHT_CLUB(Rank.EIGHT, Suit.CLUB, "8♣"),
	EIGHT_SPADE(Rank.EIGHT, Suit.SPADE, "8♠"),
	EIGHT_DIAMOND(Rank.EIGHT, Suit.DIAMOND, "8♦"),
	
	NINE_HEART(Rank.NINE, Suit.HEART, "9♥"),
	NINE_CLUB(Rank.NINE, Suit.CLUB, "9♣"),
	NINE_SPADE(Rank.NINE, Suit.SPADE, "9♠"),
	NINE_DIAMOND(Rank.NINE, Suit.DIAMOND, "9♦"),
	
	TEN_HEART(Rank.TEN, Suit.HEART, "10♥"),
	TEN_CLUB(Rank.TEN, Suit.CLUB, "10♣"),
	TEN_SPADE(Rank.TEN, Suit.SPADE, "10♠"),
	TEN_DIAMOND(Rank.TEN, Suit.DIAMOND, "10♦"),
	
	JACK_HEART(Rank.JACK, Suit.HEART, "J♥"),
	JACK_CLUB(Rank.JACK, Suit.CLUB, "J♣"),
	JACK_SPADE(Rank.JACK, Suit.SPADE, "J♠"),
	JACK_DIAMOND(Rank.JACK, Suit.DIAMOND, "J♦"),
	
	QUEEN_HEART(Rank.QUEEN, Suit.HEART, "Q♥"),
	QUEEN_CLUB(Rank.QUEEN, Suit.CLUB, "Q♣"),
	QUEEN_SPADE(Rank.QUEEN, Suit.SPADE, "Q♠"),
	QUEEN_DIAMOND(Rank.QUEEN, Suit.DIAMOND, "Q♦"),
	
	KING_HEART(Rank.KING, Suit.HEART, "K♥"),
	KING_CLUB(Rank.KING, Suit.CLUB, "K♣"),
	KING_SPADE(Rank.KING, Suit.SPADE, "K♠"),
	KING_DIAMOND(Rank.KING, Suit.DIAMOND, "K♦"),
	
	ACE_HEART(Rank.ACE, Suit.HEART, "A♥"),
	ACE_CLUB(Rank.ACE, Suit.CLUB, "A♣"),
	ACE_SPADE(Rank.ACE, Suit.SPADE, "A♠"),
	ACE_DIAMOND(Rank.ACE, Suit.DIAMOND, "A♦");

	private Rank rank;
	private Suit suit;
	private String str;

	Card(Rank rank, Suit suit) {
		this.rank = rank;
		this.suit = suit;
	}

	Card(Rank rank, Suit suit, String str) {
		this.rank = rank;
		this.suit = suit;
		this.setStr(str);
	}

	public Rank getRank() {
		return rank;
	}

	public void setRank(Rank rank) {
		this.rank = rank;
	}

	public Suit getSuit() {
		return suit;
	}

	public void setSuit(Suit suit) {
		this.suit = suit;
	}

	public String getStr() {
		return str;
	}

	public void setStr(String str) {
		this.str = str;
	}

	public static Card getCardFromRankAndSuit(Rank rank, Suit suit) {
		Card card = null;
		for (Card e : Card.values()) {
			if (e.rank == rank && e.suit == suit) {
				card = e;
				break;
			}
		}
		return card;
	}
	
	@Override
	public String toString() {
		return str;
	}
}
