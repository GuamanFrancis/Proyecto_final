import javax.swing.*;
import java.awt.*;
import java.sql.*;


public class Conexion_base_de_datos extends JFrame{
    public Connection conexion()throws SQLException {
        String url = "jdbc:mysql://uingjeu1gmkd2ikl:ngOGTn2MktXQ8MflMQUd@bvaoxw2wxsng8gwobpyh-mysql.services.clever-cloud.com:3306/bvaoxw2wxsng8gwobpyh";
        String user = "uingjeu1gmkd2ikl";
        String pass = "ngOGTn2MktXQ8MflMQUd";

        return DriverManager.getConnection(url,user,pass);
    }
    public void cerrarRecursos(Connection conn, Statement stmt, ResultSet rs) {
        if (rs != null) {
            try {
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace(); // Manejo de excepciones
            }
        }
        if (stmt != null) {
            try {
                stmt.close();
            } catch (SQLException e) {
                e.printStackTrace(); // Manejo de excepciones
            }
        }
        if (conn != null) {
            try {
                conn.close(); // Cerrar la conexión
            } catch (SQLException e) {
                e.printStackTrace(); // Manejo de excepciones
            }
        }
    }


    public static class CustomPanel extends JPanel {
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
    public void ingresar() {
        setVisible(true);
        setSize(900,600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }
    public static void personalizeButton(JButton button) {
        button.setFocusPainted(false);
        button.setBorderPainted(false);
        button.setContentAreaFilled(false);
        button.setFont(new Font("Arial", Font.BOLD, 16));
        button.setForeground(Color.CYAN);
        button.setBackground(Color.DARK_GRAY);
        button.setPreferredSize(new Dimension(20, 20)); // Tamaño personalizado
        button.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR)); // Cambiar el cursor

    }
    public static void metodobotones(JButton button) {
        button.setFont(new Font("Arial", Font.BOLD, 16));
        button.setForeground(Color.white);
        button.setBackground(new Color(122, 153, 227, 255));
        button.setOpaque(true);
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createLineBorder(Color.black, 2));
        button.setPreferredSize(new Dimension(120, 40));
    }








}
