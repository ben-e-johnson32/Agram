package com.Ben;


import java.util.ArrayList;
import java.util.Scanner;

public class Trick
{
    // We need a scanner so the player can choose a card to play.
    private static Scanner numScanner = new Scanner(System.in);

    // A variable that tells us which player is going first.
    Player first;

    // A variable that tells us what suit the first player played (and therefore what suit the second player must play,
    // if able).
    String suit;

    // Two variables reflecting the winner and loser of the round.
    Player winner;
    Player loser;

    public Trick(Player first)
    {
        this.first = first;
    }

    public Card turn(Player going)
    {
        this.first = going;

        // If the player taking the turn is a human and no suit has been established for the round (i.e. the human is
        // going first), display the player's cards and have them choose one.
        if (going instanceof HumanPlayer && this.suit == null)
        {
            System.out.println("Play a card by entering the number before it:");
            for (int x = 0; x < going.hand.size(); x++)
            {
                System.out.println((x + 1) + ". " + going.hand.get(x));
            }

            Card choice;

            // A while true loop for validation.
            while (true)
            {
                try
                {
                    // Assuming the user chooses a number from the list above, get that card from their hand and break
                    // out of the validation loop.
                    int intChoice = numScanner.nextInt() - 1;
                    choice = going.hand.get(intChoice);
                    break;
                }

                catch (IndexOutOfBoundsException ioobe)
                {
                    // If they chose an invalid number, display a message and have them input again.
                    System.out.println("Invalid input. Enter a valid number.");
                    continue;
                }

                // Confused here. I wanted to catch it if the user enters a non-integer character, but it wouldn't
                // go back to the try block - it just prints the error message infinitely.
//                catch (InputMismatchException ime)
//                {
//                    System.out.println("Invalid input. Enter a valid number.");
//                    continue;
//                }
            }

            // Remove the chosen card from the player's hand and set the suit for the round.
            going.hand.remove(choice);
            this.suit = choice.getSuit();
            return choice;
        }

        // If the computer player is going second,
        else if (going instanceof ComputerPlayer && this.suit != null)
        {
            // Instantiate a junk card. Just used to find the lowest value card.
            // (To me, it seems like generally the best strategy in Agram is to play your lowest cards first so you
            // have a better chance to win the last round. Of course, it's more complicated than that (i.e. if you have
            // many cards of one suit it would be better to play those first to get a sense of how many cards of that
            // suit the opposing player has)).
            Card toPlay = new Card("x", 20);

            // For each card in the player's hand,
            for (Card c : going.hand)
            {
                // if the card matches the suit of the round and its value is lower than any other matching card,
                // make it the card to play.
                if (c.getSuit().equals(this.suit) && c.getValue() < toPlay.getValue())
                {
                    toPlay = c;
                }
            }

            // If no cards matched the suit of the round, find the lowest value amongst the cards.
            if (toPlay.getValue() == 20)
            {
                for (Card c : going.hand)
                {
                    if (c.getValue() < toPlay.getValue())
                    {
                        toPlay = c;
                    }
                }
            }

            // Remove the card to play from the player's hand.
            going.hand.remove(toPlay);
            return toPlay;
        }

        // If the computer player is going first,
        else if (going instanceof ComputerPlayer && this.suit == null)
        {
            // Another junk card for comparison.
            Card toPlay = new Card("x", 20);

            // Finds the lowest value amongst the cards in the hand.
            for (Card c : going.hand)
            {
                if (c.getValue() < toPlay.getValue())
                {
                    toPlay = c;
                }
            }

            // Set the suit for the round and remove the chosen card from the hand.
            this.suit = toPlay.getSuit();
            going.hand.remove(toPlay);
            return toPlay;
        }

        // If the human player is going second,
        else if (going instanceof HumanPlayer && this.suit != null)
        {
            // We need a new list for the cards that are valid to play (i.e. their suit matches the suit of the card
            // the computer player played.
            ArrayList<Card> validCards = new ArrayList<>();

            System.out.println("Play a card by entering the number before it:");

            // Add all valid cards to the array list.
            for (int x = 0; x < going.hand.size(); x++)
            {
                if (going.hand.get(x).getSuit().equals(this.suit))
                {
                    validCards.add(going.hand.get(x));
                }
            }

            // If any cards were valid,
            if (validCards.size() > 0)
            {
                // Display each valid card in a list for the user to choose from.
                for (int y = 0; y < validCards.size(); y++)
                {
                    System.out.println((y + 1) + ". " + validCards.get(y).toString());
                }

                Card choice;

                // A while true loop for validation, as above.
                while (true)
                {
                    try
                    {
                        int intChoice = numScanner.nextInt();
                        choice = validCards.get(intChoice - 1);
                        break;
                    }

                    catch (IndexOutOfBoundsException ioobe)
                    {
                        System.out.println("Invalid input. Enter a valid number.");
                        continue;
                    }

//                    catch (InputMismatchException ime)
//                    {
//                        System.out.println("Invalid input. Enter a valid number.");
//                        continue;
//                    }
                }

                // Remove the chosen card from the hand.
                going.hand.remove(choice);
                return choice;
            }

            else
            {
                // If no valid cards were in the player's hand, display all cards.
                for (int z = 0; z < going.hand.size(); z++)
                {
                    System.out.println((z + 1) + ". " + going.hand.get(z).toString());
                }

                Card choice;

                // While true for validation. User chooses any card from their hand to play.
                while (true)
                {
                    try
                    {
                        int intChoice = numScanner.nextInt();
                        choice = going.hand.get(intChoice - 1);
                        break;
                    }

                    catch (IndexOutOfBoundsException ioobe)
                    {
                        System.out.println("Invalid input. Enter a valid number.");
                        continue;
                    }
                }

                // Remove the chosen card.
                going.hand.remove(choice);
                return choice;
            }
        }

        return null;
    }
}
