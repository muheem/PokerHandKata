package com.techrunners;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Hand {
    private String[] hand = new String[Game.NUMBER_OF_CARDS_IN_A_HAND];
    public Card[] fiveCards = new Card[Game.NUMBER_OF_CARDS_IN_A_HAND];

    boolean sameSuit = false;
    public char highestCard;

    public boolean straight = false;

    public BEST_HAND myHand = null;
    public Card[] orderedHand = new Card[Game.NUMBER_OF_CARDS_IN_A_HAND];

    public Map<Character, Long> groupedRank;
    public Map<Character, Long> groupedSuit;
    public enum WinType {
        Multiples,
        Sequence,
        Same_Suit,
        High_Card;
    };

    public enum BEST_HAND {
        HIGHEST_CARD(1,"high card:", WinType.High_Card) ,
        A_PAIR(2,"a pair", WinType.Multiples), // Check the rank number
        TWO_PAIR(3,"two pair", WinType.Multiples), // rank number and another PAIR
        THREE_OF_A_KIND(4,"three of a Kind", WinType.Multiples), // rank number and PAIR
        A_STRAIGHT(5,"a straight", WinType.Sequence), //  sequence in rank and suit
        A_FLUSH(6,"a flush", WinType.Same_Suit), //
        FULL_HOUSE(7,"full house", WinType.Multiples),
        FOUR_OF_A_KIND(8,"four of a kind", WinType.Sequence),
        STRAIGHT_FLUSH(9,"a straight flush", WinType.Sequence),
        ROYAL_FLUSH(10,"a royaL fLush", WinType.Sequence);

        public final int ordinal;
        public final String label;
        public final WinType type;

        String name;

        private BEST_HAND(int ordinal, String label, WinType type) {
            this.ordinal = ordinal;
            this.label = label;
            this.type = type;
        }
    };

    public int index = 0;

    Hand(){
    }
    Hand(String[] cards){
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
        calculate();
        workOutBestHand();
    }

    private void calculate() {
        String rank = "";
        String suit = "";

        // Split rank ans suite
        for (int i=0; i < Game.NUMBER_OF_CARDS_IN_A_HAND; i++) {
            fiveCards[i] = new Card(hand[i]);
            rank += hand[i].charAt(0);
            suit += hand[i].charAt(1);
        }
        System.arraycopy(fiveCards, 0, orderedHand, 0, fiveCards.length);

        char[] rankA = rank.toCharArray();
        int i = Game.GetHighestCard(rankA);
        highestCard = hand[i].charAt(0);

        // need to know for a flush (which ever type)
        String finalSuit = suit;
        sameSuit = suit.chars().allMatch(c -> c == finalSuit.charAt(0)) ;

        List<Character> rankS= Game.convertStringToCharList(rank);
        groupedSuit
                = rankS.stream().collect(
                Collectors.groupingBy(
                        Function.identity(),
                        Collectors.counting()));

        // Check for pairs and three-of-a-kind and 4-of-a-kind.
        List<Character> rankL= Game.convertStringToCharList(rank);
        groupedRank
                = rankL.stream().collect(
                Collectors.groupingBy(
                        Function.identity(),
                        Collectors.counting()));

        //if no pair, 3 or 4, then MAYBE its a straight...
        straight =  Game.checkforAStraight(rank);
    }


    public void workOutBestHand() {

        // Don't consider ROYAL_FLUSH for now

        if (straight && sameSuit) {
            myHand = BEST_HAND.STRAIGHT_FLUSH;
            return;
        }

        if (straight) {
            myHand = BEST_HAND.A_STRAIGHT;
            return;
        }

        if (sameSuit) {
            myHand = BEST_HAND.A_FLUSH;
            return;
        }

        int three_of_a_kind = 0;
        int two_of_a_kind = 0;
        for ( Map.Entry<Character, Long> entry : groupedRank.entrySet()) {
            Character key = entry.getKey();
            Long value = entry.getValue();
            if (value == 4) {
                myHand = BEST_HAND.FOUR_OF_A_KIND;
                return;
            }
            if (value == 3)
                three_of_a_kind++;
            if (value == 2)
                two_of_a_kind++;
        }

        if (three_of_a_kind == 1 && two_of_a_kind == 1 ) {
            myHand = BEST_HAND.FULL_HOUSE;
            return;
        }
        if (three_of_a_kind > 0) {
            myHand = BEST_HAND.THREE_OF_A_KIND;
            return;
        }
        if (two_of_a_kind > 0) {
            myHand = two_of_a_kind == 2 ? BEST_HAND.TWO_PAIR : BEST_HAND.A_PAIR;
            return;
        }
        // Finally what is left, highest card
        myHand =  BEST_HAND.HIGHEST_CARD;
    }

    // Find  highest value card.
}

