package com.techrunners;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class PokerGame {


    public enum BEST_HAND {
        A_PAIR, // Check the rank number
        TWO_PAIR, // rank number and another PAIR
        THREE_OF_A_KIND, // rank number and PAIR
        A_STRAIGHT, //  sequence in rank and suit
        FLUSH, //
        FULL_HOUSE,
        FOUR_OF_A_KIND,
        STRAIGHT_FLUSH,
        ROYAL_FLUSH
    };

    Dealer dealer;
    PokerPlayer player1;
    PokerPlayer player2;

    private static int GetHighestCard (char[] rank) {
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

    public static List<Character> convertStringToCharList(String str)
    {

        // Create an empty List of character
        List<Character> chars = str

                // Convert to String to IntStream
                .chars()

                // Convert IntStream to Stream<Character>
                .mapToObj(e -> (char)e)

                // Collect the elements as a List Of Characters
                .collect(Collectors.toList());

        // return the List
        return chars;
    }

    PokerGame(Dealer dealer){
        this.dealer = dealer;
        this.player1 = new PokerPlayer("Black");
        this.player2 = new PokerPlayer("White");

    }
    PokerGame(Dealer dealer, PokerPlayer player1, PokerPlayer player2){
        this.dealer = dealer;
        this.player1 = player1;
        this.player2 = player2;
    }

    public PokerPlayer playTheGame() {
        dealer.dealARound(player1, player2);
        checkHand(player1);
        checkHand(player2);

        return winner();
    }

    public PokerPlayer winner() {
        // RoyalFlush
        // Winner has a full house


        return player1;
    }
    
    private void checkHand(PokerPlayer p) {
        String[] hand = p.hand();
        String rank = "";
        String suit = "";

        // Split rank ans suite
        for(String val:hand) {
            rank += val.charAt(0);
            suit += val.charAt(1);
        }

        char[] rankA = rank.toCharArray();
        int i = GetHighestCard(rankA);
        p.highestCard = Character.toString(rank.charAt(i) + suit.charAt(i)  );

        // need to know for a flush (which ever type)
        String finalSuit = suit;
        p.sameSuit = suit.chars().allMatch(c -> c == finalSuit.charAt(0)) ;

        // Check for pairs and three-of-a-kind
        List<Character> rankL= convertStringToCharList(rank);
        Map<Character, Long> result
                = rankL.stream().collect(
                Collectors.groupingBy(
                        Function.identity(),
                        Collectors.counting()));
    }

}
