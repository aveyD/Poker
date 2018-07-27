import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;

public class Dealer {

	private static List<Card> flopTurnRiver = new ArrayList<Card>();

	private static Map<Integer, Card> initDeck() {
		Map<Integer, Card> deck = new HashMap<Integer, Card>();
		int i = 0;

		// TODO: fix this to loop over and add
		deck.put(i, Card.TWO_HEART);
		deck.put(i++, Card.TWO_CLUB);
		deck.put(i++, Card.TWO_SPADE);
		deck.put(i++, Card.TWO_DIAMOND);
		deck.put(i++, Card.THREE_HEART);
		deck.put(i++, Card.THREE_CLUB);
		deck.put(i++, Card.THREE_SPADE);
		deck.put(i++, Card.THREE_DIAMOND);
		deck.put(i++, Card.FOUR_HEART);
		deck.put(i++, Card.FOUR_CLUB);
		deck.put(i++, Card.FOUR_SPADE);
		deck.put(i++, Card.FOUR_DIAMOND);
		deck.put(i++, Card.FIVE_HEART);
		deck.put(i++, Card.FIVE_CLUB);
		deck.put(i++, Card.FIVE_SPADE);
		deck.put(i++, Card.FIVE_DIAMOND);
		deck.put(i++, Card.SIX_HEART);
		deck.put(i++, Card.SIX_CLUB);
		deck.put(i++, Card.SIX_SPADE);
		deck.put(i++, Card.SIX_DIAMOND);
		deck.put(i++, Card.SEVEN_HEART);
		deck.put(i++, Card.SEVEN_CLUB);
		deck.put(i++, Card.SEVEN_SPADE);
		deck.put(i++, Card.SEVEN_DIAMOND);
		deck.put(i++, Card.EIGHT_HEART);
		deck.put(i++, Card.EIGHT_CLUB);
		deck.put(i++, Card.EIGHT_SPADE);
		deck.put(i++, Card.EIGHT_DIAMOND);
		deck.put(i++, Card.NINE_HEART);
		deck.put(i++, Card.NINE_CLUB);
		deck.put(i++, Card.NINE_SPADE);
		deck.put(i++, Card.NINE_DIAMOND);
		deck.put(i++, Card.TEN_HEART);
		deck.put(i++, Card.TEN_CLUB);
		deck.put(i++, Card.TEN_SPADE);
		deck.put(i++, Card.TEN_DIAMOND);
		deck.put(i++, Card.JACK_HEART);
		deck.put(i++, Card.JACK_CLUB);
		deck.put(i++, Card.JACK_SPADE);
		deck.put(i++, Card.JACK_DIAMOND);
		deck.put(i++, Card.QUEEN_HEART);
		deck.put(i++, Card.QUEEN_CLUB);
		deck.put(i++, Card.QUEEN_SPADE);
		deck.put(i++, Card.QUEEN_DIAMOND);
		deck.put(i++, Card.KING_HEART);
		deck.put(i++, Card.KING_CLUB);
		deck.put(i++, Card.KING_SPADE);
		deck.put(i++, Card.KING_DIAMOND);
		deck.put(i++, Card.ACE_HEART);
		deck.put(i++, Card.ACE_CLUB);
		deck.put(i++, Card.ACE_SPADE);
		deck.put(i++, Card.ACE_DIAMOND);

		return deck;
	}

	public static boolean regularDeal(List<Player> players) {
		int handSize = 5;
		return deal(players, handSize, false);
	}

	public static boolean texasHoldemDeal(List<Player> players) {
		int handSize = 2;
		return deal(players, handSize, true);
	}

	public static boolean deal(List<Player> players, int handSize, boolean flopTurnRiver) {
		Map<Integer, Card> deck = initDeck();
		Random rng = new Random();
		Set<Integer> generated = new LinkedHashSet<Integer>();
		int playerCards = (handSize * players.size());
		while (generated.size() < playerCards) {
			Integer next = rng.nextInt(deck.size() - 1) + 1;
			generated.add(next);
		}
		if (flopTurnRiver) {
			while (generated.size() < (playerCards + 5)) {
				Integer next = rng.nextInt(deck.size() - 1) + 1;
				generated.add(next);
			}
		}
		int playerNum = 0;

		for (Integer card : generated) {
			Player player = players.get(playerNum);
			if (player.getCards().size() < handSize) {
				player.getCards().add(deck.get(card));
			} else {
				if (flopTurnRiver) {
					System.out.println("We made it to the flop/turn/river.");
					getFlopTurnRiver().add(deck.get(card));
				} else {
					System.out.println("Something went wrong.");
				}
			}
			if (playerNum >= players.size() - 1) {
				playerNum = 0;
			} else {
				playerNum++;
			}
		}
		return true;
	}

	public static List<Card> getFlopTurnRiver() {
		return flopTurnRiver;
	}

	public void setFlopTurnRiver(List<Card> flopTurnRiver) {
		Dealer.flopTurnRiver = flopTurnRiver;
	}
}
