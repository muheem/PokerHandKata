package com.techrunners;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class SameOfAKind implements WinningHand {
    private int threeOfAKind = 0;
    private int twoOfAKind = 0;
    private int fourOfAKind = 0;

    public static WinningHand findWinningHand(Card[] hand) {
        Map<Character, Long> groupedRank;

        // if a pair or more, then cannot have a straight
        char[] rank = new char[Game.NUMBER_OF_CARDS_IN_A_HAND];

        // Split rank and suite
        for (int i = 0; i < Game.NUMBER_OF_CARDS_IN_A_HAND; i++) {
            rank[i] += hand[i].getRank().label;
        }

        // Check for pairs and three-of-a-kind and 4-of-a-kind.
        List<Character> rankL = Game.convertStringToCharList(rank);
        groupedRank
                = rankL.stream().collect(
                Collectors.groupingBy(
                        Function.identity(),
                        Collectors.counting()));

        return (new SameOfAKind(groupedRank));
    }

    private  int CalculateSequence(Map<Character, Long> groupedRank) {
        for (Map.Entry<Character, Long> entry : groupedRank.entrySet()) {
            switch (Long.valueOf(entry.getValue()).intValue()) {
                case 2:
                    twoOfAKind++;
                    break;
                case 3:
                    threeOfAKind++ ;
                    break;
                case 4:
                    fourOfAKind = 1;
            }
        }
        return (twoOfAKind + threeOfAKind + fourOfAKind);
    }

    SameOfAKind(Map<Character, Long> groupedRank) {
        this.CalculateSequence(groupedRank);
    }
}
