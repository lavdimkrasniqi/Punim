package com.app;

public class Player {
    private Card[] hand;
    private int hand_count;
    private int hand_size;
    private Card[] earned_cards;
    private boolean isHuman;


    public Player(int size, boolean isHuman) {
        this.isHuman = isHuman;
        this.hand_size = size;
        this.hand = new Card[hand_size];
    }


    public void setNewHandCards(Card[] cards) {//kjo vendos letrat lojtareve
        hand = cards;
        hand_count = cards.length;
    }

    public Card pullCardFromHand(int index) {//kjo metode sherben per me e qit letren ne tavoline
        Card next_card = null;
        if (hand_count == 0) {
            System.out.println("CardHand error: no more cards");
        } else {
            next_card = hand[index];//ineksi merret prej konsoles

            for (int i = index + 1; i != hand_count; i++)//largon letren nga hand

            {
                hand[i - 1] = hand[i];
            }
            hand_count = hand_count - 1;
        }
        return next_card;
    }


    public Card[] getHandCards() { //shfaqe letrat e lojtarit
        return hand;
    }

    public int getHandCount() {//shfaq numrin e letrave te lojtarit
        return hand_count;
    }

    public void appendEarnedCards(Card[] cards) {//shton letrat qe i kena fitu gjat lojes
        if (earned_cards != null) {
            Card[] newEarnedCards = new Card[earned_cards.length + cards.length];

            for (int i = 0; i < earned_cards.length; i++) newEarnedCards[i] = earned_cards[i];

            for (int i = earned_cards.length; i < newEarnedCards.length; i++)
                newEarnedCards[i] = cards[i - earned_cards.length];


            earned_cards = newEarnedCards;
        } else
            earned_cards = cards;
    }

    public int getEarnedCardCount() {//kthen rezultatin e letrave te fituara
        if (earned_cards == null)
            return 0;

        return earned_cards.length;
    }

    public boolean isHuman() {
        return isHuman;
    }//tregon nese eshte duke luajtur lojtari apo jo
}
