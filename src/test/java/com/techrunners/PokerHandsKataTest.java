package com.techrunners;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class PokerHandsKataTest {

        @Test
        public void CheckAllocatingFirstSeats() {
                PokerPlayer slim = new PokerPlayer("slim");
                PokerPlayer sharp = new PokerPlayer("sharp");

                Dealer dealer = new Dealer(slim, sharp);
                assertEquals(slim, dealer.dealARound());
        }
}
