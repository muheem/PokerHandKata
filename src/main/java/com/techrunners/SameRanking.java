package com.techrunners;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class SameRanking implements BestHand {
    private int threeOfAKind = 0;
    private int twoOfAKind = 0;
    private int fourOfAKind = 0;
    public Card[] fiveCards = new Card[Game.NUMBER_OF_CARDS_IN_A_HAND];

    public Hand.PokerHand show() {

        if (fourOfAKind == 1)
            return Hand.PokerHand.FOUR_OF_A_KIND;

        if (threeOfAKind == 1 && twoOfAKind == 1)
            return Hand.PokerHand.FULL_HOUSE;

        if (threeOfAKind > 0)
            return Hand.PokerHand.THREE_OF_A_KIND;

        if (twoOfAKind > 0)
            return twoOfAKind == 2 ? Hand.PokerHand.TWO_PAIR : Hand.PokerHand.A_PAIR;

        return Hand.PokerHand.NULL;
    }

    public void calculate(Card[] hand, char[] rank) {

        // Check for pairs and three-of-a-kind and 4-of-a-kind.
        List<Character> rankList = Hand.convertStringToCharList(rank);
        Map<Character, Long> groupedRank = rankList.stream().collect(
                Collectors.groupingBy(
                        Function.identity(),
                        Collectors.counting()));
        calculateWhichSequence(groupedRank);
    }

    private void calculateWhichSequence(Map<Character, Long> groupedRank) {
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
    }
}
