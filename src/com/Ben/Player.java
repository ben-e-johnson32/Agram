package com.Ben;

import java.util.ArrayList;

public abstract class Player
{
    // A player just has a hand and a name. In this case, the subclasses just let us know who is taking a turn -
    // a human or the computer.
    ArrayList<Card> hand = new ArrayList<>();
    String name;
}

class HumanPlayer extends Player
{
    public HumanPlayer()
    {
        this.name = "Human";
    }
}

class ComputerPlayer extends Player
{
    public ComputerPlayer()
    {
        this.name = "NPC";
    }
}
