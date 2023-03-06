package com.techrunners;


public class PokerDealer extends Dealer{

    public PlayingCard dealACard() {
        PlayingCard card = pack.deal();
        return card;
    }
    void dealARound(PokerPlayer p1, PokerPlayer p2){
        PokerHand hand1 = p1.hand;
        PokerHand hand2 = p2.hand;
    }

}
