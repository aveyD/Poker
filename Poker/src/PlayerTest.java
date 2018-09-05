import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;

public class PlayerTest {

	@Test
	public void compareToHighCardTest() {
		// High card Tie
		PokerHand player1 = createPokerHand(Card.TWO_HEART, Card.THREE_HEART, Card.FOUR_HEART, Card.FIVE_HEART,
				Card.ACE_SPADE);
		PokerHand player2 = createPokerHand(Card.TWO_SPADE, Card.THREE_SPADE, Card.FOUR_SPADE, Card.FIVE_SPADE,
				Card.ACE_HEART);
		assertEquals(0, PokerService.compareTo(player1, player2));

		// High card David Win
		player1 = createPokerHand(Card.KING_HEART, Card.THREE_HEART, Card.FOUR_HEART, Card.FIVE_HEART, Card.ACE_SPADE);
		player2 = createPokerHand(Card.TWO_SPADE, Card.THREE_SPADE, Card.FOUR_SPADE, Card.FIVE_SPADE, Card.ACE_HEART);
		assertEquals(1, PokerService.compareTo(player1, player2));

		// High card David Win
		player1 = createPokerHand(Card.KING_HEART, Card.THREE_HEART, Card.FOUR_HEART, Card.FIVE_HEART, Card.ACE_SPADE);
		player2 = createPokerHand(Card.SIX_SPADE, Card.THREE_SPADE, Card.FOUR_SPADE, Card.FIVE_SPADE, Card.KING_HEART);
		assertEquals(1, PokerService.compareTo(player1, player2));

		// High card Shelly Win
		player1 = createPokerHand(Card.TWO_HEART, Card.THREE_HEART, Card.FOUR_HEART, Card.FIVE_HEART, Card.ACE_SPADE);
		player2 = createPokerHand(Card.KING_SPADE, Card.THREE_SPADE, Card.FOUR_SPADE, Card.FIVE_SPADE, Card.ACE_HEART);
		assertEquals(-1, PokerService.compareTo(player1, player2));
	}

	@Test
	public void compareToStraightTest() {
		// Straight Tie
		PokerHand player1 = createPokerHand(Card.TWO_HEART, Card.THREE_HEART, Card.FOUR_HEART, Card.FIVE_HEART,
				Card.SIX_SPADE);
		PokerHand player2 = createPokerHand(Card.TWO_SPADE, Card.THREE_SPADE, Card.FOUR_SPADE, Card.FIVE_SPADE,
				Card.SIX_HEART);
		assertEquals(0, PokerService.compareTo(player1, player2));

		// Straight David Win
		player1 = createPokerHand(Card.SIX_HEART, Card.THREE_HEART, Card.FOUR_HEART, Card.FIVE_HEART, Card.SEVEN_SPADE);
		player2 = createPokerHand(Card.TWO_SPADE, Card.THREE_SPADE, Card.FOUR_SPADE, Card.FIVE_SPADE, Card.SIX_HEART);
		assertEquals(1, PokerService.compareTo(player1, player2));

		// Straight Shelly Win
		player1 = createPokerHand(Card.TWO_HEART, Card.THREE_HEART, Card.FOUR_HEART, Card.FIVE_HEART, Card.SIX_SPADE);
		player2 = createPokerHand(Card.KING_SPADE, Card.TEN_SPADE, Card.JACK_SPADE, Card.QUEEN_SPADE, Card.ACE_HEART);
		assertEquals(-1, PokerService.compareTo(player1, player2));
	}

