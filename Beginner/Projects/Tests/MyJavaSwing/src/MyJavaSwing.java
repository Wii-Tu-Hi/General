import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;

public class MyJavaSwing 
{
    public static void main (String[] arg) 
    {
        new DylSwing();
    }
}

class DylSwing implements ActionListener 
{
    JFrame f = new JFrame("Sudoku Swing");
    JButton[][] bton = new JButton[9][9];
    JPanel bt_pan = new JPanel();

    DylSwing() 
    {
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setMinimumSize(new Dimension(500, 500));
        f.getContentPane().setBackground(new Color(250, 184, 97));
        f.setLayout(new BorderLayout());
        f.setVisible(true);

        bt_pan.setLayout(new GridLayout(9,9,5,5));
        bt_pan.setBackground(new Color(0,0,0));

        for (int i = 0; i < 9; i++) 
        {
            for (int j = 0; j < 9; j++)
            {
                bton[i][j] = new JButton();
                bt_pan.add(bton[i][j]);
                bton[i][j].setFont(new Font("Serif", Font.BOLD, 80));
                bton[i][j].setFocusable(false);
                bton[i][j].setBackground(Color.darkGray);
                bton[i][j].setForeground(Color.white);
                bton[i][j].setBorder(BorderFactory.createRaisedBevelBorder());
                bton[i][j].addActionListener(this);
                bton[i][j].addMouseListener(new MouseListener()
                {   
                    public void mouseClicked(MouseEvent e) 
                    {  
                    }

                    public void mousePressed(MouseEvent e) 
                    {
                    }

                    public void mouseReleased(MouseEvent e) 
                    {
                    }

                    public void mouseEntered(MouseEvent e) 
                    {
                        for(int i=0; i < 9; i++)
                        {
                            for(int j=0; j < 9; j++)
                            {
                                if(e.getSource() == bton[i][j])
                                {
                                    bton[i][j].setBackground(Color.lightGray);
                                }
                            }
                        }
                    }

                    public void mouseExited(MouseEvent e) 
                    {
                        for(int i=0; i < 9; i++)
                        {
                            for(int j=0; j < 9; j++)
                            {
                                if(e.getSource() == bton[i][j])
                                {
                                    bton[i][j].setBackground(Color.darkGray);
                                }
                            }
                        }
                    }
                });
            }
        }
        
        f.add(bt_pan);

        startGame();
    }

    public void startGame() 
    {
        int a = JOptionPane.showConfirmDialog(f, "Ready to play Sudoku?");
        if(a == JOptionPane.NO_OPTION)
        {
            f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            f.dispose();
        }
    }

    public void actionPerformed(ActionEvent e) 
    {
        for(int i=0; i < 9; i++)
        {
            for(int j=0; j < 9; j++)
            {
                if(e.getSource() == bton[i][j])
                {
                    txtCheck(i, j);
                }
            }
        }
    }

    public void txtCheck(int i, int j) 
    {
        if(bton[i][j].getText() == "" || bton[i][j].getText() == "9")
        {
            bton[i][j].setText("1");
        } 
        else if (bton[i][j].getText() == "1")
        {
            bton[i][j].setText("2");
        }
        else if(bton[i][j].getText() == "2")
        {
            bton[i][j].setText("3");
        }
        else if(bton[i][j].getText() == "3")
        {
            bton[i][j].setText("4");
        }
        else if(bton[i][j].getText() == "4")
        {
            bton[i][j].setText("5");
        }
        else if(bton[i][j].getText() == "5")
        {
            bton[i][j].setText("6");
        }
        else if(bton[i][j].getText() == "6")
        {
            bton[i][j].setText("7");
        }
        else if(bton[i][j].getText() == "7")
        {
            bton[i][j].setText("8");
        }
        else if(bton[i][j].getText() == "8")
        {
            bton[i][j].setText("9");
        }
    }
}