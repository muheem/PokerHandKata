package com.techrunners;

public class HighCard implements BestHand {

    int highestCardIndex = 1;
    Card[] hand = new Card[Game.NUMBER_OF_CARDS_IN_A_HAND];

    public Hand.MyHand show(){
        return Hand.MyHand.HIGH_CARD;
    }

    public void calculate(Card[] card, char[] rank) {
        hand = Straight.inSequence(card);

        char highest = ' ';
        int index = 0;
        for (int i = 0; i < 5; i++) {
            // 23456789JQK1
            // Ascii-wise 1, smallest but highest, and King < Queen/
            if (rank[i] == 'A')
                highestCardIndex =  i;
            if (rank[i] == 'K')
                highestCardIndex =  i;
            if (highest < rank[i]) {
                highest = rank[i];
                highestCardIndex = i;
            }
        }
    }

}
