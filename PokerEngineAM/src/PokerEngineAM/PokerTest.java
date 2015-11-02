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
	public void TestRoyalFlush() {
		Hand h = new Hand();
		h.AddCardToHand(new Card(RANK.ACE, SUIT.DIAMOND));
		h.AddCardToHand(new Card(RANK.KING, SUIT.DIAMOND));
		h.AddCardToHand(new Card(RANK.QUEEN, SUIT.DIAMOND));
		h.AddCardToHand(new Card(RANK.JACK, SUIT.DIAMOND));
		h.AddCardToHand(new Card(RANK.TEN, SUIT.DIAMOND));
		h.EvaluateHand();
		
		assertEquals(h.getHandStrength(), HANDTYPE.ROYALFLUSH.getHANDTYPE());
		assertEquals(h.getHighPairStrength(), 0);
		assertEquals(h.getLowPairStrength(), 0);
		assertEquals(h.getKicker(), 0);
		
	}
	
/*	@Test
	public void TestJokerRoyalFlush() {
		Hand h = new Hand();
		h.AddCardToHand(new Card(RANK.ACE, SUIT.DIAMOND));
		h.AddCardToHand(new Card(RANK.KING, SUIT.DIAMOND));
		h.AddCardToHand(new Card(RANK.QUEEN, SUIT.DIAMOND));
		h.AddCardToHand(new Card(RANK.JOKER, SUIT.JOKER));
		h.AddCardToHand(new Card(RANK.TEN, SUIT.DIAMOND));
		h.EvaluateHand();
		
		assertEquals(h.getHandStrength(), HANDTYPE.JOKERROYALFLUSH.getHANDTYPE());
		assertEquals(h.getHighPairStrength(), 0);
		assertEquals(h.getLowPairStrength(), 0);
		assertEquals(h.getKicker(), 0);
		
	}
*/	
	@Test
	public void TestFiveKind() {
		Hand h = new Hand();
		h.AddCardToHand(new Card(RANK.JOKER, SUIT.DIAMOND));
		h.AddCardToHand(new Card(RANK.FOUR, SUIT.DIAMOND));
		h.AddCardToHand(new Card(RANK.FOUR, SUIT.CLUB));
		h.AddCardToHand(new Card(RANK.FOUR, SUIT.JOKER));
		h.AddCardToHand(new Card(RANK.FOUR, SUIT.SPADE));
		h.EvaluateHand();
		
		assertEquals(h.getHandStrength(), HANDTYPE.FIVEKIND.getHANDTYPE());
		assertEquals(h.getHighPairStrength(), RANK.JOKER.getRank());
		assertEquals(h.getLowPairStrength(), 0);
		assertEquals(h.getKicker(), 0);
		
	}
	
	@Test
	public void TestStraightFlush() {
		Hand h = new Hand();
		h.AddCardToHand(new Card(RANK.NINE, SUIT.HEART));
		h.AddCardToHand(new Card(RANK.EIGHT, SUIT.HEART));
		h.AddCardToHand(new Card(RANK.SEVEN, SUIT.HEART));
		h.AddCardToHand(new Card(RANK.SIX, SUIT.HEART));
		h.AddCardToHand(new Card(RANK.FIVE, SUIT.HEART));
		h.EvaluateHand();
		
		assertEquals(h.getHandStrength(), HANDTYPE.STRAIGHTFLUSH.getHANDTYPE());
		assertEquals(h.getHighPairStrength(), RANK.NINE.getRank());
		assertEquals(h.getLowPairStrength(), 0);
		assertEquals(h.getKicker(), 0);
		
	}
	
	@Test
	public void TestStraight() {
		Hand h = new Hand();
		h.AddCardToHand(new Card(RANK.EIGHT, SUIT.DIAMOND));
		h.AddCardToHand(new Card(RANK.SEVEN, SUIT.CLUB));
		h.AddCardToHand(new Card(RANK.SIX, SUIT.SPADE));
		h.AddCardToHand(new Card(RANK.FIVE, SUIT.CLUB));
		h.AddCardToHand(new Card(RANK.FOUR, SUIT.HEART));
		h.EvaluateHand();
		
		assertEquals(h.getHandStrength(), HANDTYPE.STRAIGHT.getHANDTYPE());
		assertEquals(h.getHighPairStrength(), RANK.EIGHT.getRank());
		assertEquals(h.getLowPairStrength(), 0);
		assertEquals(h.getKicker(), 0);
		
	}
	
	@Test
	public void TestFlush() {
		Hand h = new Hand();
		h.AddCardToHand(new Card(RANK.NINE, SUIT.DIAMOND));
		h.AddCardToHand(new Card(RANK.SIX, SUIT.DIAMOND));
		h.AddCardToHand(new Card(RANK.FOUR, SUIT.DIAMOND));
		h.AddCardToHand(new Card(RANK.THREE, SUIT.DIAMOND));
		h.AddCardToHand(new Card(RANK.TWO, SUIT.DIAMOND));
		h.EvaluateHand();
		
		assertEquals(h.getHandStrength(), HANDTYPE.FLUSH.getHANDTYPE());
		assertEquals(h.getHighPairStrength(), RANK.NINE.getRank());
		assertEquals(h.getLowPairStrength(), 0);
		assertEquals(h.getKicker(), 0);
		
	}
	
	@Test
	public void FourOfAKind1() {
		Hand h = new Hand();
		h.AddCardToHand(new Card(RANK.FIVE, SUIT.CLUB));
		h.AddCardToHand(new Card(RANK.FIVE, SUIT.DIAMOND));
		h.AddCardToHand(new Card(RANK.FIVE, SUIT.HEART));
		h.AddCardToHand(new Card(RANK.FIVE, SUIT.SPADE));
		h.AddCardToHand(new Card(RANK.EIGHT, SUIT.SPADE));
		h.EvaluateHand();
		
		assertEquals(h.getHandStrength(), HANDTYPE.FOURKIND.getHANDTYPE());
		assertEquals(h.getHighPairStrength(), RANK.FIVE.getRank());
		assertEquals(h.getLowPairStrength(), 0);
		assertEquals(h.getKicker(), RANK.EIGHT.getRank());
		
	}	
	
	@Test
	public void FourOfAKind2() {
		Deck d = new Deck();
		Hand h = new Hand();
		h.AddCardToHand(new Card(RANK.QUEEN, SUIT.CLUB));
		h.AddCardToHand(new Card(RANK.JACK, SUIT.DIAMOND));
		h.AddCardToHand(new Card(RANK.JACK, SUIT.HEART));
		h.AddCardToHand(new Card(RANK.JACK, SUIT.SPADE));
		h.AddCardToHand(new Card(RANK.JACK, SUIT.CLUB));
		h.EvaluateHand();
		
		assertEquals(h.getHandStrength(), HANDTYPE.FOURKIND.getHANDTYPE());
		assertEquals(h.getHighPairStrength(), RANK.JACK.getRank());
		assertEquals(h.getLowPairStrength(), 0);
		assertEquals(h.getKicker(), RANK.QUEEN.getRank());
		
	}
	
	@Test
	public void TestFullHouse1() {
		Hand h = new Hand();
		h.AddCardToHand(new Card(RANK.EIGHT, SUIT.DIAMOND));
		h.AddCardToHand(new Card(RANK.EIGHT, SUIT.HEART));
		h.AddCardToHand(new Card(RANK.THREE, SUIT.DIAMOND));
		h.AddCardToHand(new Card(RANK.THREE, SUIT.CLUB));
		h.AddCardToHand(new Card(RANK.THREE, SUIT.SPADE));
		h.EvaluateHand();
		
		assertEquals(h.getHandStrength(), HANDTYPE.FULLHOUSE.getHANDTYPE());
		assertEquals(h.getHighPairStrength(), RANK.THREE.getRank());
		assertEquals(h.getLowPairStrength(), RANK.EIGHT.getRank());
		assertEquals(h.getKicker(), 0);
		
	}
	
	@Test
	public void TestFullHouse2() {
		Hand h = new Hand();
		h.AddCardToHand(new Card(RANK.EIGHT, SUIT.DIAMOND));
		h.AddCardToHand(new Card(RANK.EIGHT, SUIT.HEART));
		h.AddCardToHand(new Card(RANK.EIGHT, SUIT.CLUB));
		h.AddCardToHand(new Card(RANK.THREE, SUIT.CLUB));
		h.AddCardToHand(new Card(RANK.THREE, SUIT.SPADE));
		h.EvaluateHand();
		
		assertEquals(h.getHandStrength(), HANDTYPE.FULLHOUSE.getHANDTYPE());
		assertEquals(h.getHighPairStrength(), RANK.EIGHT.getRank());
		assertEquals(h.getLowPairStrength(), RANK.THREE.getRank());
		assertEquals(h.getKicker(), 0);
		
		
	}
	
	@Test
	public void TwoPair1() {
		Hand h = new Hand();
		h.AddCardToHand(new Card(RANK.EIGHT, SUIT.DIAMOND));
		h.AddCardToHand(new Card(RANK.EIGHT, SUIT.HEART));
		h.AddCardToHand(new Card(RANK.SEVEN, SUIT.CLUB));
		h.AddCardToHand(new Card(RANK.SEVEN, SUIT.HEART));
		h.AddCardToHand(new Card(RANK.THREE, SUIT.SPADE));
		h.EvaluateHand();
		
		assertEquals(h.getHandStrength(), HANDTYPE.TWOPAIR.getHANDTYPE());
		assertEquals(h.getHighPairStrength(), RANK.EIGHT.getRank());
		assertEquals(h.getLowPairStrength(), RANK.SEVEN.getRank());
		assertEquals(h.getKicker(), RANK.THREE.getRank());
		
		
	}
	
	@Test
	public void TwoPair2() {
		Hand h = new Hand();
		h.AddCardToHand(new Card(RANK.JACK, SUIT.DIAMOND));
		h.AddCardToHand(new Card(RANK.TEN, SUIT.HEART));
		h.AddCardToHand(new Card(RANK.TEN, SUIT.CLUB));
		h.AddCardToHand(new Card(RANK.THREE, SUIT.CLUB));
		h.AddCardToHand(new Card(RANK.THREE, SUIT.SPADE));
		h.EvaluateHand();
		
		assertEquals(h.getHandStrength(), HANDTYPE.TWOPAIR.getHANDTYPE());
		assertEquals(h.getHighPairStrength(), RANK.TEN.getRank());
		assertEquals(h.getLowPairStrength(), RANK.THREE.getRank());
		assertEquals(h.getKicker(), RANK.JACK.getRank());
		
		
	}
	
	@Test
	public void Pair1() {
		Hand h = new Hand();
		h.AddCardToHand(new Card(RANK.JACK, SUIT.DIAMOND));
		h.AddCardToHand(new Card(RANK.JACK, SUIT.HEART));
		h.AddCardToHand(new Card(RANK.TEN, SUIT.CLUB));
		h.AddCardToHand(new Card(RANK.THREE, SUIT.CLUB));
		h.AddCardToHand(new Card(RANK.TWO, SUIT.SPADE));
		h.EvaluateHand();
		
		assertEquals(h.getHandStrength(), HANDTYPE.PAIR.getHANDTYPE());
		assertEquals(h.getHighPairStrength(), RANK.JACK.getRank());
		assertEquals(h.getLowPairStrength(), 0);
		assertEquals(h.getKicker(), RANK.TEN.getRank());
		
		
	}
	
	@Test
	public void Pair2() {
		Hand h = new Hand();
		h.AddCardToHand(new Card(RANK.QUEEN, SUIT.DIAMOND));
		h.AddCardToHand(new Card(RANK.TEN, SUIT.HEART));
		h.AddCardToHand(new Card(RANK.TEN, SUIT.CLUB));
		h.AddCardToHand(new Card(RANK.THREE, SUIT.CLUB));
		h.AddCardToHand(new Card(RANK.TWO, SUIT.SPADE));
		h.EvaluateHand();
		
		assertEquals(h.getHandStrength(), HANDTYPE.PAIR.getHANDTYPE());
		assertEquals(h.getHighPairStrength(), RANK.TEN.getRank());
		assertEquals(h.getLowPairStrength(), 0);
		assertEquals(h.getKicker(), RANK.QUEEN.getRank());
		
		
	}
	
	@Test
	public void Pair3() {
		Hand h = new Hand();
		h.AddCardToHand(new Card(RANK.QUEEN, SUIT.DIAMOND));
		h.AddCardToHand(new Card(RANK.JACK, SUIT.HEART));
		h.AddCardToHand(new Card(RANK.TEN, SUIT.CLUB));
		h.AddCardToHand(new Card(RANK.THREE, SUIT.CLUB));
		h.AddCardToHand(new Card(RANK.THREE, SUIT.SPADE));
		h.EvaluateHand();
		
		assertEquals(h.getHandStrength(), HANDTYPE.PAIR.getHANDTYPE());
		assertEquals(h.getHighPairStrength(), RANK.THREE.getRank());
		assertEquals(h.getLowPairStrength(), 0);
		assertEquals(h.getKicker(), RANK.QUEEN.getRank());
		
		
	}
	
	@Test
	public void TestHighCard() {
		Hand h = new Hand();
		h.AddCardToHand(new Card(RANK.JACK, SUIT.HEART));
		h.AddCardToHand(new Card(RANK.EIGHT, SUIT.SPADE));
		h.AddCardToHand(new Card(RANK.SEVEN, SUIT.DIAMOND));
		h.AddCardToHand(new Card(RANK.THREE, SUIT.HEART));
		h.AddCardToHand(new Card(RANK.TWO, SUIT.CLUB));
		h.EvaluateHand();
		
		assertEquals(h.getHandStrength(), HANDTYPE.HIGHCARD.getHANDTYPE());
		assertEquals(h.getHighPairStrength(), RANK.JACK.getRank());
		assertEquals(h.getLowPairStrength(), 0);
		assertEquals(h.getKicker(), RANK.EIGHT.getRank());
		
		
	}
}
