import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

public class PokerTest {
	
	@Before
	public void setup()
	{
		Poker.players = new ArrayList<Player>();
	}
	
	@Test
	public void parsePlayerHandTest()
	{
		assertTrue(Poker.parsePlayerHand("David, AS, KS, QS, JS, 10S"));
		assertTrue(Poker.parsePlayerHand("David, 2D, 3D, 4D, 5D, 6D"));
		assertTrue(Poker.parsePlayerHand("David, 7C, 8C, 9C, 10C, JC"));
		assertTrue(Poker.parsePlayerHand("David, 10h, jh, qh, kh, ah"));
		
		assertFalse(Poker.parsePlayerHand("David, AS, KS, QS, JS, 11S"));
		assertFalse(Poker.parsePlayerHand("David, 2D, 3D, 4D, 5D"));
		assertFalse(Poker.parsePlayerHand("David, 7C, 8C, 9C, 10C, JC, QC"));
		assertFalse(Poker.parsePlayerHand("David, 10h, jh, qh, kh, ab"));
	}
	
	@Test
	public void rankHandsTest()
	{
		Player player1 = createPlayer("David", Card.TWO_HEART, Card.THREE_CLUB, Card.FIVE_DIAMOND, Card.KING_CLUB, Card.ACE_SPADE);
		Player player2 = createPlayer("Shelly", Card.TWO_DIAMOND, Card.THREE_SPADE, Card.FIVE_HEART, Card.KING_DIAMOND, Card.KING_CLUB);
		Player player3 = createPlayer("Joe", Card.TWO_HEART, Card.TWO_CLUB, Card.FIVE_DIAMOND, Card.FIVE_CLUB, Card.ACE_SPADE);
		Player player4 = createPlayer("Jane", Card.SEVEN_DIAMOND, Card.SEVEN_SPADE, Card.SEVEN_HEART, Card.KING_DIAMOND, Card.QUEEN_CLUB);
		Player player5 = createPlayer("Bob", Card.TWO_HEART, Card.THREE_CLUB, Card.FIVE_DIAMOND, Card.FOUR_CLUB, Card.SIX_SPADE);
		Player player6 = createPlayer("Sue", Card.THREE_SPADE, Card.SEVEN_SPADE, Card.SIX_SPADE, Card.KING_SPADE, Card.QUEEN_SPADE);
		Player player7 = createPlayer("John", Card.SEVEN_DIAMOND, Card.SEVEN_SPADE, Card.SEVEN_HEART, Card.KING_DIAMOND, Card.KING_CLUB);
		Player player8 = createPlayer("Jacob", Card.EIGHT_HEART, Card.EIGHT_CLUB, Card.EIGHT_DIAMOND, Card.FOUR_HEART, Card.EIGHT_SPADE);
		Player player9 = createPlayer("Junior", Card.TEN_SPADE, Card.JACK_SPADE, Card.NINE_SPADE, Card.SEVEN_SPADE, Card.EIGHT_SPADE);
		
		Poker.players.add(player1);
		Poker.players.add(player2);
		Poker.players.add(player3);
		Poker.players.add(player4);
		Poker.players.add(player5);
		Poker.players.add(player6);
		Poker.players.add(player7);
		Poker.players.add(player8);
		Poker.players.add(player9);
		
		assertNull(Poker.players.get(0).getPokerHandRank());
		assertNull(Poker.players.get(1).getPokerHandRank());
		assertNull(Poker.players.get(2).getPokerHandRank());
		assertNull(Poker.players.get(3).getPokerHandRank());
		assertNull(Poker.players.get(4).getPokerHandRank());
		assertNull(Poker.players.get(5).getPokerHandRank());
		assertNull(Poker.players.get(6).getPokerHandRank());
		assertNull(Poker.players.get(7).getPokerHandRank());
		assertNull(Poker.players.get(8).getPokerHandRank());
		
		Poker.rankHands();
		
		assertEquals(PokerHandRank.HIGH_CARD, Poker.players.get(0).getPokerHandRank());
		assertEquals(PokerHandRank.ONE_PAIR, Poker.players.get(1).getPokerHandRank());
		assertEquals(PokerHandRank.TWO_PAIR, Poker.players.get(2).getPokerHandRank());
		assertEquals(PokerHandRank.THREE_OF_A_KIND, Poker.players.get(3).getPokerHandRank());
		assertEquals(PokerHandRank.STRAIGHT, Poker.players.get(4).getPokerHandRank());
		assertEquals(PokerHandRank.FLUSH, Poker.players.get(5).getPokerHandRank());
		assertEquals(PokerHandRank.FULL_HOUSE, Poker.players.get(6).getPokerHandRank());
		assertEquals(PokerHandRank.FOUR_OF_A_KIND, Poker.players.get(7).getPokerHandRank());
		assertEquals(PokerHandRank.STRAIGHT_FLUSH, Poker.players.get(8).getPokerHandRank());
	}
	
