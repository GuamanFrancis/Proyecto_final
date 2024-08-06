import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class Ventana_login extends JFrame{

    private JPanel panellogin;
    private JTextField Userid;
    private JTextField pass;
    private JButton ingresarButton;
    private JLabel imagenlogo;
    private JLabel passwordLabel;
    private JLabel usuarioLabel;
    private JButton cerrar;
    private JButton minimizar;
    private JPanel panel2;
    private JPanel panelllogo;

    public Ventana_login(String rol) {
        super("Ventana login");
        setContentPane(panellogin);
        setUndecorated(true);
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
        cerrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        minimizar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setState(Frame.ICONIFIED);
            }
        });
    }
    public void ingresar(){
        setVisible(true);
        setSize(900,600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }

    public void validardatos(String rol)throws SQLException{
        Conexion_base_de_datos datos = new Conexion_base_de_datos();
        Connection conexion = datos.conexion();
        String sql="";
        if ("Administradores".equalsIgnoreCase(rol)) {
             sql = "SELECT * FROM Administradores WHERE username = ? AND password = ?";

        } else if ("Cajeros".equalsIgnoreCase(rol)) {
             sql = "SELECT * FROM Cajeros WHERE username = ? AND password = ?";

        }
        String usuario = Userid.getText();
        String password = pass.getText();
        PreparedStatement stmt = conexion.prepareStatement(sql);
        stmt.setString(1,usuario);
        stmt.setString(2,password);
        ResultSet rs = stmt.executeQuery();
        if (rs.next()){
            int idGenerado = rs.getInt("codigo");
                if ("Cajeros".equalsIgnoreCase(rol)) {
                    Ventana_menu_cajero cajero = new Ventana_menu_cajero(idGenerado);
                    cajero.ingresar();
                    dispose();
                } else if ("Administradores".equalsIgnoreCase(rol)) {
                    Ventana_menu_administrador menu = new Ventana_menu_administrador();
                    menu.ingresar();
                    dispose();
                }


        }else{
            JOptionPane.showMessageDialog(null,"Nombre de usuario o contraseña  incorrecto");
        }
    }


    private void createUIComponents() {
        // TODO: place custom component creation code here
        panellogin = new Conexion_base_de_datos.CustomPanel("./src/fondoazulmarino.png");
        Userid = new JTextField();
        pass = new JTextField();
        modificarjtextfield(Userid);
        modificarjtextfield(pass);
        imagenlogo = new JLabel();
        Image imagen= new ImageIcon("./src/imagenes/logoa-fotor-bg-remover-2024073116374.png").getImage();
        ImageIcon img1=new ImageIcon(imagen.getScaledInstance(150, 150, Image.SCALE_SMOOTH));
        imagenlogo.setIcon(img1);
        usuarioLabel = new JLabel();
        passwordLabel = new JLabel();
        modificarlaber(usuarioLabel);
        modificarlaber(passwordLabel);
        panelllogo = new JPanel();
        panelllogo = new Conexion_base_de_datos.CustomPanel("./src/imagenes/descargar-removebg.png");
        cerrar = new JButton();
        minimizar = new JButton();
        personalizeButton(cerrar);
        personalizeButton(minimizar);

    }
    public void modificarjtextfield(JTextField textField){
        textField.setBackground(Color.LIGHT_GRAY);
        textField.setForeground(Color.white);
        textField.setFont(new Font("Serif", Font.BOLD, 14));
        textField.setBorder(BorderFactory.createLineBorder(Color.white));
        textField.setEditable(true);


    }
    public void modificarlaber(JLabel label){

        label.setFont(new Font("Serif", Font.BOLD, 15));
        label.setForeground(Color.white); // Color del texto
        label.setOpaque(false);
        label.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); // Márgenes superior, izquierdo, inferior y derecho


    }
    private void personalizeButton(JButton button) {
        button.setFocusPainted(false);
        button.setBorderPainted(false);
        button.setContentAreaFilled(false);
        button.setFont(new Font("Arial", Font.BOLD, 16));
        button.setForeground(Color.CYAN);
        button.setBackground(Color.DARK_GRAY);
        button.setPreferredSize(new Dimension(20, 20));
        button.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
    }

}

