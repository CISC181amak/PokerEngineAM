package PokerEngineAM;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;


public class Hand {
	
	

	private ArrayList<Card> cardsInHand;
	
	public ArrayList<Card> CardsInHand() {
		return this.cardsInHand;
	}
	
	
	private int HandStrength;
	private int HighHand;
	private int LowHand;
	private int Kicker;
	private boolean bScored = false;
	
	
	public void  AddCardToHand(Card c)
	{
		if (this.cardsInHand == null)
		{
			cardsInHand = new ArrayList<Card>();
		}
		this.cardsInHand.add(c);
	}
	
	public Card  GetCardFromHand(int location)
	{
		return cardsInHand.get(location);
	}
	
	public Hand() // No arg constructor
	{
	}
	
	

	public Hand(Deck deck) {
		ArrayList<Card> HAND = new ArrayList<Card>();
		for (int x = 0; x < 5; x++) {
			HAND.add(deck.drawFromDeck());
		}
		cardsInHand = HAND;
	}
	
	
	
	
	public enum HANDTYPE { // Enumeration of different hands
		ROYALFLUSH(10),
		STRAIGHTFLUSH(9),
		FOURKIND(8),
		FULLHOUSE(7),
		FLUSH(6),
		STRAIGHT(5),
		THREEKIND(4),
		TWOPAIR(3),
		PAIR(2),
		HIGHCARD(1);
		
		private int index;
		private HANDTYPE(int index) {
			this.index = index;
		}
		
		public int getHANDTYPE() {
			return index;
		}
		public String getHANDTYPE(int index) { // Prints string representation of the different hands
			return HANDTYPE.values()[index].toString();
		}
	}
	
	
	
	public int getHandStrength() {
		return HandStrength;
	}


	public int getKicker() {
		return Kicker;
	}

	public int getHighPairStrength() {
		return HighHand;
	}

	public int getLowPairStrength() {
		return LowHand;
	}
	
	
	
	
	
	public static Hand EvaluateHand(ArrayList<Card> SeededHand) {
		
		Deck d = new Deck();
		Hand h = new Hand(d);
		h.cardsInHand = SeededHand;

		return h;
	}
	
	
	
	public void EvaluateHand() {
		Collections.sort(cardsInHand, Card.CardRank);
		
		// Evaluate Royal Flush
		
		if(cardsInHand.get(0).getRank().getRank() == 14 &&
				cardsInHand.get(1).getRank().getRank() == 13 &&
				cardsInHand.get(2).getRank().getRank() == 12 &&
				cardsInHand.get(3).getRank().getRank() == 11 &&
				cardsInHand.get(4).getRank().getRank() == 10 &&
				cardsInHand.get(0).getSuit().getSuit() == cardsInHand.get(1).getSuit().getSuit() &&
				cardsInHand.get(1).getSuit().getSuit() == cardsInHand.get(2).getSuit().getSuit() &&
				cardsInHand.get(2).getSuit().getSuit() == cardsInHand.get(3).getSuit().getSuit() &&
				cardsInHand.get(3).getSuit().getSuit() == cardsInHand.get(4).getSuit().getSuit())
		{ ScoreHand(HANDTYPE.ROYALFLUSH, 0, 0, 0); }
		
		// Evaluate Straight Flush
		
		else if (cardsInHand.get(1).getRank().getRank() == cardsInHand.get(0).getRank().getRank() - 1 &&
				 cardsInHand.get(2).getRank().getRank() == cardsInHand.get(1).getRank().getRank() - 1 &&
				 cardsInHand.get(3).getRank().getRank() == cardsInHand.get(2).getRank().getRank() - 1 &&
				 cardsInHand.get(4).getRank().getRank() == cardsInHand.get(3).getRank().getRank() - 1 &&
				 cardsInHand.get(0).getSuit().getSuit() == cardsInHand.get(1).getSuit().getSuit() &&
				 cardsInHand.get(1).getSuit().getSuit() == cardsInHand.get(2).getSuit().getSuit() &&
				 cardsInHand.get(2).getSuit().getSuit() == cardsInHand.get(3).getSuit().getSuit() &&
				 cardsInHand.get(3).getSuit().getSuit() == cardsInHand.get(4).getSuit().getSuit())
		{ ScoreHand(HANDTYPE.STRAIGHTFLUSH, cardsInHand.get(0).getRank().getRank(), 0, 0); }
		
		
		// Full House
		
		else if ( FullHouse() ) { ScoreHand(HANDTYPE.FULLHOUSE, cardsInHand.get(0).getRank().getRank(), cardsInHand.get(4).getRank().getRank(), 0); }
				
		
		// N of a Kind
		
		else if (NofAKind() == "Four") { ScoreHand(HANDTYPE.FOURKIND, cardsInHand.get(0).getRank().getRank(), 0, cardsInHand.get(4).getRank().getRank()); }
		
		else if (NofAKind() == "Three") { ScoreHand(HANDTYPE.THREEKIND, cardsInHand.get(0).getRank().getRank(), 0, cardsInHand.get(4).getRank().getRank()); }
		
		
		
		
		// Evaluate Flush
		
		else if (cardsInHand.get(0).getSuit().getSuit() == cardsInHand.get(1).getSuit().getSuit() &&
				cardsInHand.get(1).getSuit().getSuit() == cardsInHand.get(2).getSuit().getSuit() &&
				cardsInHand.get(2).getSuit().getSuit() == cardsInHand.get(3).getSuit().getSuit() &&
				cardsInHand.get(3).getSuit().getSuit() == cardsInHand.get(4).getSuit().getSuit())
		{ ScoreHand(HANDTYPE.FLUSH, cardsInHand.get(0).getRank().getRank(), 0, 0); }
		
		
		// Evaluate Straight
		
		else if (cardsInHand.get(1).getRank().getRank() == cardsInHand.get(0).getRank().getRank() - 1 &&
				 cardsInHand.get(2).getRank().getRank() == cardsInHand.get(1).getRank().getRank() - 1 &&
				 cardsInHand.get(3).getRank().getRank() == cardsInHand.get(2).getRank().getRank() - 1 &&
				 cardsInHand.get(4).getRank().getRank() == cardsInHand.get(3).getRank().getRank() - 1)
		{ ScoreHand(HANDTYPE.STRAIGHT, cardsInHand.get(0).getRank().getRank(), 0, 0); }
		
		else if (Pair() == 2) { ScoreHand(HANDTYPE.TWOPAIR, cardsInHand.get(1).getRank().getRank(), cardsInHand.get(3).getRank().getRank(), 0); }
		
		else if (Pair() == 1) { ScoreHand(HANDTYPE.PAIR, cardsInHand.get(0).getRank().getRank(), 0, cardsInHand.get(4).getRank().getRank()); }
		
		else ScoreHand(HANDTYPE.HIGHCARD, cardsInHand.get(0).getRank().getRank(), 0, cardsInHand.get(1).getRank().getRank());;
		
	}
	
	
	
		
		
		
		// Helpers for Full House, Pairs, # of a Kind
		
