package com.techrunners;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Card {


    public enum Suit {
        Clubs("C"),
        Diamonds("D"),
        Hearts("H"),
        Spades("S");

        public final String label;

        private Suit(String label) {
            this.label = label;
        }
    };


    public enum Rank {
        ONE(1,'A') ,
        TWO(2,'2'),
        THREE(3,'3'),
        FOUR(4,'4'),
        FIVE(5,'5'),
        SIX(6,'6'),
        SEVEN(7,'7'),
        EIGHT(8,'8'),
        NINE(9,'9'),
        TEN(10,'T'),
        JACK(11,'J'),
        QUEEN(12,'Q'),
        KING(12,'K'),
        ACE(13,'A')
        ;

        public final int ordinal;
        public final char label;

        Rank(int ordinal, char label) {
            this.ordinal = ordinal;
            this.label = label;
        }

    };

    public boolean removed() {
        return removed;
    }
    public void remove() {
        removed = true;
    }

    private Rank rank;
    private Suit suit;

    boolean removed = false;

    Card(Rank rank, Suit suit) {
        this.rank = rank;
        this.suit = suit;
    }
    Card(String s) {
        setRank(s.charAt(0));
        setSuit(s.charAt(1));
    }


    private void setSuit(char c) {
        switch (c) {
            case 'C':
                suit = Suit.Clubs; break;
            case 'D':
                suit =  Suit.Diamonds; break;
            case 'H':
                suit =  Suit.Hearts; break;
            case 'S':
                suit =  Suit.Spades; break;
            default:
                System.out.println("Invalid Suit " + c);
                suit =  Suit.Clubs;
        }
    }

    public Rank getRank() {
        return rank;
    }
    public Suit getSuit() {
        return suit;
    }
    static public Rank getRank(int i) {
        switch (i) {
            case 1:
                return Rank.ACE;
            case 2:
                return Rank.TWO;
            case 3:
                return Rank.THREE;
            case 4:
                return Rank.FOUR;
            case 5:
                return Rank.FIVE;
            case 6:
                return Rank.SIX;
            case 7:
                return Rank.SEVEN;
            case 8:
                return Rank.EIGHT;
            case 9:
                return Rank.NINE;
            case 10:
                return Rank.TEN;
            case 11:
                return Rank.JACK;
            case 12:
                return Rank.QUEEN;
            case 13:
                return Rank.KING;
            //case 'A':
            //    rank = Rank.ACE; break;
            default:
                System.out.println("Invalid Rank " + i);
                return  Rank.ACE;
        }
    }

    private void setRank(char c) {
        switch (c) {
            case '1':
                rank = Rank.ONE; break;
            case '2':
                rank = Rank.TWO; break;
            case '3':
                rank = Rank.THREE; break;
            case '4':
                rank = Rank.FOUR; break;
            case '5':
                rank = Rank.FIVE; break;
            case '6':
                rank = Rank.SIX; break;
            case '7':
                rank = Rank.SEVEN; break;
            case '8':
                rank = Rank.EIGHT; break;
            case '9':
                rank = Rank.NINE; break;
            case 'T':
                rank = Rank.TEN; break;
            case 'J':
                rank = Rank.JACK; break;
            case 'Q':
                rank = Rank.QUEEN; break;
            case 'K':
                rank = Rank.KING; break;
            case 'A':
                rank = Rank.ACE; break;
            default:
                System.out.println("Invalid Rank " + c);
                rank = Rank.ACE;
        }
    }
}
