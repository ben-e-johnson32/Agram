package com.Ben;

import java.util.ArrayList;
import java.util.Collections;

public class Deck
{
    // An empty arraylist for the cards in the deck.
    public ArrayList<Card> cards;

    public Deck()
    {
        this.cards = new ArrayList<>();

        // An array of strings representing the suits in a deck of playing cards.
        String[] suits = {"Diamonds", "Hearts", "Clubs", "Spades"};

        // Initialize a card variable.
        Card card;

        // For each suit,
        for (String suit : suits)
        {
            // create the necessary cards for that suit. Since all twos are removed, and aces are valued at 11,
            // start the counter at 3.
            for (int x = 3; x < 12; x++)
            {
                card = new Card(suit, x);

                // The Ace of Spades is also removed, so as long as the card is not the Ace of Spades, add it.
                if (!card.toString().equals("Ace of Spades"))
                { this.cards.add(card); }
            }
        }
    }

    public void shuffle()
    {
        // Shuffle the deck using Collections.
        Collections.shuffle(this.cards);
    }

    public void deal(Player human, Player npc)
    {
        // Fill both players' hands.
        for (int x = 0; x < 2; x++)
        {
            for (int y = 0; y < 3; y++)
            {
                human.hand.add(this.cards.get(0));
                this.cards.remove(0);
            }
            for (int z = 0; z < 3; z++)
            {
                npc.hand.add(this.cards.get(0));
                this.cards.remove(0);
            }
        }
    }
}
