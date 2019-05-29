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


    public void shuffleArray(Card[] ar) {
        // If running on Java 6 or older, use `new Random()` on RHS here
        Random rnd = ThreadLocalRandom.current();
        for (int i = ar.length - 1; i > 0; i--) {
            int index = rnd.nextInt(i + 1);
            // Simple swap
            Card a = ar[index];
            ar[index] = ar[i];
            ar[i] = a;
        }
    }

    /**
     * newCard  gets a new card from the deck.
     *
     * @return a card not used before, or return null, if no cards are left
     */
    public Card newCard() {
        Card next_card = null;
        if (card_count == 0) {
            System.out.println("CardDeck error: no more cards");
        } else {
            int index = (int) (Math.random() * card_count);  // randomly choose
            next_card = deck[index];
            // once card is extracted from deck, shift other cards to fill gap:
            for (int i = index + 1; i != card_count; i++)
            // so far, cards from  index + 1 to i-1 have been shifted left
            //  in the array by one position
            {
                deck[i - 1] = deck[i];
            }
            card_count = card_count - 1;
        }

        return next_card;
    }

    public Card[] takeAHand(int hand_size) {
        Card[] cards = new Card[hand_size];
        if (card_count == 0) {
            System.out.println("CardDeck error: no more cards");
        } else {
            for (int ii = 0; ii < hand_size; ii++) {
                cards[ii] = deck[0];
                // once card is extracted from deck, shift other cards to fill gap:
                for (int i = 1; i != card_count; i++)
                // so far, cards from  index + 1 to i-1 have been shifted left
                //  in the array by one position
                {
                    deck[i - 1] = deck[i];
                }

                card_count = card_count - 1;
            }
        }

        return cards;
    }

    /**
     * moreCards  states whether the deck has more cards to give.
     *
     * @return whether the deck is nonempty
     */
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