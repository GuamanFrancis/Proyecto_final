import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Ventana_menu_administrador extends JFrame{
    private JButton button1;
    private JButton button2;
    private JButton button3;
    private JButton button4;
    private JPanel panelmenu;

    public Ventana_menu_administrador() {
        super("Ventana Menu");
        setContentPane(panelmenu);
        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Registrar_producto ingreso = new Registrar_producto();
                ingreso.ingresar();
                dispose();


            }
        });

        button2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Ventana_Busqueda busqueda = new Ventana_Busqueda();
               busqueda.ingresar();
                dispose();

            }
        });
        button4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                Ventana_Eliminar eliminar = new Ventana_Eliminar();
                eliminar.ingresar();
                dispose();

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
