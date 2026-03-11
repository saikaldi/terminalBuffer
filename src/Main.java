//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        TerminalBuffer buffer = new TerminalBuffer(10, 5);

        buffer.writeText("Hello");

        System.out.println(buffer.getLine(0));
    }
}