package com.techrunners;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;



public class PokerHandsKataTest {
/*
        @Test
        public void CheckEachPlayerGivenFiveCards() {
                PokerPlayer slim = new PokerPlayer("slim");
                PokerPlayer sharp = new PokerPlayer("sharp");

                Dealer dealer = new PokerDealer();
                PokerGame game = new PokerGame(dealer, slim, sharp);
                game.playTheGame();
                PokerHand hand1 = slim.hand();
                PokerHand hand2 = slim.hand();

                System.out.println("Slim's hand : " + hand1);
                System.out.println("Sharp's hand : " + hand2);
                assertEquals(5, hand1.index);
                assertEquals(5, hand2.index);
        }

        @Test
        public void CheckNoDuplicateCardsInAnyHand() {
                PokerPlayer slim = new PokerPlayer("slim");
                PokerPlayer sharp = new PokerPlayer("sharp");

                Dealer dealer = new PokerDealer();
                PokerGame game = new PokerGame(dealer, slim, sharp);
                game.playTheGame();
                PokerHand hand1 = slim.hand();
                PokerHand hand2 = slim.hand();

                System.out.println("Slim's hand : " + hand1);
                System.out.println("Sharp's hand : " + hand2);
                assertEquals(5, hand1.index);
                assertEquals(5, hand2.index);
        }

        @Test
        public void CheckHandUsingTestDealer() {
                String blackHand = "2H SD 5S 9C KD";
                String whiteHand = "2C 3H 4S 8C AH";

                Dealer td = new TestDealer();
                ((TestDealer) td).newGameData(blackHand, whiteHand);
                PokerGame game = new PokerGame(td);

                assertEquals("White wins.", game.playTheGame());
        }
*/
        @Test
        public void CheckHandWinningWithPair() {
                String blackHand = "2H SD 2S 9C KD";
                String whiteHand = "2C 3H 5S 8C AH";

                Dealer td = new TestDealer();
                ((TestDealer) td).newGameData(blackHand, whiteHand);
                PokerGame game = new PokerGame(td);
                assertEquals("Black wins.", game.playTheGame());
        }
}
