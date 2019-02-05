package Table;

import Rules.Move;
import Rules.Mark;

public class TicTacToeTable implements GameTable {

    private Mark[][] table;
    private int width;
    private int matchGoal;
    private int emptyCells;

    public TicTacToeTable(int width, int goal) {
        this.width = width;
        matchGoal = goal;
        emptyCells = width * width;
        setupTable();
    }

    private void setupTable() {
        table = new Mark[width][width];
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < width; j++) {
                table[i][j] = Mark.Empty;
            }
        }
    }

    public void set(Move move)  {
        int row = move.getRow();
        int col = move.getCol();
        Mark m = move.getMark();

        if (row < 0 || col < 0 || row > width || col > width)
            throw new RuntimeException("Cell address is wrong");
        if (table[row][col] != Mark.Empty)
            throw new RuntimeException("This cell was field before");

        table[row][col] = m;
        emptyCells--;
    }

    public Mark hasWinner() {
        // row
        int count = 0;
        Mark matched = Mark.Empty;
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < width; j++) {
                Mark crt = table[i][j];
                if (crt == Mark.Empty) {
                    count = 0;
                    matched = Mark.Empty;
                } else if (crt == matched) {
                    count++;
                    if (count == matchGoal)
                        return matched;
                } else {
                    matched = crt;
                    count = 1;
                }
            }
        }
        // column
        count = 0;
        matched = Mark.Empty;
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < width; j++) {
                Mark crt = table[j][i];
                if (crt == Mark.Empty) {
                    count = 0;
                    matched = Mark.Empty;
                } else if (crt == matched) {
                    count++;
                    if (count == matchGoal)
                        return matched;
                } else {
                    matched = crt;
                    count = 1;
                }
            }
        }
        // diagonal
        count = 0;
        matched = Mark.Empty;
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < width; j++) {
                if (i + j >= width)
                    break;
                Mark crt = table[j][j + i];
                if (crt == Mark.Empty) {
                    count = 0;
                    matched = Mark.Empty;
                } else if (crt == matched) {
                    count++;
                    if (count == matchGoal)
                        return matched;
                } else {
                    matched = crt;
                    count = 1;
                }
            }
        }
        count = 0;
        matched = Mark.Empty;
        for (int i = 1; i < width; i++) {
            for (int j = 0; j < width; j++) {
                if (i + j >= width)
                    break;
                Mark crt = table[j + i][j];
                if (crt == Mark.Empty) {
                    count = 0;
                    matched = Mark.Empty;
                } else if (crt == matched) {
                    count++;
                    if (count == matchGoal)
                        return matched;
                } else {
                    matched = crt;
                    count = 1;
                }
            }
        }
        // back diagonal
        count = 0;
        matched = Mark.Empty;
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < width; j++) {
                if (i - j < 0)
                    break;
                Mark crt = table[j][i - j];
                if (crt == Mark.Empty) {
                    count = 0;
                    matched = Mark.Empty;
                } else if (crt == matched) {
                    count++;
                    if (count == matchGoal)
                        return matched;
                } else {
                    matched = crt;
                    count = 1;
                }
            }
        }
        count = 0;
        matched = Mark.Empty;
        for (int i = 1; i < width; i++) {
            for (int j = 0; j < width; j++) {
                if (i + j >= width)
                    break;
                Mark crt = table[i + j][width - j - 1];
                if (crt == Mark.Empty) {
                    count = 0;
                    matched = Mark.Empty;
                } else if (crt == matched) {
                    count++;
                    if (count == matchGoal)
                        return matched;
                } else {
                    matched = crt;
                    count = 1;
                }
            }
        }
        return Mark.Empty;
    }

    public boolean isDraw() {
        return emptyCells == 0;
    }

    public Mark[][] getTable() {
        Mark[][] tmpTable = new Mark[width][width];
        for (int i = 0; i < width; i++) {
            System.arraycopy(table[i], 0, tmpTable[i], 0, width);
        }
        return tmpTable;
    }

    public int getWidth() {
        return width;
    }

    public int getMatchGoal() {
        return matchGoal;
    }

    private void setTable(Mark[][] table) {
        this.table = table;
    }

    public static TicTacToeTable from (TicTacToeTable table) {
        int w = table.getWidth();
        int g = table.getMatchGoal();
        TicTacToeTable t = new TicTacToeTable(w, g);
        t.setTable(table.getTable());
        return t;
    }

    public boolean isValidMove(Move move) {
        int row = move.getRow();
        int col = move.getCol();
        return  table[row][col] == Mark.Empty;
    }
}