	@Test
	public void compareToFlushTest() {
		// Flush Tie
		PokerHand player1 = createPokerHand(Card.TWO_HEART, Card.THREE_HEART, Card.FOUR_HEART, Card.FIVE_HEART,
				Card.SEVEN_HEART);
		PokerHand player2 = createPokerHand(Card.TWO_SPADE, Card.THREE_SPADE, Card.FOUR_SPADE, Card.FIVE_SPADE,
				Card.SEVEN_SPADE);
		assertEquals(0, PokerService.compareTo(player1, player2));

		// Flush David Win
		player1 = createPokerHand(Card.KING_HEART, Card.THREE_HEART, Card.FOUR_HEART, Card.FIVE_HEART,
				Card.SEVEN_HEART);
		player2 = createPokerHand(Card.TWO_SPADE, Card.THREE_SPADE, Card.FOUR_SPADE, Card.FIVE_SPADE, Card.SEVEN_SPADE);
		assertEquals(1, PokerService.compareTo(player1, player2));

		// Flush Shelly Win
		player1 = createPokerHand(Card.TWO_HEART, Card.THREE_HEART, Card.FOUR_HEART, Card.FIVE_HEART, Card.SEVEN_HEART);
		player2 = createPokerHand(Card.FIVE_SPADE, Card.TEN_SPADE, Card.JACK_SPADE, Card.QUEEN_SPADE, Card.ACE_SPADE);
		assertEquals(-1, PokerService.compareTo(player1, player2));
	}

	@Test
	public void compareToStraightFlushTest() {
		// Straight Flush Tie
		PokerHand player1 = createPokerHand(Card.TWO_HEART, Card.THREE_HEART, Card.FOUR_HEART, Card.FIVE_HEART,
				Card.SIX_HEART);
		PokerHand player2 = createPokerHand(Card.TWO_SPADE, Card.THREE_SPADE, Card.FOUR_SPADE, Card.FIVE_SPADE,
				Card.SIX_SPADE);
		assertEquals(0, PokerService.compareTo(player1, player2));

		// Straight Flush David Win
		player1 = createPokerHand(Card.SIX_HEART, Card.THREE_HEART, Card.FOUR_HEART, Card.FIVE_HEART, Card.SEVEN_HEART);
		player2 = createPokerHand(Card.TWO_SPADE, Card.THREE_SPADE, Card.FOUR_SPADE, Card.FIVE_SPADE, Card.SIX_SPADE);
		assertEquals(1, PokerService.compareTo(player1, player2));

		// Straight Flush Shelly Win
		player1 = createPokerHand(Card.TWO_HEART, Card.THREE_HEART, Card.FOUR_HEART, Card.FIVE_HEART, Card.SIX_HEART);
		player2 = createPokerHand(Card.KING_SPADE, Card.TEN_SPADE, Card.JACK_SPADE, Card.QUEEN_SPADE, Card.ACE_SPADE);
		assertEquals(-1, PokerService.compareTo(player1, player2));
	}

	@Test
	public void compareToPairOnePairTest() {
		// One Pair Tie
		PokerHand player1 = createPokerHand(Card.TWO_HEART, Card.TWO_CLUB, Card.SEVEN_SPADE, Card.FIVE_HEART,
				Card.SIX_HEART);
		PokerHand player2 = createPokerHand(Card.TWO_SPADE, Card.TWO_DIAMOND, Card.SEVEN_HEART, Card.FIVE_SPADE,
				Card.SIX_SPADE);
		assertEquals(0, PokerService.compareToPair(player1, player2));

		// One Pair David Win
		player1 = createPokerHand(Card.TWO_HEART, Card.TWO_CLUB, Card.KING_SPADE, Card.FIVE_HEART, Card.SIX_HEART);
		player2 = createPokerHand(Card.TWO_SPADE, Card.TWO_DIAMOND, Card.SEVEN_HEART, Card.FIVE_SPADE, Card.SIX_SPADE);
		assertEquals(1, PokerService.compareToPair(player1, player2));

		// One Pair Shelly Win
		player1 = createPokerHand(Card.TWO_HEART, Card.TWO_CLUB, Card.SEVEN_SPADE, Card.FIVE_HEART, Card.SIX_HEART);
		player2 = createPokerHand(Card.TWO_SPADE, Card.TWO_DIAMOND, Card.ACE_HEART, Card.FIVE_SPADE, Card.SIX_SPADE);
		assertEquals(-1, PokerService.compareToPair(player1, player2));
	}

