package com.Ben;

public class Main {

    public static void main(String[] args)
    {
        // Create a deck and shuffle it.
        Deck deck = new Deck();
        deck.shuffle();

        // Create two players - one human, one computer.
        HumanPlayer human = new HumanPlayer();
        ComputerPlayer npc = new ComputerPlayer();

        // In this version, the human is always the first dealer, so the NPC goes first.
        Player first = npc;
        Player second = human;

        // Deal 6 cards, 3 at a time, to each player.
        deck.deal(human, npc);

        // Display the player's hand, in case they have their own strategy.
        System.out.println("Player Hand:");
        for (Card c : human.hand)
        {
            System.out.println(c.toString());
        }
        // Commented out code to test that the deck-building and dealing are working properly.
//        System.out.println("NPC Hand:");
//        for (Card c : npc.hand)
//        {
//            System.out.println(c.toString());
//        }
//
//        System.out.println("Remaining cards in deck:");
//        for (Card c : deck.cards)
//        {
//            System.out.println(c.toString());
//        }

        // The game loop. Agram has six rounds.
        for (int x = 0; x < 6; x++)
        {
            // Each round is called a 'trick' - the first trick starts with the first player. In this case, the NPC.
            Trick t = new Trick(first);

            // Each player takes their turn.
            Card c1 = t.turn(first);
            System.out.println(first.name + " played " + c1.toString());
            Card c2 = t.turn(second);
            System.out.println(second.name + " played " + c2.toString());

            // If the first player played a higher card, or the second player didn't have a card with a matching suit,
            // the first player wins the trick and remains the first player.
            if (c1.getValue() > c2.getValue() || !c2.getSuit().equals(t.suit))
            {
                t.winner = first;
                t.loser = second;
                System.out.println(first.name + " wins the round.");
            }

            // If the second player played a higher card of the same suit, that player wins and becomes first player.
            else
            {
                t.winner = second;
                t.loser = first;
                System.out.println(second.name + " wins the round.");
            }

            // Set the first and second players based on the winner and loser of the 'trick'.
            first = t.winner;
            second = t.loser;

            // Display a message stating who won.
            System.out.println("End of round. " + first.name + " wins round " + (x + 1) + ".");

            // If it's the end of the final round, declare the winner of the game.
            if (x == 5)
            {
                System.out.println("Game over. " + first.name + " wins!");
            }
        }
    }
}