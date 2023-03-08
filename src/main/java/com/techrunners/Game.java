package com.techrunners;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;


public class Game {
    public static final int NUMBER_OF_CARDS_IN_A_HAND = 5;

    Player player1;
    Player player2;

    private static boolean orderCards(char[] rankA) {
        String straight = "A23456789JQKA";


        return false;
    }

    public static boolean checkHighestCard(char[] rankA) {
        String straight = "A23456789JQKA";
        // order cards
        // pick the last
        return false;
    }

    public static boolean checkforAStraight(char[] rankA) {
        String straight = "A23456789JQKA";
        // order cards
        // check for a sequence.

        return false;
    }

    public static int GetHighestCard(char[] rank) {
        char highest = ' ';
        int index = 0;
        for (int i = 0; i < 5; i++) {
            // 23456789JQK1
            // Ascii-wise 1, smallest but highest, and King < Queen/
            if (rank[i] == 'A')
                return i;
            if (rank[i] == 'K')
                return i;
            if (highest < rank[i]) {
                highest = rank[i];
                index = i;
            }
        }
        return index;
    }

    public static List<Character> convertStringToCharList(String str) {

        // Create an empty List of character
        List<Character> chars = str

                // Convert to String to IntStream
                .chars()

                // Convert IntStream to Stream<Character>
                .mapToObj(e -> (char) e)

                // Collect the elements as a List Of Characters
                .collect(Collectors.toList());

        // return the List
        return chars;
    }


    Game(Player player1, Player player2) {
        this.player1 = player1;
        this.player2 = player2;
    }

    public String playTheGame() {
        return workOutTheWinningHand();
    }

    public String workOutTheWinningHand() {
        Hand.BEST_HAND hand1 = player1.hand.myHand;
        Hand.BEST_HAND hand2 = player2.hand.myHand;

        if (hand1.ordinal > hand2.ordinal)
            return player1.name() + " wins. - with " + hand1.label /*+ " : " + " "*/;
        else if (hand1.ordinal < hand2.ordinal)
            return player2.name() + " wins. - with " + hand2.label;
        else if (hand1 == Hand.BEST_HAND.HIGHEST_CARD) {
            String hc1 = player1.hand.highestCard, hc2 = player2.hand.highestCard;
            // compare cards.
            if (hc1.equals(hc2))
                return "Tie";
            else
                return "Black or White win: " + hc1 + " " + hc2;
        } else
            return "Tie";

    }
}
