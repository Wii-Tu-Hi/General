import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;

public class MyJavaSwing {
    public static void main (String[] arg) {
        new DylSwing();
    }
}

class DylSwing implements ActionListener {
    JFrame f = new JFrame();
    JButton[] bton = new JButton[81];
    JPanel bt_pan = new JPanel();

    DylSwing() {
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setSize(1920,1080);
        f.getContentPane().setBackground(new Color(250, 184, 97));
        f.setTitle("Tic Tac Toe Game in Swing");
        f.setLayout(new BorderLayout());
        f.setVisible(true);

        bt_pan.setLayout(new GridLayout(9,9));
        bt_pan.setBackground(new Color(0, 0, 0));

        for (int i = 0; i < 81; i++) 
        {
            bton[i] = new JButton();
            bt_pan.add(bton[i]);
            bton[i].setFont(new Font("Serif", Font.BOLD, 120));
            bton[i].setFocusable(false);
            bton[i].setBackground(Color.cyan);
            bton[i].addActionListener(this);
        }
        
        f.add(bt_pan);

        startGame();
    }

    public void startGame() {
        try {
            JOptionPane.showMessageDialog(f, "Loading...", "Game Start", 1);
            Thread.sleep(4000);

        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        
    }
}