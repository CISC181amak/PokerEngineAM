package PokerEngineAM;

import java.util.Comparator;


public class Card {
	
	private RANK rank; // Integers 1-13 represent Ace to King
	private SUIT suit; // Integers 1-4 represent Hearts, Spades, Clubs, Diamonds
	private boolean wild = false;
	
	
	 enum RANK { // Enumeration of the different ranks
		ACE(14), 
		TWO(2), 
		THREE(3), 
		FOUR(4), 
		FIVE(5), 
		SIX(6), 
		SEVEN(7), 
		EIGHT(8), 
		NINE(9), 
		TEN(10), 
		JACK(11),
		QUEEN(12), 
		KING(13),
		JOKER(15);
		 
		
		private int rank;
		
		private RANK(int rank){
			this.rank = rank; 
			};
			
		public int getRank() {
			return rank;
		}
			
		public String getRANK(int i) { // Prints the string representation of the different ranks
			return RANK.values()[i].toString();
		};	
		}
	
	
	
	
	
	enum SUIT { // Enumeration of different suits
		HEART(1), 
		SPADE(2), 
		CLUB(3), 
		DIAMOND(4),
		JOKER(5);
		
		
		private int suit;
		private SUIT(int suit){
			this.suit = suit;
			};
			
		public int getSuit() {
			return suit;
		}
			
			
		public String getSuit(int i) { // Prints the string representation of the different suits
			return SUIT.values()[i].toString();
		};
		}
	
	
	
	
	
	public Card() { // No arg constructor
	}
	
	public Card(RANK rank, SUIT suit) { // Constructs a card with integers rank and suit
		this.rank = rank;
		this.suit = suit;
		this.wild = false;
	}
	
	public Card(RANK rank, SUIT suit, boolean wild) { // Constructs a card with integers rank and suit
		this.rank = rank;
		this.suit = suit;
		this.wild = wild;
	}
	
	
	
	

	public RANK getRank() { // Getter for Rank
		return rank;
	}


	public SUIT getSuit() { // Getter for Suit
		return suit;
	}
	
	public boolean getWild() {
		return this.wild;
	}
	
	public void setWild() {
		this.wild = true;
	}
	
	
	
	
	public static Comparator<Card> CardRank = new Comparator<Card>() {

		public int compare(Card c1, Card c2) {

		   int Cno1 = c1.getRank().getRank();
		   int Cno2 = c2.getRank().getRank();

		   /*For descending order*/
		   return Cno2 - Cno1;

	   }};

}
