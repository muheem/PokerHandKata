package com.techrunners;

import java.util.*;

import static com.techrunners.Card.NUMBER_OF_CARDS_IN_A_DECK;
import static com.techrunners.Card.NUMBER_OF_CARDS_IN_A_SUIT;
import static com.techrunners.Game.NUMBER_OF_CARDS_IN_A_HAND;

public class Main {

    static Card[] cards = new Card[NUMBER_OF_CARDS_IN_A_DECK];

    static void LoadTheDeck() {
        int index = 0;
        for (int i = 1; i <= NUMBER_OF_CARDS_IN_A_SUIT; i++, index++) {
            cards[index] = new Card(Card.getRank(i), Card.Suit.Clubs);
        }
        for (int i = 1; i <= NUMBER_OF_CARDS_IN_A_SUIT; i++, index++) {
            cards[index] = new Card(Card.getRank(i), Card.Suit.Diamonds);
        }
        for (int i = 1; i <= NUMBER_OF_CARDS_IN_A_SUIT; i++, index++) {
            cards[index] = new Card(Card.getRank(i), Card.Suit.Hearts);
        }
        for (int i = 1; i <= NUMBER_OF_CARDS_IN_A_SUIT; i++, index++) {
            cards[index] = new Card(Card.getRank(i), Card.Suit.Spades);
        }
    }

    static String[] deal() {
        // Get a random number from 1 to 52.
        // "remove" the card at that numbered location in the deck.
        // Mark the card as played
        String[] stringHand = new String[NUMBER_OF_CARDS_IN_A_HAND];

        Random rand = new Random();
        int num = 0;
        for (int i = 0; i < NUMBER_OF_CARDS_IN_A_HAND; i++) {
            num = rand.nextInt(51) + 1;
            stringHand[i] = cards[num].getRank().label + cards[num].getSuit().label;
        }
        return stringHand; //  Pass to player...
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);    //System.in is a standard input stream
        LoadTheDeck();

        System.out.println("***************************");
        System.out.println("WELCOME TO POKER HANDS KATA");
        System.out.println("***************************");
        System.out.println("                           ");


        while (true) {
            String black = "";
            String white = "";
            System.out.println("Please Enter BLACK poker hand in the following format: ");
            System.out.println("-------  JD TD 3H 3D 5H   (enter 'bye' to exit)  -------");
            black = sc.nextLine();
            if ("bye".equalsIgnoreCase(black))
                break;
            else if (black.length() == 0)
                black = String.join(" ", deal());


            System.out.println("Please Enter WHITE poker hand  in the following format: ");
            System.out.println("-------  KS 6D KS KS TS  (enter 'bye' to exit)  -------");
            white = sc.nextLine();
            if ("bye".equalsIgnoreCase(white))
                break;
            else if (white.length() == 0) {
                white = String.join(" ", deal());
            }

            // Compare hands
            Game game = new Game(black.split(" "), white.split(" "));
            System.out.println("***************************");
            System.out.println("Black: " + black + " ,White: " + white );
            System.out.println(game.playTheGame());
            System.out.println("***************************");
            System.out.println("                           ");
        }
    }
}