	@Test
	public void rankHighCardsHighCardTest()
	{
		Player player1 = createPlayer("David", Card.SEVEN_HEART, Card.ACE_CLUB, Card.FIVE_DIAMOND, Card.KING_CLUB, Card.THREE_CLUB);
		Player player2 = createPlayer("Shelly", Card.ACE_SPADE, Card.THREE_SPADE, Card.FIVE_CLUB, Card.KING_SPADE, Card.SEVEN_DIAMOND);
		Player player3 = createPlayer("Joe", Card.TWO_SPADE, Card.THREE_DIAMOND, Card.ACE_DIAMOND, Card.KING_DIAMOND, Card.FIVE_SPADE);
		
		List<Player> players = Arrays.asList(player1, player2, player3);
		List<Player> winners = Poker.rankHighCards(players);
		
		// David and Shelly have the same hand, but Joe has a lower kicker than the winners
		assertEquals(2, winners.size());
	}
	
	@Test
	public void rankHighCardsFlushTest()
	{
		Player player1 = createPlayer("David", Card.SEVEN_HEART, Card.ACE_HEART, Card.FIVE_HEART, Card.KING_HEART, Card.THREE_HEART);
		Player player2 = createPlayer("Shelly", Card.ACE_SPADE, Card.THREE_SPADE, Card.FIVE_SPADE, Card.KING_SPADE, Card.SEVEN_SPADE);
		Player player3 = createPlayer("Joe", Card.TWO_DIAMOND, Card.THREE_DIAMOND, Card.ACE_DIAMOND, Card.KING_DIAMOND, Card.FIVE_DIAMOND);
		
		List<Player> players = Arrays.asList(player1, player2, player3);
		List<Player> winners = Poker.rankHighCards(players);
		
		// David and Shelly have the same hand, but Joe has a lower kicker than the winners
		assertEquals(2, winners.size());
	}
	
	@Test
	public void rankHighCardsStraightTest()
	{
		Player player1 = createPlayer("David", Card.SEVEN_HEART, Card.FOUR_CLUB, Card.FIVE_DIAMOND, Card.SIX_CLUB, Card.THREE_CLUB);
		Player player2 = createPlayer("Shelly", Card.FOUR_SPADE, Card.THREE_SPADE, Card.FIVE_CLUB, Card.SIX_SPADE, Card.SEVEN_DIAMOND);
		Player player3 = createPlayer("Joe", Card.TWO_SPADE, Card.THREE_DIAMOND, Card.SIX_DIAMOND, Card.FOUR_DIAMOND, Card.FIVE_SPADE);
		
		List<Player> players = Arrays.asList(player1, player2, player3);
		List<Player> winners = Poker.rankHighCards(players);
		
		// David and Shelly have the same hand, but Joe has a lower kicker than the winners
		assertEquals(2, winners.size());
	}
	
