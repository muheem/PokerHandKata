package com.techrunners;

import java.util.*;

public class Hand {

    private BestHand sameOfAKind = new SameRanking();
    private BestHand straight = new Straight();
    private BestHand highCard = new HighCard();

    private String[] StringHand = new String[Game.NUMBER_OF_CARDS_IN_A_HAND];
    private Card[] fiveCards = new Card[Game.NUMBER_OF_CARDS_IN_A_HAND];

    private boolean sameSuit = false;
    private  char highestCard;

    public PokerHand myHand = null;

    public enum WinType {
        NoHand,
        SameOfAKind,
        Sequence,
        Same_Suit,
        High_Card;
    };

    public enum PokerHand {
        NULL(0, "no hand", WinType.NoHand),
        HIGH_CARD(1, "high card:", WinType.High_Card),
        A_PAIR(2, "a pair", WinType.SameOfAKind), // Check the rank number
        TWO_PAIR(3, "two pair", WinType.SameOfAKind), // rank number and another PAIR
        THREE_OF_A_KIND(4, "three of a kind", WinType.SameOfAKind), // rank number and PAIR
        A_STRAIGHT(5, "a straight", WinType.Sequence), //  sequence in rank and suit
        A_FLUSH(6, "a flush", WinType.Same_Suit), //
        FULL_HOUSE(7, "full house", WinType.SameOfAKind),
        FOUR_OF_A_KIND(8, "four of a kind", WinType.SameOfAKind),
        STRAIGHT_FLUSH(9, "a straight flush", WinType.Sequence),
        ROYAL_FLUSH(10, "a royaL fLush", WinType.Sequence);

        public final int ordinal;
        public final String label;
        public final WinType type;

        PokerHand(int ordinal, String label, WinType type) {
            this.ordinal = ordinal;
            this.label = label;
            this.type = type;
        }
    };

    private String name = " ";

    Hand(String name) {
        this.name = name;
    }

    public void setStringHand(String[] cards) {
        StringHand = cards;
        calculate();
        workOutBestHand();
    }

    public Card[] getHand() {
        return fiveCards;

    }
    public char getHighestCard() {
        return highestCard;
    }
    public void setHighestCard(char card) {
        highestCard = card;
    }
    private void calculate() {
        fiveCards = new Card[Game.NUMBER_OF_CARDS_IN_A_HAND];

        char[] rank = new char[Game.NUMBER_OF_CARDS_IN_A_HAND];
        String suit = "";

        // Split rank and suite
        for (int i = 0; i < Game.NUMBER_OF_CARDS_IN_A_HAND; i++) {
            fiveCards[i] = new Card(StringHand[i]);
            rank[i] += fiveCards[i].getRank().label;
            suit += StringHand[i].charAt(1);
        }

        sameOfAKind.calculate(fiveCards,rank);
        if (sameOfAKind.show() == PokerHand.NULL )
            straight.calculate(fiveCards, rank);
        highCard.calculate(fiveCards, rank);

        // need to know for a flush (which ever type)
        String finalSuit = suit;
        sameSuit = suit.chars().allMatch(c -> c == finalSuit.charAt(0));
    }

    public void workOutBestHand() {

        // Don't consider ROYAL_FLUSH for now
        if (straight.show() != PokerHand.NULL  && sameSuit) {
            myHand = PokerHand.STRAIGHT_FLUSH;
            return;
        }

        PokerHand sameOfAKindType = sameOfAKind.show();
        if (sameOfAKindType == PokerHand.FOUR_OF_A_KIND ) {
            myHand = sameOfAKindType;
            return;
        }

        if (sameOfAKindType == PokerHand.FULL_HOUSE) {
            myHand = sameOfAKindType;
            return;
        }

        if (sameSuit) {
            myHand = PokerHand.A_FLUSH;
            return;
        }

        if (straight.show() != PokerHand.NULL) {
            myHand = PokerHand.A_STRAIGHT;
            return;
        }

        if (sameOfAKindType != PokerHand.NULL) {
            myHand = sameOfAKindType;
            return;
        }

        // Finally what is left, highest card
        myHand = PokerHand.HIGH_CARD;
    }

    public void setWinningCardValue(char value) {
        highestCard = value;
    }

    public String winningMessage(Hand losingHand) {
        if (myHand.type == Hand.WinType.High_Card)
            return name + " wins. - with " + myHand.label + " " + highestCard + " beats " + losingHand.highestCard;
        else
            return name + " wins. - with " + myHand.label;
    }

    public static List<Character> convertStringToCharList(char[] array) {

        List<Character> listArray = new ArrayList<>();
        for (char c : array)
            listArray.add(c);

        // return the List
        return listArray;
    }
}