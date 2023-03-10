package com.techrunners;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Main {
/*
    Card deal() {
        // Get a random number from 1 to 52.
        // "remove" the card at that numbered location in the deck.
        // Mark the card as played

        Random rand = new Random();
        int num = rand.nextInt(51) + 1;
        while (cards[num].removed()){
            num = rand.nextInt(51) + 1;
        }
        cards[num].remove(); // .. from pack
        return cards[num]; //  Pass to player...
    }
*/

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
        return rank[index];
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

    public static void main(String[] args) {

        // for each string in array hand1.getHand()
        // check if there are any pairs or triples (and how many pairs)

        String black = "2H SD 2S 2C KD";
        String white = "2C 3H 5S 8C AH";

        String[] bl = black.split(" ");
        String rank = "";
        String suit = "";

        // Split rank ans suite
        for(String val:bl) {
            rank += val.charAt(0);
            suit += val.charAt(1);
        }
        System.out.println("rank=" + rank + " suit=" + suit);

        String finalSuit = suit;
        // need to know for a flush (which ever type)
        boolean sameSuit = suit.chars().allMatch(c -> c == finalSuit.charAt(0)) ;
        if (sameSuit)
            System.out.println("All the same suit");

        String finalRank = rank;
        boolean pair = rank.chars().anyMatch(c -> c == finalRank.charAt(0)) ;
        if (pair)
            System.out.println("There is a match");

        char[] rankA = rank.toCharArray();
        List<Character> rankL= convertStringToCharList(rank);
        System.out.println("rankL=" + rankL );

        char test = rank.charAt(0);
        long count = rank.chars().filter(ch -> ch == test).count();
        System.out.println("There are " + count + " 2's");

        Map<Character, Long> result
                = rankL.stream().collect(
                        Collectors.groupingBy(
                                Function.identity(),
                                Collectors.counting()));

        System.out.println("Map=" + result );

        int i = GetHighestCard(rankA);
        System.out.println( "Highest is card " + rank.charAt(i) + suit.charAt(i));

    }
}