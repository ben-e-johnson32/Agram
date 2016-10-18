package com.Ben;

import java.util.ArrayList;

public class Deck
{
    public ArrayList<Card> cards;

    public Deck()
    {
        this.cards = new ArrayList<>();
        String[] suits = {"Diamonds", "Hearts", "Clubs", "Spades"};
        Card card;

        for (String suit : suits)
        {
            for (int x = 3; x < 12; x++)
            {
                card = new Card(suit, x);
                if (!card.toString().equals("Ace of Spades"))
                { this.cards.add(card); }
            }
        }
    }
}
