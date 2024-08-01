import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class Ventana_menu_cajero extends JFrame{
    private JButton button1;
    private JButton comprarButton;
    private JButton mostrarButton;
    private JPanel panelcajero;

    public Ventana_menu_cajero() {
        super("Ventana Menu Cajero");
        setContentPane(panelcajero);
        comprarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                    Ventana_compra compra = new Ventana_compra();
                    compra.ingresar();
                    dispose();



            }
        });

    }

    private void createUIComponents() {

        // TODO: place custom component creation code here
        comprarButton = new JButton();
        modificarbotones(comprarButton);
        mostrarButton = new JButton();
        modificarbotones(mostrarButton);
        button1 = new JButton();
        button1.setOpaque(false);
        button1.setFocusPainted(false);
        button1.setContentAreaFilled(false);
        ImageIcon originalIcon = new ImageIcon("C:\\Users\\USER\\IdeaProjects\\Proyecto_final\\src\\manga.png"); // Asegúrate de que la ruta sea correcta
        Image originalImage = originalIcon.getImage(); // Obtener la imagen original
        Image resizedImage = originalImage.getScaledInstance(100, 100, Image.SCALE_SMOOTH); // Redimensionar la imagen
        ImageIcon resizedIcon = new ImageIcon(resizedImage); // Crear un nuevo ImageIcon con la imagen redimensionada
        button1.setIcon(resizedIcon);
        panelcajero = new JPanel();
        panelcajero = new CustomPanel("./src/fondocajero.pmg.jpg");






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


    public  void modificarbotones(JButton button){
        button.setFont(new Font("Arial", Font.BOLD, 16)); // Cambiar fuente
        button.setForeground(Color.BLACK); // Cambiar color de texto
        button.setBackground(Color.LIGHT_GRAY); // Cambiar color de fondo
        button.setBorderPainted(false); // Quitar el borde del botón
        button.setContentAreaFilled(true); // Quitar el área de contenido
        button.setFocusPainted(false); // Quitar el borde de enfoque




    }
    public void ingresar(){
        setVisible(true);
        setSize(400,500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }
}
