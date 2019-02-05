package Player;
import Rules.Move;
import Table.GameTable;

public interface Player {
    public Move play(GameTable table);
}
