package com.techrunners;


public class PokerDealer extends Dealer{
    public PlayingCard dealACard() {
        return pack.deal();
    }
}
