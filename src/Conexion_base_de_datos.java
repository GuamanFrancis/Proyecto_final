import javax.swing.*;
import javax.swing.*;
import java.awt.*;
import java.sql.*;

/**
 * La clase Conexion_base_de_datos maneja la conexión a una base de datos MySQL y proporciona
 * métodos para personalizar componentes de la interfaz gráfica de usuario.
 *
 * @version 1.0
 * @since 2024-08-08
 */
public class Conexion_base_de_datos extends JFrame {

    /**
     * Establece una conexión a la base de datos MySQL utilizando los parámetros especificados.
     *
     * @return La conexión establecida.
     * @throws SQLException Si ocurre un error al establecer la conexión.
     */
    public Connection conexion() throws SQLException {
        String url = "jdbc:mysql://uingjeu1gmkd2ikl:ngOGTn2MktXQ8MflMQUd@bvaoxw2wxsng8gwobpyh-mysql.services.clever-cloud.com:3306/bvaoxw2wxsng8gwobpyh";
        String user = "uingjeu1gmkd2ikl";
        String pass = "ngOGTn2MktXQ8MflMQUd";

        return DriverManager.getConnection(url, user, pass);
    }

    /**
     * Cierra los recursos de la base de datos, incluyendo la conexión, la declaración y el conjunto de resultados.
     *
     * @param conn La conexión a la base de datos.
     * @param stmt La declaración SQL.
     * @param rs   El conjunto de resultados SQL.
     */
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

    /**
     * La clase CustomPanel extiende JPanel y permite establecer una imagen de fondo.
     */
    public static class CustomPanel extends JPanel {
        private Image backgroundImage;

        /**
         * Constructor que inicializa el panel con una imagen de fondo.
         *
         * @param imagePath La ruta de la imagen de fondo.
         */
        public CustomPanel(String imagePath) {
            backgroundImage = new ImageIcon(imagePath).getImage();
        }

        /**
         * Dibuja la imagen de fondo en el panel.
         *
         * @param g El contexto gráfico.
         */
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
        }
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
     * Personaliza un botón con estilos específicos.
     *
     * @param button El botón a personalizar.
     */
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

    /**
     * Configura un botón con estilos específicos.
     *
     * @param button El botón a configurar.
     */
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
