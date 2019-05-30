package com.app;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class CardDeck {
    private int card_count;  // how many cards remain in the deck
    private Card[] deck = new Card[4 * Card.SIZE_OF_ONE_SUIT];
    // invariant: elements  deck[0]..deck[card_count - 1]  hold cards

    /**
     * Constructor  CardDeck  creates a new card deck with all its cards
     */
    public CardDeck() {
        createSuit(Card.SPADES);
        createSuit(Card.HEARTS);
        createSuit(Card.CLUBS);
        createSuit(Card.DIAMONDS);
        shuffleArray(deck);
    }


    public void shuffleArray(Card[] ar) {// i perzin letrat ne fillim te lojes
        // If running on Java 6 or older, use `new Random()` on RHS here
        Random rnd = ThreadLocalRandom.current();  //kjo e kthen nje numer te rastit
        for (int i = ar.length - 1; i > 0; i--) {
            int index = rnd.nextInt(i + 1);
            // Simple swap
            Card a = ar[index]; //ua ndrron vendet elementeve te aarray
            ar[index] = ar[i];
            ar[i] = a;
        }
    }

    public Card[] takeAHand(int hand_size) {// kjo ua shperndan letrat lojtareve
        Card[] cards = new Card[hand_size];
        if (card_count == 0) {
            System.out.println("CardDeck error: no more cards");
        } else {
            for (int ii = 0; ii < hand_size; ii++) {
                cards[ii] = deck[0];

                for (int i = 1; i != card_count; i++)

                {
                    deck[i - 1] = deck[i]; //kjo e largon elementin e pare
                }

                card_count = card_count - 1;
            }
        }

        return cards;
    }

    public boolean moreCards() {
        return (card_count > 0);
    }

    private void createSuit(String which_suit) {
        for (int i = 1; i <= Card.SIZE_OF_ONE_SUIT; i = i + 1) {
            deck[card_count] = new Card(which_suit, i);
            card_count = card_count + 1;
        }
    }

    public int getCardCount() {
        return card_count;
    }
}