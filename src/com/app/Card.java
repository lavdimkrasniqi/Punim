package com.app;


public class Card {
    public static final String SPADES = "spades";
    public static final String HEARTS = "hearts";
    public static final String DIAMONDS = "diamonds";
    public static final String CLUBS = "clubs";
    public static final int ACE = 1;
    public static final int JACK = 11;
    public static final int QUEEN = 12;
    public static final int KING = 13;
    public static final int SIZE_OF_ONE_SUIT = 13; // how many cards in one suit
    // These are the card’s attributes:
    private String suit;
    private int count;

    /**
     * Constructor Card sets the suit and count.
     *
     * @param s - the suit
     * @param c - the count
     */
    public Card(String s, int c) {
        suit = s;
        count = c;
    }

    /**
     * getSuit returns the card’s suit.
     */
    public String getSuit() {
        return suit;
    }

    /**
     * getCount returns the card’s count.
     */
    public int getCount() {
        return count;
    }

    public String toString() {
        String s = " of " + suit;
        if (count == ACE) {
            s = "ace" + s;
        } else if (count == JACK) {
            s = "jack" + s;
        } else if (count == QUEEN) {
            s = "queen" + s;
        } else if (count == KING) {
            s = "king" + s;
        } else {
            s = count + s;
        }
        return s;
    }
}
