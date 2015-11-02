package PokerEngineAM;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;


public class Hand {
	
	

	private ArrayList<Card> cardsInHand;
	
	public ArrayList<Card> CardsInHand()
		{ return this.cardsInHand; }
	
	
	
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
	{ return cardsInHand.get(location); }
	
	
	
	
	
	
	public Hand() // No arg constructor
	{;}

	public Hand(Deck deck) {
		ArrayList<Card> HAND = new ArrayList<Card>();
		for (int x = 0; x < 5; x++) {
			HAND.add(deck.drawFromDeck());
		}
		cardsInHand = HAND;
	}
	
	
	
	
	
	// Enumeration for the different types of hands
	
	public enum HANDTYPE {
		ROYALFLUSH(12),
		FIVEKIND(11),
		JOKERROYALFLUSH(10),
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
		
		if(CardsInHand().get(0).getRank().getRank() == 14 &&
				CardsInHand().get(1).getRank().getRank() == 13 &&
				CardsInHand().get(2).getRank().getRank() == 12 &&
				CardsInHand().get(3).getRank().getRank() == 11 &&
				CardsInHand().get(4).getRank().getRank() == 10 &&
				CardsInHand().get(0).getSuit().getSuit() == CardsInHand().get(1).getSuit().getSuit() &&
				CardsInHand().get(1).getSuit().getSuit() == CardsInHand().get(2).getSuit().getSuit() &&
				CardsInHand().get(2).getSuit().getSuit() == CardsInHand().get(3).getSuit().getSuit() &&
				CardsInHand().get(3).getSuit().getSuit() == CardsInHand().get(4).getSuit().getSuit())
		{ ScoreHand(HANDTYPE.ROYALFLUSH, 0, 0, 0); }
		
		// Evaluate Five of a Kind
		
		else if ((CardsInHand().get(0).getRank().getRank() == 15 ) && 
				
				(((CardsInHand().get(1).getRank().getRank()) == (CardsInHand().get(2).getRank().getRank())) &&
				((CardsInHand().get(2).getRank().getRank()) == (CardsInHand().get(3).getRank().getRank())) &&
				((CardsInHand().get(3).getRank().getRank()) == (CardsInHand().get(4).getRank().getRank()))))
			
		{ ScoreHand(
				HANDTYPE.FIVEKIND,
				CardsInHand().get(0).getRank().getRank(),
				0,
				0);				
		}


		// Evaluate Joker Royal Flush

		else if(((CardsInHand().get(0).getRank().getRank() == 14) || (CardsInHand().get(0).getRank().getRank() == 15)) &&
				((CardsInHand().get(1).getRank().getRank() == 13) || (CardsInHand().get(1).getRank().getRank() == 15)) &&
				((CardsInHand().get(2).getRank().getRank() == 12) || (CardsInHand().get(2).getRank().getRank() == 15)) &&
				((CardsInHand().get(3).getRank().getRank() == 11) || (CardsInHand().get(3).getRank().getRank() == 15)) &&
				((CardsInHand().get(4).getRank().getRank() == 10) || (CardsInHand().get(4).getRank().getRank() == 15)) &&
	
				((CardsInHand().get(0).getSuit().getSuit() == CardsInHand().get(1).getSuit().getSuit()) 
						|| ((CardsInHand().get(0).getSuit().getSuit()) == 5)
						|| ((CardsInHand().get(1).getSuit().getSuit()) == 5)) &&
				((CardsInHand().get(1).getSuit().getSuit() == CardsInHand().get(2).getSuit().getSuit()) 
						|| ((CardsInHand().get(1).getSuit().getSuit()) == 5)
						|| ((CardsInHand().get(2).getSuit().getSuit()) == 5)) &&
				((CardsInHand().get(2).getSuit().getSuit() == CardsInHand().get(3).getSuit().getSuit()) 
						|| ((CardsInHand().get(2).getSuit().getSuit()) == 5)
						|| ((CardsInHand().get(3).getSuit().getSuit()) == 5)) &&
				((CardsInHand().get(3).getSuit().getSuit() == CardsInHand().get(4).getSuit().getSuit()) 
						|| ((CardsInHand().get(3).getSuit().getSuit()) == 5)
						|| ((CardsInHand().get(4).getSuit().getSuit()) == 5)))
		{ ScoreHand(HANDTYPE.JOKERROYALFLUSH, 0, 0, 0); }
		
	
		
		
		
		
		// Evaluate Straight Flush
		
