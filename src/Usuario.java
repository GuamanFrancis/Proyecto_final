import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class Usuario extends JFrame{
    private JButton admi;
    private JButton cajero;
    private JPanel usuarioo;
    private JLabel labeltitulo;
    private JLabel labeltexto;
    private JLabel imagenlogo;
    private JPanel imagenfondo;
    private JButton cerrar;
    private JButton minimizar;


    public Usuario() throws IOException {
        super("Ventana Usuario");
        setContentPane(usuarioo);
        setUndecorated(true);
        setVisible(true);
        admi.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String Usuariodeingreso ="Administradores";
                Ventana_login login = new Ventana_login(Usuariodeingreso);
                login.ingresar();
                dispose();

            }
        });
        cajero.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String Usuariodeingreso ="Cajeros";
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

    public void ingresar(){
        setVisible(true);
        setSize(900,600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }




    private void createUIComponents() {
        admi = new JButton();
        cajero = new JButton();
        metodobotones(cajero);
        metodobotones(admi);
        usuarioo = new JPanel();
        usuarioo = new Conexion_base_de_datos.CustomPanel("./src/fondoazulmarino.png");
        imagenfondo = new JPanel();
        imagenfondo = new Conexion_base_de_datos.CustomPanel("./src/imagenes/descargar-removebg.png");// Reemplaza con la ruta a tu imagen
        usuarioo.setLayout(new FlowLayout(FlowLayout.LEFT));
        usuarioo.setBackground(Color.black); // Cambia el color de fondo
        usuarioo.getGraphics();

        labeltexto =  new JLabel();
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

    private void metodobotones(JButton button) {
        button.setFont(new Font("Arial", Font.BOLD, 16));
        button.setForeground(Color.white);
        button.setBackground(new Color(122, 153, 227, 255));
        button.setOpaque(true);
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createLineBorder(Color.black, 2));
        button.setPreferredSize(new Dimension(120, 40));
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

