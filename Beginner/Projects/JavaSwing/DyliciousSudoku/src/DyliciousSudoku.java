import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class DyliciousSudoku implements ActionListener, Runnable 
{

    private JFrame frame;
    private JPanel panel;
    private JButton[][] buttons;
    private JButton checkButton;
    private final int WIDTH = 550;
    private final int HEIGHT = 550;

    public DyliciousSudoku() 
    {
        frame = new JFrame("Sudoku Grid");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(WIDTH, HEIGHT);

        panel = new JPanel(new GridLayout(9, 9, 2, 2));
        frame.add(panel, BorderLayout.CENTER);

        // setting up check button
        checkButton = new JButton("Check puzzle");
        checkButton.setPreferredSize(new Dimension(50,50));
        checkButton.addActionListener(new checkAction());
        frame.add(checkButton, BorderLayout.PAGE_END);

        buttons = new JButton[9][9];
        for (int row = 0; row < 9; row++) 
        {
            for (int col = 0; col < 9; col++) 
            {
                JButton button = new JButton();
                button.setPreferredSize(new Dimension(50, 50));
                button.addActionListener(this);
                buttons[row][col] = button;
                panel.add(button);
                
                // create grouping of buttons with borders
                if ((row < 3 || row > 5) && (col < 3 || col > 5)) 
                {
                    button.setBorder(BorderFactory.createMatteBorder(5, 5, 5, 5, Color.BLACK));
                } else if ((row < 3 || row > 5) && (col >= 3 && col <= 5)) 
                {
                    button.setBorder(BorderFactory.createMatteBorder(5, 5, 5, 5, Color.BLUE));
                } else if ((row >= 3 && row <= 5) && (col < 3 || col > 5)) 
                {
                    button.setBorder(BorderFactory.createMatteBorder(5, 5, 5, 5, Color.BLUE));
                } else 
                {
                    button.setBorder(BorderFactory.createMatteBorder(5, 5, 5, 5, Color.BLACK));
                }
            }
        }

        // dynamically set font size based on window size
        frame.addComponentListener(new ComponentAdapter() 
        {
            public void componentResized(ComponentEvent e) 
            {
                int fontSize = (int) (panel.getHeight() * 0.5 / 9);
                for (int row = 0; row < 9; row++) 
                {
                    for (int col = 0; col < 9; col++) 
                    {
                        buttons[row][col].setFont(new Font("Serif", Font.BOLD, fontSize));
                    }
                }
            }
        });

        frame.setVisible(true);
    }
 
    private class checkAction implements ActionListener 
    {
        @Override
        public void actionPerformed(ActionEvent e)
        {
            // check for blanks
            for (int i=0; i < 9; i++)
            {
                for (int j=0; j < 9; j++)
                {
                    String text = buttons[i][j].getText();
                    if (text.isEmpty())
                    {
                        JOptionPane.showMessageDialog(frame, "Blank at row: " + (i + 1) + ", and column: " + (j + 1) + ".", "User Error", JOptionPane.WARNING_MESSAGE);
                        return;
                    }
                }
            }
            
            // if no blanks, check for repeating numbers using 2^(n-1), check pic on phone
            abc
        }
    }


    @Override
    public void actionPerformed(ActionEvent e) 
    {
        // handle button clicks here
        for (int i=0; i < 9; i++)
        {
            for (int j=0; j < 9; j++)
            {
                if (e.getSource() == buttons[i][j])
                {
                    txtSet(i, j);
                }
            }
        }
    }

    public void txtSet(int i, int j) 
    {
        if (buttons[i][j].getText() == "" || buttons[i][j].getText() == "9")
        {
            buttons[i][j].setText("1");
        } 
        else if (buttons[i][j].getText() == "1")
        {
            buttons[i][j].setText("2");
        }
        else if (buttons[i][j].getText() == "2")
        {
            buttons[i][j].setText("3");
        }
        else if (buttons[i][j].getText() == "3")
        {
            buttons[i][j].setText("4");
        }
        else if (buttons[i][j].getText() == "4")
        {
            buttons[i][j].setText("5");
        }
        else if (buttons[i][j].getText() == "5")
        {
            buttons[i][j].setText("6");
        }
        else if (buttons[i][j].getText() == "6")
        {
            buttons[i][j].setText("7");
        }
        else if (buttons[i][j].getText() == "7")
        {
            buttons[i][j].setText("8");
        }
        else if (buttons[i][j].getText() == "8")
        {
            buttons[i][j].setText("9");
        }
    }

    @Override
    public void run() 
    {
        // seems to start at the beginning of the instance
    }

    public static void main(String[] args) 
    {
        new DyliciousSudoku();
    }
}
