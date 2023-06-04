import javax.swing.*;

public class PlayerVsPlayer extends GameField {

    public PlayerVsPlayer(ConfigurationField configurationField) {
        super(configurationField);
    }

    @Override
    void checkEnd() {
        if (winCheck())
        {
            String winner = step % 2 == 1 ? "Крестики" : "Нолики";
            JOptionPane.showMessageDialog(this, "Победа! Победили " + winner);
            for (int i = 0; i < 9; i++) {
                JButton button = (JButton) fieldGrid.getComponent(i);
                button.setEnabled(false);
            }
            isEnd = true;
        }
        if (checkMoves())
        {
            JOptionPane.showMessageDialog(this, "Ничья!");
            isEnd = true;
        }
    }
}
