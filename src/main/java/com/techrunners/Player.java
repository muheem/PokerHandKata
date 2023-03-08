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

}
