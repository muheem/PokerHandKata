package com.techrunners;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

public class SameOfAKind implements BestHand {
    private int threeOfAKind = 0;
    private int twoOfAKind = 0;
    private int fourOfAKind = 0;
    public Card[] fiveCards = new Card[Game.NUMBER_OF_CARDS_IN_A_HAND];

    public Hand.MyHand show() {

        if (fourOfAKind == 1)
            return Hand.MyHand.FOUR_OF_A_KIND;

        if (threeOfAKind == 1 && twoOfAKind == 1)
            return Hand.MyHand.FULL_HOUSE;

        if (threeOfAKind > 0)
            return Hand.MyHand.THREE_OF_A_KIND;

        if (twoOfAKind > 0)
            return twoOfAKind == 2 ? Hand.MyHand.TWO_PAIR : Hand.MyHand.A_PAIR;

        return Hand.MyHand.NULL;
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