		public boolean FullHouse() {
			if ((CardsInHand().get(0).getRank().getRank() == (CardsInHand().get(1).getRank().getRank()))
					&& (CardsInHand().get(2).getRank().getRank() == CardsInHand().get(3).getRank().getRank()) 
					&& (CardsInHand().get(3).getRank().getRank() == CardsInHand().get(4).getRank().getRank())
				|| 
				(CardsInHand().get(3).getRank().getRank() == (CardsInHand().get(4).getRank().getRank())
				&& (CardsInHand().get(0).getRank().getRank() == CardsInHand().get(1).getRank().getRank()) 
				&& (CardsInHand().get(1).getRank().getRank() == CardsInHand().get(2).getRank().getRank())))
				{ return true; }
			else return false;
		}	
			
		
		public String NofAKind() {
			int count = 0;
			for (int i = 0; i<4; i++) {
				if (CardsInHand().get(i).getRank().getRank() == CardsInHand().get(i+1).getRank().getRank()) {
					count += 1;
				} else count += 0;
			}
			if (count == 4) { return "Four"; }
			else if (count == 3) { return "Three"; }
			else return "";
		
		}
		
		public int Pair() {
			int pair = 0;
			if ( (CardsInHand().get(0).getRank().getRank() == CardsInHand().get(1).getRank().getRank()) ||
					(CardsInHand().get(0).getRank().getRank() != CardsInHand().get(2).getRank().getRank()) ||
					(CardsInHand().get(0).getRank().getRank() != CardsInHand().get(3).getRank().getRank()) ||
					(CardsInHand().get(0).getRank().getRank() != CardsInHand().get(4).getRank().getRank()) )
			{ pair +=1; }
			else pair += 0;
			
			if ( (CardsInHand().get(1).getRank().getRank() == CardsInHand().get(2).getRank().getRank()) ||
					(CardsInHand().get(1).getRank().getRank() != CardsInHand().get(0).getRank().getRank()) ||
					(CardsInHand().get(1).getRank().getRank() != CardsInHand().get(3).getRank().getRank()) ||
					(CardsInHand().get(1).getRank().getRank() != CardsInHand().get(4).getRank().getRank()) )
			{ pair +=1; }
			else pair += 0;
			
			if ( (CardsInHand().get(2).getRank().getRank() == CardsInHand().get(3).getRank().getRank()) ||
					(CardsInHand().get(2).getRank().getRank() != CardsInHand().get(0).getRank().getRank()) ||
					(CardsInHand().get(2).getRank().getRank() != CardsInHand().get(1).getRank().getRank()) ||
					(CardsInHand().get(2).getRank().getRank() != CardsInHand().get(4).getRank().getRank()) )
			{ pair +=1; }
			else pair += 0;
			
			if ( (CardsInHand().get(3).getRank().getRank() == CardsInHand().get(4).getRank().getRank()) ||
					(CardsInHand().get(3).getRank().getRank() != CardsInHand().get(0).getRank().getRank()) ||
					(CardsInHand().get(3).getRank().getRank() != CardsInHand().get(1).getRank().getRank()) ||
					(CardsInHand().get(3).getRank().getRank() != CardsInHand().get(2).getRank().getRank()) )
			{ pair +=1; }
			else { pair += 0; }
			
			return pair;
		}
			
		
		
		private void ScoreHand(HANDTYPE hST, int HiHand, int LoHand, int Kicker) {
			this.HandStrength = hST.getHANDTYPE();
			this.HighHand = HiHand;
			this.LowHand = LoHand;
			this.Kicker = Kicker;
			this.bScored = true;

		}

		/**
		 * Custom sort to figure the best hand in an array of hands
		 */
		public static Comparator<Hand> HandRank = new Comparator<Hand>() {

			public int compare(Hand h1, Hand h2) {

				int result = 0;

				result = h2.getHandStrength() - h1.getHandStrength();

				if (result != 0) {
					return result;
				}

				result = h2.getHighPairStrength() - h1.getHighPairStrength();
				if (result != 0) {
					return result;
				}

				result = h2.getLowPairStrength() - h1.getLowPairStrength();
				if (result != 0) {
					return result;
				}

				result = h2.getKicker() - h1.getKicker();
				if (result != 0) {
					return result;
				}

				return 0;
			}
		};
}
