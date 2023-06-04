import javax.swing.*;

public class GameField extends JFrame {

    private char[][] field;

    public GameField() {
        super("Tic-Tac-Toe");
        setSize(800, 500);

        setVisible(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }


}
