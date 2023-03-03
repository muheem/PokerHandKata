package com.techrunners;

import java.util.Random;
import static java.lang.Math.*;


public class DeckOfCards {
    static final int NUMBER_OF_CARDS_IN_A_DECK = 52;
    static final int NUMBER_OF_CARDS_IN_A_SUIT = 13;

    PlayingCard[] cards = new PlayingCard[NUMBER_OF_CARDS_IN_A_DECK];

    DeckOfCards() {
        this.LoadTheDeck();
    }

    private void LoadTheDeck() {
        int index = 0;
        for (int i = 0; i < NUMBER_OF_CARDS_IN_A_SUIT; i++, index++ ) {
             cards[index] = new PlayingCard(i, PlayingCard.SUIT.Clubs);
        }
        for (int i = 0; i < NUMBER_OF_CARDS_IN_A_SUIT; i++, index++) {
            cards[index] = new PlayingCard(i, PlayingCard.SUIT.Diamonds);
        }
        for (int i = 0; i < NUMBER_OF_CARDS_IN_A_SUIT; i++, index++ ) {
            cards[index] = new PlayingCard(i, PlayingCard.SUIT.Hearts);
        }
        for (int i = 0; i < NUMBER_OF_CARDS_IN_A_SUIT; i++, index++ ) {
            cards[index] = new PlayingCard(i, PlayingCard.SUIT.Spades);
        }
    }

    PlayingCard deal() {
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
