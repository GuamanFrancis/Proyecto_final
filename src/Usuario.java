import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Usuario extends JFrame{
    private JButton admi;
    private JButton cajero;
    private JPanel usuarioo;
    private JLabel logousuario;

    public Usuario() {
        super("Ventana Usuario");
        setContentPane(usuarioo);
        Image imagen= new ImageIcon("./src/usuariotransparente.png").getImage();
        ImageIcon img1=new ImageIcon(imagen.getScaledInstance(100, 100, Image.SCALE_SMOOTH));
        logousuario.setIcon(img1);



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
        setSize(400,500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }

    private void styleButton(JButton button) {
        // Aplicar estilos al botón

        cajero = new JButton("");
        cajero.setFont(new Font("Arial", Font.BOLD, 16));
        cajero.setForeground(Color.WHITE);
        cajero.setBackground(new Color(82, 83, 84, 255));
        cajero.setOpaque(true);
        cajero.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        cajero.setPreferredSize(new Dimension(120, 40));
    }


    private void createUIComponents() {
        admi = new JButton("");
        admi.setFont(new Font("Arial", Font.BOLD, 16));
        admi.setForeground(Color.WHITE);
        admi.setBackground(new Color(82, 83, 84, 255));
        admi.setOpaque(true);
        admi.setBorder(BorderFactory.createLineBorder(Color.black, 2));
        admi.setPreferredSize(new Dimension(120, 40));
        styleButton(cajero);


        logousuario = new JLabel("");
        logousuario.setForeground(Color.WHITE); // Color del texto
        logousuario.setBackground(new Color(0, 120, 215, 2)); // Color de fondo (necesita setOpaque(true))
        logousuario.setOpaque(false); // Hace que el color de fondo sea visible
        logousuario.setPreferredSize(new Dimension(150, 40)); // Tamaño preferido del JLabel
        logousuario.setHorizontalAlignment(SwingConstants.CENTER); // Alineación horizontal del texto
        logousuario.setVerticalAlignment(SwingConstants.CENTER);
        usuarioo = new JPanel();
        usuarioo = new CustomPanel("./src/dark-black-and-gray-blurred-gradient-background-has-a-little-abstract-light-soft-background-for-wallpaper-design-graphic-and-presentation-backdrop-wall-free-photo.png"); // Reemplaza con la ruta a tu imagen
        usuarioo.setLayout(new FlowLayout(FlowLayout.LEFT));
        usuarioo.setBackground(Color.black); // Cambia el color de fondo
        //usuarioo.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2)); // Añade un borde
        usuarioo.getGraphics();




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

