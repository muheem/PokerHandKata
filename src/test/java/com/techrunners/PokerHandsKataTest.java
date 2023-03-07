package com.techrunners;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;



public class PokerHandsKataTest {


        @Test
        public void CheckHandWinningWithPair() {
                String blackHand = "2H SD 2S 9C KD";
                String whiteHand = "2C 3H 5S 8C AH";

                PokerPlayer black = new PokerPlayer("Black" );
                black.setHand(blackHand.split(" "));

                PokerPlayer white = new PokerPlayer("White" );
                white.setHand(whiteHand.split(" "));

                Dealer td = new TestDealer();
                //((TestDealer) td).newGameData(blackHand, whiteHand);
                PokerGame game = new PokerGame(td, black, white);
                assertEquals("Black wins with A Pair", game.playTheGame());
        }

        @Test
        public void CheckHandWinningWithTwoPairs() {
                String blackHand = "2H SD 2S 9C KD";
                String whiteHand = "5C 3H 5S 3C AH";

                PokerPlayer black = new PokerPlayer("Black" );
                black.setHand(blackHand.split(" "));

                PokerPlayer white = new PokerPlayer("White" );
                white.setHand(whiteHand.split(" "));

                Dealer td = new TestDealer();
                //((TestDealer) td).newGameData(blackHand, whiteHand);
                PokerGame game = new PokerGame(td, black, white);
                assertEquals("White wins with Two Pair", game.playTheGame());
        }

        @Test
        public void CheckHandWinningWithThreeOfAKind() {
                String blackHand = "2H SD 2S 9C 2D";
                String whiteHand = "5C KH 5S 3C AH";

                PokerPlayer black = new PokerPlayer("Black" );
                black.setHand(blackHand.split(" "));

                PokerPlayer white = new PokerPlayer("White" );
                white.setHand(whiteHand.split(" "));

                Dealer td = new TestDealer();
                //((TestDealer) td).newGameData(blackHand, whiteHand);
                PokerGame game = new PokerGame(td, black, white);
                assertEquals("Black wins with Three of a Kind", game.playTheGame());
        }
}

