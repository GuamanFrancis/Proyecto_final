import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class Ventana_Busqueda extends JFrame{


    private JButton buscarButton;
    private JPanel panelbusqueda;
    private JLabel imagenbusqueda;
    private JLabel imgepn;
    private JPanel panelprincipa;
    private JTextField busqueda;
    private JButton volver;

    public Ventana_Busqueda() {
        super("Ventana Busqueda");
        CustomPanel panelprincipa = new CustomPanel("./src/manga.png");
        panelprincipa.setLayout(new FlowLayout());
        setContentPane(panelbusqueda);

        //Image img= new ImageIcon("./src/manga.png").getImage();
       // ImageIcon img2=new ImageIcon(img.getScaledInstance(150, 150, Image.SCALE_SMOOTH));
        //imagenbusqueda.setIcon(img2);
        Image imge= new ImageIcon("./src/pnglogo.png").getImage();
        ImageIcon img1=new ImageIcon(imge.getScaledInstance(100, 100, Image.SCALE_SMOOTH));
        imgepn.setIcon(img1);

        buscarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                    Buscarproducto();
                }catch (SQLException ex){
                    System.out.println(ex.getMessage());
                }

            }
        });
        volver.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Ventana_menu_administrador menu = new Ventana_menu_administrador();
                menu.ingresar();
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

    private void createUIComponents() {
        // TODO: place custom component creation code here
        volver = new JButton();
        volver.setFont(new Font("Arial", Font.BOLD, 16));
        volver.setForeground(Color.WHITE);
        volver.setBackground(Color.getHSBColor(144,151,0));
        volver.setBorderPainted(true);
        volver.setCursor(new Cursor(Cursor.HAND_CURSOR));
        volver.setContentAreaFilled(true);
        volver.setFocusPainted(true);
        volver.setToolTipText("Volver al menu de opciones");



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

    public Connection conexion()throws SQLException {
        String url = "jdbc:mysql://uingjeu1gmkd2ikl:ngOGTn2MktXQ8MflMQUd@bvaoxw2wxsng8gwobpyh-mysql.services.clever-cloud.com:3306/bvaoxw2wxsng8gwobpyh";
        String user = "uingjeu1gmkd2ikl";
        String pass = "ngOGTn2MktXQ8MflMQUd";

        return DriverManager.getConnection(url,user,pass);

    }

    public void Buscarproducto()throws SQLException{
        Connection conectar = conexion();
        String producto = busqueda.getText();
        String sql = "SELECT * FROM Productos WHERE id = ? ";

        PreparedStatement stmt = conectar.prepareStatement(sql);
        stmt.setString(1,producto);

        ResultSet RS = stmt.executeQuery();
        StringBuilder datos = new StringBuilder("");
        if (RS.next()) {
            datos.append("Nombre: ").append(RS.getString("nombre")).append("\n");
            datos.append("Descipcion: ").append(RS.getString("descripcion")).append("\n");
            datos.append("Precio: ").append(RS.getString("precio")).append("\n");
            datos.append("Cantidad: ").append(RS.getString("stock")).append("\n");
            datos.append("Categoria: ").append(RS.getString("categoria_id")).append("\n");

            JOptionPane.showMessageDialog(null,datos);

        }else{

            JOptionPane.showMessageDialog(null,"No se encontro el producto");

        }
    }



}
