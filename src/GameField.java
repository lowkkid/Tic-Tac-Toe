import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Objects;

public abstract class GameField extends JFrame {

    protected char[][] field;

    protected int step = 0;

    protected static final JButton RESTART_BUTTON = new JButton("Начать заново");

    protected final JButton BACK_TO_CONFIGURATION_BUTTON = new JButton("Вернуться к настройкам");

    protected final ImageIcon ZERO_ICON = new ImageIcon(Objects.requireNonNull(getClass().getResource("images/zero.png")));

    protected final ImageIcon CROSS_ICON = new ImageIcon(Objects.requireNonNull(getClass().getResource("images/cross.png")));

    protected final JPanel fieldGrid = new JPanel();

    protected boolean isEnd = false;

    protected ConfigurationField configurationField;

    public GameField(ConfigurationField configurationField) {
        super("Tic-Tac-Toe");
        setSize(600, 700);
        this.configurationField = configurationField;


        JPanel mainGrid = new JPanel(new GridLayout(1, 2, 5, 0) );
        mainGrid.add (RESTART_BUTTON);
        mainGrid.add (BACK_TO_CONFIGURATION_BUTTON);
        JPanel flow = new JPanel(new FlowLayout(FlowLayout.CENTER));
        flow.add(mainGrid);
        Container container = getContentPane();
        container.add(flow, BorderLayout.SOUTH);


        fieldGrid.setSize(100,100);
        GridLayout layout = new GridLayout(3, 3, 0, 5);
        fieldGrid.setLayout(layout);
        init();
        container.add(fieldGrid);


        RESTART_BUTTON.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                init();
            }
        });

        BACK_TO_CONFIGURATION_BUTTON.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                dispose();
                configurationField.setVisible(true);
            }
        });

        setVisible(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    protected boolean winCheck() {
        if (field[0][0] == field[0][1] && field[0][1] == field[0][2] && field[0][0] != '\0')
        {
            for (int i = 0; i < 3; i++) {
                fieldGrid.getComponent(i).setBackground(Color.GREEN);
            }
            return true;
        }
        if (field[1][0] == field[1][1] && field[1][1] == field[1][2] && field[1][0] != '\0')
        {
            for (int i = 3; i < 6; i++) {
                fieldGrid.getComponent(i).setBackground(Color.GREEN);
            }
            return true;
        }
        if (field[2][0] == field[2][1] && field[2][1] == field[2][2] && field[2][0] != '\0')
        {
            for (int i = 6; i < 9; i++) {
                fieldGrid.getComponent(i).setBackground(Color.GREEN);
            }
            return true;
        }
        if (field[0][0] == field[1][0] && field[1][0] == field[2][0] && field[0][0] != '\0')
        {
            for (int i = 0; i < 9; i += 3) {
                fieldGrid.getComponent(i).setBackground(Color.GREEN);
            }
            return true;
        }
        if (field[0][1] == field[1][1] && field[1][1] == field[2][1] && field[0][1] != '\0')
        {
            for (int i = 1; i < 9; i += 3) {
                fieldGrid.getComponent(i).setBackground(Color.GREEN);
            }
            return true;
        }
        if ((field[0][2] == field[1][2] && field[1][2] == field[2][2] && field[0][2] != '\0'))
        {
            for (int i = 2; i < 9; i += 3) {
                fieldGrid.getComponent(i).setBackground(Color.GREEN);
            }
            return true;
        }
        if (field[0][0] == field[1][1] && field[1][1] == field[2][2] && field[0][0] != '\0')
        {
            for (int i = 0; i < 9; i += 4) {
                fieldGrid.getComponent(i).setBackground(Color.GREEN);
            }
            return true;
        }
        if (field[0][2] == field[1][1] && field[1][1] == field[2][0] && field[0][2] != '\0')
        {
            for (int i = 2; i < 8; i += 2) {
                fieldGrid.getComponent(i).setBackground(Color.GREEN);
            }
            return true;
        }
        return false;
    }

    protected boolean checkMoves() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (field[i][j] == '\0')
                {
                    return false;
                }
            }
        }
        return true;
    }

    private void init()
    {
        field = new char[3][3];
        step = 0;
        isEnd = false;
        fieldGrid.removeAll();
        for (int i = 0; i < 9; i++) {
            JButton button = new JButton();
            button.setBackground(Color.WHITE);
            button.setBorder(BorderFactory.createLineBorder(Color.BLACK));
            button.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    JButton clickedButton = (JButton) e.getSource();
                    int index = fieldGrid.getComponentZOrder(clickedButton);
                    move(clickedButton, index);
                    clickedButton.revalidate();
                    clickedButton.repaint();
                }
            });
            button.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseEntered(MouseEvent e) {
                    if (button.isEnabled())
                    {
                        button.setBackground(Color.GRAY);
                    }
                }

                @Override
                public void mouseExited(MouseEvent e) {
                    if (button.isEnabled())
                    {
                        button.setBackground(Color.WHITE);
                    }
                }
            });
            fieldGrid.add(button);
            fieldGrid.revalidate();
            fieldGrid.repaint();
        }
    }

    protected void move(JButton button, int index) {
        button.setEnabled(false);
        if (step % 2 == 0)
        {
            button.setIcon(CROSS_ICON);
            button.setDisabledIcon(CROSS_ICON);
            button.setDisabledSelectedIcon(CROSS_ICON);
            button.setBorder(null);
            button.setBackground(Color.WHITE);
            field[index / 3][index % 3] = 'X';
        } else {
            button.setIcon(CROSS_ICON);
            button.setDisabledIcon(ZERO_ICON);
            button.setDisabledSelectedIcon(ZERO_ICON);
            button.setBorder(null);
            button.setBackground(Color.white);
            field[index / 3][index % 3] = '0';
        }
        step++;

        checkEnd();
    }

    abstract void checkEnd();

}
