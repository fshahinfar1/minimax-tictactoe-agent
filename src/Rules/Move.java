package Rules;

public class Move {
    private int row;
    private int col;
    private Mark mark;

    public Move (int r, int c, Mark m) {
        row = r;
        col = c;
        mark = m;
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }

    public Mark getMark() {
        return mark;
    }
}
