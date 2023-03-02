package com.techrunners;

public class PokerPlayer {
    static final int NUMBER_OF_CARDS_IN_A_HAND = 5;
    PlayingCard[] hand = new PlayingCard[NUMBER_OF_CARDS_IN_A_HAND];
    int count = 0;
    private String name;
    PokerPlayer(String name) {
        this.name = name;
    }
    String name() {
        return name;
    }
    void hitMe(PlayingCard card) {
        hand[count++] = card;
    }

    public PlayingCard[]  hand() {
        return hand;
    }
}
