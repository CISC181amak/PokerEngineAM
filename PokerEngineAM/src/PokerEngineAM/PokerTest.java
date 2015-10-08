package PokerEngineAM;

import static org.junit.Assert.*;
import org.junit.Test;

import PokerEngineAM.Card.RANK;
import PokerEngineAM.Card.SUIT;
import PokerEngineAM.Hand.HANDTYPE;


public class PokerTest {
	
	@Test
	public void TestFullDeck() {
		Deck deck = new Deck();
		assertEquals(deck.getTotalCards(), 52);
	}
	
	@Test
	public void TestEvalHandROYALFLUSH() {
		Hand h = new Hand();
		h.AddCardToHand(new Card(RANK.ACE, SUIT.DIAMOND));
		h.AddCardToHand(new Card(RANK.KING, SUIT.DIAMOND));
		h.AddCardToHand(new Card(RANK.QUEEN, SUIT.DIAMOND));
		h.AddCardToHand(new Card(RANK.JACK, SUIT.DIAMOND));
		h.AddCardToHand(new Card(RANK.TEN, SUIT.DIAMOND));
		h.EvaluateHand();
		
		assertEquals(HANDTYPE.ROYALFLUSH.getHANDTYPE(), h.getHandStrength());
		
	}
	
	@Test
	public void TestEvalHandSTRAIGHT() {
		Hand h = new Hand();
		h.AddCardToHand(new Card(RANK.EIGHT, SUIT.DIAMOND));
		h.AddCardToHand(new Card(RANK.SEVEN, SUIT.CLUB));
		h.AddCardToHand(new Card(RANK.SIX, SUIT.SPADE));
		h.AddCardToHand(new Card(RANK.FIVE, SUIT.CLUB));
		h.AddCardToHand(new Card(RANK.FOUR, SUIT.HEART));
		h.EvaluateHand();
		
		assertEquals(HANDTYPE.STRAIGHT.getHANDTYPE(), h.getHandStrength());
		
	}
	
	@Test
	public void TestEvalHandFLUSH() {
		Hand h = new Hand();
		h.AddCardToHand(new Card(RANK.NINE, SUIT.DIAMOND));
		h.AddCardToHand(new Card(RANK.SIX, SUIT.DIAMOND));
		h.AddCardToHand(new Card(RANK.FOUR, SUIT.DIAMOND));
		h.AddCardToHand(new Card(RANK.THREE, SUIT.DIAMOND));
		h.AddCardToHand(new Card(RANK.TWO, SUIT.DIAMOND));
		h.EvaluateHand();
		
		assertEquals(HANDTYPE.FLUSH.getHANDTYPE(), h.getHandStrength());
		
	}
	
	@Test
	public void TestEvalHandFULLHOUSE() {
		Hand h = new Hand();
		h.AddCardToHand(new Card(RANK.EIGHT, SUIT.DIAMOND));
		h.AddCardToHand(new Card(RANK.EIGHT, SUIT.HEART));
		h.AddCardToHand(new Card(RANK.THREE, SUIT.DIAMOND));
		h.AddCardToHand(new Card(RANK.THREE, SUIT.CLUB));
		h.AddCardToHand(new Card(RANK.THREE, SUIT.SPADE));
		h.EvaluateHand();
		
		assertEquals(HANDTYPE.FULLHOUSE.getHANDTYPE(), h.getHandStrength());
		
	}
	
	@Test
	public void TestEvalHandSTRAIGHTFLUSH() {
		Hand h = new Hand();
		h.AddCardToHand(new Card(RANK.NINE, SUIT.HEART));
		h.AddCardToHand(new Card(RANK.EIGHT, SUIT.HEART));
		h.AddCardToHand(new Card(RANK.SEVEN, SUIT.HEART));
		h.AddCardToHand(new Card(RANK.SIX, SUIT.HEART));
		h.AddCardToHand(new Card(RANK.FIVE, SUIT.HEART));
		h.EvaluateHand();
		
		assertEquals(HANDTYPE.STRAIGHTFLUSH.getHANDTYPE(), h.getHandStrength());
		
	}
	
	@Test
	public void TestEvalHandHIGHCARD() {
		Hand h = new Hand();
		h.AddCardToHand(new Card(RANK.JACK, SUIT.HEART));
		h.AddCardToHand(new Card(RANK.EIGHT, SUIT.SPADE));
		h.AddCardToHand(new Card(RANK.SEVEN, SUIT.DIAMOND));
		h.AddCardToHand(new Card(RANK.THREE, SUIT.HEART));
		h.AddCardToHand(new Card(RANK.TWO, SUIT.CLUB));
		h.EvaluateHand();
		
		assertEquals(HANDTYPE.HIGHCARD.getHANDTYPE(), h.getHandStrength());
		
	}
}
