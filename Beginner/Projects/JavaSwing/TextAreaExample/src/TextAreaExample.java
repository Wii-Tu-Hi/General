import javax.swing.*;
import java.awt.event.*;
public class TextAreaExample extends JFrame implements ActionListener{
    JLabel l1, l2;
    JTextArea area;
    JButton b;
    TextAreaExample() {
        l1 = new JLabel();
        l1.setBounds(50,25,100,30);
        l2 = new JLabel();
        l2.setBounds(160,25,100,30);
        area = new JTextArea();
        area.setBounds(20,75,250,200);
        b = new JButton("Count Words");
        b.setBounds(100,300,120,30);
        b.addActionListener(this);
        add(l1); add(l2); add(area); add(b);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(450,450);
        setLayout(null);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent e){
        String areaText = area.getText();
        areaText = areaText.trim();
        String words[] = areaText.split("\\s"); 
        l1.setText("Words: " + words.length);
        l2.setText("Characters: " + areaText.length());
    }

    public static void main(String[] args) {
        new TextAreaExample();
    }
}
