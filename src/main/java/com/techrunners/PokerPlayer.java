package com.techrunners;

public class PokerPlayer {
    PlayingCard[] hand = new PlayingCard[Dealer.NUMBER_OF_CARDS_IN_A_HAND];
    int count = 0;
    private String name;
    PokerPlayer(String name) {
        this.name = name;
    }
    String name() {
        return name;
    }
    void hitMe(PlayingCard card) {
        if (count == Dealer.NUMBER_OF_CARDS_IN_A_HAND)
            return; // no more cards needed,
        hand[count++] = card;
    }

    public PlayingCard[] hand() {
        return hand;
    }

    public String showHand() {
        return joinWithSpace(hand);
    }

    public static String joinWithSpace(PlayingCard[] arrayOfCards){
        String str = "";
        for ( PlayingCard card : arrayOfCards) {
            str += card.getCard() + " ";
        }
        return str;
    }
}
