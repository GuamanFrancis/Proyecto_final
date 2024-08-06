import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class Ventana_menu_cajero extends JFrame{

    private JButton comprarButton;
    private JButton mostrarButton;
    private JPanel panelcajero;
    private JButton cerrar;
    private JButton minimizar;
    private JButton volverButton;
    private JPanel panel11;
    private int idCajero;

    public Ventana_menu_cajero(int idCajero) {
        super("Ventana Menu Cajero");
        setContentPane(panelcajero);
        setUndecorated(true);
        this.idCajero = idCajero;
        comprarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                    Ventana_compra compra = new Ventana_compra(idCajero);
                    compra.ingresar();
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
        volverButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Usuario usu = new Usuario();
                    usu.ingresar();
                    dispose();
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }

            }
        });
        mostrarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Ventana_facturas factura = new Ventana_facturas(idCajero);
                factura.ingresar();
                dispose();
            }
        });
    }

    private void createUIComponents() {
        // TODO: place custom component creation code here
        panelcajero = new Conexion_base_de_datos.CustomPanel("./src/fondoadministrador.jpeg");
        panel11 = new Conexion_base_de_datos.CustomPanel("./src/imagenes/descargar-removebg.png");
        cerrar = new JButton();
        minimizar = new JButton();
        volverButton = new JButton();
        comprarButton = new JButton();
        mostrarButton = new JButton();
        personalizeButton(volverButton);
        personalizeButton(cerrar);
        personalizeButton(minimizar);
        metodobotones(comprarButton);
        metodobotones(mostrarButton);
    }
    public void ingresar(){
        setVisible(true);
        setSize(900,600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }
    private void personalizeButton(JButton button) {
        button.setFocusPainted(false);
        button.setBorderPainted(false);
        button.setContentAreaFilled(false);
        button.setFont(new Font("Arial", Font.BOLD, 16));
        button.setForeground(Color.CYAN);
        button.setBackground(Color.DARK_GRAY);
        button.setPreferredSize(new Dimension(20, 20)); // Tamaño personalizado
        button.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR)); // Cambiar el cursor
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
