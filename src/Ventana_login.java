import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class Ventana_login extends JFrame{

    private JPanel panellogin;
    private JTextField Userid;
    private JTextField pass;
    private JButton ingresarButton;
    private JButton olvidasteTuContraseñaButton;
    private JLabel imagenlogo;
    private JLabel labeltext;
    private JLabel passwordLabel;
    private JLabel usuarioLabel;
    private JPanel panel2;


    public Ventana_login(String rol) {

        super("hola");
        setContentPane(panellogin);




        ingresarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    validardatos(rol);
                }catch (SQLException ex){
                    System.out.println(ex.getMessage());

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
    public Connection conexion()throws SQLException{
        String url = "jdbc:mysql://uingjeu1gmkd2ikl:ngOGTn2MktXQ8MflMQUd@bvaoxw2wxsng8gwobpyh-mysql.services.clever-cloud.com:3306/bvaoxw2wxsng8gwobpyh";
        String user = "uingjeu1gmkd2ikl";
        String pass = "ngOGTn2MktXQ8MflMQUd";

        return DriverManager.getConnection(url,user,pass);

    }

    public void validardatos(String rol)throws SQLException{
        Connection conectar = conexion();

        String sql="";
        if ("Administradores".equalsIgnoreCase(rol)) {
             sql = "SELECT * FROM Administradores WHERE username = ? AND password = ?";

        } else if ("Cajeros".equalsIgnoreCase(rol)) {
             sql = "SELECT * FROM Cajeros WHERE username = ? AND password = ?";
        }
        String usuario = Userid.getText();
        String password = pass.getText();
        String usuarios;
        PreparedStatement stmt = conectar.prepareStatement(sql);
        stmt.setString(1,usuario);
        stmt.setString(2,password);
        ResultSet rs = stmt.executeQuery();
        if (rs.next()){

            if ("Administradores".equalsIgnoreCase(rol)) {
                Ventana_menu_administrador menu = new Ventana_menu_administrador();
                menu.ingresar();
                dispose();

            } else if ("Cajeros".equalsIgnoreCase(rol)) {
                Ventana_menu_cajero cajero = new Ventana_menu_cajero();
                cajero.ingresar();
                dispose();
            }

        }else{
            JOptionPane.showMessageDialog(null,"Nombre de usuario o contraseña  incorrecto");
        }
    }


    private void createUIComponents() {
        // TODO: place custom component creation code here
        panellogin = new JPanel();
        panellogin = new CustomPanel("./src/fondocajero.pmg.jpg");
        Userid = new JTextField();
        pass = new JTextField();
        modificarjtextfield(Userid);
        modificarjtextfield(pass);
        imagenlogo = new JLabel();
        Image imagen= new ImageIcon("./src/imagenes/logoa-fotor-bg-remover-2024073116374.png").getImage();
        ImageIcon img1=new ImageIcon(imagen.getScaledInstance(150, 150, Image.SCALE_SMOOTH));
        imagenlogo.setIcon(img1);
        labeltext = new JLabel();
        usuarioLabel = new JLabel();
        passwordLabel = new JLabel();
        modificarlaber(labeltext);
        modificarlaber(usuarioLabel);
        modificarlaber(passwordLabel);
        panel2 = new JPanel();
        panel2 = new CustomPanel("./src/imagenes/3874.png");






    }
    public void modificarjtextfield(JTextField textField){
        textField.setBackground(Color.LIGHT_GRAY);
        textField.setForeground(Color.white);
        textField.setFont(new Font("Serif", Font.BOLD, 14));
        textField.setBorder(BorderFactory.createLineBorder(Color.white));
        textField.setEditable(true);


    }
    public void modificarlaber(JLabel label){

        label.setFont(new Font("Serif", Font.BOLD, 15));
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

