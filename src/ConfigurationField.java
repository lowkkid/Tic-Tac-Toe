import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ConfigurationField extends JFrame {

    private static final JButton START_GAME_BUTTON = new JButton("Начать игру!");

    private static final JLabel CHOOSE_GAME_MODE = new JLabel("Выберите режим игры:");

    private static final JRadioButton PLAYER_VS_PLAYER_BUTTON = new JRadioButton("Игрок против игрока");

    private static final JRadioButton PLAYER_VS_AI_BUTTON = new JRadioButton("Игрок против компьютера");

    private final GameField gameField = new GameField();

    public ConfigurationField() {
        super("Configure Game");
        this.setSize(400, 250);
        Container container = this.getContentPane();
        SpringLayout layout = new SpringLayout();
        container.setLayout(layout);

        container.add(CHOOSE_GAME_MODE);

        ButtonGroup group = new ButtonGroup();
        group.add(PLAYER_VS_AI_BUTTON);
        group.add(PLAYER_VS_PLAYER_BUTTON);
        container.add(PLAYER_VS_AI_BUTTON);
        PLAYER_VS_AI_BUTTON.setSelected(true);
        container.add(PLAYER_VS_PLAYER_BUTTON);
        container.add(START_GAME_BUTTON);

        layout.putConstraint(SpringLayout.NORTH, CHOOSE_GAME_MODE,
                15, SpringLayout.NORTH, container);
        layout.putConstraint(SpringLayout.WEST, CHOOSE_GAME_MODE,
                (this.getWidth() - CHOOSE_GAME_MODE.getPreferredSize().width) / 2, SpringLayout.WEST, container);
        layout.putConstraint(SpringLayout.NORTH, PLAYER_VS_AI_BUTTON,
                15, SpringLayout.SOUTH, CHOOSE_GAME_MODE);
        layout.putConstraint(SpringLayout.WEST, PLAYER_VS_AI_BUTTON,
                (this.getWidth() - PLAYER_VS_AI_BUTTON.getPreferredSize().width) / 2, SpringLayout.WEST, container);
        layout.putConstraint(SpringLayout.NORTH, PLAYER_VS_PLAYER_BUTTON,
                5, SpringLayout.SOUTH, PLAYER_VS_AI_BUTTON);
        layout.putConstraint(SpringLayout.WEST, PLAYER_VS_PLAYER_BUTTON,
                (this.getWidth() - PLAYER_VS_PLAYER_BUTTON.getPreferredSize().width) / 2, SpringLayout.WEST, container);
        layout.putConstraint(SpringLayout.NORTH, START_GAME_BUTTON,
                100, SpringLayout.NORTH, PLAYER_VS_PLAYER_BUTTON);
        layout.putConstraint(SpringLayout.WEST, START_GAME_BUTTON,
                (this.getWidth() - START_GAME_BUTTON.getPreferredSize().width) / 2, SpringLayout.WEST, container);


        START_GAME_BUTTON.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                gameField.setVisible(true);
                setVisible(false);
            }
        });





        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
