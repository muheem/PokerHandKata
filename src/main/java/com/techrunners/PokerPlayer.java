package com.techrunners;

import java.util.Map;

public class PokerPlayer {
    public PokerHand hand = new PokerHand();


    int count = 0;
    private String name;

    boolean sameSuit = false;
    public String highestCard = "";

    public boolean straight = false;

    public PokerGame.BEST_HAND myHand;

    public Map<Character, Long> groupedRank;
    public Map<Character, Long> groupedSuit;

    PokerPlayer(String name) {
        this.name = name;
    }
    String name() {
        return name;
    }

    public String showHand() {
        return hand.showHand();
    }
    public String[] hand() {
        return hand.getHand();
    }
    public void setHand(String[] cards) {
            hand.setHand(cards);
    }

    PokerGame.BEST_HAND myHand() {

        // Don't consider ROYAL_FLUSH for now

        if (straight && sameSuit) {
            myHand = PokerGame.BEST_HAND.STRAIGHT_FLUSH;
            return myHand;
        }

        if (straight) {
            myHand = PokerGame.BEST_HAND.A_STRAIGHT;
            return myHand;
        }

        if (sameSuit) {
            myHand = PokerGame.BEST_HAND.A_FLUSH;
            return myHand;
        }

        int three_of_a_kind = 0;
        int two_of_a_kind = 0;
        for ( Map.Entry<Character, Long> entry : groupedRank.entrySet()) {
            Character key = entry.getKey();
            Long value = entry.getValue();
            if (value == 4) {
                myHand = PokerGame.BEST_HAND.FOUR_OF_A_KIND;
                return myHand;
            }
            if (value == 3)
                three_of_a_kind++;
            if (value == 2)
                two_of_a_kind++;
        }

        if (three_of_a_kind == 1 && two_of_a_kind == 1 ) {
            myHand = PokerGame.BEST_HAND.FULL_HOUSE;
            return myHand;
        }
        if (three_of_a_kind > 0) {
            myHand = PokerGame.BEST_HAND.THREE_OF_A_KIND;
            return myHand;
        }
        if (two_of_a_kind > 0) {
            myHand = two_of_a_kind == 2 ? PokerGame.BEST_HAND.TWO_PAIR : PokerGame.BEST_HAND.A_PAIR;
            return myHand;
        }
        // Finally what is left, highest card
        return PokerGame.BEST_HAND.HIGHEST_CARD;
    }

}
