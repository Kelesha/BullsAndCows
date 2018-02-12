package com.company;

import java.io.*;
import java.util.Arrays;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        gameMenu();

    }
    public static void gameMenu ()
    {
        Scanner console = new Scanner(System.in);
        System.out.println();
        System.out.println("Hello, player ! This is the game BULLS AND COWS!");
        System.out.println(" *Sponsored by The Dr. TM :P !");
        System.out.println();

        System.out.println("MAIN MENU:");
        System.out.println("P-Play the game");
        System.out.println("S-View scores");
        System.out.println("A-About game and how to play");
        System.out.println("X-Exit game");

        String n = console.nextLine();
        String strLower = n.toLowerCase();

        switch (strLower)
        {
            case "p": singlePlayer();
                break;
            case "s": scoresView();
                break;
            case "a": aboutGame();
                break;
            case "x": System.out.println("Good bye, hope will appreciate my code art later!");
                break;
            default: gameMenu();
        }
    }

    public static void playerGuessArr(int[]arrPlayer)
    {
        Scanner console = new Scanner(System.in);
        System.out.println("Please try to guess four different numbers: ");
        int n = Integer.parseInt(console.nextLine());

        if (n<=1000 || n>=9999)
        {
            System.out.println("Please enter valid numbers (must to be 4 with no separation)!");
            System.out.println();
            playerGuessArr(arrPlayer);
        }

        for (int i = 0; i <arrPlayer.length ; i++)
        {
            arrPlayer[arrPlayer.length-1-i]=n%10;
            n=n/10;
        }
        for (int i = 0; i <arrPlayer.length-1 ; i++)
        {
            for (int j = i; j <arrPlayer.length-1 ; j++)
            {
                if (j<3 && arrPlayer[i]!=arrPlayer[j+1])
                {
                    continue;
                }
                if (j<3 && arrPlayer[i]==arrPlayer[j+1])
                {
                    System.out.println("Please enter VALID and DIFFERENT numbers (must to be 4 with no separation)!");
                    System.out.println();
                    playerGuessArr(arrPlayer);
                }
            }
        }
    }
    public static void mathRandomArr(int []arrSecret ,int min, int max)
    {
        int range = (max - min) + 1;
        int numSecret = (int)(Math.random() * range) + min;

        for (int i = 0; i <arrSecret.length ; i++)
        {
            arrSecret[arrSecret.length-1-i]=numSecret%10;
            numSecret=numSecret/10;
        }

        for (int i = 0; i <arrSecret.length-1 ; i++)
        {
            for (int j = i; j <arrSecret.length-1 ; j++)
            {
                if (j<3 && arrSecret[i]!=arrSecret[j+1])
                {
                    continue;
                }
                if (j<3 && arrSecret[i]==arrSecret[j+1])
                {
                    mathRandomArr(arrSecret,1000,9999);
                }
            }
        }
    }

    public static void singlePlayer()
    {
        Scanner console = new Scanner(System.in);

        System.out.print("Please enter your name: ");
        String playerName = console.nextLine();
        System.out.println();

        int numBulls=0;
        int numCows =0;

        int[] arrSecret = new int[4];
        int[] arrPlayer = new int[4];

        int counter = 7;
        int scores = 100;

        mathRandomArr(arrSecret,1000,9999);

        while(counter>0)
        {
            playerGuessArr(arrPlayer);

            numBulls=0;
            numCows=0;

            for (int i = 0; i < arrSecret.length; i++)
            {
                for (int j = 0; j < arrPlayer.length; j++)
                {

                    if (arrPlayer[i] == arrSecret[j] && i == j)
                    {
                        numBulls++;
                    }
                    else if (arrPlayer[i] == arrSecret[j] && i != j)
                    {
                        numCows++;
                    }
                }
            }
            if (numBulls==4)
            {
                System.out.println("You got it! Your scores are: "+ scores);
                System.out.println("The Secret number was: " + Arrays.toString(arrSecret));
                scoreNameWrighter(scores,playerName);
                gameMenu();
            }

            counter--;
            scores-=6;

            if (counter==0)
            {
                System.out.println("Bulls: " + numBulls);
                System.out.println("Cows: " + numCows);
                scores=0;
                System.out.println("Your scores are: " + scores);
                System.out.println("The Secret number was: " + Arrays.toString(arrSecret));
                scoreNameWrighter(scores,playerName);
                gameMenu();
            }
            else
            {
                System.out.println("Bulls: " + numBulls);
                System.out.println("Cows: " + numCows);
                System.out.println("You've got more " + counter + " tries.");
            }
        }

    }

    public static void scoreNameWrighter(int score, String name)
    {
        try {
            File statText = new File("F:\\WORK\\JAVA Progress\\BULLSandCOWS2\\Score.txt");
            FileOutputStream is = new FileOutputStream(statText);
            OutputStreamWriter osw = new OutputStreamWriter(is);
            Writer w = new BufferedWriter(osw);
            w.write(name +" "+ score);
            w.close();
        } catch (IOException e) {
            System.err.println("Problem writing to the file Score.txt");
        }

    }
    public static void scoresView()
    {


    }
    public static void aboutGame()
    {
        Scanner console = new Scanner(System.in);
        System.out.println("Description");
        System.out.println();
        System.out.println("One player, the Chooser (here the computer generator will be), thinks of a four-numbers and the other player, the Guesser (the user), tries to guess it.");
        System.out.println("The numbers can not start with 0 and can be between 0 and 9 include");
        System.out.println("- The Bulls are numbers correct in the right position.");
        System.out.println("- The Cows are numbers correct but in the wrong position.");
        System.out.println("- You have got 7 tries. After that you loose all the points and must start the game from the beginning with new numbers.");
        System.out.println();
        System.out.println("Example:");
        System.out.println();
        System.out.println("Secret number: 4271");
        System.out.println("Opponent's try: 1234");
        System.out.println("Answer: 1 bull and 2 cows.");
        System.out.println();
        System.out.println("If you want to go to MAIN MENU, please press 1");
        System.out.println("If you want to leave game, please press 2");

        int n = Integer.parseInt(console.nextLine());
        if (n==1)
        {
            gameMenu();
        }
        else
        {
            return;
        }
    }
}
