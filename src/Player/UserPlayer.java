package Player;

import Rules.Mark;
import Rules.Move;
import Table.GameTable;

import java.util.Scanner;

public class UserPlayer implements Player {

    private Scanner input;
    private Mark mark;

    public  UserPlayer(Mark m) {
        input = new Scanner(System.in);
        mark = m;
    }

    @Override
    public Move play(GameTable table) {
        System.out.println("Your turn");
        System.out.println(("Row: "));
        int row = input.nextInt();
        System.out.println(("Column: "));
        int col = input.nextInt();
        Move move = new Move(row, col, mark);
        return move;
    }
}
