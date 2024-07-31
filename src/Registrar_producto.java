import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import static java.awt.Cursor.HAND_CURSOR;

public class Registrar_producto extends JFrame{
    private JButton ingresarButton;
    private JTextField nombre;
    private JTextField Des;
    private JTextField Prec;
    private JTextField cant;
    private JTextField cate;
    private JTextField textField6;
    private JPanel panelregistro;
    private JButton volverButton;

    public Registrar_producto() {
        super("Ventana Registro");
        setContentPane(panelregistro);
        ingresarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                    Registrarproductos();
                }catch(SQLException ex){
                    System.out.println(ex.getMessage());

                }

            }
        });
        volverButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Ventana_menu_administrador menu = new Ventana_menu_administrador();
                menu.ingresar();
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

    public Connection conexion()throws SQLException {
        String url = "jdbc:mysql://uingjeu1gmkd2ikl:ngOGTn2MktXQ8MflMQUd@bvaoxw2wxsng8gwobpyh-mysql.services.clever-cloud.com:3306/bvaoxw2wxsng8gwobpyh";
        String user = "uingjeu1gmkd2ikl";
        String pass = "ngOGTn2MktXQ8MflMQUd";

        return DriverManager.getConnection(url,user,pass);

    }

    public void Registrarproductos()throws SQLException{
        Connection conectar=conexion();
        String nom = nombre.getText();
        String descripcion = Des.getText();
        Double precio = Double.parseDouble(Prec.getText());
        String cantidad = cant.getText();
        String cateogira = cate.getText();
        String sql = "INSERT  INTO Productos(nombre,descripcion,precio,stock,categoria_id,fecha_creacion)values(?,?,?,?,?,NOW())";
        PreparedStatement stmt = conectar.prepareStatement(sql);
        stmt.setString(1,nom);
        stmt.setString(2,descripcion);
        stmt.setDouble(3,precio);
        stmt.setInt(4,Integer.parseInt(cantidad));
        stmt.setString(5,cateogira);

        int columnas = stmt.executeUpdate();

        if (columnas>0){
            JOptionPane.showMessageDialog(null,"Datos ingresados exitosamente");

        }else{
            JOptionPane.showMessageDialog(null,"Datos no ingresados");

        }

    }

    private void createUIComponents() {
        // TODO: place custom component creation code here

        volverButton = new JButton();
        volverButton.setFont(new Font("Arial", Font.BOLD, 16));
        volverButton.setForeground(Color.WHITE);
        volverButton.setBackground(Color.getHSBColor(144,151,0));
        volverButton.setBorderPainted(true);
        volverButton.setCursor(new Cursor(Cursor.HAND_CURSOR));

    }
}
