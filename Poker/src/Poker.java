import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Stream;

/**
 * Poker! This will determine which poker hand(s) wins
 * 
 * @author David
 */
public class Poker implements PokerService {

	static List<Player> players = null;
	static List<Card> allCardsInPlay = new ArrayList<Card>();
	static boolean readFromConsole = false; // TODO: if you want to read from the console, leave this true.
											// If you want to read from a file change this to false
	static boolean readFromFile = false;
	static boolean dealer = true;
	static boolean texasHoldem = true;
	static int numPlayers = 0;
	static File file = new File("E:\\David\\Desktop\\poker.txt"); // TODO: Set this to the file of your choosing.

	public static void main(String[] args) throws Exception {
		boolean allValid = true;
		BufferedReader bufferRead = null;

		if (dealer) {
			if (numPlayers == 0) {
				bufferRead = new BufferedReader(new InputStreamReader(System.in));

				System.out.print("Enter the number of players playing poker: ");

				numPlayers = getNumberOfPlayers(bufferRead, readFromConsole);
			}
			if (numPlayers <= 1 || numPlayers > 10) {
				allValid = false;
				System.out.println("Invalid number of players. Must be between 2-10");
			} else {
				players = new ArrayList<Player>(numPlayers);
				initPlayers(players, numPlayers);
				allValid = dealToPlayers(players);
			}
		} else {
			if (readFromConsole) {
				bufferRead = new BufferedReader(new InputStreamReader(System.in));
			} else if (readFromFile) {
				// read from file
				bufferRead = new BufferedReader(new FileReader(file));
			}
			// first we need to input the number of players
			System.out.print("Enter the number of players playing poker: ");

			int numPlayers = getNumberOfPlayers(bufferRead, readFromConsole);
			if (numPlayers <= 1 || numPlayers > 10) {
				allValid = false;
				System.out.println("Invalid number of players. Must be between 2-10");
			} else {
				// Now we need to read in the players and their poker hands
				players = new ArrayList<Player>(numPlayers);
				allValid = readPlayerHands(numPlayers, bufferRead, readFromConsole);
			}
		}
		if (allValid) {
			// all players hands are valid. Now it's time to rank them
			rankAllHands();
			System.out.print("Player ranks: \n-------------\n");

			// print out what hand each player has
			for (Player player : players) {
				System.out.println(player.getName() + ": " + player.getPokerHandRank() + ": " + player.getCards());
			}

			List<PokerHand> allHands = new ArrayList<PokerHand>();
			for (Player player : players) {
				PokerHand pokerHand = new PokerHand(player.getCards());
				allHands.add(pokerHand);
			}
			List<PokerHand> bestPokerHands = determineBestHand(allHands);

			// if any other player has a hand that ties the best hand we have multiple
			// winners. These are the player(s) with the best hand
			List<Player> playersWithBestHand = new ArrayList<Player>();
			for (Player player : players) {
				PokerHand pokerHand = new PokerHand(player.getCards());

				// really only need the first best poker hand since the compare doesn't compare
				// suits unless it affects the rank.
				PokerHand bestPokerHand = bestPokerHands.get(0);
				if (PokerService.compareTo(pokerHand, bestPokerHand) == 0) {
					playersWithBestHand.add(player);
					player.setWinner(true);
				}
			}

			if (playersWithBestHand.size() > 1) {
				System.out.print("\nAnd the winners are: ");
			} else {
				System.out.print("\nAnd the winner is: ");
			}

			// print out the comma separated list of winning players if there are more than
			// one
			System.out.println(playersWithBestHand + "\n");
		} else {
			System.out.print("Game Over!");
		}
	}

