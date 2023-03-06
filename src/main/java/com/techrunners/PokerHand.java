package com.techrunners;

public class PokerHand {
    private  String[] hand = new String[Dealer.NUMBER_OF_CARDS_IN_A_HAND];

    public int index = 0;

    PokerHand(){
    }
    PokerHand(String[] cards){
        hand = cards;
        index = cards.length;
    }
    public String showHand(){
        return String.join(" ", hand);
    }
    public String[] getHand(){
        return hand;
    }

    public void setHand(String[] cards) {
        hand = cards;
        index = cards.length;
    }

}
