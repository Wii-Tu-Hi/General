import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;

public class MyJavaSwing {
    public static void main (String[] arg) {
        new DylSwing();
    }
}

class DylSwing extends JFrame {
    
    DylSwing() {
        setSize(1920, 1080);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Sudoku Swing");
        setLayout(new BorderLayout());
        setVisible(true);
    }
}