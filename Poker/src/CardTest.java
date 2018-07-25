import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class CardTest {
	
	@Test
	public void getCardFromRankAndSuitTest()
	{
		assertEquals(Card.FIVE_HEART, Card.getCardFromRankAndSuit(Rank.FIVE, Suit.HEART));
		assertEquals(Card.TWO_CLUB, Card.getCardFromRankAndSuit(Rank.TWO, Suit.CLUB));
		assertEquals(Card.JACK_DIAMOND, Card.getCardFromRankAndSuit(Rank.JACK, Suit.DIAMOND));
		assertEquals(Card.ACE_SPADE, Card.getCardFromRankAndSuit(Rank.ACE, Suit.SPADE));
	}
}
