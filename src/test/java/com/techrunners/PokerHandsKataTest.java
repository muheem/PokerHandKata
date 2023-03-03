package com.techrunners;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class PokerHandsKataTest {

        @Test
        public void CheckEachPlayerGivenFiveCards() {
                PokerPlayer slim = new PokerPlayer("slim");
                PokerPlayer sharp = new PokerPlayer("sharp");

                Dealer dealer = new PokerDealer();
                PokerGame game = new PokerGame(dealer, slim, sharp);
                game.playTheGame();
                PlayingCard[]  hand1 = slim.hand();
                PlayingCard[]  hand2 = slim.hand();

                System.out.println("Slim's hand : " + slim.showHand());
                System.out.println("Sharp's hand : " + sharp.showHand());
                assertEquals(5, hand1.length);
                assertEquals(5, hand2.length);
        }

        @Test
        public void CheckNoDuplicateCardsInAnyHand() {
                PokerPlayer slim = new PokerPlayer("slim");
                PokerPlayer sharp = new PokerPlayer("sharp");

                Dealer dealer = new PokerDealer();
                PokerGame game = new PokerGame(dealer, slim, sharp);
                game.playTheGame();
                PlayingCard[]  hand1 = slim.hand();
                PlayingCard[]  hand2 = slim.hand();

                System.out.println("Slim's hand : " + slim.showHand());
                System.out.println("Sharp's hand : " + sharp.showHand());
                assertEquals(5, hand1.length);
                assertEquals(5, hand2.length);
        }

}
