package com.techrunners;


public class Game {
    public static final int NUMBER_OF_CARDS_IN_A_HAND = 5;

    Hand black = new Hand("Black");
    Hand white = new Hand("White");


    Game(String[] blackHand, String[] whiteHand) {
        this.black.setStringHand(blackHand);
        this.white.setStringHand(whiteHand);
    }

    public String playTheGame() {
        return workOutTheWinningHand();
    }

    private String workOutTheWinningHand() {
        int handB = black.myHand.ordinal;
        int handW = white.myHand.ordinal;
        Hand.WinType type = black.myHand.type;

        if (handB > handW)
            return black.winningMessage(white);
        if (handB < handW)
            return white.winningMessage(black);

        if (type == Hand.WinType.High_Card)
            return HandleHighCard();
        else if (type != Hand.WinType.NoHand)
            return compareHighestCardsWhenATie();

        return "Tie";
    }

    private String HandleHighCard() {
        char hc1 = black.highestCard;
        char hc2 = white.highestCard;

        if ( hc1 == hc2 )
            return (compareHighestCardsWhenATie());
        if (hc1 =='A' )
            return black.winningMessage(white);
        if (hc2 =='A' )
            return white.winningMessage(black);
        if (hc1 > hc2)
            return black.winningMessage(white);
        if (hc1 < hc2)
            return white.winningMessage(black);
        return "Tie";
    }

    private String compareHighestCardsWhenATie() {
        Card[] wh = black.getHand();
        Card[] bh = white.getHand();

        boolean winner = false;
        for (int i = wh.length - 1; i >= 0  ; i--) {
            if (wh[i].getRank().ordinal > bh[i].getRank().ordinal) {
                black.setWinningCardValue(wh[i].getRank().label);
                white.highestCard = bh[i].getRank().label;
                return black.winningMessage(white);
            }
            else if (wh[i].getRank().ordinal < bh[i].getRank().ordinal) {
                white.setWinningCardValue(bh[i].getRank().label);
                black.highestCard = wh[i].getRank().label;
                return white.winningMessage(black);
            }
        }
        return "Tie";
    }
}
