package com.techrunners;

import java.util.ArrayList;
import java.util.List;


public class Game {
    public static final int NUMBER_OF_CARDS_IN_A_HAND = 5;

    Player player1;
    Player player2;


    public static boolean checkforAStraight(char[] rank) {
        String straight = "A23456789TJQKA";
        String sortedRank = String.valueOf(rank);
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
    public static List<Character> convertStringToCharList(char[] array) {

        List<Character> listArray = new ArrayList<>();
        for (char c : array)
            listArray.add(c);

        // return the List
        return listArray;
    }

    Game(Player player1, Player player2) {
        this.player1 = player1;
        this.player2 = player2;
    }

    public String playTheGame() {
        return workOutTheWinningHand();
    }

    public String workOutTheWinningHand() {
        Hand.BestHand hand1 = player1.hand.myHand;
        Hand.BestHand hand2 = player2.hand.myHand;

        if (hand1.ordinal > hand2.ordinal)
            return player1.winningMessage(player2.hand);
        else if (hand1.ordinal < hand2.ordinal)
            return player2.winningMessage(player1.hand);
        else if (hand1.type == Hand.WinType.Sequence)
            // same hand, but one is probably higher value.
            return compareHighestCards();
        else if (hand1.type == Hand.WinType.Multiples)
            // same hand, but one is probably higher value.
            return compareHighestCards();
        else if (hand1.type == Hand.WinType.Same_Suit)
            // same hand, but one is probably higher value.
            return compareHighestCards();

        if (hand1 == Hand.BestHand.HIGH_CARD)
            return HandleHighCard();

        return "Tie";
    }

    private String HandleHighCard() {
        char hc1 = player1.hand.highestCard;
        char hc2 = player2.hand.highestCard;

        if ( hc1 == hc2 )
            return (compareHighestCards());
        if (hc1 =='A' )
            return player1.winningMessage(player2.hand);
        if (hc2 =='A' )
            return player2.winningMessage(player1.hand);
        if (hc1 > hc2)
            return player1.winningMessage(player2.hand);
        if (hc1 < hc2)
            return player2.winningMessage(player1.hand);
        return "Tie";
    }

    private String compareHighestCards() {
        Card[] oH1 = player1.hand.orderedHand;
        Card[] oH2 = player2.hand.orderedHand;

        boolean winner = false;
        for (int i = oH1.length - 1; i >= 0  ; i--) {
            if (oH1[i].getRank().ordinal > oH2[i].getRank().ordinal) {
                player1.setWinningCardValue(oH1[i].getRank().label);
                player2.hand.highestCard = oH2[i].getRank().label;
                return player1.winningMessage(player2.hand);
            }
            else if (oH1[i].getRank().ordinal < oH2[i].getRank().ordinal) {
                player2.setWinningCardValue(oH2[i].getRank().label);
                player1.hand.highestCard = oH1[i].getRank().label;
                return player2.winningMessage(player1.hand);
            }
        }
        return "Tie";
    }
}
