import Display.Display;
import Player.AiPlayer;
import Player.Player;
import Player.UserPlayer;
import Rules.Mark;
import Rules.Move;
import Table.GameTable;
import Table.TicTacToeTable;
import Display.CLDisp;

import java.util.Random;

public class Main {

    public static void main(String[] args){
        System.out.println("Hello world");


        int tableWidth = 3;

        // create game elements
        GameTable table = new TicTacToeTable(tableWidth, tableWidth);
        Player[] players = new Player[2];
        players[0] = new AiPlayer(Mark.O);
        players[1] = new UserPlayer(Mark.X);
        Display disp = new CLDisp();

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
            // display new state
            disp.display(table);

            // ask player to move
            Move mov = players[current].play(table);

            // check move is valid
            int row = mov.getRow();
            int col = mov.getCol();
            if (row < 0 || col < 0 || row >= tableWidth || col >= tableWidth) {
                System.out.println("move not in table bounds");
                continue;
            }

            if (!table.isValidMove(mov)) {
                System.out.println("move not valid. try again");
                continue;
            }

            // play the move
            table.set(mov);

            // check if game is over
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

            // next player turn
            current += 1;
            current %= 2;
        }

        // show game state after game over
        disp.display(table);
    }
}
