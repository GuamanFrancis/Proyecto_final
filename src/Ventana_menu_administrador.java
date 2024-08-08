import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

/**
 * Clase Ventana_menu_administrador que representa la interfaz gráfica para el menú de administración.
 * Extiende JFrame para crear una ventana donde los administradores pueden realizar diferentes acciones.
 */
public class Ventana_menu_administrador extends JFrame {
    private JButton button1;
    private JButton button2;
    private JButton button4;
    private JPanel panelmenu;
    private JPanel panellogo;
    private JButton cerrar;
    private JButton minimizar;
    private JPanel panel2;
    private JButton volverButton;

    /**
     * Constructor de la clase Ventana_menu_administrador.
     */
    public Ventana_menu_administrador() {
        super("Ventana Menu");
        setContentPane(panelmenu);
        setUndecorated(true); // Desactiva el decorado de la ventana

        // Acción para registrar un producto
        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Registrar_producto ingreso = new Registrar_producto();
                ingreso.ingresar(); // Muestra la ventana para registrar un producto
                dispose(); // Cierra la ventana actual
            }
        });

        // Acción para buscar productos
        button2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Ventana_Busqueda busqueda = new Ventana_Busqueda();
                busqueda.ingresar(); // Muestra la ventana de búsqueda
                dispose(); // Cierra la ventana actual
            }
        });

        // Acción para cerrar la aplicación
        cerrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0); // Cierra la aplicación
            }
        });

        // Acción para minimizar la ventana
        minimizar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setState(Frame.ICONIFIED); // Minimiza la ventana
            }
        });

        // Acción para volver al menú anterior
        volverButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Usuario usu = new Usuario();
                    usu.ingresar(); // Muestra la ventana del usuario
                    dispose(); // Cierra la ventana actual
                } catch (IOException ex) {
                    throw new RuntimeException(ex); // Manejo de excepciones
                }
            }
        });
    }

    /**
     * Método para mostrar la ventana del menú de administrador.
     */
    public void ingresar() {
        setVisible(true); // Hace visible la ventana
        setSize(900, 600); // Establece el tamaño de la ventana
        setLocationRelativeTo(null); // Centra la ventana en la pantalla
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Cierra la aplicación al cerrar la ventana
    }

    /**
     * Método para crear los componentes de la interfaz.
     */
    private void createUIComponents() {
        panelmenu = new Conexion_base_de_datos.CustomPanel("./src/fondocajero.pmg.jpg"); // Panel principal
        button1 = new JButton(); // Botón para registrar producto
        button2 = new JButton(); // Botón para buscar producto
        button4 = new JButton(); // Botón adicional
        metodobotones(button1); // Personaliza el botón de registrar
        metodobotones(button2); // Personaliza el botón de buscar
        metodobotones(button4); // Personaliza el botón adicional
        panel2 = new Conexion_base_de_datos.CustomPanel("./src/imagenes/descargar-removebg.png"); // Panel adicional
        panellogo = new Conexion_base_de_datos.CustomPanel("./src/imagenes/descargar-removebg.png"); // Panel para el logo
        cerrar = new JButton(); // Botón de cerrar
        minimizar = new JButton(); // Botón de minimizar
        volverButton = new JButton(); // Botón para volver
        personalizeButton(volverButton); // Personaliza el botón para volver
        personalizeButton(cerrar); // Personaliza el botón de cerrar
        personalizeButton(minimizar); // Personaliza el botón de minimizar
    }

    /**
     * Método para personalizar un botón.
     *
     * @param button El botón a personalizar.
     */
    public void modificarbotones(JButton button) {
        button.setFont(new Font("Arial", Font.BOLD, 16));
        button.setForeground(Color.BLACK);
        button.setBackground(Color.LIGHT_GRAY);
        button.setBorderPainted(true);
        button.setContentAreaFilled(true);
        button.setFocusPainted(true);
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
        button.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR)); // Cambia el cursor al pasar sobre el botón
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
        button.setPreferredSize(new Dimension(120, 40)); // Establece el tamaño preferido del botón
    }
}

