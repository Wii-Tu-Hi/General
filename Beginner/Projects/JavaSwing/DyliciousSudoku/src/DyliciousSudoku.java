import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

public class DyliciousSudoku implements ActionListener 
{

    private JFrame frame;
    private JPanel panel;
    private JButton[][] buttons;
    private JButton checkButton;
    private final int WIDTH = 550;
    private final int HEIGHT = 550;
    String[][] buttonString;
    Random rand = new Random();
    boolean repeats;
    String B, C, D;
    int b, c, d, b1, c1, d1, b2, c2, d2;
    double[][] buttonDouble;
    double[][] doubleSums;
    int[][] doubleInt;

    public static void main(String[] args) 
    {
        new DyliciousSudoku();
    }

    public DyliciousSudoku() 
    {
        // frame construction
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

        // play field constuction
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
        startGame();
    }

    public void startGame() // handles the spots which are grayed out
    {
        int a = JOptionPane.showConfirmDialog(frame, "Ready to start?", "Welcome", JOptionPane.YES_NO_OPTION);
        if (a == JOptionPane.NO_OPTION)
        {
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.dispose();
        }
        else if (a == JOptionPane.YES_OPTION)
        {
            // button x
            b1 = rand.nextInt(8);
            c1 = rand.nextInt(8);
            d1 = rand.nextInt(8);

            // button y
            b2 = rand.nextInt(8);
            c2 = rand.nextInt(8);
            d2 = rand.nextInt(8);

            // button text values
            b = rand.nextInt(8) + 1;
            c = rand.nextInt(8) + 1;
            d = rand.nextInt(8) + 1;

            while (b == c || b == d || c == d)
            {
                b = rand.nextInt(8) + 1;
                c = rand.nextInt(8) + 1;
                d = rand.nextInt(8) + 1;
            }

            // convert int value to String
            B = String.valueOf(b);
            C = String.valueOf(c);
            D = String.valueOf(d);

            // assign random values to buttons
            buttons[b1][b2].setText(B);
            buttons[c1][c2].setText(C);
            buttons[d1][d2].setText(D);
            buttons[b1][b2].setEnabled(false);
            buttons[c1][c2].setEnabled(false);
            buttons[d1][d2].setEnabled(false);
        }
    }

