package PokerEngineAM;

public class Play {
	

	public static void main(String[] args) {
		Deck deck = new Deck();
		Hand hand = new Hand(deck);
		hand.EvaluateHand();
	}
}
