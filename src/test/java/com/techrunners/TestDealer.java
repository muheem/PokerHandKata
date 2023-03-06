package com.techrunners;

class TestDealer extends Dealer {
    PlayingCard[] hand1 = new PlayingCard[NUMBER_OF_CARDS_IN_A_HAND];
    PlayingCard[] hand2 = new PlayingCard[NUMBER_OF_CARDS_IN_A_HAND];


    public void newGameData(String black, String white)  {
        String[] arrayB = black.trim().split("\\s+");
        String[] arrayW = black.trim().split("\\s+");
        for (int i=0; i< NUMBER_OF_CARDS_IN_A_HAND; i++) {
            hand1[i] = new PlayingCard(arrayB[i]);
            hand2[i] = new PlayingCard(arrayW[i]);
        }
    }

    void dealARound(PokerPlayer p1, PokerPlayer p2){
        //newGameData();
    }
    void fillHands(){
        for (int i = 0; i < NUMBER_OF_CARDS_IN_A_HAND; i++ ) {
            hand1[i] = pack.deal();
            hand2[i] = pack.deal();
        }
    }

}