package Player;

import Rules.Mark;
import Rules.Move;
import Table.GameTable;
import Table.TicTacToeTable;

import java.util.ArrayList;
import java.util.List;

public class AiPlayer implements Player {

    private Mark mark;
    private int winValue = 10;
    private int drawValue = 0;

    private Move chosen;

    public AiPlayer(Mark m) {
        mark = m;
    }

    @Override
    public Move play(GameTable table) {
        int value = miniMax(table, true);
        return chosen;
    }

    private int miniMax(GameTable table, boolean maximum) {
        chosen = null;
        if (table.isDraw()) {
            return drawValue;
        }
        Mark winner = table.hasWinner();
        if (winner == this.mark) {
            return winValue;
        } else if (winner != Mark.Empty) {
            return -winValue;
        }

        Mark mark = this.mark;
        if (!maximum) {
            if (mark == Mark.O) {
                mark = Mark.X;
            } else {
                mark = Mark.O;
            }
        }

        List<State> states = nextStates((TicTacToeTable) table, mark);

        if (maximum) {
            int value = -Integer.MAX_VALUE;
            for (State st: states) {
                int stateVal = miniMax(st.table, false);
                if (stateVal > value) {
                    value = stateVal;
                    chosen = st.move;
                }
            }
            return value;

        } else {
            int value = Integer.MAX_VALUE;
            for (State st: states) {
                int stateVal = miniMax(st.table, true);
                if (stateVal < value) {
                    value = stateVal;
                    chosen = st.move;
                }
            }
            return  value;
        }
    }

    private List<Move> getMoves(GameTable table, Mark mark) {
        List<Move> moves = new ArrayList<Move>();
        int w = table.getWidth();
        Mark[][] t = table.getTable();

        for (int r = 0; r < w; r++) {
            for (int c = 0; c < w; c++) {
                if (t[r][c] == Mark.Empty) {
                    Move m = new Move(r, c, mark);
                    moves.add(m);
                }
            }
        }
        return moves;
    }

    private List<State> nextStates(TicTacToeTable table, Mark m) {
        List<Move> moves = getMoves(table, mark);

        List<State> states = new ArrayList<State>();
        for (Move move: moves) {
            GameTable t = TicTacToeTable.from(table);
            t.set(move);
            State state = new State(t, move);
            states.add(state);
        }

        return states;
    }

    private class State {
        GameTable table;
        Move move;

        State(GameTable t, Move m) {
            table = t;
            move = m;
        }
    }
}