	private static List<PokerHand> determineBestHand(List<PokerHand> allHands) {
		// determine what is the best poker hand rank
		PokerHandRank bestHandRank = PokerHandRank.HIGH_CARD;
		for (PokerHand pokerHand : allHands) {
			rankHands(pokerHand);
			if (pokerHand.getHandRank().ordinal() > bestHandRank.ordinal()) {
				bestHandRank = pokerHand.getHandRank();
			}
		}

		// these are all the best poker hands
		ArrayList<PokerHand> bestRankedHands = new ArrayList<PokerHand>();
		for (PokerHand pokerHand : allHands) {
			rankHands(pokerHand);
			if (pokerHand.getHandRank().equals(bestHandRank)) {
				// any poker hand that is the best type of hand will be added to this list
				bestRankedHands.add(pokerHand);
			}
		}

		// takes into account lower/higher card wins and kickers
		// if there are more than 1 winning poker hands they are equivalent hands
		List<PokerHand> bestPokerHands = complexRules(bestRankedHands, bestHandRank);
		return bestPokerHands;
	}

	private static boolean dealToPlayers(List<Player> players) {
		boolean allValid = false;
		if (texasHoldem) {
			allValid = Dealer.texasHoldemDeal(players);
		} else {
			allValid = Dealer.regularDeal(players);
		}
		for (Player player : players) {
			System.out.println(player.printPlayerHand());
		}
		return allValid;
	}

	private static void initPlayers(List<Player> players, int numPlayers) {
		for (int i = 0; i < numPlayers; i++) {
			players.add(new Player(i + 1));
		}
	}

