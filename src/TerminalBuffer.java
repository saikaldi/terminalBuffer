import java.util.LinkedList;
import java.util.List;
public class TerminalBuffer {
    private int width;
    private int height;
    private Cell[][] screen;  // screen: rows x cols
    private int cursorRow;
    private int cursorCol;



    private List<Cell[]> scrollback;       // stores lines that scrolled off the screen
    private int maxScrollback = 100;       // maximum number of lines to keep

    public TerminalBuffer(int width, int height) {
        this.width = width;
        this.height = height;
        this.cursorRow = 0;
        this.cursorCol = 0;
        scrollback = new LinkedList<>();

        // create an empty screen
        screen = new Cell[height][width];
        for (int r = 0; r < height; r++) {
            for (int c = 0; c < width; c++) {
                screen[r][c] = new Cell();
            }
        }
    }

    // Get current cursor row
    public int getCursorRow() {
        return cursorRow;
    }

    // Get current cursor column
    public int getCursorCol() {
        return cursorCol;
    }

    // Set cursor position
    public void setCursor(int row, int col) {
        if (row >= 0 && row < height && col >= 0 && col < width) {
            cursorRow = row;
            cursorCol = col;
        }
    }

    // Write a character at the current cursor position
    public void writeChar(char c) {
        screen[cursorRow][cursorCol].setCharacter(c);
        cursorCol++;
        if (cursorCol >= width) {
            cursorCol = 0;
            cursorRow++;
            if (cursorRow >= height) {
                cursorRow = height - 1; // scrollback not implemented yet
            }
        }
    }

    // Write a full string starting at the current cursor, moving cursor automatically
    public void writeText(String text) {
        for (char c : text.toCharArray()) {
            writeChar(c);
        }
    }
    // Insert an empty line at the bottom of the screen, scroll content up
    public void insertEmptyLine() {
        // Move top line to scrollback
        scrollback.add(screen[0]);
        if (scrollback.size() > maxScrollback) {
            scrollback.remove(0); // remove oldest line
        }

        // Shift all screen lines up
        for (int r = 0; r < height - 1; r++) {
            screen[r] = screen[r + 1];
        }

        // Create a new empty line at the bottom
        screen[height - 1] = new Cell[width];
        for (int c = 0; c < width; c++) {
            screen[height - 1][c] = new Cell();
        }

        // Move cursor to start of the new line
        cursorRow = height - 1;
        cursorCol = 0;
    }
    // Clear all cells on the screen
    public void clearScreen() {
        for (int r = 0; r < height; r++) {
            for (int c = 0; c < width; c++) {
                screen[r][c] = new Cell();
            }
        }
        cursorRow = 0;
        cursorCol = 0;
    }

    // Clear the scrollback history
    public void clearScrollback() {
        scrollback.clear();
    }
    // get a single cell
    public Cell getCell(int row, int col) {
        return screen[row][col];
    }

    // get scrollback size
    public int getScrollbackSize() {
        return scrollback.size();
    }

    // Return the content of a specific row as a String
    public String getLine(int row) {
        StringBuilder sb = new StringBuilder();
        for (int c = 0; c < width; c++) {
            sb.append(screen[row][c].getCharacter());
        }
        return sb.toString();
    }

    // Return the entire screen as a single String (multiple lines)
    public String getScreenContent() {
        StringBuilder sb = new StringBuilder();
        for (int r = 0; r < height; r++) {
            sb.append(getLine(r));
            if (r != height - 1) sb.append("\n");
        }
        return sb.toString();
    }

    // Return scrollback + current screen as a single String
    public String getFullContent() {
        StringBuilder sb = new StringBuilder();

        // scrollback lines
        for (Cell[] line : scrollback) {
            for (Cell c : line) {
                sb.append(c.getCharacter());
            }
            sb.append("\n");
        }

        // current screen lines
        sb.append(getScreenContent());

        return sb.toString();
    }
    // Move cursor up by n rows (do not go above 0)
    public void moveCursorUp(int n) {
        cursorRow = Math.max(0, cursorRow - n);
    }

    // Move cursor down by n rows (do not go below height-1)
    public void moveCursorDown(int n) {
        cursorRow = Math.min(height - 1, cursorRow + n);
    }

    // Move cursor left by n columns (do not go below 0)
    public void moveCursorLeft(int n) {
        cursorCol = Math.max(0, cursorCol - n);
    }

    // Move cursor right by n columns (do not go beyond width-1)
    public void moveCursorRight(int n) {
        cursorCol = Math.min(width - 1, cursorCol + n);
    }
    // Insert a string at the current cursor position, with wrapping
    public void insertText(String text) {
        for (char c : text.toCharArray()) {
            writeChar(c);
        }
    }

    // Fill the current row with a specific character
    public void fillLine(char c) {
        for (int col = 0; col < width; col++) {
            screen[cursorRow][col].setCharacter(c);
        }
    }
    // Clear both screen and scrollback history
    public void clearScreenAndScrollback() {
        clearScreen();
        clearScrollback();
    }

    public List<Cell[]> getFullContentLines() {
        List<Cell[]> all = new LinkedList<>(scrollback);
        for (Cell[] row : screen) {
            all.add(row);
        }
        return all;
    }
}