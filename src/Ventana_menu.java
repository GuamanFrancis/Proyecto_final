import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Ventana_menu extends JFrame{
    private JButton button1;
    private JButton button2;
    private JButton button3;
    private JButton button4;
    private JPanel panelmenu;

    public Ventana_menu() {
        super("Ventana Menu");
        setContentPane(panelmenu);
        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });

    }
    public void ingresar(){
        setVisible(true);
        setSize(400,500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }
}
