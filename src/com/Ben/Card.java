package com.Ben;

public class Card
{
    String suit;
    int value;

    public Card(String s, int v)
    {
        this.suit = s;
        this.value = v;
    }

    public String getSuit()
    {
        return this.suit;
    }

    public int getValue()
    {
        return this.value;
    }

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