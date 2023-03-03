package com.techrunners;

abstract class Dealer {
    DeckOfCards pack = new DeckOfCards();
    public static final int NUMBER_OF_CARDS_IN_A_HAND = 5;
    abstract PlayingCard dealACard();

}