	@Test
	public void compareToPairTwoPairTest() {
		// Two Pair Tie
		PokerHand player1 = createPokerHand(Card.TWO_HEART, Card.TWO_CLUB, Card.SEVEN_SPADE, Card.SEVEN_HEART,
				Card.SIX_HEART);
		PokerHand player2 = createPokerHand(Card.TWO_SPADE, Card.TWO_DIAMOND, Card.SEVEN_CLUB, Card.SEVEN_DIAMOND,
				Card.SIX_SPADE);
		assertEquals(0, PokerService.compareToPair(player1, player2));

		// Two Pair David Win
		player1 = createPokerHand(Card.TWO_HEART, Card.TWO_CLUB, Card.SEVEN_SPADE, Card.SEVEN_HEART, Card.EIGHT_HEART);
		player2 = createPokerHand(Card.TWO_SPADE, Card.TWO_DIAMOND, Card.SEVEN_CLUB, Card.SEVEN_DIAMOND,
				Card.SIX_SPADE);
		assertEquals(1, PokerService.compareToPair(player1, player2));

		// Two Pair Shelly Win
		player1 = createPokerHand(Card.TWO_HEART, Card.TWO_CLUB, Card.SEVEN_SPADE, Card.SEVEN_HEART, Card.EIGHT_HEART);
		player2 = createPokerHand(Card.TWO_SPADE, Card.TWO_DIAMOND, Card.SEVEN_CLUB, Card.SEVEN_DIAMOND,
				Card.KING_SPADE);
		assertEquals(-1, PokerService.compareToPair(player1, player2));
	}

	@Test
	public void compareToPairThreeOfAKindTest() {
		// There cannot be a tie in Three of a Kind

		// Three of a Kind David Win
		PokerHand player1 = createPokerHand(Card.TWO_HEART, Card.SEVEN_CLUB, Card.SEVEN_SPADE, Card.SEVEN_HEART,
				Card.EIGHT_HEART);
		PokerHand player2 = createPokerHand(Card.TWO_SPADE, Card.SIX_DIAMOND, Card.SIX_CLUB, Card.SEVEN_DIAMOND,
				Card.SIX_SPADE);
		assertEquals(1, PokerService.compareToPair(player1, player2));

		// Three of a Kind Shelly Win
		player1 = createPokerHand(Card.TWO_HEART, Card.TWO_CLUB, Card.TWO_SPADE, Card.SEVEN_HEART, Card.EIGHT_HEART);
		player2 = createPokerHand(Card.TWO_SPADE, Card.KING_DIAMOND, Card.KING_CLUB, Card.SEVEN_DIAMOND,
				Card.KING_SPADE);
		assertEquals(-1, PokerService.compareToPair(player1, player2));
	}

	@Test
	public void compareToPairFullHouseTest() {
		// There cannot be a tie in Full House

		// Full House David Win
		PokerHand player1 = createPokerHand(Card.TWO_HEART, Card.SEVEN_CLUB, Card.SEVEN_SPADE, Card.SEVEN_HEART,
				Card.TWO_HEART);
		PokerHand player2 = createPokerHand(Card.EIGHT_SPADE, Card.SIX_DIAMOND, Card.SIX_CLUB, Card.EIGHT_DIAMOND,
				Card.SIX_SPADE);
		assertEquals(1, PokerService.compareToPair(player1, player2));

		// Full House Shelly Win
		player1 = createPokerHand(Card.TWO_HEART, Card.TWO_CLUB, Card.TWO_SPADE, Card.SEVEN_HEART, Card.SEVEN_SPADE);
		player2 = createPokerHand(Card.QUEEN_HEART, Card.KING_DIAMOND, Card.KING_CLUB, Card.QUEEN_DIAMOND,
				Card.KING_SPADE);
		assertEquals(-1, PokerService.compareToPair(player1, player2));
	}

