package com.techrunners;

import java.util.Random;


public class Deck {
    static final int NUMBER_OF_CARDS_IN_A_DECK = 52;
    static final int NUMBER_OF_CARDS_IN_A_SUIT = 13;

    Card[] cards = new Card[NUMBER_OF_CARDS_IN_A_DECK];

    Deck() {
        //this.LoadTheDeck();
    }

    private void LoadTheDeck() {
        int index = 0;
        for (int i = 0; i < NUMBER_OF_CARDS_IN_A_SUIT; i++, index++ ) {
             cards[index] = new Card(Card.getRank(i), Card.Suit.Clubs);
        }
        for (int i = 0; i < NUMBER_OF_CARDS_IN_A_SUIT; i++, index++) {
            cards[index] = new Card(Card.getRank(i), Card.Suit.Diamonds);
        }
        for (int i = 0; i < NUMBER_OF_CARDS_IN_A_SUIT; i++, index++ ) {
            cards[index] = new Card(Card.getRank(i), Card.Suit.Hearts);
        }
        for (int i = 0; i < NUMBER_OF_CARDS_IN_A_SUIT; i++, index++ ) {
            cards[index] = new Card(Card.getRank(i), Card.Suit.Spades);
        }
    }

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
}
