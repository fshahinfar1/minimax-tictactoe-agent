package Table;

import Rules.Mark;
import Rules.Move;

public interface GameTable {
    void set (Move move);
    Mark hasWinner();
    boolean isDraw();
    Mark[][] getTable();
    int getWidth();
    boolean isValidMove(Move move);
}
