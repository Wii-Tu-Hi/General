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
    JFrame f = new JFrame();
    JButton[] bton = new JButton[81];
    JPanel bt_pan = new JPanel();

    DylSwing() 
    {
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setSize(1280,720);
        f.getContentPane().setBackground(new Color(250, 184, 97));
        f.setTitle("Sudoku Swing");
        f.setLayout(new BorderLayout());
        f.setVisible(true);

        bt_pan.setLayout(new GridLayout(9,9));
        bt_pan.setBackground(new Color(0, 0, 0));

        for (int i = 0; i < 81; i++) 
        {
            bton[i] = new JButton();
            bt_pan.add(bton[i]);
            bton[i].setFont(new Font("Serif", Font.BOLD, 80));
            bton[i].setFocusable(false);
            bton[i].setBackground(Color.black);
            bton[i].setForeground(Color.white);
            bton[i].addActionListener(this);
            bton[i].addMouseListener(new MouseListener()
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
                    for(int i=0; i < 81; i++)
                    {
                        if(e.getSource()==bton[i])
                        {
                            bton[i].setBackground(Color.DARK_GRAY);
                        }
                    }
                }

                public void mouseExited(MouseEvent e) 
                {
                    for(int i=0; i < 81; i++)
                    {
                        if(e.getSource()==bton[i])
                        {
                            bton[i].setBackground(Color.black);
                        }
                    }
                }
            });
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
        for(int i=0; i<81; i++)
        {
            if(e.getSource() == bton[i])
            {
                txtCheck(i);
            }
        }
    }

    public void txtCheck(int i) 
    {
        if(bton[i].getText() == "" || bton[i].getText() == "9")
        {
            bton[i].setText("1");
        } 
        else if (bton[i].getText() == "1")
        {
            bton[i].setText("2");
        }
        else if(bton[i].getText() == "2")
        {
            bton[i].setText("3");
        }
        else if(bton[i].getText() == "3")
        {
            bton[i].setText("4");
        }
        else if(bton[i].getText() == "4")
        {
            bton[i].setText("5");
        }
        else if(bton[i].getText() == "5")
        {
            bton[i].setText("6");
        }
        else if(bton[i].getText() == "6")
        {
            bton[i].setText("7");
        }
        else if(bton[i].getText() == "7")
        {
            bton[i].setText("8");
        }
        else if(bton[i].getText() == "8")
        {
            bton[i].setText("9");
        }
    }
}