package Table;

import Rules.Mark;
import Rules.Move;

public interface GameTable {
    void set (Move move);
    Mark hasWinner(Move move);
    boolean isDraw();
    Mark[][] getTable();
}
