package com.techrunners;

public class PokerPlayer {
    public PokerHand hand = new PokerHand();

    int count = 0;
    private String name;

    boolean sameSuit = false;
    public String highestCard = "";

    PokerPlayer(String name) {
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
