package com.techrunners;

public class PlayingCard {
    public boolean removed() {
        return removed;
    }
    public void remove() {
        removed = true;
    }
    public enum SUIT {Clubs, Diamonds, Hearts, Spades};

    private int rank;
    private SUIT suit;

    boolean removed = false;

    PlayingCard(int rank, SUIT suit) {
        this.rank = rank;
        this.suit = suit;
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
    public int getRank() { return rank;}
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
        return Character.toString(getSuit() + getCharRank());
    }


}
