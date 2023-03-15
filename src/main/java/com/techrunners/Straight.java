package com.techrunners;

import java.util.Arrays;
import java.util.Comparator;

public class Straight implements BestHand {
    boolean isStraight = false;

    public Hand.MyHand show(){
        if (isStraight)
            return Hand.MyHand.A_STRAIGHT;
        return Hand.MyHand.NULL;
    }

    public void calculate(Card[] card, char[] rank) {

        Card[] orderedHand = new Card[Game.NUMBER_OF_CARDS_IN_A_HAND];
        orderedHand = Arrays.copyOf(card, card.length);
        Arrays.sort(orderedHand, Comparator.comparing(Card::getRank));
        rank[0] =  orderedHand[0].getRank().label;
        rank[1] =  orderedHand[1].getRank().label;
        rank[2] =  orderedHand[2].getRank().label;
        rank[3] =  orderedHand[3].getRank().label;
        rank[4] =  orderedHand[4].getRank().label;


        isStraight = checkforAStraight(rank);

        Card lastCard = orderedHand[Game.NUMBER_OF_CARDS_IN_A_HAND - 1];
        Card.Rank lastCardRank = lastCard.getRank();
        Card.Rank nextToLastCardRank = orderedHand[Game.NUMBER_OF_CARDS_IN_A_HAND - 2].getRank();

        if (!isStraight && lastCardRank == Card.Rank.ACE && nextToLastCardRank != Card.Rank.KING) {
            // Move the ace from the end to the start of the hand.
            char[] rank2 = new char[Game.NUMBER_OF_CARDS_IN_A_HAND];
            rank2[0] = orderedHand[4].getRank().label;
            rank2[1] = orderedHand[0].getRank().label;
            rank2[2] = orderedHand[1].getRank().label;
            rank2[3] = orderedHand[2].getRank().label;
            rank2[4] = orderedHand[3].getRank().label;
            isStraight = checkforAStraight(rank2);
            if (isStraight) {
                rank = rank2.clone();
                Card one = new Card(Card.Rank.ONE, lastCard.getSuit());
                card[Game.NUMBER_OF_CARDS_IN_A_HAND - 1] = one;
                Arrays.sort(card, Comparator.comparing(Card::getRank));
            }
        }
    }

    public static boolean checkforAStraight(char[] rank) {
        String straight = "A23456789TJQKA";
        String sortedRank = String.valueOf(rank);
        return straight.contains(sortedRank);
    }

    static Card[] inSequence(Card[] card) {
        Card lastCard = card[Game.NUMBER_OF_CARDS_IN_A_HAND - 1];
        Card.Rank lastCardRank = card[Game.NUMBER_OF_CARDS_IN_A_HAND - 1].getRank();
        Card.Rank nextToLastCardRank = card[Game.NUMBER_OF_CARDS_IN_A_HAND - 2].getRank();

        Arrays.sort(card, Comparator.comparing(Card::getRank));
        if (lastCardRank == Card.Rank.ACE && nextToLastCardRank != Card.Rank.KING) {
            // Move the ace from the end to the start of the hand.
            char[] rank2 = new char[Game.NUMBER_OF_CARDS_IN_A_HAND];
            rank2[0] = card[4].getRank().label;
            rank2[1] = card[0].getRank().label;
            rank2[2] = card[1].getRank().label;
            rank2[3] = card[2].getRank().label;
            rank2[4] = card[3].getRank().label;
            if (checkforAStraight(rank2)) {
                Card one = new Card(Card.Rank.ONE, lastCard.getSuit());
                card[Game.NUMBER_OF_CARDS_IN_A_HAND - 1] = one;
                Arrays.sort(card, Comparator.comparing(Card::getRank));
            }
        }
        return card;
    }

}
