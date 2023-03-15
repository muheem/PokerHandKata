package com.techrunners;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

public class PokerHandsKataTest {


        @Test
        public void CheckHandWinningWithPair() {
                String black = "2H 5D 2S 9C KD";
                String white = "2C 3H 5S 8C AH";

                Game game = new Game(black.split(" "), white.split(" "));
                assertEquals("Black wins. - with a pair", game.playTheGame());
        }

        @Test
        public void CheckHandWinningWithTwoPairs() {
                String black = "2H 5D 2S 9C KD";
                String white = "5C 3H 5S 3C AH";
                Game game = new Game(black.split(" "), white.split(" "));
                assertEquals("White wins. - with two pair", game.playTheGame());
        }

        @Test
        public void CheckHandWinningWithThreeOfAKind() {
                String black = "2H 5D 2S 9C 2D";
                String white = "5C KH 5S 3C AH";
                Game game = new Game(black.split(" "), white.split(" "));

                assertEquals("Black wins. - with three of a kind", game.playTheGame());
        }


        @ParameterizedTest
        @CsvFileSource(resources="/PokerHandsKataTest.csv", numLinesToSkip = 1)
        public void CheckBowlingGameUsingCSVFile(String blackHand, String whiteHand, String expected) {

                Game game = new Game(blackHand.split(" "), whiteHand.split(" "));
                assertEquals(expected, game.playTheGame());
        }
}

