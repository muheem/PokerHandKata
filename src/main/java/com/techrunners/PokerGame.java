package com.techrunners;

public class PokerGame {


    public enum BEST_HAND {
        A_PAIR,
        TWO_PAIR,
        THREE_OF_A_KIND,
        A_STRAIGHT,
        FLUSH,
        FULL_HOUSE,
        FOUR_OF_A_KIND,
        STRAIGHT_FLUSH,
        ROYAL_FLUSH
    };

    Dealer dealer;
    PokerPlayer player1;
    PokerPlayer player2;

    PokerGame(Dealer dealer){
        this.dealer = dealer;
    }
    PokerGame(Dealer dealer, PokerPlayer player1, PokerPlayer player2){
        this.dealer = dealer;
        this.player1 = player1;
        this.player2 = player2;
    }

    public void playTheGame() {

        for (int i = 0; i < dealer.NUMBER_OF_CARDS_IN_A_HAND; i++) {
            player1.hitMe(dealer.dealACard());
            player2.hitMe(dealer.dealACard());
        }
    }

    public String compareHands(PlayingCard[] hand1, PlayingCard[] hand2) {

        if ( hand1[0].getRank() > hand2[0].getRank())
            return ("Player " + player1.name() + " wins" );
        else if ( hand1[0].getRank() < hand1[0].getRank())
            return ("Player " + player2.name() + " wins" );
         else
             return ("Result is a draw." );
    }

    public String compareHands() {
        PlayingCard hand1 = player1.hand()[0];
        PlayingCard hand2 = player2.hand()[0];

        if ( hand1.getRank() > hand2.getRank())
            return (hand1.getCard() + ">" + hand2.getCard() + ". Player " + player1.name() + " wins" );
        else if ( hand1.getRank() < hand2.getRank())
            return (hand2.getCard() + ">" + hand1.getCard() + ". Player " + player2.name() + " wins"  );
        else
            return (hand2.getCard() + "=" + hand1.getCard() + ".Result is a draw." );
    }

}
