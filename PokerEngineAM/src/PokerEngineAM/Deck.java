package PokerEngineAM;

import java.util.ArrayList;
import java.util.Collections;



public class Deck {

	private ArrayList<Card> cards;
	
	public Deck() { // Creates a Deck with an ArrayList of Cards and shuffles it
		ArrayList<Card> NewDeck = new ArrayList<Card>();
		for (int i = 0; i <= 3; i++) {
			Card.SUIT Suit = Card.SUIT.values()[i];		
			for (int j = 0; j <= 12; j++) {
				Card.RANK Rank = Card.RANK.values()[j];				
				Card NewCard = new Card(Rank, Suit);
				NewDeck.add(NewCard);
			}
		}
		cards = NewDeck;
		Collections.shuffle(cards);
	}
	
	public Card drawFromDeck() {
		// Removes the first card from the deck and return the card
		Card FirstCard = cards.get(0);
		cards.remove(0);
		return FirstCard;
	}

	
	public int getTotalCards() {
		// Returns the total number of cards still in the deck
		return cards.size();
	}
	
	
	
	public ArrayList<Card> getCards()
	{
		return this.cards;
	}
	
	
	
	
}

