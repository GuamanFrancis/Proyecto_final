import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class Ventana_menu_administrador extends JFrame{
    private JButton button1;
    private JButton button2;
    private JButton button4;
    private JPanel panelmenu;
    private JPanel panellogo;
    private JButton cerrar;
    private JButton minimizar;
    private JPanel panel2;
    private JButton volverButton;


    public Ventana_menu_administrador() {
        super("Ventana Menu");
        setContentPane(panelmenu);
        setUndecorated(true);
        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Registrar_producto ingreso = new Registrar_producto();
                ingreso.ingresar();
                dispose();


            }
        });

        button2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Ventana_Busqueda busqueda = new Ventana_Busqueda();
               busqueda.ingresar();
                dispose();

            }
        });
        button4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                Ventana_Eliminar eliminar = new Ventana_Eliminar();
                eliminar.ingresar();
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
    }
    public void ingresar(){
        setVisible(true);
        setSize(900,600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }

    private void createUIComponents() {
        // TODO: place custom component creation code here

        panelmenu = new JPanel();
        panelmenu = new Conexion_base_de_datos.CustomPanel("./src/fondocajero.pmg.jpg");
        button1 = new JButton();
        button2 = new JButton();
        button4 = new JButton();
        metodobotones(button1);
        metodobotones(button2);
        metodobotones(button4);
        panel2 = new JPanel();
        panel2 = new Conexion_base_de_datos.CustomPanel("./src/imagenes/descargar-removebg.png");
        panellogo = new JPanel();
        panellogo = new Conexion_base_de_datos.CustomPanel("./src/imagenes/descargar-removebg.png");
        cerrar = new JButton();
        minimizar = new JButton();
        volverButton = new JButton();
        personalizeButton(volverButton);
        personalizeButton(cerrar);
        personalizeButton(minimizar);



    }
    public  void modificarbotones(JButton button){
        button.setFont(new Font("Arial", Font.BOLD, 16));
        button.setForeground(Color.BLACK);
        button.setBackground(Color.LIGHT_GRAY);
        button.setBorderPainted(true);
        button.setContentAreaFilled(true);
        button.setFocusPainted(true);

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
