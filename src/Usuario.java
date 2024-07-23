import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class Usuario extends JFrame{
    private JButton admi;
    private JButton cajero;
    private JPanel usuarioo;

    public Usuario() {
        super("Ventana Usuario");
        setContentPane(usuarioo);
        admi.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String Usuariodeingreso ="Administradores";
                Ventana_login login = new Ventana_login(Usuariodeingreso);
                login.ingresar();
                dispose();



            }
        });
        cajero.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String Usuariodeingreso ="Cajeros";
                Ventana_login login = new Ventana_login(Usuariodeingreso);
                login.ingresar();
                dispose();



            }
        });
    }
    public Connection conexion()throws SQLException {
        String url = "jdbc:mysql://uingjeu1gmkd2ikl:ngOGTn2MktXQ8MflMQUd@bvaoxw2wxsng8gwobpyh-mysql.services.clever-cloud.com:3306/bvaoxw2wxsng8gwobpyh";
        String user = "uingjeu1gmkd2ikl";
        String pass = "ngOGTn2MktXQ8MflMQUd";

        return DriverManager.getConnection(url,user,pass);

    }
    public void ingresar(){
        setVisible(true);
        setSize(400,500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }


}

