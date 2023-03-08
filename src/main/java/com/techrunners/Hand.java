package com.techrunners;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Hand {
    private  String[] hand = new String[Game.NUMBER_OF_CARDS_IN_A_HAND];

    boolean sameSuit = false;
    public String highestCard = "";

    public boolean straight = false;

    public BEST_HAND myHand = null;

    public Map<Character, Long> groupedRank;
    public Map<Character, Long> groupedSuit;

    public enum BEST_HAND {
        HIGHEST_CARD(1,"highest card") ,
        A_PAIR(2,"a pair"), // Check the rank number
        TWO_PAIR(3,"two pair"), // rank number and another PAIR
        THREE_OF_A_KIND(4,"three of a Kind"), // rank number and PAIR
        A_STRAIGHT(5,"a straight"), //  sequence in rank and suit
        A_FLUSH(6,"a flush"), //
        FULL_HOUSE(7,"full house"),
        FOUR_OF_A_KIND(8,"four of a kind"),
        STRAIGHT_FLUSH(9,"straight flush"),
        ROYAL_FLUSH(10,"royaL fLush");

        public final int ordinal;
        public final String label;

        String name;

        private BEST_HAND(int ordinal, String label) {
            this.ordinal = ordinal;
            this.label = label;
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
        for(String val:hand) {
            rank += val.charAt(0);
            suit += val.charAt(1);
        }

        char[] rankA = rank.toCharArray();
        int i = Game.GetHighestCard(rankA);
        highestCard = hand[i];

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
        straight =  Game.checkforAStraight(rankA);
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
                myHand = Hand.BEST_HAND.FOUR_OF_A_KIND;
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

