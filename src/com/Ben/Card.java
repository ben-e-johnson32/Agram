package com.Ben;

public class Card
{
    // A card has two attributes - a suit and a number value.
    String suit;
    int value;

    // Basic constructor for a card.
    public Card(String s, int v)
    {
        this.suit = s;
        this.value = v;
    }

    // Getters for the suit and the value.
    public String getSuit()
    {
        return this.suit;
    }

    public int getValue()
    {
        return this.value;
    }

    // A method to return the card in string form. A card with numeric value 11 is an ace.
    public String toString()
    {
        if (this.value != 11)
        {
            return this.value + " of " + this.suit;
        }

        else
        {
            return "Ace of " + this.suit;
        }
    }
}