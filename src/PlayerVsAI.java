import javax.swing.*;

public class PlayerVsAI extends GameField{

    ConfigurationField configurationField;

    public PlayerVsAI(ConfigurationField configurationField) {
        super(configurationField);
    }

    @Override
    protected void move(JButton button, int index) {
        super.move(button, index);

        if (isEnd)
        {
            return;
        }
        int AIMove;
        do {
            AIMove = (int) (Math.random()*9);
        } while (field[AIMove / 3][AIMove % 3] != '\0');


        super.move((JButton) fieldGrid.getComponent(AIMove), AIMove);

    }

    @Override
    void checkEnd() {
        if (winCheck())
        {
            String dialog = step % 2 == 1 ? "Победа! Вы выиграили компьютер" : "Проигрыш! Победил компьютер";
            JOptionPane.showMessageDialog(this, dialog);
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
