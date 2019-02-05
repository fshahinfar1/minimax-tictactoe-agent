import Player.AiPlayer;
import Player.Player;
import Player.UserPlayer;
import Rules.Mark;
import Rules.Move;
import Table.GameTable;
import Table.TicTacToeTable;

import java.util.Random;

public class Main {

    public static void main(String[] args){
        System.out.println("Hello world");


        GameTable table = new TicTacToeTable(3, 3);
        Player[] players = new Player[2];
        players[0] = new AiPlayer(Mark.O);
        players[1] = new UserPlayer(Mark.X);


        // choose a random player to start the game
        Random dice = new Random();
        double val = dice.nextDouble();
        System.out.println(val);
        int current = 0;
        if (val > 0.5) {
            current = 1;
        }

        boolean run = true;
        while (run) {
            Move mov = players[current].play(table);
            table.set(mov);
            if (table.isDraw()) {
                System.out.println("draw!");
                run = false;
                break;
            }
            Mark winner = table.hasWinner();
            if (winner != Mark.Empty) {
                System.out.println(("Winner: " + winner.toString()));
                run = false;
                break;
            }

            current += 1;
            current %= 2;
        }
    }
}