	@Test
	public void compareToPairFourOfAKindTest() {
		// There cannot be a tie in Four of a Kind

		// Four of a Kind David Win
		PokerHand player1 = createPokerHand(Card.TWO_HEART, Card.SEVEN_CLUB, Card.SEVEN_SPADE, Card.SEVEN_HEART,
				Card.SEVEN_DIAMOND);
		PokerHand player2 = createPokerHand(Card.TWO_SPADE, Card.SIX_DIAMOND, Card.SIX_CLUB, Card.SIX_HEART,
				Card.SIX_SPADE);
		assertEquals(1, PokerService.compareToPair(player1, player2));

		// Four of a Kind Shelly Win
		player1 = createPokerHand(Card.TWO_HEART, Card.TWO_CLUB, Card.TWO_SPADE, Card.TWO_DIAMOND, Card.EIGHT_HEART);
		player2 = createPokerHand(Card.TWO_SPADE, Card.KING_DIAMOND, Card.KING_CLUB, Card.KING_HEART, Card.KING_SPADE);
		assertEquals(-1, PokerService.compareToPair(player1, player2));
	}

	@Test
	public void complexListSortFourOfAKindTest() {
		// sort 4 of a kind
		List<Card> cards = Arrays.asList(Card.SEVEN_HEART, Card.TWO_CLUB, Card.TWO_DIAMOND, Card.TWO_HEART,
				Card.TWO_SPADE);
		List<Card> sortedCards = PokerService.complexListSort(cards);

		assertEquals(Card.TWO_CLUB, sortedCards.get(0));
		assertEquals(Card.TWO_DIAMOND, sortedCards.get(1));
		assertEquals(Card.TWO_HEART, sortedCards.get(2));
		assertEquals(Card.TWO_SPADE, sortedCards.get(3));
		assertEquals(Card.SEVEN_HEART, sortedCards.get(4));
	}

	@Test
	public void complexListSortThreeOfAKindTest() {
		// sort 3 of a kind
		List<Card> cards = Arrays.asList(Card.SEVEN_HEART, Card.TWO_CLUB, Card.TWO_DIAMOND, Card.EIGHT_HEART,
				Card.TWO_SPADE);
		List<Card> sortedCards = PokerService.complexListSort(cards);

		assertEquals(Card.TWO_CLUB, sortedCards.get(0));
		assertEquals(Card.TWO_DIAMOND, sortedCards.get(1));
		assertEquals(Card.TWO_SPADE, sortedCards.get(2));
		assertEquals(Card.EIGHT_HEART, sortedCards.get(3));
		assertEquals(Card.SEVEN_HEART, sortedCards.get(4));
	}

	@Test
	public void complexListSortTwoPairTest() {
		// sort 2 pair
		List<Card> cards = Arrays.asList(Card.SEVEN_HEART, Card.TWO_CLUB, Card.SEVEN_DIAMOND, Card.EIGHT_HEART,
				Card.TWO_SPADE);
		List<Card> sortedCards = PokerService.complexListSort(cards);

		assertEquals(Card.SEVEN_HEART, sortedCards.get(0));
		assertEquals(Card.SEVEN_DIAMOND, sortedCards.get(1));
		assertEquals(Card.TWO_CLUB, sortedCards.get(2));
		assertEquals(Card.TWO_SPADE, sortedCards.get(3));
		assertEquals(Card.EIGHT_HEART, sortedCards.get(4));
	}

	@Test
	public void complexListSortFullHouseTest() {
		// sort full house
		List<Card> cards = Arrays.asList(Card.SEVEN_HEART, Card.TWO_CLUB, Card.SEVEN_DIAMOND, Card.TWO_HEART,
				Card.TWO_SPADE);
		List<Card> sortedCards = PokerService.complexListSort(cards);

		assertEquals(Card.TWO_CLUB, sortedCards.get(0));
		assertEquals(Card.TWO_HEART, sortedCards.get(1));
		assertEquals(Card.TWO_SPADE, sortedCards.get(2));
		assertEquals(Card.SEVEN_HEART, sortedCards.get(3));
		assertEquals(Card.SEVEN_DIAMOND, sortedCards.get(4));
	}

	private PokerHand createPokerHand(Card... cards) {
		PokerHand pokerHand = new PokerHand(Arrays.asList(cards));
		return pokerHand;
	}
}
