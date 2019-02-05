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

    public Mark hasWinner(Move move) {
        int row = move.getRow();
        int col = move.getCol();

        int count = 0;
        Mark m = Mark.Empty;

        // check for column match
        for (int i = 0; i < width; i++) {
            Mark current = table[row][i];
            if (current == Mark.Empty) {
                count = 0;
                m = current;
            } else if (current == m) {
                count++;
                if (count == matchGoal)
                    return m;

            } else {
                count = 1;
                m = current;
            }
        }

        // check for row match
        count = 0;
        m = Mark.Empty;
        for (int i = 0; i < width; i++) {
            Mark current = table[i][col];
            if (current == Mark.Empty) {
                count = 0;
                m = current;
            } else if (current == m) {
                count++;
                if (count == matchGoal)
                    return m;
            } else {
                count = 1;
                m = current;
            }
        }

        // check for diagonal match
        count = 0;
        m = Mark.Empty;
        int cRow = row - col;
        int cCol = 0;
        if (cRow < 0) {
            cCol = -cRow;
            cRow = 0;
        }
        while (cRow < width && cCol < width) {
            Mark currnet = table[cRow][cCol];
            if (currnet == Mark.Empty) {
                count = 0;
                m = currnet;
            } else if (currnet == m) {
                count++;
                if (count == matchGoal)
                    return m;
            } else {
                count = 1;
                m = currnet;
            }
            cRow++;
            cCol++;
        }

        // check for back diagonal match
        count = 0;
        m = Mark.Empty;
        cRow = row + col;
        cCol = 0;
        if (cRow > width) {
            cCol = cRow - width;
            cRow = width - 1;
        }
        while (cRow > 0 && cCol < width) {
            Mark currnet = table[cRow][cCol];
            if (currnet == Mark.Empty) {
                count = 0;
                m = currnet;
            } else if (currnet == m) {
                count++;
                if (count == matchGoal)
                    return m;
            } else {
                count = 1;
                m = currnet;
            }
            cRow--;
            cCol++;
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
}
