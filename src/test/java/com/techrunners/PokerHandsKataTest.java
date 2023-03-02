package com.techrunners;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class PokerHandsKataTest {

        @Test
        public void CheckEachPlayerAllocatedFiveCards() {
                PokerPlayer slim = new PokerPlayer("slim");
                PokerPlayer sharp = new PokerPlayer("sharp");

                Dealer dealer = new Dealer(slim, sharp);
                dealer.dealARound();
                PlayingCard[]  hand1 = slim.hand();
                PlayingCard[]  hand2 = slim.hand();

                assertEquals(5, hand1.length);
                assertEquals(5, hand2.length);
        }
}
