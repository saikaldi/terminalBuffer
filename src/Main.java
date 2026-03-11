
public class Main {
    public static void main(String[] args) {
        TerminalBuffer buffer = new TerminalBuffer(5, 3);
        buffer.writeText("Hello");
        buffer.insertEmptyLine();
        buffer.writeText("World");

        System.out.println("=== Screen ===");
        System.out.println(buffer.getScreenContent());

        System.out.println("=== Full Content (scrollback + screen) ===");
        System.out.println(buffer.getFullContent());
    }
}