	@Test
	public void rankHighCardsStraightFlushTest()
	{
		Player player1 = createPlayer("David", Card.SEVEN_CLUB, Card.FOUR_CLUB, Card.FIVE_CLUB, Card.SIX_CLUB, Card.THREE_CLUB);
		Player player2 = createPlayer("Shelly", Card.FOUR_SPADE, Card.THREE_SPADE, Card.FIVE_SPADE, Card.SIX_SPADE, Card.SEVEN_SPADE);
		Player player3 = createPlayer("Joe", Card.TWO_DIAMOND, Card.THREE_DIAMOND, Card.SIX_DIAMOND, Card.FOUR_DIAMOND, Card.FIVE_DIAMOND);
		
		List<Player> players = Arrays.asList(player1, player2, player3);
		List<Player> winners = Poker.rankHighCards(players);
		
		// David and Shelly have the same hand, but Joe has a lower kicker than the winners
		assertEquals(2, winners.size());
	}
	
	@Test
	public void rankPairsOnePairTest()
	{
		Player player1 = createPlayerWithHighPair("David", Rank.SEVEN, Card.SEVEN_HEART, Card.QUEEN_CLUB, Card.FIVE_DIAMOND, Card.KING_CLUB, Card.SEVEN_CLUB);
		Player player2 = createPlayerWithHighPair("Shelly", Rank.SEVEN, Card.QUEEN_SPADE, Card.SEVEN_SPADE, Card.FIVE_CLUB, Card.KING_SPADE, Card.SEVEN_DIAMOND);
		Player player3 = createPlayerWithHighPair("Joe", Rank.THREE, Card.TWO_SPADE, Card.THREE_DIAMOND, Card.ACE_DIAMOND, Card.THREE_SPADE, Card.FIVE_SPADE);
		
		List<Player> players = Arrays.asList(player1, player2, player3);
		List<Player> winners = Poker.rankPairs(players, 2);
		
		// David and Shelly have the same hand, but Joe has a lower kicker than the winners
		assertEquals(2, winners.size());
		assertEquals("David", winners.get(0).getName());
		assertEquals("Shelly", winners.get(1).getName());
	}
	
	@Test
	public void rankPairsTwoPairTest()
	{
		Player player1 = createPlayerWithHighPair("David", Rank.SEVEN, Card.SEVEN_HEART, Card.QUEEN_CLUB, Card.FIVE_DIAMOND, Card.FIVE_CLUB, Card.SEVEN_CLUB);
		Player player2 = createPlayerWithHighPair("Shelly", Rank.SEVEN, Card.QUEEN_SPADE, Card.SEVEN_SPADE, Card.FIVE_HEART, Card.FIVE_SPADE, Card.SEVEN_DIAMOND);
		Player player3 = createPlayerWithHighPair("Joe", Rank.FOUR, Card.FOUR_SPADE, Card.THREE_CLUB, Card.ACE_DIAMOND, Card.THREE_DIAMOND, Card.FOUR_DIAMOND);
		
		List<Player> players = Arrays.asList(player1, player2, player3);
		List<Player> winners = Poker.rankPairs(players, 2);
		
		// David and Shelly have the same hand, but Joe has a lower kicker than the winners
		assertEquals(2, winners.size());
		assertEquals("David", winners.get(0).getName());
		assertEquals("Shelly", winners.get(1).getName());
	}
	
	@Test
	public void rankPairsThreeOfAKindTest()
	{
		Player player1 = createPlayerWithHighPair("David", Rank.QUEEN, Card.QUEEN_HEART, Card.QUEEN_CLUB, Card.FIVE_DIAMOND, Card.QUEEN_SPADE, Card.SEVEN_CLUB);
		Player player2 = createPlayerWithHighPair("Shelly", Rank.SEVEN, Card.QUEEN_DIAMOND, Card.SEVEN_SPADE, Card.SEVEN_CLUB, Card.KING_SPADE, Card.SEVEN_DIAMOND);
		Player player3 = createPlayerWithHighPair("Joe", Rank.THREE, Card.TWO_SPADE, Card.THREE_DIAMOND, Card.ACE_DIAMOND, Card.THREE_SPADE, Card.THREE_CLUB);
		
		List<Player> players = Arrays.asList(player1, player2, player3);
		List<Player> winners = Poker.rankPairs(players, 3);
		
		// There can only be one winner in 3 of a kind since there are only 4 ranks
		assertEquals(1, winners.size());
		assertEquals("David", winners.get(0).getName());
	}
	