		else if (CardsInHand().get(1).getRank().getRank() == CardsInHand().get(0).getRank().getRank() - 1 &&
				 CardsInHand().get(2).getRank().getRank() == CardsInHand().get(1).getRank().getRank() - 1 &&
				 CardsInHand().get(3).getRank().getRank() == CardsInHand().get(2).getRank().getRank() - 1 &&
				 CardsInHand().get(4).getRank().getRank() == CardsInHand().get(3).getRank().getRank() - 1 &&
				 CardsInHand().get(0).getSuit().getSuit() == CardsInHand().get(1).getSuit().getSuit() &&
				 CardsInHand().get(1).getSuit().getSuit() == CardsInHand().get(2).getSuit().getSuit() &&
				 CardsInHand().get(2).getSuit().getSuit() == CardsInHand().get(3).getSuit().getSuit() &&
				 CardsInHand().get(3).getSuit().getSuit() == CardsInHand().get(4).getSuit().getSuit())
		{ ScoreHand(
				HANDTYPE.STRAIGHTFLUSH, 
				CardsInHand().get(0).getRank().getRank(), 
				0, 
				0);
		}
		
				
		
		
		
		// Four of a Kind
		
		else if ((CardsInHand().get(0).getRank().getRank() == CardsInHand().get(1).getRank().getRank()) &&
				(CardsInHand().get(1).getRank().getRank() == CardsInHand().get(2).getRank().getRank()) &&
				(CardsInHand().get(2).getRank().getRank() == CardsInHand().get(3).getRank().getRank()))
		{ ScoreHand(
				HANDTYPE.FOURKIND, 
				CardsInHand().get(0).getRank().getRank(), 
				0, 
				CardsInHand().get(4).getRank().getRank());
		}
		
		else if ((CardsInHand().get(1).getRank().getRank() == CardsInHand().get(2).getRank().getRank()) &&
				(CardsInHand().get(2).getRank().getRank() == CardsInHand().get(3).getRank().getRank()) &&
				(CardsInHand().get(3).getRank().getRank() == CardsInHand().get(4).getRank().getRank()))
		
		{ ScoreHand(
				HANDTYPE.FOURKIND, 
				CardsInHand().get(1).getRank().getRank(), 
				0, 
				CardsInHand().get(0).getRank().getRank());
		}
		
		
		
		// Full House
		
		else if ((CardsInHand().get(0).getRank().getRank() == (CardsInHand().get(1).getRank().getRank()))
				&& (CardsInHand().get(2).getRank().getRank() == CardsInHand().get(3).getRank().getRank()) 
				&& (CardsInHand().get(3).getRank().getRank() == CardsInHand().get(4).getRank().getRank()))
			
		{ ScoreHand(
				HANDTYPE.FULLHOUSE, 
				CardsInHand().get(2).getRank().getRank(), 
				CardsInHand().get(0).getRank().getRank(), 
				0);
		}
		else if ((CardsInHand().get(3).getRank().getRank() == (CardsInHand().get(4).getRank().getRank())
			&& (CardsInHand().get(0).getRank().getRank() == CardsInHand().get(1).getRank().getRank()) 
			&& (CardsInHand().get(1).getRank().getRank() == CardsInHand().get(2).getRank().getRank())))
			
		{ ScoreHand(
				HANDTYPE.FULLHOUSE, 
				CardsInHand().get(0).getRank().getRank(), 
				CardsInHand().get(3).getRank().getRank(), 
				0);
		}
		
		
		
		
		
		
		// Evaluate Flush
		
		else if (CardsInHand().get(0).getSuit().getSuit() == CardsInHand().get(1).getSuit().getSuit() &&
				 CardsInHand().get(1).getSuit().getSuit() == CardsInHand().get(2).getSuit().getSuit() &&
				 CardsInHand().get(2).getSuit().getSuit() == CardsInHand().get(3).getSuit().getSuit() &&
				 CardsInHand().get(3).getSuit().getSuit() == CardsInHand().get(4).getSuit().getSuit())
			
		{ ScoreHand(
				HANDTYPE.FLUSH, 
				CardsInHand().get(0).getRank().getRank(), 
				0, 
				0);
		}
		
		
		
		
		
		
		// Evaluate Straight
		
		else if (CardsInHand().get(1).getRank().getRank() == CardsInHand().get(0).getRank().getRank() - 1 &&
				 CardsInHand().get(2).getRank().getRank() == CardsInHand().get(1).getRank().getRank() - 1 &&
				 CardsInHand().get(3).getRank().getRank() == CardsInHand().get(2).getRank().getRank() - 1 &&
				 CardsInHand().get(4).getRank().getRank() == CardsInHand().get(3).getRank().getRank() - 1)
		
