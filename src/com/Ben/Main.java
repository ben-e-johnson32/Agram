package com.Ben;

public class Main {

    public static void main(String[] args)
    {
        Deck deck = new Deck();
        for (Card c : deck.cards)
        {
            System.out.println(c.toString());
        }
    }
}
