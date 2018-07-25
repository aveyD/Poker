
public enum Card
{
	TWO_HEART(Rank.TWO, Suit.HEART),
	TWO_CLUB(Rank.TWO, Suit.CLUB),
	TWO_SPADE(Rank.TWO, Suit.SPADE),
	TWO_DIAMOND(Rank.TWO, Suit.DIAMOND),
	
	THREE_HEART(Rank.THREE, Suit.HEART),
	THREE_CLUB(Rank.THREE, Suit.CLUB),
	THREE_SPADE(Rank.THREE, Suit.SPADE),
	THREE_DIAMOND(Rank.THREE, Suit.DIAMOND),
	
	FOUR_HEART(Rank.FOUR, Suit.HEART),
	FOUR_CLUB(Rank.FOUR, Suit.CLUB),
	FOUR_SPADE(Rank.FOUR, Suit.SPADE),
	FOUR_DIAMOND(Rank.FOUR, Suit.DIAMOND),
	
	FIVE_HEART(Rank.FIVE, Suit.HEART),
	FIVE_CLUB(Rank.FIVE, Suit.CLUB),
	FIVE_SPADE(Rank.FIVE, Suit.SPADE),
	FIVE_DIAMOND(Rank.FIVE, Suit.DIAMOND),
	
	SIX_HEART(Rank.SIX, Suit.HEART),
	SIX_CLUB(Rank.SIX, Suit.CLUB),
	SIX_SPADE(Rank.SIX, Suit.SPADE),
	SIX_DIAMOND(Rank.SIX, Suit.DIAMOND),
	
	SEVEN_HEART(Rank.SEVEN, Suit.HEART),
	SEVEN_CLUB(Rank.SEVEN, Suit.CLUB),
	SEVEN_SPADE(Rank.SEVEN, Suit.SPADE),
	SEVEN_DIAMOND(Rank.SEVEN, Suit.DIAMOND),
	
	EIGHT_HEART(Rank.EIGHT, Suit.HEART),
	EIGHT_CLUB(Rank.EIGHT, Suit.CLUB),
	EIGHT_SPADE(Rank.EIGHT, Suit.SPADE),
	EIGHT_DIAMOND(Rank.EIGHT, Suit.DIAMOND),
	
	NINE_HEART(Rank.NINE, Suit.HEART),
	NINE_CLUB(Rank.NINE, Suit.CLUB),
	NINE_SPADE(Rank.NINE, Suit.SPADE),
	NINE_DIAMOND(Rank.NINE, Suit.DIAMOND),
	
	TEN_HEART(Rank.TEN, Suit.HEART),
	TEN_CLUB(Rank.TEN, Suit.CLUB),
	TEN_SPADE(Rank.TEN, Suit.SPADE),
	TEN_DIAMOND(Rank.TEN, Suit.DIAMOND),
	
	JACK_HEART(Rank.JACK, Suit.HEART),
	JACK_CLUB(Rank.JACK, Suit.CLUB),
	JACK_SPADE(Rank.JACK, Suit.SPADE),
	JACK_DIAMOND(Rank.JACK, Suit.DIAMOND),
	
	QUEEN_HEART(Rank.QUEEN, Suit.HEART),
	QUEEN_CLUB(Rank.QUEEN, Suit.CLUB),
	QUEEN_SPADE(Rank.QUEEN, Suit.SPADE),
	QUEEN_DIAMOND(Rank.QUEEN, Suit.DIAMOND),
	
	KING_HEART(Rank.KING, Suit.HEART),
	KING_CLUB(Rank.KING, Suit.CLUB),
	KING_SPADE(Rank.KING, Suit.SPADE),
	KING_DIAMOND(Rank.KING, Suit.DIAMOND),
	
	ACE_HEART(Rank.ACE, Suit.HEART),
	ACE_CLUB(Rank.ACE, Suit.CLUB),
	ACE_SPADE(Rank.ACE, Suit.SPADE),
	ACE_DIAMOND(Rank.ACE, Suit.DIAMOND);
	
	private Rank rank;
	private Suit suit;
	
	Card(Rank rank, Suit suit)
	{
		this.rank = rank;
		this.suit = suit;
	}
	
	public Rank getRank()
	{
		return rank;
	}

	public void setRank(Rank rank)
	{
		this.rank = rank;
	}

	public Suit getSuit()
	{
		return suit;
	}

	public void setSuit(Suit suit)
	{
		this.suit = suit;
	}
	
	public static Card getCardFromRankAndSuit(Rank rank, Suit suit)
	{
		Card card = null;
		for (Card e : Card.values())
		{
			if (e.rank == rank && e.suit == suit)
			{
				card = e;
				break;
			}
		}
		return card;
	}
}
