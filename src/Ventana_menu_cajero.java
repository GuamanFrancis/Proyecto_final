import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

/**
 * Clase Ventana_menu_cajero que representa la interfaz gráfica para el menú de cajero.
 * Extiende JFrame para crear una ventana donde los cajeros pueden realizar diferentes acciones.
 */
public class Ventana_menu_cajero extends JFrame {

    private JButton comprarButton;
    private JButton mostrarButton;
    private JPanel panelcajero;
    private JButton cerrar;
    private JButton minimizar;
    private JButton volverButton;
    private JPanel panel11;
    private int idCajero;

    /**
     * Constructor de la clase Ventana_menu_cajero.
     *
     * @param idCajero El ID del cajero.
     */
    public Ventana_menu_cajero(int idCajero) {
        super("Ventana Menu Cajero");
        setContentPane(panelcajero);
        setUndecorated(true); // Desactiva el decorado de la ventana
        this.idCajero = idCajero; // Asigna el ID del cajero

        // Acción para realizar una compra
        comprarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Ventana_compra compra = new Ventana_compra(idCajero);
                compra.ingresar(); // Muestra la ventana de compra
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

        // Acción para mostrar las facturas
        mostrarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Ventana_facturas factura = new Ventana_facturas(idCajero);
                factura.ingresar(); // Muestra la ventana de facturas
                dispose(); // Cierra la ventana actual
            }
        });
    }

    /**
     * Método para crear los componentes de la interfaz.
     */
    private void createUIComponents() {
        panelcajero = new Conexion_base_de_datos.CustomPanel("./src/fondoadministrador.jpeg"); // Panel principal
        panel11 = new Conexion_base_de_datos.CustomPanel("./src/imagenes/descargar-removebg.png"); // Panel adicional
        cerrar = new JButton(); // Botón de cerrar
        minimizar = new JButton(); // Botón de minimizar
        volverButton = new JButton(); // Botón para volver
        comprarButton = new JButton(); // Botón para comprar
        mostrarButton = new JButton(); // Botón para mostrar facturas
        Conexion_base_de_datos.personalizeButton(volverButton); // Personaliza el botón para volver
        Conexion_base_de_datos.personalizeButton(cerrar); // Personaliza el botón de cerrar
        Conexion_base_de_datos.personalizeButton(minimizar); // Personaliza el botón de minimizar
        Conexion_base_de_datos.metodobotones(comprarButton); // Personaliza el botón de comprar
        Conexion_base_de_datos.metodobotones(mostrarButton); // Personaliza el botón de mostrar facturas
    }

    /**
     * Método para mostrar la ventana del menú de cajero.
     */
    public void ingresar() {
        setVisible(true); // Hace visible la ventana
        setSize(900, 600); // Establece el tamaño de la ventana
        setLocationRelativeTo(null); // Centra la ventana en la pantalla
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Cierra la aplicación al cerrar la ventana
    }



}

