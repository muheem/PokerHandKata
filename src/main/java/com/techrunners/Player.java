package com.techrunners;

import java.util.Map;

public class Player {
    public Hand hand = new Hand();

    int count = 0;
    private String name;


    Player(String name) {
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

    public void setWinningCardValue(char value) {
        hand.highestCard = value;
    }
    public String winningMessage(Hand losingHand) {
        if (hand.myHand.type == Hand.WinType.High_Card)
            return name() + " wins. - with " + hand.myHand.label + " " + hand.highestCard + " beats " + losingHand.highestCard;
        else
            return name() + " wins. - with " + hand.myHand.label;
    }
}