    public void eraseBoard()
    {
        for (int i=0; i < 9; i++)
        {
            for (int j=0; j < 9; j++)
            {
                if (!buttons[i][j].isEnabled())
                {
                    buttons[i][j].setEnabled(true);
                }
                buttons[i][j].setText("");
            }
        }
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

            // check for repeating numbers
            repeats = repeatNum();
            if (repeats)
            {
                int a = JOptionPane.showConfirmDialog(frame, "Repeats are present. Do you give up?", "Repeats!", JOptionPane.YES_NO_OPTION);
                if (a == JOptionPane.YES_OPTION)
                {
                    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                    eraseBoard();
                    startGame();
                }
            } else
            {
                int a = JOptionPane.showConfirmDialog(frame, "No repeating numbers, you win! " + "New Game?", "Congrats", JOptionPane.YES_NO_OPTION);
                if (a == JOptionPane.YES_OPTION)
                {
                    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                    eraseBoard();
                    startGame();
                } else
                {
                    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                    frame.dispose();
                }
            }
        }
    }

    public boolean repeatNum()
    {
        repeats = false;
        buttonString = new String[9][9];
        buttonDouble = new double[9][9];

        for (int i=0; i < 9; i++)
        {
            for (int j=0; j < 9; j++)
            {
                buttonString[i][j] = buttons[i][j].getText();
                buttonDouble[i][j] = Math.pow(2, (Double.valueOf(buttonString[i][j]) - 1));
            }
        }

        // sum rows
        doubleSums = new double[9][3];
        doubleSums[0][0] = buttonDouble[0][0] + buttonDouble[0][1] + buttonDouble[0][2] + buttonDouble[0][3] + buttonDouble[0][4] + buttonDouble[0][5] + buttonDouble[0][6] + buttonDouble[0][7] + buttonDouble[0][8];
        doubleSums[1][0] = buttonDouble[1][0] + buttonDouble[1][1] + buttonDouble[1][2] + buttonDouble[1][3] + buttonDouble[1][4] + buttonDouble[1][5] + buttonDouble[1][6] + buttonDouble[1][7] + buttonDouble[1][8];
        doubleSums[2][0] = buttonDouble[2][0] + buttonDouble[2][1] + buttonDouble[2][2] + buttonDouble[2][3] + buttonDouble[2][4] + buttonDouble[2][5] + buttonDouble[2][6] + buttonDouble[2][7] + buttonDouble[2][8];
        doubleSums[3][0] = buttonDouble[3][0] + buttonDouble[3][1] + buttonDouble[3][2] + buttonDouble[3][3] + buttonDouble[3][4] + buttonDouble[3][5] + buttonDouble[3][6] + buttonDouble[3][7] + buttonDouble[3][8];
        doubleSums[4][0] = buttonDouble[4][0] + buttonDouble[4][1] + buttonDouble[4][2] + buttonDouble[4][3] + buttonDouble[4][4] + buttonDouble[4][5] + buttonDouble[4][6] + buttonDouble[4][7] + buttonDouble[4][8];
        doubleSums[5][0] = buttonDouble[5][0] + buttonDouble[5][1] + buttonDouble[5][2] + buttonDouble[5][3] + buttonDouble[5][4] + buttonDouble[5][5] + buttonDouble[5][6] + buttonDouble[5][7] + buttonDouble[5][8];
        doubleSums[6][0] = buttonDouble[6][0] + buttonDouble[6][1] + buttonDouble[6][2] + buttonDouble[6][3] + buttonDouble[6][4] + buttonDouble[6][5] + buttonDouble[6][6] + buttonDouble[6][7] + buttonDouble[6][8];
        doubleSums[7][0] = buttonDouble[7][0] + buttonDouble[7][1] + buttonDouble[7][2] + buttonDouble[7][3] + buttonDouble[7][4] + buttonDouble[7][5] + buttonDouble[7][6] + buttonDouble[7][7] + buttonDouble[7][8];
        doubleSums[8][0] = buttonDouble[8][0] + buttonDouble[8][1] + buttonDouble[8][2] + buttonDouble[8][3] + buttonDouble[8][4] + buttonDouble[8][5] + buttonDouble[8][6] + buttonDouble[8][7] + buttonDouble[8][8];

        // sum columns
        doubleSums[0][1] = buttonDouble[0][0] + buttonDouble[1][0] + buttonDouble[2][0] + buttonDouble[3][0] + buttonDouble[4][0] + buttonDouble[5][0] + buttonDouble[6][0] + buttonDouble[7][0] + buttonDouble[8][0];
        doubleSums[1][1] = buttonDouble[0][1] + buttonDouble[1][1] + buttonDouble[2][1] + buttonDouble[3][1] + buttonDouble[4][1] + buttonDouble[5][1] + buttonDouble[6][1] + buttonDouble[7][1] + buttonDouble[8][1];
        doubleSums[2][1] = buttonDouble[0][2] + buttonDouble[1][2] + buttonDouble[2][2] + buttonDouble[3][2] + buttonDouble[4][2] + buttonDouble[5][2] + buttonDouble[6][2] + buttonDouble[7][2] + buttonDouble[8][2];
        doubleSums[3][1] = buttonDouble[0][3] + buttonDouble[1][3] + buttonDouble[2][3] + buttonDouble[3][3] + buttonDouble[4][3] + buttonDouble[5][3] + buttonDouble[6][3] + buttonDouble[7][3] + buttonDouble[8][3];
        doubleSums[4][1] = buttonDouble[0][4] + buttonDouble[1][4] + buttonDouble[2][4] + buttonDouble[3][4] + buttonDouble[4][4] + buttonDouble[5][4] + buttonDouble[6][4] + buttonDouble[7][4] + buttonDouble[8][4];
        doubleSums[5][1] = buttonDouble[0][5] + buttonDouble[1][5] + buttonDouble[2][5] + buttonDouble[3][5] + buttonDouble[4][5] + buttonDouble[5][5] + buttonDouble[6][5] + buttonDouble[7][5] + buttonDouble[8][5];
        doubleSums[6][1] = buttonDouble[0][6] + buttonDouble[1][6] + buttonDouble[2][6] + buttonDouble[3][6] + buttonDouble[4][6] + buttonDouble[5][6] + buttonDouble[6][6] + buttonDouble[7][6] + buttonDouble[8][6];
        doubleSums[7][1] = buttonDouble[0][7] + buttonDouble[1][7] + buttonDouble[2][7] + buttonDouble[3][7] + buttonDouble[4][7] + buttonDouble[5][7] + buttonDouble[6][7] + buttonDouble[7][7] + buttonDouble[8][7];
        doubleSums[8][1] = buttonDouble[0][8] + buttonDouble[1][8] + buttonDouble[2][8] + buttonDouble[3][8] + buttonDouble[4][8] + buttonDouble[5][8] + buttonDouble[6][8] + buttonDouble[7][8] + buttonDouble[8][8];

        // sum boxes
        doubleSums[0][2] = buttonDouble[0][0] + buttonDouble[0][1] + buttonDouble[0][2] + buttonDouble[1][0] + buttonDouble[1][1] + buttonDouble[1][2] + buttonDouble[2][0] + buttonDouble[2][1] + buttonDouble[2][2];
        doubleSums[1][2] = buttonDouble[0][3] + buttonDouble[0][4] + buttonDouble[0][5] + buttonDouble[1][3] + buttonDouble[1][4] + buttonDouble[1][5] + buttonDouble[2][3] + buttonDouble[2][4] + buttonDouble[2][5];
        doubleSums[2][2] = buttonDouble[0][6] + buttonDouble[0][7] + buttonDouble[0][8] + buttonDouble[1][6] + buttonDouble[1][7] + buttonDouble[1][8] + buttonDouble[2][6] + buttonDouble[2][7] + buttonDouble[2][8];
        doubleSums[3][2] = buttonDouble[3][0] + buttonDouble[3][1] + buttonDouble[3][2] + buttonDouble[4][0] + buttonDouble[4][1] + buttonDouble[4][2] + buttonDouble[5][0] + buttonDouble[5][1] + buttonDouble[5][2];
        doubleSums[4][2] = buttonDouble[3][3] + buttonDouble[3][4] + buttonDouble[3][5] + buttonDouble[4][3] + buttonDouble[4][4] + buttonDouble[4][5] + buttonDouble[5][3] + buttonDouble[5][4] + buttonDouble[5][5];
        doubleSums[5][2] = buttonDouble[3][6] + buttonDouble[3][7] + buttonDouble[3][8] + buttonDouble[4][6] + buttonDouble[4][7] + buttonDouble[4][8] + buttonDouble[5][6] + buttonDouble[5][7] + buttonDouble[5][8];
        doubleSums[6][2] = buttonDouble[6][0] + buttonDouble[6][1] + buttonDouble[6][2] + buttonDouble[7][0] + buttonDouble[7][1] + buttonDouble[7][2] + buttonDouble[8][0] + buttonDouble[8][1] + buttonDouble[8][2];
        doubleSums[7][2] = buttonDouble[6][3] + buttonDouble[6][4] + buttonDouble[6][5] + buttonDouble[7][3] + buttonDouble[7][4] + buttonDouble[7][5] + buttonDouble[8][3] + buttonDouble[8][4] + buttonDouble[8][5];
        doubleSums[8][2] = buttonDouble[6][6] + buttonDouble[6][7] + buttonDouble[6][8] + buttonDouble[7][6] + buttonDouble[7][7] + buttonDouble[7][8] + buttonDouble[8][6] + buttonDouble[8][7] + buttonDouble[8][8];

        // convert to integer, arithmetic wasn't agreeing with doubles
        doubleInt = new int[9][3];
        for (int i = 0; i < 9; i++)
        {
            for (int j = 0; j < 3; j++)
            {
                doubleInt[i][j] = (int) doubleSums[i][j];
            }
        }

        // checking row sums
        if (!repeats)
        {
            for (int i=0; i < 9; i++)
            {
                if (i != 0)
                {
                    if (doubleInt[i][0] == doubleInt[0][0])
                    {
                        repeats = true;
                    }
                } else if (i != 1)
                {
                    if (doubleInt[i][0] == doubleInt[1][0])
                    {
                        repeats = true;
                    }
                } else if (i != 2)
                {
                    if (doubleInt[i][0] == doubleInt[2][0])
                    {
                        repeats = true;
                    }
                } else if (i != 3)
                {
                    if (doubleInt[i][0] == doubleInt[3][0])
                    {
                        repeats = true;
                    }
                } else if (i != 4)
                {
                    if (doubleInt[i][0] == doubleInt[4][0])
                    {
                        repeats = true;
                    }
                } else if (i != 5)
                {
                    if (doubleInt[i][0] == doubleInt[5][0])
                    {
                        repeats = true;
                    }
                } else if (i != 6)
                {
                    if (doubleInt[i][0] == doubleInt[6][0])
                    {
                        repeats = true;
                    }
                } else if (i != 7)
                {
                    if (doubleInt[i][0] == doubleInt[7][0])
                    {
                        repeats = true;
                    }
                } else if (i != 8)
                {
                    if (doubleInt[i][0] == doubleInt[8][0])
                    {
                        repeats = true;
                    }
                }
            }
        }

        // checking column sums
        if (!repeats)
        {
            for (int i=0; i < 9; i++)
            {
                if (i != 0)
                {
                    if (doubleInt[i][1] == doubleInt[0][1])
                    {
                        repeats = true;
                    }
                } else if (i != 1)
                {
                    if (doubleInt[i][1] == doubleInt[1][1])
                    {
                        repeats = true;
                    }
                } else if (i != 2)
                {
                    if (doubleInt[i][1] == doubleInt[2][1])
                    {
                        repeats = true;
                    }
                } else if (i != 3)
                {
                    if (doubleInt[i][1] == doubleInt[3][1])
                    {
                        repeats = true;
                    }
                } else if (i != 4)
                {
                    if (doubleInt[i][1] == doubleInt[4][1])
                    {
                        repeats = true;
                    }
                } else if (i != 5)
                {
                    if (doubleInt[i][1] == doubleInt[5][1])
                    {
                        repeats = true;
                    }
                } else if (i != 6)
                {
                    if (doubleInt[i][1] == doubleInt[6][1])
                    {
                        repeats = true;
                    }
                } else if (i != 7)
                {
                    if (doubleInt[i][1] == doubleInt[7][1])
                    {
                        repeats = true;
                    }
                } else if (i != 8)
                {
                    if (doubleInt[i][1] == doubleInt[8][1])
                    {
                        repeats = true;
                    }
                }
            }
        }

        // checking box sums
        if (!repeats)
        {
            for (int i=0; i < 9; i++)
            {
                if (i != 0)
                {
                    if (doubleInt[i][2] == doubleInt[0][2])
                    {
                        repeats = true;
                    }
                } else if (i != 1)
                {
                    if (doubleInt[i][2] == doubleInt[1][2])
                    {
                        repeats = true;
                    }
                } else if (i != 2)
                {
                    if (doubleInt[i][2] == doubleInt[2][2])
                    {
                        repeats = true;
                    }
                } else if (i != 3)
                {
                    if (doubleInt[i][2] == doubleInt[3][2])
                    {
                        repeats = true;
                    }
                } else if (i != 4)
                {
                    if (doubleInt[i][2] == doubleInt[4][2])
                    {
                        repeats = true;
                    }
                } else if (i != 5)
                {
                    if (doubleInt[i][2] == doubleInt[5][2])
                    {
                        repeats = true;
                    }
                } else if (i != 6)
                {
                    if (doubleInt[i][2] == doubleInt[6][2])
                    {
                        repeats = true;
                    }
                } else if (i != 7)
                {
                    if (doubleInt[i][2] == doubleInt[7][2])
                    {
                        repeats = true;
                    }
                } else if (i != 8)
                {
                    if (doubleInt[i][2] == doubleInt[8][2])
                    {
                        repeats = true;
                    }
                }
            }
        }

        return repeats;        
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
}