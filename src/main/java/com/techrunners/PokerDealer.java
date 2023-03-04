package com.techrunners;


public class PokerDealer extends Dealer{
    PlayingCard[] hand1 = new PlayingCard[NUMBER_OF_CARDS_IN_A_HAND];
    PlayingCard[] hand2 = new PlayingCard[NUMBER_OF_CARDS_IN_A_HAND];

    public PlayingCard dealACard() {
        PlayingCard card = pack.deal();
        return card;
    }
    void dealARound(PokerPlayer p1, PokerPlayer p2){
        fillHands();
        p1.myHand(hand1);
        p2.myHand(hand2);
    }

    void fillHands(){
        for (int i = 0; i < NUMBER_OF_CARDS_IN_A_HAND; i++ ) {
            hand1[i] = pack.deal();
            hand2[i] = pack.deal();
        }
    }
}
