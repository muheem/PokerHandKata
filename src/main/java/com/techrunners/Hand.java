package com.techrunners;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Hand {
    private String[] hand = new String[Game.NUMBER_OF_CARDS_IN_A_HAND];
    public Card[] fiveCards = new Card[Game.NUMBER_OF_CARDS_IN_A_HAND];
    public Card[] orderedHand = new Card[Game.NUMBER_OF_CARDS_IN_A_HAND];

    private boolean sameSuit = false;
    public char highestCard;
    private int threeOfAKind = 0;
    private int twoOfAKind = 0;
    private int fourOfAKind = 0;
    public boolean straight = false;
    public BestHand myHand = null;

    public Map<Character, Long> groupedRank;

    public enum WinType {
        Multiples,
        Sequence,
        Same_Suit,
        High_Card;
    };

    public enum BestHand {
        HIGH_CARD(1, "high card:", WinType.High_Card),
        A_PAIR(2, "a pair", WinType.Multiples), // Check the rank number
        TWO_PAIR(3, "two pair", WinType.Multiples), // rank number and another PAIR
        THREE_OF_A_KIND(4, "three of a kind", WinType.Multiples), // rank number and PAIR
        A_STRAIGHT(5, "a straight", WinType.Sequence), //  sequence in rank and suit
        A_FLUSH(6, "a flush", WinType.Same_Suit), //
        FULL_HOUSE(7, "full house", WinType.Multiples),
        FOUR_OF_A_KIND(8, "four of a kind", WinType.Multiples),
        STRAIGHT_FLUSH(9, "a straight flush", WinType.Sequence),
        ROYAL_FLUSH(10, "a royaL fLush", WinType.Sequence);

        public final int ordinal;
        public final String label;
        public final WinType type;

        BestHand(int ordinal, String label, WinType type) {
            this.ordinal = ordinal;
            this.label = label;
            this.type = type;
        }
    };

    public int index = 0;

    Hand() {
    }

    Hand(String[] cards) {
        hand = cards;
        index = cards.length;
    }

    public String showHand() {
        return String.join(" ", hand);
    }

    public String[] getHand() {
        return hand;
    }

    public void setHand(String[] cards) {
        hand = cards;
        index = cards.length;
        calculate();
        workOutBestHand();
    }

    private void calculate() {
        char[] rank = new char[Game.NUMBER_OF_CARDS_IN_A_HAND];
        String suit = "";

        // Split rank and suite
        for (int i = 0; i < Game.NUMBER_OF_CARDS_IN_A_HAND; i++) {
            fiveCards[i] = new Card(hand[i]);
            rank[i] += fiveCards[i].getRank().label;
            suit += hand[i].charAt(1);
        }

        // Check for pairs and three-of-a-kind and 4-of-a-kind.
        List<Character> rankL = Game.convertStringToCharList(rank);
        groupedRank
                = rankL.stream().collect(
                Collectors.groupingBy(
                        Function.identity(),
                        Collectors.counting()));

        orderedHand = Arrays.copyOf(fiveCards, fiveCards.length);
        Arrays.sort(orderedHand, Comparator.comparing(Card::getRank));

        // Now decide if we can have a straight.
        if (CalculateSequence() == 0)
            CalculateStraight(rank);

        int i = Game.GetHighestCard(rank);
        highestCard = hand[i].charAt(0);

        // need to know for a flush (which ever type)
        String finalSuit = suit;
        sameSuit = suit.chars().allMatch(c -> c == finalSuit.charAt(0));

        //rank = orderedHand;

        rank[0] =  orderedHand[0].getRank().label;
        rank[1] =  orderedHand[1].getRank().label;
        rank[2] =  orderedHand[2].getRank().label;
        rank[3] =  orderedHand[3].getRank().label;
        rank[4] =  orderedHand[4].getRank().label;

        straight = Game.checkforAStraight(rank);
    }

    private int CalculateSequence() {

        for (Map.Entry<Character, Long> entry : groupedRank.entrySet()) {
            switch (Long.valueOf(entry.getValue()).intValue()) {
                case 2:
                    twoOfAKind++;
                    break;
                case 3:
                    threeOfAKind++ ;
                    break;
                case 4:
                    fourOfAKind = 1;
            }
        }
        return (twoOfAKind + threeOfAKind + fourOfAKind);
    }

    private void CalculateStraight(char[] rank) {
        Card lastCard = orderedHand[Game.NUMBER_OF_CARDS_IN_A_HAND - 1];
        Card.Rank lastCardRank = orderedHand[Game.NUMBER_OF_CARDS_IN_A_HAND - 1].getRank();
        Card.Rank nextToLastCardRank = orderedHand[Game.NUMBER_OF_CARDS_IN_A_HAND - 2].getRank();

        if (lastCardRank == Card.Rank.ACE && nextToLastCardRank != Card.Rank.KING) {
            // Move the ace from the end to the start of the hand.
            char[] rank2 = new char[Game.NUMBER_OF_CARDS_IN_A_HAND];
            rank2[0] =  orderedHand[4].getRank().label;
            rank2[1] =  orderedHand[0].getRank().label;
            rank2[2] =  orderedHand[1].getRank().label;
            rank2[3] =  orderedHand[2].getRank().label;
            rank2[4] =  orderedHand[3].getRank().label;
            if (Game.checkforAStraight(rank2)) {
                rank = rank2.clone();
                Card one = new Card(Card.Rank.ONE, lastCard.getSuit());
                orderedHand[Game.NUMBER_OF_CARDS_IN_A_HAND - 1] = one;
                Arrays.sort(orderedHand, Comparator.comparing(Card::getRank));
            }
        }
    }

    public void workOutBestHand() {

        // Don't consider ROYAL_FLUSH for now
        if (straight && sameSuit) {
            myHand = BestHand.STRAIGHT_FLUSH;
            return;
        }

        if (fourOfAKind == 1) {
            myHand = BestHand.FOUR_OF_A_KIND;
            return;
        }

        if (threeOfAKind == 1 && twoOfAKind == 1) {
            myHand = BestHand.FULL_HOUSE;
            return;
        }

        if (sameSuit) {
            myHand = BestHand.A_FLUSH;
            return;
        }

        if (straight) {
            myHand = BestHand.A_STRAIGHT;
            return;
        }

        if (threeOfAKind > 0) {
            myHand = BestHand.THREE_OF_A_KIND;
            return;
        }
        if (twoOfAKind > 0) {
            myHand = twoOfAKind == 2 ? BestHand.TWO_PAIR : BestHand.A_PAIR;
            return;
        }
        // Finally what is left, highest card
        myHand = BestHand.HIGH_CARD;
    }
}