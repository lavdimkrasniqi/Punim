package com.app;

public class Table {
    private CardDeck deck;  // the deck the dealer uses
    private Card[] cards_on_table;
    private int card_table_count;


    public Table() {
        deck = new CardDeck();
        cards_on_table = new Card[4 * Card.SIZE_OF_ONE_SUIT];
        card_table_count = 0;
    }

    public Card getLastTableCard() { //merr karten e fundit ne tabel
        if (card_table_count > 0)
            return cards_on_table[card_table_count - 1];

        return null;
    }

    public Card[] takeAHand(int hand_size) {
        return deck.takeAHand(hand_size);  //shperndan letrat me madhesi 4
    }

    public Card[] pullTableCards() {
        Card[] cards = new Card[card_table_count]; //i largon letrat e lojes kur lojtari i fiton ato

        for (int i = 0; i < this.card_table_count; i++) {
            cards[i] = cards_on_table[i];
        }

        card_table_count = 0;
        return cards;
    }

    public void appendTableCards(Card[] cards) { //shton letrat ne tavoline
        for (int i = this.card_table_count; i < this.card_table_count + cards.length; i++)
            cards_on_table[i] = cards[i];

        card_table_count += cards.length;
    }

    public void appendTableCard(Card card) {//shton nje leter ne tavoline
        cards_on_table[this.card_table_count] = card;
        card_table_count++;
    }

    public int getDeckCardCount() { //kthen numrin e letrave qe kan me u shpernda gjat lojes
        return deck.getCardCount();
    }

    public int getTableCardCount() {
        return card_table_count;   //kthen numrin e letrave qe jan ne tavoline
    }

    public boolean moreCards() {
        return deck.moreCards();  //tregon nese ka apo jo me letra qe duhet shperndare
    }

}
