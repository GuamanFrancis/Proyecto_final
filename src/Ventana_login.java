import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

/**
 * Clase Ventana_login que representa la interfaz gráfica para el inicio de sesión.
 * Extiende JFrame para crear una ventana donde los usuarios pueden ingresar sus credenciales.
 */
public class Ventana_login extends JFrame {

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

    /**
     * Constructor de la clase Ventana_login.
     *
     * @param rol El rol del usuario que está intentando iniciar sesión (Administradores o Cajeros).
     */
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
                System.exit(0); // Cierra la aplicación
            }
        });


        minimizar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setState(Frame.ICONIFIED);
            }
        });
    }

    /**
     * Método para mostrar la ventana de inicio de sesión.
     */
    public void ingresar() {
        setVisible(true);
        setSize(900, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    /**
     * Método que valida los datos ingresados por el usuario.
     *
     * @param rol El rol del usuario (Administradores o Cajeros).
     */
    public void validardatos(String rol) {
        Conexion_base_de_datos datos = new Conexion_base_de_datos();
        String sql = "";
        String usuario = Userid.getText();
        String password = pass.getText();

        // Define la consulta SQL según el rol del usuario
        if ("Administradores".equalsIgnoreCase(rol)) {
            sql = "SELECT * FROM Administradores WHERE username = ? AND password = ?";
        } else if ("Cajeros".equalsIgnoreCase(rol)) {
            sql = "SELECT * FROM Cajeros WHERE username = ? AND password = ?";
        } else {
            JOptionPane.showMessageDialog(null, "Rol no válido");
            return;
        }

        // Conexión a la base de datos y ejecución de la consulta
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
                    dispose();
                } else {
                    JOptionPane.showMessageDialog(null, "Nombre de usuario o contraseña incorrecto"); // Mensaje de error si las credenciales son incorrectas
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error al validar los datos: " + e.getMessage()); // Mensaje de error en caso de excepción
        }
    }

    /**
     * Método para crear los componentes de la interfaz.
     */
    private void createUIComponents() {
        panellogin = new Conexion_base_de_datos.CustomPanel("./src/fondoazulmarino.png");
        imagenlogo = new JLabel();
        Image imagen = new ImageIcon("./src/imagenes/logoa-fotor-bg-remover-2024073116374.png").getImage();
        ImageIcon img1 = new ImageIcon(imagen.getScaledInstance(150, 150, Image.SCALE_SMOOTH));
        imagenlogo.setIcon(img1); // Establece la imagen del logo

        panelllogo = new Conexion_base_de_datos.CustomPanel("./src/imagenes/descargar-removebg.png");
        cerrar = new JButton();
        personalizeButton(cerrar);
        minimizar = new JButton();
        personalizeButton(minimizar);
        ingresarButton = new JButton();
        metodobotones(ingresarButton);
    }

    /**
     * Método para personalizar un botón.
     *
     * @param button El botón a personalizar.
     */
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

    /**
     * Método para personalizar un botón de acción.
     *
     * @param button El botón a personalizar.
     */
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


