import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public interface PokerService {

	/**
	 * Used for comparing poker hands: HIGH_CARD, FLUSH, STRAIGHT, STRAIGHT_FLUSH
	 * 
	 * @param player the player to compare against
	 * @return the compare result
	 */
	public static int compareTo(PokerHand hand1, PokerHand hand2) {
		List<Card> hand1Cards = hand1.getPokerHand();
		List<Card> hand2Cards = hand2.getPokerHand();

		// sort from highest to lowest
		Collections.sort(hand1Cards, Collections.reverseOrder());
		Collections.sort(hand2Cards, Collections.reverseOrder());

		return compareCards(hand1Cards, hand2Cards);
	}

	/**
	 * Used for comparing poker hands: ONE_PAIR, TWO_PAIR, THREE_OF_A_KIND,
	 * FULL_HOUSE, FOUR_OF_A_KIND
	 * 
	 * @param player the player to compare against
	 * @return the compare result
	 */
	public static int compareToPair(PokerHand hand1, PokerHand hand2) {
		List<Card> hand1Cards = hand1.getPokerHand();
		List<Card> hand2Cards = hand2.getPokerHand();

		// Sort list by pairs first then highest to lowest
		hand1Cards = complexListSort(hand1Cards);
		hand2Cards = complexListSort(hand2Cards);

		return compareCards(hand1Cards, hand2Cards);
	}

	/**
	 * Compare the 5 sorted cards and return the result
	 * 
	 * @param player1Cards player 1s cards
	 * @param player2Cards player 2s cards
	 * @return the compare result between all 5 cards
	 */
	static int compareCards(List<Card> player1Cards, List<Card> player2Cards) {
		int compareVal = 0;

		if (player1Cards.size() < 5 || player2Cards.size() < 5) {
			System.out.println("Error comparing cards. Hand size is less than 5.");
		} else {
			for (int i = 0; i < 5; i++) {
				if (player1Cards.get(i).getRank().ordinal() > player2Cards.get(i).getRank().ordinal()) {
					compareVal = 1;
					break;
				} else if (player1Cards.get(i).getRank().ordinal() < player2Cards.get(i).getRank().ordinal()) {
					compareVal = -1;
					break;
				}
			}
		}
		return compareVal;
	}

	/**
	 * Sorts pairs first then kickers highest to lowest
	 * 
	 * @param cards the cards to sort
	 * @return the sorted list of cards
	 */
	public static List<Card> complexListSort(List<Card> cards) {
		Set<Card> playerCards = new LinkedHashSet<Card>();
		Map<Rank, Integer> rankMap = new HashMap<Rank, Integer>();

		for (Card card : cards) {
			Rank rank = card.getRank();
			Integer count = rankMap.get(rank);
			rankMap.put(rank, (count == null) ? 1 : count + 1);
		}

		// Convert map to list of <Rank,Integer> entries
		List<Map.Entry<Rank, Integer>> list = new ArrayList<Map.Entry<Rank, Integer>>(rankMap.entrySet());

		// Sort list by integer values
		Collections.sort(list, new Comparator<Map.Entry<Rank, Integer>>() {
			public int compare(Map.Entry<Rank, Integer> o1, Map.Entry<Rank, Integer> o2) {
				// compare o2 to o1, instead of o1 to o2, to get descending freq. order
				int compareVal = (o2.getValue()).compareTo(o1.getValue());

				// if the count is the same (2 pair) we want to sort on rank
				return compareVal == 0 ? o2.getKey().compareTo(o1.getKey()) : compareVal;
			}
		});

		// Populate the result into a list
		List<Rank> result = new ArrayList<Rank>();
		for (Map.Entry<Rank, Integer> entry : list) {
			for (int i = 0; i < entry.getValue(); i++) {
				result.add(entry.getKey());
			}
		}

		// once the results are sorted we need the suits back on them
		for (Rank rank : result) {
			for (Card card : cards) {
				if (rank.equals(card.getRank())) {
					playerCards.add(card);
				}
			}
		}

		return new ArrayList<Card>(playerCards);
	}
}
