package Display;

import Rules.Mark;
import Table.GameTable;

public class CLDisp implements Display {

    public void display(GameTable table) {
        Mark[][] data = table.getTable();
        int width = table.getWidth();

        int strSize = (width + 2) * (width * 2 + 1) + width;
        StringBuilder sbuilder = new StringBuilder(strSize);

        for (int i = 0; i < 2 * width + 1; i++)
            sbuilder.append('-');
        sbuilder.append('\n');

        for (int i = 0; i < width; i++) {
            sbuilder.append('|');
            for (int j = 0; j < width; j++) {
                char c = data[i][j].toString().charAt(0);
                if (data[i][j] == Mark.Empty)
                    c = ' ';
                sbuilder.append(c);
                sbuilder.append('|');
            }
            sbuilder.append('\n');
        }

        for (int i = 0; i < 2 * width + 1; i++)
            sbuilder.append('-');
        sbuilder.append('\n');

        String str = sbuilder.toString();
        System.out.println(str);
    }

}
