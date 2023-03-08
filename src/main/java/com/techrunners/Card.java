package com.techrunners;

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
        ONE(1,"A") ,
        TWO(2,"2"),
        THREE(3,"3"),
        FOUR(4,"4"),
        FIVE(5,"5"),
        SIX(6,"6"),
        SEVEN(7,"7"),
        EIGHT(8,"8"),
        NINE(9,"9"),
        TEN(10,"10"),
        JACK(11,"J"),
        QUEEN(12,"Q"),
        KING(12,"K"),
        ACE(13,"A")
        ;

        public final int ordinal;
        public final String label;

        private Rank(int ordinal, String label) {
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


    private int rank;
    private Suit suit;

    boolean removed = false;

    Card(int rank, Suit suit) {
        this.rank = rank;
        this.suit = suit;
    }
    Card(String input) {
        this.setCard(input);
    }
    public char getSuit() {
        switch (suit) {
            case Clubs:
                return 'C';
            case Diamonds:
                return 'D';
            case Hearts:
                return 'H';
            case Spades:
                return 'S';
            default:
                System.out.println("Invalid Suit" + suit.toString());
                return 'X';
        }
    }
    public Suit setSuit(char c) {
        switch (c) {
            case 'C':
                return Suit.Clubs;
            case 'D':
                return Suit.Diamonds;
            case 'H':
                return Suit.Hearts;
            case 'S':
                return Suit.Spades;
            default:
                System.out.println("Invalid Suit " + c);
                return Suit.Clubs;
        }
    }

    public int getRank() {
        return rank;
    }

    public char getCharRank() {
        char c = (char) (rank + '0'); // 2-9

        // Special cases if 10 - J, 11 - Q, 12 - K, 1 - A
        if (rank == 1)
            c = 'A';
        else if (rank == 10)
            c = 'J';
        else if (rank == 11)
            c = 'Q';
        else if (rank == 12)
            c = 'Q';

        return c;
    }
    String getCard() {
        return Character.toString(getCharRank()) + Character.toString(getSuit());
    }
    void setCard(String s) {
        if (s.length() == 2) {
            rank = s.charAt(0);
            suit = setSuit(s.charAt(1));
        }
    }
}
