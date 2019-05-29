package com.app;

import java.nio.charset.StandardCharsets;

public class TestCard2 {

    Table table;
    int hand_size = 4;
    int number_of_players = 2;

    public TestCard2() {
        table = new Table();

        Player[] players = new Player[number_of_players];
        for (int i = 0; i != number_of_players; i = i + 1) {
            players[i] = new Player(hand_size, i == 0);
        }

        table.appendTableCards(table.takeAHand(hand_size));

        shareCardsToPlayers(players);

        boolean processing = true;

        while (processing) {
            Player player = players[0];
            printPlayerStatus(player);
            playHuman(player);
            printStatus(players);

        }

        // make the players show their cards:
//        for (int i = 0; i != number_of_players; i = i + 1) {
//            System.out.println("Player " + i + " holds:");
//            printCards(players[i].showCards());
//        }
        System.out.println();
    }

//    private void printCards(Card[] hand) {
//        for (int i = 0; i != hand.length; i = i + 1) {
//            System.out.println(hand[i].getCount() + " of " + hand[i].getSuit());
//        }
//        System.out.println();
//    }

    private void shareCardsToPlayers(Player[] players) {
        System.out.println("********** sharing letters to players **********");

        for (Player player : players)
            player.setNewHandCards(table.takeAHand(hand_size));

        printStatus(players);
    }

    private void printStatus(Player[] players) {
        String lastCard = table.getLastTableCard() != null ? table.getLastTableCard().toString() : "none";

        System.out.println("Human player card Earned: " + players[0].getEarnedCardCount());
        System.out.println("PC player card Earned: " + players[1].getEarnedCardCount());
        System.out.println();
        System.out.println("Total deck cards: " + table.getDeckCardCount());
        System.out.println("Total table cards: " + table.getTableCardCount());
        System.out.println("Last cards on table: " + lastCard);

        System.out.println("*************************************************");
    }

    private void printPlayerStatus(Player player) {
        if (player.isHuman())
            System.out.println("********** Human Player **********");
        else
            System.out.println("********** Pc Player **********");
        System.out.println("Earned cards: " + player.getEarnedCardCount());
        System.out.println("Cards: ");

        Card[] handCards = player.getHandCards();

        for (int i = 0; i < player.getHandCount(); i++)
            System.out.println("   " + i + ". " + handCards[i]);
    }

    private void playHuman(Player player) {
        int selectedCardIndex = getConsoleMessage();
        if (selectedCardIndex <= player.getHandCount()) {

            Card selectedHandCard = player.pullCardFromHand(selectedCardIndex);
            Card lastTableCard = table.getLastTableCard();

            if (lastTableCard != null && selectedHandCard.getCount() == lastTableCard.getCount()) {
                table.appendTableCard(selectedHandCard);
                player.appendEarnedCards(table.pullTableCards());
            } else {
                table.appendTableCard(selectedHandCard);
            }

            System.out.println("Earned cards: " + player.getEarnedCardCount());
            System.out.println("*************************************************");
        } else {
            System.out.println("out of range index");
            playHuman(player);
        }
    }

    public void playPC(Player player) {
        int selectedCardIndex = getConsoleMessage();
        if (selectedCardIndex <= player.getHandCount()) {

            Card selectedHandCard = player.pullCardFromHand(selectedCardIndex);
            Card lastTableCard = table.getLastTableCard();

            if (lastTableCard != null && selectedHandCard.getCount() == lastTableCard.getCount()) {
                table.appendTableCard(selectedHandCard);
                player.appendEarnedCards(table.pullTableCards());
            } else {
                table.appendTableCard(selectedHandCard);
            }

            System.out.println("Earned cards: " + player.getEarnedCardCount());
            System.out.println("*************************************************");
        } else {
            System.out.println("out of range index");
            playHuman(player);
        }
    }

    private int getConsoleMessage() {
        try {
            byte[] b = new byte[1024];
            System.in.read(b);
            String text = new String(b, StandardCharsets.UTF_8);
            text = text.replace(text.charAt(text.length() - 1) + "", "");
            return Integer.valueOf(text.trim());

        } catch (Exception e) {

        }

        return 0;
    }

//    private boolean isGameEnded(Player[] players){
//      //  boolean ended = table.getDeckCardCount()>0 || ta;
//
//        for (int i = 0; i < players.length; i++) {
//
//        }
//
//        return ended;
//    }

    public static void main(String[] args) {
        new TestCard2();
    }
}