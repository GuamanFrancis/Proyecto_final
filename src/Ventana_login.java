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
    private JLabel labeltext;
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
                validardatos(rol);
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

    public void validardatos(String rol) {
        Conexion_base_de_datos datos = new Conexion_base_de_datos();
        String sql = "";
        String usuario = Userid.getText();
        String password = pass.getText();
        if ("Administradores".equalsIgnoreCase(rol)) {
            sql = "SELECT * FROM Administradores WHERE username = ? AND password = ?";
        } else if ("Cajeros".equalsIgnoreCase(rol)) {
            sql = "SELECT * FROM Cajeros WHERE username = ? AND password = ?";
        } else {
            JOptionPane.showMessageDialog(null, "Rol no válido");
            return;
        }
        try (Connection conexion = datos.conexion();
             PreparedStatement stmt = conexion.prepareStatement(sql)) {
            stmt.setString(1, usuario);
            stmt.setString(2, password);
            try (ResultSet rs = stmt.executeQuery()) {
                // Verifica si hay resultados
                if (rs.next()) {
                    int idGenerado = rs.getInt("codigo");
                    if ("Cajeros".equalsIgnoreCase(rol)) {
                        Ventana_menu_cajero cajero = new Ventana_menu_cajero(idGenerado);
                        cajero.ingresar();
                    } else if ("Administradores".equalsIgnoreCase(rol)) {
                        Ventana_menu_administrador menu = new Ventana_menu_administrador();
                        menu.ingresar();
                    }
                    dispose(); // Cierra la ventana actual
                } else {
                    JOptionPane.showMessageDialog(null, "Nombre de usuario o contraseña incorrecto");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error al validar los datos: " + e.getMessage());
        }
    }



    private void createUIComponents() {
        // TODO: place custom component creation code here
        panellogin = new Conexion_base_de_datos.CustomPanel("./src/fondoazulmarino.png");
        imagenlogo = new JLabel();
        Image imagen= new ImageIcon("./src/imagenes/logoa-fotor-bg-remover-2024073116374.png").getImage();
        ImageIcon img1=new ImageIcon(imagen.getScaledInstance(150, 150, Image.SCALE_SMOOTH));
        imagenlogo.setIcon(img1);
        panelllogo = new Conexion_base_de_datos.CustomPanel("./src/imagenes/descargar-removebg.png");
        cerrar = new JButton();
        personalizeButton(cerrar);
        minimizar = new JButton();
        personalizeButton(minimizar);
        ingresarButton = new JButton();
        metodobotones(ingresarButton);

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
    private void metodobotones(JButton button) {
        button.setFont(new Font("Arial", Font.BOLD, 16));
        button.setForeground(Color.white);
        button.setBackground(new Color(122, 153, 227, 255));
        button.setOpaque(true);
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createLineBorder(Color.black, 2));
        button.setPreferredSize(new Dimension(120, 40));
    }

}

