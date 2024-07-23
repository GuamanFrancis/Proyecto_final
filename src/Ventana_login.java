import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class Ventana_login extends JFrame{

    private JPanel panellogin;
    private JTextField Userid;
    private JTextField pass;
    private JButton ingresarButton;
    private JButton olvidasteTuContraseñaButton;


    public Ventana_login(String rol) {
        super("hola");
        setContentPane(panellogin);


        ingresarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                try {
                    validardatos(rol);
                }catch (SQLException ex){
                    System.out.println(ex.getMessage());

                }
            }
        });
    }
    public void ingresar(){
        setVisible(true);
        setSize(400,500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }
    public Connection conexion()throws SQLException{
        String url = "jdbc:mysql://uingjeu1gmkd2ikl:ngOGTn2MktXQ8MflMQUd@bvaoxw2wxsng8gwobpyh-mysql.services.clever-cloud.com:3306/bvaoxw2wxsng8gwobpyh";
        String user = "uingjeu1gmkd2ikl";
        String pass = "ngOGTn2MktXQ8MflMQUd";

        return DriverManager.getConnection(url,user,pass);

    }

    public void validardatos(String rol)throws SQLException{
        Connection conectar = conexion();

        String sql="";
        if ("Administradores".equalsIgnoreCase(rol)) {
             sql = "SELECT * FROM Administradores WHERE username = ? AND password = ?";

        } else if ("Cajeros".equalsIgnoreCase(rol)) {
             sql = "SELECT * FROM Cajeros WHERE username = ? AND password = ?";
        }
        String usuario = Userid.getText();
        String password = pass.getText();
        String usuarios;
        PreparedStatement stmt = conectar.prepareStatement(sql);
        stmt.setString(1,usuario);
        stmt.setString(2,password);
        ResultSet rs = stmt.executeQuery();
        if (rs.next()){
            Ventana_menu menu = new Ventana_menu();
            menu.ingresar();
            dispose();
        }else{
            JOptionPane.showMessageDialog(null,"Nombre de usuario o contraseña  incorrecto");
        }
    }




}
