package com.techrunners;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;


public class PokerHandsKataTest {


        @Test
        public void CheckHandWinningWithPair() {
                String blackHand = "2H 5D 2S 9C KD";
                String whiteHand = "2C 3H 5S 8C AH";

                Player black = new Player("Black" );
                black.setHand(blackHand.split(" "));

                Player white = new Player("White" );
                white.setHand(whiteHand.split(" "));

                Game game = new Game(black, white);
                assertEquals("Black wins. - with a pair", game.playTheGame());
        }

        @Test
        public void CheckHandWinningWithTwoPairs() {
                String blackHand = "2H 5D 2S 9C KD";
                String whiteHand = "5C 3H 5S 3C AH";


                Player black = new Player("Black" );
                black.setHand(blackHand.split(" "));

                Player white = new Player("White" );
                white.setHand(whiteHand.split(" "));

                Game game = new Game(black, white);
                assertEquals("White wins. - with two pair", game.playTheGame());
        }

        @Test
        public void CheckHandWinningWithThreeOfAKind() {
                String blackHand = "2H 5D 2S 9C 2D";
                String whiteHand = "5C KH 5S 3C AH";

                Player black = new Player("Black" );
                black.setHand(blackHand.split(" "));

                Player white = new Player("White" );
                white.setHand(whiteHand.split(" "));

                Game game = new Game( black, white);
                assertEquals("Black wins. - with three of a kind", game.playTheGame());
        }


        @ParameterizedTest
        @CsvFileSource(resources="/PokerHandsKataTest.csv", numLinesToSkip = 1)
        public void CheckBowlingGameUsingCSVFile(String blackHand, String whiteHand, String expected) {
                Player black = new Player("Black" );
                black.setHand(blackHand.split(" "));

                Player white = new Player("White" );
                white.setHand(whiteHand.split(" "));

                Game game = new Game( black, white);
                assertEquals(expected, game.playTheGame());
        }
}

