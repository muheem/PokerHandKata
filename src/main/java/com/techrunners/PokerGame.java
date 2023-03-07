package com.techrunners;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class PokerGame {

    public enum Element {
        H("Hydrogen"),
        HE("Helium"),
        // ...
        NE("Neon");

        public final String label;

        private Element(String label) {
            this.label = label;
        }
    }

    public enum BEST_HAND {
        HIGHEST_CARD(1,"Highest Card") ,
        A_PAIR(2,"A Pair"), // Check the rank number
        TWO_PAIR(3,"Two Pair"), // rank number and another PAIR
        THREE_OF_A_KIND(4,"Three of a Kind"), // rank number and PAIR
        A_STRAIGHT(5,"A Straight"), //  sequence in rank and suit
        A_FLUSH(6,"A Flush"), //
        FULL_HOUSE(7,"Full House"),
        FOUR_OF_A_KIND(8,"Four of a Kind"),
        STRAIGHT_FLUSH(9,"Straight Flush"),
        ROYAL_FLUSH(10,"RoyaL FLush");

        public final int ordinal;
        public final String label;

        String name;

        private BEST_HAND(int ordinal, String label) {
            this.ordinal = ordinal;
            this.label = label;
        }
    };

    Dealer dealer;
    PokerPlayer player1;
    PokerPlayer player2;

    private static boolean orderCards(char[] rankA) {
        String straight = "A23456789JQKA";


        return false;
    }
    private static boolean checkHighestCard(char[] rankA) {
        String straight = "A23456789JQKA";
        // order cards
        // pick the last
        return false;
    }
    private static boolean checkforAStraight(char[] rankA) {
        String straight = "A23456789JQKA";
        // order cards
        // check for a sequence.

        return false;
    }

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

    public String playTheGame() {
        dealer.dealARound(player1, player2);
        checkHand(player1);
        checkHand(player2);

        return winner() ;
    }

    public String winner() {
        BEST_HAND hand1 = player1.myHand();
        BEST_HAND hand2 = player2.myHand();

        if (hand1.ordinal > hand2.ordinal)
            return player1.name() + " wins with " + player1.myHand().label;
        else if (hand1.ordinal < hand2.ordinal)
            return player2.name() + " wins with " + player2.myHand().label;
        else
            return "A Draw";
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

        List<Character> rankS= convertStringToCharList(rank);
        p.groupedSuit
                = rankS.stream().collect(
                Collectors.groupingBy(
                        Function.identity(),
                        Collectors.counting()));

        // Check for pairs and three-of-a-kind and 4-of-a-kind.
        List<Character> rankL= convertStringToCharList(rank);
        p.groupedRank
                = rankL.stream().collect(
                Collectors.groupingBy(
                        Function.identity(),
                        Collectors.counting()));

        //if no pair, 3 or 4, then MAYBE its a straight...
        p.straight =  checkforAStraight(rankA);
    }

    // Find  highest value card.
}