		{ ScoreHand(
				HANDTYPE.STRAIGHT, 
				CardsInHand().get(0).getRank().getRank(), 
				0, 
				0);
		}
		
		
		
		
		
		
		// Three of a Kind
		
		else if ((CardsInHand().get(0).getRank().getRank() == CardsInHand().get(1).getRank().getRank()) &&
				(CardsInHand().get(1).getRank().getRank() == CardsInHand().get(2).getRank().getRank()))
		{ ScoreHand(
				HANDTYPE.THREEKIND, 
				CardsInHand().get(0).getRank().getRank(), 
				0, 
				CardsInHand().get(3).getRank().getRank());
		}
		
		else if ((CardsInHand().get(1).getRank().getRank() == CardsInHand().get(2).getRank().getRank()) &&
				(CardsInHand().get(2).getRank().getRank() == CardsInHand().get(3).getRank().getRank()))
		{ ScoreHand(
				HANDTYPE.THREEKIND, 
				CardsInHand().get(1).getRank().getRank(), 
				0, 
				CardsInHand().get(0).getRank().getRank());
		}
		
		else if ((CardsInHand().get(2).getRank().getRank() == CardsInHand().get(3).getRank().getRank()) &&
				(CardsInHand().get(3).getRank().getRank() == CardsInHand().get(4).getRank().getRank()))
		{ ScoreHand(
				HANDTYPE.THREEKIND, 
				CardsInHand().get(2).getRank().getRank(), 
				0, 
				CardsInHand().get(0).getRank().getRank());
		}
		
		
		
		
		
		
		// Evaluate Two Pair
		
		else if (
				 (CardsInHand().get(0).getRank().getRank() == CardsInHand().get(1).getRank().getRank() &&
				 (CardsInHand().get(2).getRank().getRank() == CardsInHand().get(3).getRank().getRank()))) 
			
				 { ScoreHand(
						 HANDTYPE.TWOPAIR,
						 CardsInHand().get(0).getRank().getRank(),
						 CardsInHand().get(2).getRank().getRank(),
						 CardsInHand().get(4).getRank().getRank()); 
				 }
		
		else if (
				(CardsInHand().get(0).getRank().getRank() == CardsInHand().get(1).getRank().getRank() &&
			    (CardsInHand().get(3).getRank().getRank() == CardsInHand().get(4).getRank().getRank()))) 
			
				{ ScoreHand(
						HANDTYPE.TWOPAIR,
						CardsInHand().get(0).getRank().getRank(),
						CardsInHand().get(3).getRank().getRank(),
						CardsInHand().get(2).getRank().getRank()); 
				}
		
		else if (
			    (CardsInHand().get(1).getRank().getRank() == CardsInHand().get(2).getRank().getRank() &&
			    (CardsInHand().get(3).getRank().getRank() == CardsInHand().get(4).getRank().getRank())))
			
				{ ScoreHand(
						HANDTYPE.TWOPAIR,
						CardsInHand().get(1).getRank().getRank(),
						CardsInHand().get(3).getRank().getRank(),
						CardsInHand().get(0).getRank().getRank()); 
				}
		
		
		
		
		
		// One Pair
		
		else if (CardsInHand().get(0).getRank().getRank() == CardsInHand().get(1).getRank().getRank())
			{ ScoreHand(
					HANDTYPE.PAIR,
					CardsInHand().get(0).getRank().getRank(),
					0,
					CardsInHand().get(2).getRank().getRank()); 
			}
		else if (CardsInHand().get(1).getRank().getRank() == CardsInHand().get(2).getRank().getRank())
			{ ScoreHand(
					HANDTYPE.PAIR,
					CardsInHand().get(1).getRank().getRank(),
					0,
					CardsInHand().get(0).getRank().getRank()); 
			}
		else if (CardsInHand().get(2).getRank().getRank() == CardsInHand().get(3).getRank().getRank())
			{ ScoreHand(
					HANDTYPE.PAIR,
					CardsInHand().get(2).getRank().getRank(),
					0,
					CardsInHand().get(0).getRank().getRank()); 
			}
		else if (CardsInHand().get(3).getRank().getRank() == CardsInHand().get(4).getRank().getRank())
			{ ScoreHand(
					HANDTYPE.PAIR,
					CardsInHand().get(3).getRank().getRank(),
					0,
					CardsInHand().get(0).getRank().getRank()); 
			}
		
		
		
		
		// High Card
		
		else ScoreHand(
				HANDTYPE.HIGHCARD, 
				CardsInHand().get(0).getRank().getRank(), 
				0, 
				CardsInHand().get(1).getRank().getRank());
		
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
