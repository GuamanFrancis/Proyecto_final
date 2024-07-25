import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class Ventana_Eliminar extends JFrame{
    private JTextField eliminar;
    private JButton eliminarButton;
    private JPanel paneleliminar;

    public Ventana_Eliminar() {
        super("Panel de Busqueda");
        setContentPane(paneleliminar);
        eliminarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                try{
                    Eliminarproducto();

                }catch (SQLException ex){
                    System.out.println(ex.getMessage());

                }


            }
        });
    }

    public void ingresar(){
        setVisible(true);

        setSize(500,600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }
    public Connection conexion()throws SQLException {
        String url = "jdbc:mysql://uingjeu1gmkd2ikl:ngOGTn2MktXQ8MflMQUd@bvaoxw2wxsng8gwobpyh-mysql.services.clever-cloud.com:3306/bvaoxw2wxsng8gwobpyh";
        String user = "uingjeu1gmkd2ikl";
        String pass = "ngOGTn2MktXQ8MflMQUd";

        return DriverManager.getConnection(url,user,pass);

    }

    public void Eliminarproducto()throws SQLException{

        Connection conectar = conexion();
        String producto = eliminar.getText();
        String sql = "SELECT * FROM Productos WHERE id = ? ";

        PreparedStatement stmt = conectar.prepareStatement(sql);
        stmt.setString(1,producto);

        ResultSet RS = stmt.executeQuery();

        if (RS.next()) {

            eliminar();

        }else{

            JOptionPane.showMessageDialog(null,"No se encontro el producto");

        }
    }

    public void eliminar()throws SQLException{
        Connection conectar = conexion();
        String producto = eliminar.getText();
        String sql = "DELETE  FROM Productos WHERE id = ? ";
        PreparedStatement stmt = conectar.prepareStatement(sql);
        stmt.setString(1,producto);
        int columnas = stmt.executeUpdate();

        if (columnas>0){
            JOptionPane.showMessageDialog(null,"Datos eliminados exitosamente");

        }else{
            JOptionPane.showMessageDialog(null,"Error");

        }


    }
}
