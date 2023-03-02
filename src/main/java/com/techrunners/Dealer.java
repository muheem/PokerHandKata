package com.techrunners;

public class Dealer {
    PokerPlayer player1;
    PokerPlayer player2;

    Dealer(PokerPlayer player1, PokerPlayer player2){
        this.player1 = player1;
        this.player2 = player2;
    }

    String dealARound(){
        DeckOfCards pack = new DeckOfCards();

        player1.hitMe(pack.deal());
        player2.hitMe(pack.deal());

        // only one card for now.
        return CompareHands(player1.hand(), player2.hand());
    }

    private String CompareHands(PlayingCard[] hand1, PlayingCard[] hand2) {

        if ( hand1[0].getRank() > hand2[0].getRank())
            return ("Player " + player1.name() + " wins" );
        else if ( hand1[0].getRank() < hand1[0].getRank())
            return ("Player " + player2.name() + " wins" );
         else
             return ("Result is a draw." );
    }
}