	@Test
	public void rankPairsFullHouseTest()
	{
		Player player1 = createPlayerWithHighPair("David", Rank.QUEEN, Card.QUEEN_HEART, Card.QUEEN_CLUB, Card.FIVE_DIAMOND, Card.QUEEN_SPADE, Card.FIVE_CLUB);
		Player player2 = createPlayerWithHighPair("Shelly", Rank.SEVEN, Card.KING_DIAMOND, Card.SEVEN_SPADE, Card.SEVEN_CLUB, Card.KING_SPADE, Card.SEVEN_DIAMOND);
		Player player3 = createPlayerWithHighPair("Joe", Rank.THREE, Card.TWO_SPADE, Card.THREE_DIAMOND, Card.TWO_DIAMOND, Card.THREE_SPADE, Card.THREE_CLUB);
		
		List<Player> players = Arrays.asList(player1, player2, player3);
		List<Player> winners = Poker.rankPairs(players, 3);
		
		// There can only be one winner in a full house since there are only 4 ranks
		assertEquals(1, winners.size());
		assertEquals("David", winners.get(0).getName());
	}
	
	@Test
	public void rankPairsFourOfAKindTest()
	{
		Player player1 = createPlayerWithHighPair("David", Rank.QUEEN, Card.QUEEN_HEART, Card.QUEEN_CLUB, Card.QUEEN_DIAMOND, Card.QUEEN_SPADE, Card.FIVE_CLUB);
		Player player2 = createPlayerWithHighPair("Shelly", Rank.SEVEN, Card.SEVEN_HEART, Card.SEVEN_SPADE, Card.SEVEN_CLUB, Card.KING_SPADE, Card.SEVEN_DIAMOND);
		Player player3 = createPlayerWithHighPair("Joe", Rank.THREE, Card.TWO_SPADE, Card.THREE_DIAMOND, Card.THREE_HEART, Card.THREE_SPADE, Card.THREE_CLUB);
		
		List<Player> players = Arrays.asList(player1, player2, player3);
		List<Player> winners = Poker.rankPairs(players, 4);
		
		// There can only be one winner in a 4 of a kind since there are only 4 ranks
		assertEquals(1, winners.size());
		assertEquals("David", winners.get(0).getName());
	}
	
	@Test
	public void testMain() throws Exception
	{
		Poker.readFromConsole = false;
		Poker.main(null);
	}
	
	@Test
	public void testPokerDealer() throws Exception {
		int numDeals = 0;
		boolean winningHand = false;
		Poker.dealer = true;
		Poker.notTest = false;
		Poker.numPlayers = 6;
		while (winningHand == false) {
			Poker.main(null);
			for (Player player : Poker.players) {
				if (player.getPokerHandRank().equals(PokerHandRank.STRAIGHT_FLUSH)) {
					System.out.println("We have a winner!!! after [" + numDeals + "] deals.");
					System.out.println(player.printPlayerHand());
					winningHand = true;
				}
				numDeals++;
			}
		}
	}
	
	private Player createPlayer(String name, Card ... cards)
	{
		Player player = new Player();
		player.setName(name);
		player.setCards(Arrays.asList(cards));
		return player;
	}
	
	private Player createPlayerWithHighPair(String name, Rank highPair, Card ... cards)
	{
		Player player = createPlayer(name, cards);
		player.setHighPair(highPair);
		return player;
	}
}
