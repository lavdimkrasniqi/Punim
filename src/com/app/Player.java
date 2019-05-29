package com.app;

public class Player {
    private Card[] hand;
    private int hand_count;
    private int hand_size;
    private Card[] earned_cards;
    private boolean isHuman;

    /**
     * Constructor Player initializes the object
     *
     * @param size - the max. number of cards the player can hold
     */
    public Player(int size, boolean isHuman) {
        this.isHuman = isHuman;
        this.hand_size = size;
        this.hand = new Card[hand_size];
    }

    /**
     * receiveCard  accepts a card and adds it to the hand
     *
     * @param cards - the card to be added
     */
    public void setNewHandCards(Card[] cards) {
        hand = cards;
        hand_count = cards.length;
    }

    public Card pullCardFromHand(int index) {
        Card next_card = null;
        if (hand_count == 0) {
            System.out.println("CardHand error: no more cards");
        } else {
            next_card = hand[index];
            // once card is extracted from deck, shift other cards to fill gap:
            for (int i = index + 1; i != hand_count; i++)
            // so far, cards from  index+1 to i-1  have been shifted left
            //  in the array by one position
            {
                hand[i - 1] = hand[i];
            }
            hand_count = hand_count - 1;
        }
        return next_card;
    }

    /**
     * showCards gives the player's hand to others to see
     *
     * @return (the address of) the hand of cards
     */
    public Card[] getHandCards() {
        return hand;
    }

    public int getHandCount() {
        return hand_count;
    }

    public void appendEarnedCards(Card[] cards) {
        if (earned_cards != null) {
            Card[] newEarnedCards = new Card[earned_cards.length + cards.length];

            for (int i = 0; i < earned_cards.length; i++) newEarnedCards[i] = earned_cards[i];

            for (int i = newEarnedCards.length; i < newEarnedCards.length + cards.length; i++)
                newEarnedCards[i] = cards[i];


            earned_cards = newEarnedCards;
        } else
            earned_cards = cards;
    }

    public int getEarnedCardCount() {
        if (earned_cards == null)
            return 0;

        return earned_cards.length;
    }

    public boolean isHuman() {
        return isHuman;
    }
}
