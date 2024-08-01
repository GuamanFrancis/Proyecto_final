import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import static java.awt.Transparency.OPAQUE;

public class Usuario extends JFrame{
    private JButton admi;
    private JButton cajero;
    private JPanel usuarioo;
    private JLabel labeltitulo;
    private JLabel labeltexto;
    private JLabel imagenlogo;


    public Usuario() throws IOException {
        super("Ventana Usuario");
        setContentPane(usuarioo);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setVisible(true);

        //Image image = ImageIO.read(new File("./src/png-transparent-light-circle-geometry-science-and-technology-blue-mechanical-blue-angle-electronics.png"));
       // ImageIcon icon = new ImageIcon(image);
        //BufferedImage bufferedImage = ImageIO.read(new File("./src/png-transparent-light-circle-geometry-science-and-technology-blue-mechanical-blue-angle-electronics.png"));
        //ImageIcon icon = new ImageIcon(bufferedImage);
        //imagenlogo.setIcon(icon);




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
    }

    public void ingresar(){
        setVisible(true);
        setSize(900,600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }

    private void metodobotones(JButton button) {
        // Aplicar estilos al botón


        button.setFont(new Font("Arial", Font.BOLD, 16));
        button.setForeground(Color.yellow);
        button.setBackground(new Color(122, 153, 227, 255));
        button.setOpaque(true);
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createLineBorder(Color.black, 2));
        button.setPreferredSize(new Dimension(120, 40));
    }


    private void createUIComponents() {
        admi = new JButton();
        cajero = new JButton();
        metodobotones(cajero);
        metodobotones(admi);
        usuarioo = new JPanel();
        usuarioo = new CustomPanel("./src/fondoazulmarino.png"); // Reemplaza con la ruta a tu imagen
        usuarioo.setLayout(new FlowLayout(FlowLayout.LEFT));
        usuarioo.setBackground(Color.black); // Cambia el color de fondo
        //usuarioo.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2)); // Añade un borde

        usuarioo.getGraphics();
        labeltitulo = new JLabel();
        modificarlaber(labeltitulo);
        labeltexto =  new JLabel();
        labeltexto.setFont(new Font("centaur", Font.BOLD, 20));
        labeltexto.setForeground(Color.white);
        //label.setOpaque(false);
        imagenlogo = new JLabel();
        Image imagen= new ImageIcon("./src/imagenes/logoa-fotor-bg-remover-2024073116374.png").getImage();
        ImageIcon img1=new ImageIcon(imagen.getScaledInstance(150, 150, Image.SCALE_SMOOTH));
        imagenlogo.setIcon(img1);















    }
    public void modificarlaber(JLabel label){

        label.setFont(new Font("Serif", Font.BOLD, 30));
        label.setForeground(Color.white); // Color del texto
        label.setOpaque(false);
        label.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); // Márgenes superior, izquierdo, inferior y derecho


    }

    public class CustomPanel extends JPanel {
        private Image backgroundImage;
        public CustomPanel(String imagePath) {

            backgroundImage = new ImageIcon(imagePath).getImage();
        }
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);

            g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
        }
    }
}

