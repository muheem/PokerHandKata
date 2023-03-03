package com.techrunners;

public class Dealer {
    DeckOfCards pack = new DeckOfCards();
    public static final int NUMBER_OF_CARDS_IN_A_HAND = 5;

    public PlayingCard dealACard() {
        return pack.deal();
    }
}
