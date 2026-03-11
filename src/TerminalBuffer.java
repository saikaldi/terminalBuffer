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
}