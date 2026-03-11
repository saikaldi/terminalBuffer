import java.util.ArrayList;
import java.util.List;

public class TerminalBuffer {

    private int width;
    private int height;

    private int cursorRow = 0;
    private int cursorColumn = 0;

    private List<List<Cell>> screen;

    public TerminalBuffer(int width, int height) {

        this.width = width;
        this.height = height;

        screen = new ArrayList<>();

        for (int r = 0; r < height; r++) {

            List<Cell> line = new ArrayList<>();

            for (int c = 0; c < width; c++) {
                line.add(new Cell());
            }

            screen.add(line);
        }
    }

    public void writeText(String text) {

        for (char ch : text.toCharArray()) {

            if (cursorColumn >= width) {
                break;
            }

            Cell cell = screen.get(cursorRow).get(cursorColumn);
            cell.setCharacter(ch);

            cursorColumn++;
        }
    }

    public String getLine(int row) {

        StringBuilder sb = new StringBuilder();

        for (Cell cell : screen.get(row)) {
            sb.append(cell.getCharacter());
        }

        return sb.toString();
    }

}