	/**
	 * Get the number of players that will be playing poker
	 * 
	 * @param bufferRead      the buffered reader
	 * @param readFromConsole
	 * @return the number of players
	 */
	private static int getNumberOfPlayers(BufferedReader bufferRead, boolean readFromConsole) {
		int numberOfPlayers = 0;
		String numPlayersString;
		try {
			numPlayersString = bufferRead.readLine();
			try {
				numberOfPlayers = Integer.parseInt(numPlayersString);
				if (!readFromConsole)
					System.out.println(numberOfPlayers);
			} catch (NumberFormatException nfe) {
				System.out.println(numPlayersString + " is not a number.");
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		return numberOfPlayers;
	}

	/**
	 * Read in the players hands one by one
	 * 
	 * @param numPlayers the number of players
	 * @param bufferRead the Buffered Reader
	 * @return true if all players have valid hands, false if otherwise
	 */
	private static boolean readPlayerHands(int numPlayers, BufferedReader bufferRead, boolean readFromConsole) {
		boolean playerValid = true;
		for (int playerNum = 0; playerNum < numPlayers; playerNum++) {
			System.out.print("Player " + (playerNum + 1) + ": ");

			try {
				String s = bufferRead.readLine();
				playerValid = parsePlayerHand(s);
				if (!playerValid) {
					break;
				}
				if (!readFromConsole)
					System.out.println(s);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		// All cards are valid, but we need to check for duplicates
		Set<Card> set = new HashSet<Card>(allCardsInPlay);
		if (set.size() != allCardsInPlay.size()) {
			playerValid = false;
			System.out.println("There was a duplicate card... We have a cheater...");
		}

		return playerValid;
	}

	/**
	 * Parse the players poker hand to determine if it is valid
	 * 
	 * @param str the string to be parsed
	 * @return true if the poker hand is valid, false if otherwise
	 */
	public static boolean parsePlayerHand(String str) {
		boolean validInput = true;
		List<String> items = Arrays.asList(str.split("\\s*,\\s*"));

		// format: name, card1, card2, card3, card4, card5
		if (items.size() != 6) {
			validInput = false;
			System.out.println("Invalid input.");
		} else {
			Player player = new Player();
			player.setName(items.get(0));

			List<Card> cards = new ArrayList<Card>(5);

			// loop over each card and add it to their hand
			for (int i = 1; i < 6; i++) {
				Card card = parseCard(items.get(i));
				if (card != null) {
					cards.add(card);
					allCardsInPlay.add(card); // to check for duplicate cards
				} else {
					// if we find an invalid card the game is over
					validInput = false;
					System.out.println("Invalid card[" + items.get(i) + "]");
					break;
				}
			}

			if (validInput) {
				player.setCards(cards);
				players.add(player);
			}
		}

		return validInput;
	}

	/**
	 * parse a single card
	 * 
	 * @param card the string representing the card
	 * @return the valid card or null
	 */
	private static Card parseCard(String card) {
		boolean valid = true;
		Rank rank = null;
		Suit suit = null;
		Card validCard = null;

		if (card.length() >= 1) {
			String strRank = card.substring(0, card.length() - 1).toUpperCase();

			switch (strRank) {
			case "2":
				rank = Rank.TWO;
				break;
			case "3":
				rank = Rank.THREE;
				break;
			case "4":
				rank = Rank.FOUR;
				break;
			case "5":
				rank = Rank.FIVE;
				break;
			case "6":
				rank = Rank.SIX;
				break;
			case "7":
				rank = Rank.SEVEN;
				break;
			case "8":
				rank = Rank.EIGHT;
				break;
			case "9":
				rank = Rank.NINE;
				break;
			case "10":
				rank = Rank.TEN;
				break;
			case "J":
				rank = Rank.JACK;
				break;
			case "Q":
				rank = Rank.QUEEN;
				break;
			case "K":
				rank = Rank.KING;
				break;
			case "A":
				rank = Rank.ACE;
				break;
			default:
				valid = false;
				break;
			}

			char charSuit = Character.toUpperCase(card.charAt(card.length() - 1));

			switch (charSuit) {
			case 'H':
				suit = Suit.HEART;
				break;
			case 'C':
				suit = Suit.CLUB;
				break;
			case 'S':
				suit = Suit.SPADE;
				break;
			case 'D':
				suit = Suit.DIAMOND;
				break;
			default:
				valid = false;
				break;
			}
		} else {
			valid = false;
		}

		if (valid && rank != null && suit != null) {
			// we have a valid card
			validCard = Card.getCardFromRankAndSuit(rank, suit);
		}

		return validCard;
	}

	/**
	 * Complex tie rules that determines the true winner (includes kicker
	 * considerations)
	 * 
	 * @param playersWithBestHand the list of players with the best hand
	 * @param bestHand            the hand rank that was the winning hand
	 * @return the list of player(s) that actually is/are the winner(s)
	 */
	static List<PokerHand> complexRules(List<PokerHand> bestRankedHands, PokerHandRank bestHand) {
		List<PokerHand> bestPokerHands = new ArrayList<PokerHand>();
		// if a player has the best hand they will continue
		switch (bestHand) {
		case HIGH_CARD:
		case STRAIGHT:
		case FLUSH:
		case STRAIGHT_FLUSH:
			bestPokerHands = rankHighCards(bestRankedHands);
			break;
		case ONE_PAIR:
		case TWO_PAIR:
			bestPokerHands = rankPairs(bestRankedHands, 2);
			break;
		case THREE_OF_A_KIND:
		case FULL_HOUSE:
			bestPokerHands = rankPairs(bestRankedHands, 3);
			break;
		case FOUR_OF_A_KIND:
			bestPokerHands = rankPairs(bestRankedHands, 4);
			break;
		default:
			break;
		}
		return bestPokerHands;
	}

	/**
	 * for HIGH_CARD, FLUSH, STRAIGHT, STRAIGHT_FLUSH the highest card wins unless 2
	 * or more have the same highest card of a different suit, then it goes to the
	 * next highest card and so on
	 * 
	 * more efficient way to rank high cards using compareTo (includes kickers)
	 */
	static List<PokerHand> rankHighCards(List<PokerHand> bestRankedHands) {
		List<PokerHand> bestPokerHands = new ArrayList<PokerHand>();
		PokerHand bestPokerHand = bestRankedHands.get(0);
		// determine the best player hand
		for (PokerHand pokerHand : bestRankedHands) {
			if (PokerService.compareTo(pokerHand, bestPokerHand) > 0) {
				bestPokerHand = pokerHand;
			}
		}

		// if there is a hand that ties the best hand we have multiple winners
		for (PokerHand pokerHand : bestRankedHands) {
			if (PokerService.compareTo(pokerHand, bestPokerHand) == 0) {
				bestPokerHands.add(pokerHand);
			}
		}
		return bestPokerHands;
	}

	/**
	 * for ONE_PAIR, TWO_PAIR, THREE_OF_A_KIND, FULL_HOUSE, FOUR_OF_A_KIND the
	 * highest pair wins unless 2 or more have the same highest pair of a different
	 * suit, then it goes to the next highest card and so on
	 */
	static List<PokerHand> rankPairs(List<PokerHand> bestRankedHands, int pairSize) {
		Rank highCardRank = Rank.TWO;
		List<PokerHand> hasHighPair = new ArrayList<PokerHand>();

		// get highest pair from all players
		for (PokerHand pokerHand : bestRankedHands) {

			Rank playerHighCard = pokerHand.getHighPair();
			highCardRank = playerHighCard.ordinal() > highCardRank.ordinal() ? playerHighCard : highCardRank;
		}

		System.out.println("The high pair is: " + highCardRank.name());

		// if a players hand contains the high pair then they are added to the winners
		// list
		for (PokerHand pokerHand : bestRankedHands) {
			List<Rank> fullListOfRanks = new ArrayList<Rank>();
			Map<Rank, Integer> rankMap = new HashMap<Rank, Integer>();
			for (Card card : pokerHand.getPokerHand()) {
				Rank rank = card.getRank();
				fullListOfRanks.add(rank);
				Integer count = rankMap.get(rank);
				rankMap.put(rank, (count == null) ? 1 : count + 1);
			}

			if (getHighPair(rankMap, pairSize) == highCardRank) {
				// this player has the high pair but kickers need to be taken into account
				hasHighPair.add(pokerHand);
			}
		}
		List<PokerHand> bestPokerHands = determineIfTie(hasHighPair);

		return bestPokerHands;
	}

	/**
	 * Determine if there is a tie by using the pair compare method
	 * 
	 * @param hasHighPair the list of players with the high pair
	 * @return the list of winner(s)
	 */
	private static List<PokerHand> determineIfTie(List<PokerHand> hasHighPair) {
		List<PokerHand> bestPokerHands = new ArrayList<PokerHand>();
		PokerHand bestPokerHand = hasHighPair.get(0);
		// determine the best player hand
		for (PokerHand pokerHand : hasHighPair) {
			if (PokerService.compareToPair(pokerHand, bestPokerHand) > 0) {
				bestPokerHand = pokerHand;
			}
		}

		// if any other player has a hand that ties the best hand we have multiple
		// winners
		for (PokerHand pokerHand : hasHighPair) {
			if (PokerService.compareToPair(pokerHand, bestPokerHand) == 0) {
				bestPokerHands.add(pokerHand);
			}
		}
		return bestPokerHands;
	}

	public static void rankAllHands() {
		for (Player player : players) {
			if (texasHoldem) {
				initAllPossibleHoldemHands(player);
				List<PokerHand> bestHands = determineBestHand(player.getAllPossibleHoldemHands());
				if (bestHands.size() > 1) {
					System.out
							.println("We have multiple winning hands for player[" + player.getName() + "]" + bestHands);
				}
				for (PokerHand bestHand : bestHands) {
					rankHands(bestHand);
					player.setCards(bestHand.getPokerHand());
					player.setPokerHandRank(bestHand.getHandRank());
					player.setHighPair(bestHand.getHighPair());
				}
			} else {
				PokerHand pokerHand = new PokerHand(player.getCards());
				rankHands(pokerHand);
				player.setPokerHandRank(pokerHand.getHandRank());
				player.setHighPair(pokerHand.getHighPair());
			}
		}
	}

	private static void initAllPossibleHoldemHands(Player player) {
		Card[] playerCardsAndFlopTurnRiver = Stream
				.concat(Arrays.stream(player.getCards().toArray()), Arrays.stream(player.getFlopTurnRiver().toArray()))
				.toArray(Card[]::new);
		combination(player, playerCardsAndFlopTurnRiver, 5);
	}

	public static void combination(Player player, Card[] elements, int K) {

		// get the length of the array
		// for a texas holdem hand including flop/turn/river it will be 7
		int N = elements.length;

		if (K > N) {
			System.out.println("Invalid input, K > N");
			return;
		}
		// calculate the possible combinations
		// e.g. for getting poker hands it will be c(7,5)
		c(N, K);

		// get the combination by index
		// e.g. 01 --> AB , 23 --> CD
		int combination[] = new int[K];

		// position of current index
		// if (r = 1) r*
		// index ==> 0 | 1 | 2
		// element ==> A | B | C
		int r = 0;
		int index = 0;

		while (r >= 0) {
			// possible indexes for 1st position "r=0" are "0,1,2" --> "A,B,C"
			// possible indexes for 2nd position "r=1" are "1,2,3" --> "B,C,D"

			// for r = 0 ==> index < (4+ (0 - 2)) = 2
			if (index <= (N + (r - K))) {
				combination[r] = index;

				// if we are at the last position print and increase the index
				if (r == K - 1) {

					// do something with the combination e.g. add to list or print
//					print(combination, elements);
					setOnePokerHand(player, combination, elements);
					index++;
				} else {
					// select index for next position
					index = combination[r] + 1;
					r++;
				}
			} else {
				r--;
				if (r > 0)
					index = combination[r] + 1;
				else
					index = combination[0] + 1;
			}
		}
	}

	private static int c(int n, int r) {
		int nf = fact(n);
		int rf = fact(r);
		int nrf = fact(n - r);
		int npr = nf / nrf;
		int ncr = npr / rf;

//		System.out.println("C(" + n + "," + r + ") = " + ncr);

		return ncr;
	}

	private static int fact(int n) {
		if (n == 0)
			return 1;
		else
			return n * fact(n - 1);
	}

//	private static void print(int[] combination, Object[] elements) {
//
//		String output = "";
//		for (int z = 0; z < combination.length; z++) {
//			output += elements[combination[z]];
//		}
//		System.out.println(output);
//	}

	private static void setOnePokerHand(Player player, int[] combination, Card[] elements) {
		ArrayList<Card> oneHand = new ArrayList<Card>();
		for (int z = 0; z < combination.length; z++) {
			oneHand.add(elements[combination[z]]);
		}
		PokerHand pokerHand = new PokerHand(oneHand);
		player.getAllPossibleHoldemHands().add(pokerHand);
//		System.out.println(oneHand);
	}

//	private static void initAllPossibleHoldemHands(Player player) {
//		List<Card> input = player.getCards();    // input array
//		int k = 5;                             // sequence length   
//
//		List<List<Card>> subsets = new ArrayList<>();
//
//		List<Card> s = new ArrayList<Card>(k);                  // here we'll keep indices 
//		                                       // pointing to elements in input array
//
//		if (k <= input.size()) {
//		    // first index sequence: 0, 1, 2, ...
//		    for (int i = 0; (s.get(i) = i) < k - 1; i++);  
//		    subsets.add(getSubset(input, s));
//		    for(;;) {
//		        int i;
//		        // find position of item that can be incremented
//		        for (i = k - 1; i >= 0 && s[i] == input.size() - k + i; i--); 
//		        if (i < 0) {
//		            break;
//		        }
//		        s[i]++;                    // increment this item
//		        for (++i; i < k; i++) {    // fill up remaining items
//		            s[i] = s[i - 1] + 1; 
//		        }
//		        subsets.add(getSubset(input, s));
//		    }
//		}
//	}
//	
//	// generate actual subset by index sequence
//	private List<Card> getSubset(List<Card> input, List<Card> subset) {
//	    List<Card> result = new ArrayList<Card>(subset.size()); 
//	    for (int i = 0; i < subset.size(); i++) 
//	        result.get(i) = input.get(subset.get(i));
//	    return result;
//	}

	/**
	 * Rank the hands from best to worst and set it on the player objects
	 */
	public static void rankHands(PokerHand hand) {
		Set<Suit> suits = new HashSet<Suit>();
		Set<Rank> ranks = new HashSet<Rank>();
		Map<Rank, Integer> rankMap = new HashMap<Rank, Integer>();
		List<Rank> fullListOfRanks = new ArrayList<Rank>();

		// add suits and ranks to a set to remove duplicates
		// also add each rank to a map with the count of how many times it appears
		for (Card card : hand.getPokerHand()) {
			Rank rank = card.getRank();
			suits.add(card.getSuit());
			ranks.add(rank);
			fullListOfRanks.add(rank);

			Integer count = rankMap.get(rank);
			rankMap.put(rank, (count == null) ? 1 : count + 1);
		}

		// if it's a flush there will only be one suit
		if (suits.size() == 1) {
			hand.setHandRank(PokerHandRank.FLUSH);
		}

		// if it's a high card or a strait there will be 5 ranks
		if (ranks.size() == 5) {
			boolean straight = true;
			// sort the list
			Collections.sort(fullListOfRanks);
			for (int i = 0; i < fullListOfRanks.size() - 1; i++) {
				// check if the ranks are consecutive or not
				if (fullListOfRanks.get(i).ordinal() + 1 != fullListOfRanks.get(i + 1).ordinal()) {
					// Not a strait
					straight = false;
					break;
				}
			}

			if (straight) {
				// it's both a straight and a flush
				if (suits.size() == 1) {
					hand.setHandRank(PokerHandRank.STRAIGHT_FLUSH);
				} else {
					hand.setHandRank(PokerHandRank.STRAIGHT);
				}
			} else if (suits.size() != 1) {
				hand.setHandRank(PokerHandRank.HIGH_CARD);
				Rank highestRank = Collections.max(ranks);
				hand.setHighPair(highestRank);
			}
		}

		// if it's a one pair there will be 4 ranks
		if (ranks.size() == 4) {
			hand.setHandRank(PokerHandRank.ONE_PAIR);
			hand.setHighPair(getHighPair(rankMap, 2));
		}

		// if it's three of a kind or two of a kind there will be 3 ranks
		if (ranks.size() == 3) {
			// the rank map will contain 2 ranks with value 2
			if (rankMap.containsValue(2)) {
				hand.setHandRank(PokerHandRank.TWO_PAIR);
				hand.setHighPair(getHighPair(rankMap, 2));
			}
			// the rank map will contain 1 rank with value 3
			else if (rankMap.containsValue(3)) {
				hand.setHandRank(PokerHandRank.THREE_OF_A_KIND);
				hand.setHighPair(getHighPair(rankMap, 3));
			}
		}

		// if it's a full house or four of a kind there will be 2 ranks
		if (ranks.size() == 2) {
			// the rank map will contain 1 rank with value 4
			if (rankMap.containsValue(4)) {
				hand.setHandRank(PokerHandRank.FOUR_OF_A_KIND);
				hand.setHighPair(getHighPair(rankMap, 4));
			}
			// the rank map will contain 1 rank with value 3 and 1 rank with value 2
			else if (rankMap.containsValue(3) && rankMap.containsValue(2)) {
				hand.setHandRank(PokerHandRank.FULL_HOUSE);
				hand.setHighPair(getHighPair(rankMap, 3));
			}
		}
	}

	/**
	 * Get the highest pair from the rank map
	 * 
	 * @param rankMap  the map with ranks and counts
	 * @param pairSize the count size of the pair
	 * @return the highest rank
	 */
	private static Rank getHighPair(Map<Rank, Integer> rankMap, int pairSize) {
		Rank rank = Rank.TWO;
		for (Map.Entry<Rank, Integer> entry : rankMap.entrySet()) {
			if (entry.getValue() == pairSize) {
				rank = entry.getKey().ordinal() > rank.ordinal() ? entry.getKey() : rank;
			}
		}
		return rank;
	}
}
