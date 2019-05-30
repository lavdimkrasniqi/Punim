package com.app;

import java.nio.charset.StandardCharsets;

public class TestCard2 {
    private Table table;
    private int hand_size = 4;
    private int number_of_players = 2;

    public TestCard2() {
        table = new Table();

        Player[] players = new Player[number_of_players];//kemi kriju lojtaret
        for (int i = 0; i != number_of_players; i = i + 1) {
            players[i] = new Player(hand_size, i == 0);//nese i ==0 lojtari qe luan eshte shfrytezuesi i app-it
        }

        table.appendTableCards(table.takeAHand(hand_size));//i shton 4 letra te reja ne fillim te lojes ne tabele

        shareCardsToPlayers(players);//iau shperndan nga 4 letra lojtareve

        boolean processing = true;

        while (processing) {
            for (Player player : players) {//for loop qe printon statusin e lojtareve varesishte nese eshte pc playeri ose personi
                printPlayerStatus(player);
                if (player.isHuman())
                    playHuman(player);
                else
                    playPC(player);
                printStatus(players);
            }
            if (players[0].getHandCount() == 0) {
                if (!table.moreCards())
                    processing = false;
                else
                    shareCardsToPlayers(players);
            }
        }
    }

    private void shareCardsToPlayers(Player[] players) {
        System.out.println("********** sharing letters to players **********");

        for (Player player : players)
            player.setNewHandCards(table.takeAHand(hand_size));//ia shton lojtareve letrat

        printStatus(players);
    }

    private void printStatus(Player[] players) {
        String lastCard = table.getLastTableCard() != null ? table.getLastTableCard().toString() : "none";

        System.out.println("Human player card Earned: " + players[0].getEarnedCardCount());//tregon per letrat qe i ka fitu playeri 0
        System.out.println("PC player card Earned: " + players[1].getEarnedCardCount());//tregon per letrat qe i ka fitu playeri 1
        System.out.println();
        System.out.println("Total deck cards: " + table.getDeckCardCount());//tregon letrat qe kan mbet per me u shpernda
        System.out.println("Total table cards: " + table.getTableCardCount());//tregon numrin e letrave qe jan ne tabele
        System.out.println("Last cards on table: " + lastCard);//printon letren e fundit

        System.out.println("*************************************************");
    }

    private void printPlayerStatus(Player player) {//kjo metode kthen statusin e lojtareve psh letrat qe mbajn ne ore cili eshte me radhe
        if (player.isHuman())
            System.out.println("********** Human Player **********");
        else
            System.out.println("********** Pc Player **********");
        System.out.println("Earned cards: " + player.getEarnedCardCount());//tregon letrat qe i ka fitu pc
        System.out.println("Cards: ");

        Card[] handCards = player.getHandCards();

        for (int i = 0; i < player.getHandCount(); i++) //printon letrat e lojtareve
            System.out.println("   " + i + ". " + handCards[i]);
    }

    private void playHuman(Player player) {
        int selectedCardIndex = getConsoleMessage();// merr indeksin e letres te selektuar ne konsole
        if (selectedCardIndex < player.getHandCount()) {//nese letra e selektuar eshte ma e vogel se nr i letrave qe ka ne dore

            Card selectedHandCard = player.pullCardFromHand(selectedCardIndex); //merre letren nga dora ne baze te indeksit
            Card lastTableCard = table.getLastTableCard();// merre letren e fundit ne tabele

            if (lastTableCard != null && selectedHandCard.getCount() == lastTableCard.getCount()) {
                table.appendTableCard(selectedHandCard);//nese letra e selektuar eshte e njejt me letren ne tabel shfaqe nje leter te re ne tabel dhe shfaqi letrat e fituara tek lojtari
                player.appendEarnedCards(table.pullTableCards());
            } else {
                table.appendTableCard(selectedHandCard); // nese letra nuk eshte e njejt me ate ne tabel vetem e shfaq ate ne tabele
            }

            System.out.println("Earned cards: " + player.getEarnedCardCount());
            System.out.println("*************************************************");
        } else {
            System.out.println("out of range index");
            playHuman(player);
        }
    }

    public void playPC(Player player) {
        int selectedCardIndex = (int) (Math.random() * player.getHandCount());// select a random card index from hand

        Card lastTableCard = table.getLastTableCard();  // get the last card in table
        Card[] handCards = player.getHandCards();

        if (lastTableCard != null)
            for (int i = 0; i < player.getHandCount(); i++)
                if (handCards[i].getCount() == lastTableCard.getCount()) {  //search if the any hand card matches with table card
                    selectedCardIndex = i;
                    break;
                }

        Card selectedHandCard = player.pullCardFromHand(selectedCardIndex);
        if (lastTableCard != null && selectedHandCard.getCount() == lastTableCard.getCount()) {
            table.appendTableCard(selectedHandCard);//if the selected card matches with the table card append the card on table and append earned cards to given player
            player.appendEarnedCards(table.pullTableCards());
        } else {
            table.appendTableCard(selectedHandCard); // if card is not the same with te table one just append it on the table
        }

        System.out.println("Earned cards: " + player.getEarnedCardCount());
        System.out.println("*************************************************");
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

    public static void main(String[] args) {
        new TestCard2();
    }
}