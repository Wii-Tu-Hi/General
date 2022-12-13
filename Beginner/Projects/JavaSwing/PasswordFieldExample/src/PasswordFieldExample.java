import javax.swing.*;    
import java.awt.event.*;  
public class PasswordFieldExample {  
    public static void main(String[] args) {    
        JFrame f=new JFrame("Password Field Example");    
        final JLabel label1 = new JLabel();            
        label1.setBounds(20,150, 200,50); 
        final JLabel label2 = new JLabel();
        label2.setBounds(20,200,200,50);
        final JPasswordField value = new JPasswordField();   
        value.setBounds(100,75,100,30);
        JCheckBox box = new JCheckBox("Show Password"); 
        box.setBounds(210,75,150,30);  
        JLabel l1=new JLabel("Username:");    
        l1.setBounds(20,20,80,30);    
        JLabel l2=new JLabel("Password:");    
        l2.setBounds(20,75,80,30);    
        JButton b = new JButton("Login");  
        b.setBounds(100,120,80,30);    
        final JTextField text = new JTextField();  
        text.setBounds(100,20, 100,30);    
        f.add(value); f.add(l1); f.add(label1); f.add(l2); f.add(b);  
        f.add(text); f.add(label2); 
        f.add(box); 
        f.setSize(400,325);    
        f.setLayout(null);    
        f.setVisible(true);  
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        b.addActionListener(new ActionListener() {  
            public void actionPerformed(ActionEvent e) {       
            String data = "Username: " + text.getText();  
            String pass = "Password: " + new String(value.getPassword());   
            label1.setText(data); 
            label2.setText(pass);         
            }  
        
        });
        // Notice the difference in the variable names between ActionPerformed and ItemPerformed
        box.addItemListener(new ItemListener() { 
            public void itemStateChanged(ItemEvent e) {
                if(e.getStateChange() == ItemEvent.SELECTED){
                    value.setEchoChar((char) 0);
                } else {
                    value.setEchoChar('\u2022');
                }
            }
        });   
    }  
}  