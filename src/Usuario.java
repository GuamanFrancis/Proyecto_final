import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

/**
 * La clase Usuario maneja la interfaz gráfica para seleccionar entre los roles de Administrador y Cajero.
 *
 * @version 1.0
 * @since 2024-08-08
 */
public class Usuario extends JFrame {
    private JButton admi;
    private JButton cajero;
    private JPanel usuarioo;
    private JLabel labeltitulo;
    private JLabel labeltexto;
    private JLabel imagenlogo;
    private JPanel imagenfondo;
    private JButton cerrar;
    private JButton minimizar;

    /**
     * Constructor que inicializa la interfaz gráfica y configura los manejadores de eventos.
     *
     * @throws IOException Si ocurre un error al cargar recursos.
     */
    public Usuario() throws IOException {
        super("Ventana Usuario");
        setContentPane(usuarioo);
        setUndecorated(true);
        setVisible(true);

        admi.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String Usuariodeingreso = "Administradores";
                Ventana_login login = new Ventana_login(Usuariodeingreso);
                login.ingresar();
                dispose();
            }
        });

        cajero.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String Usuariodeingreso = "Cajeros";
                Ventana_login login = new Ventana_login(Usuariodeingreso);
                login.ingresar();
                dispose();
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

    /**
     * Configura y muestra la ventana principal.
     */
    public void ingresar() {
        setVisible(true);
        setSize(900, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    /**
     * Crea componentes personalizados de la interfaz gráfica.
     */
    private void createUIComponents() {
        admi = new JButton();
        cajero = new JButton();
        metodobotones(cajero);
        metodobotones(admi);

        usuarioo = new JPanel();
        usuarioo = new Conexion_base_de_datos.CustomPanel("./src/fondoazulmarino.png");

        imagenfondo = new JPanel();
        imagenfondo = new Conexion_base_de_datos.CustomPanel("./src/imagenes/descargar-removebg.png");

        usuarioo.setLayout(new FlowLayout(FlowLayout.LEFT));
        usuarioo.setBackground(Color.black);

        labeltexto = new JLabel();
        labeltexto.setFont(new Font("centaur", Font.BOLD, 20));
        labeltexto.setForeground(Color.white);

        imagenlogo = new JLabel();
        Image imagen = new ImageIcon("./src/imagenes/logoa-fotor-bg-remover-2024073116374.png").getImage();
        ImageIcon img1 = new ImageIcon(imagen.getScaledInstance(150, 150, Image.SCALE_SMOOTH));
        imagenlogo.setIcon(img1);

        cerrar = new JButton();
        minimizar = new JButton();
        personalizeButton(cerrar);
        personalizeButton(minimizar);
    }

    /**
     * Personaliza el estilo de los botones especificados.
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

    /**
     * Personaliza el estilo de los botones de cerrar y minimizar.
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
}


