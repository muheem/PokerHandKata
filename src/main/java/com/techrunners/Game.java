package com.techrunners;

import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;


public class Game {
    public static final int NUMBER_OF_CARDS_IN_A_HAND = 5;

    Player player1;
    Player player2;


    public static boolean checkforAStraight(String rank) {
        String straight = "A23456789TJQKA";
        int[] intA = new int[rank.length()];
        char[] rankArray = rank.toCharArray();

        for (int j = 0; j < rankArray.length; j++) {
            for (int i = 0; i < straight.length(); i++) {
                if (rank.charAt(j) == straight.charAt(i))
                    intA[j] = i;
            }
        }
        Arrays.sort(intA);

        // Reform string in order.
        for (int i = 0; i < rank.length(); i++) {
            rankArray[i] = straight.charAt(intA[i]);
        }

        String sortedRank = String.valueOf(rankArray);

        return straight.contains(sortedRank);
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
            return player1.winningMessage();
        else if (hand1.ordinal < hand2.ordinal)
            return player2.winningMessage();
        else if (hand1.type == Hand.WinType.Sequence) {
            // same hand, but one is probably higher value.
            return compareHighestCards();
        } else if (hand1.type == Hand.WinType.Multiples) {
            // same hand, but one is probably higher value.
            return compareHighestCards();
        }


        if (hand1 == Hand.BEST_HAND.HIGHEST_CARD) {
            char hc1 = player1.hand.highestCard;
            char hc2 = player2.hand.highestCard;

            // compare high cards.
            if ( hc1 == hc2 )
                return (compareHighestCards());

            if (hc1 =='A' )
                return player1.winningMessage();
            if (hc2 =='A' )
                return player2.winningMessage();
            if (hc1 > hc2)
                return player1.winningMessage();
            if (hc1 < hc2)
                return player2.winningMessage();
        }
        return "Tie";
    }

    private String compareHighestCards() {
        Card[] oH1 = player1.hand.orderedHand;
        Card[] oH2 = player2.hand.orderedHand;

        boolean winner = false;
        for (int i = oH1.length - 1; i >=0  ; i--) {
            if (oH1[i].getRank().ordinal > oH2[i].getRank().ordinal) {
                player1.setWinningCardValue(oH1[i].getRank().label);
                return player1.winningMessage();
            } else if (oH1[i].getRank().ordinal < oH2[i].getRank().ordinal) {
                player2.setWinningCardValue(oH2[i].getRank().label);
                return player2.winningMessage();
            }
        }
        return "Tie";
    }
}
