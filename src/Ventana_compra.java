import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;


public class Ventana_compra extends JFrame{
    private JButton button1;
    private JButton volverButton;
    private JPanel panelcompra;
    private JTextField buscarproductos;
    private JTable table1111;
    private JTextField textField1;
    private JTextField textField2;
    private JTextField textField3;
    private JTextField textField4;
    private JLabel nombre;
    private JLabel ca;
    private JLabel otro;
    private JTextField textField5;
    private JButton limpiarButton;
    private JButton ingresarButton;
    private JTextField clienteid;
    private JTextField cajeroid;
    private JTextField productoid;
    private JTextField cant;
    private JButton buscarButton;

    public Ventana_compra() {
        super("Ventana Compra");
        setContentPane(panelcompra);




        volverButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Ventana_menu_cajero cajero = new Ventana_menu_cajero();
                cajero.ingresar();
                dispose();

            }
        });

        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                    mostrarunitario();
                }catch (SQLException ex){
                    System.out.println(ex.getMessage());
                }
            }
        });
        limpiarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String espacioenblanco = "";
                textField1.setText(espacioenblanco);
                textField2.setText(espacioenblanco);
                textField3.setText(espacioenblanco);
                textField4.setText(espacioenblanco);
                textField5.setText(espacioenblanco);
                buscarproductos.setText("");


            }
        });
        ingresarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                    registrarCompra();
                }catch (SQLException ex){
                    System.out.println(ex.getMessage());
                }

            }
        });
    }



    public void ingresar(){
        setVisible(true);
        setSize(800,500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }

    private void createUIComponents() {
        // TODO: place custom component creation code here
        table1111 = new JTable();

        DefaultTableModel model = new DefaultTableModel(new Object[]{"Código", "Nombre", "Precio", "Descripción","Stock","Categoria_id"}, 0);
        table1111.setModel(model);
        table1111.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        table1111.setShowGrid(true); // Mostrar la cuadrícula
        table1111.setGridColor(Color.GRAY); // Color de la cuadrícula
        table1111.getTableHeader().setReorderingAllowed(false);


        cargarProductos(model);
        JScrollPane scrollPane = new JScrollPane(table1111);
        button1 = new JButton();
        volverButton = new JButton();
        limpiarButton = new JButton();
        ingresarButton = new JButton();
        modificarbotones(button1);
        modificarbotones(volverButton);
        modificarbotones(limpiarButton);
        modificarbotones(ingresarButton);
        textField1 = new JTextField();
        modificarjtextfield(textField1);
        panelcompra = new JPanel();
        panelcompra = new JPanel();
        panelcompra = new CustomPanel("./src/fondo azul.png");
        //modificarfondo(panelcompra);







    }
    public  void modificarfondo(JPanel panel){

        panel = new CustomPanel("./src/fondo azul.png"); // Reemplaza con la ruta a tu imagen
        panel.setLayout(new FlowLayout(FlowLayout.LEFT));
        panel.setBackground(Color.black); // Cambia el color de fondo
        panel.getGraphics();

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
    public void modificarjtextfield(JTextField textField){
        textField.setBackground(Color.LIGHT_GRAY);
        textField.setForeground(Color.BLACK);
        textField.setFont(new Font("Serif", Font.BOLD, 14));
        textField.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        textField.setEditable(true);


    }
    public  void modificarbotones(JButton button){
        button.setFont(new Font("Arial", Font.BOLD, 16)); // Cambiar fuente
        button.setForeground(Color.BLACK); // Cambiar color de texto
        button.setBackground(Color.LIGHT_GRAY); // Cambiar color de fondo
        button.setBorderPainted(false); // Quitar el borde del botón
        button.setContentAreaFilled(true); // Quitar el área de contenido
        button.setFocusPainted(false); // Quitar el borde de enfoque

    }



    public Connection conexion()throws SQLException {
        String url = "jdbc:mysql://uingjeu1gmkd2ikl:ngOGTn2MktXQ8MflMQUd@bvaoxw2wxsng8gwobpyh-mysql.services.clever-cloud.com:3306/bvaoxw2wxsng8gwobpyh";
        String user = "uingjeu1gmkd2ikl";
        String pass = "ngOGTn2MktXQ8MflMQUd";

        return DriverManager.getConnection(url,user,pass);

    }

    private static void cargarProductos(DefaultTableModel model) {

        Statement statement = null;
        ResultSet resultSet = null;
        Connection connection = null;

        String url = "jdbc:mysql://uingjeu1gmkd2ikl:ngOGTn2MktXQ8MflMQUd@bvaoxw2wxsng8gwobpyh-mysql.services.clever-cloud.com:3306/bvaoxw2wxsng8gwobpyh";
        String user = "uingjeu1gmkd2ikl";
        String pass = "ngOGTn2MktXQ8MflMQUd";


        try {
            connection = DriverManager.getConnection(url, user, pass);

            // Crear la consulta
            statement = connection.createStatement();

            resultSet = statement.executeQuery("SELECT * FROM Productos");

            // Procesar el resultado
            while (resultSet.next()) {
                String codigo = resultSet.getString("id");
                String nombre = resultSet.getString("nombre");
                double precio = resultSet.getDouble("precio");
                String descripcion = resultSet.getString("descripcion");
                String cantidad_stock = resultSet.getString("stock");
                String categoria =  resultSet.getString("categoria_id");
                model.addRow(new Object[]{codigo, nombre, precio, descripcion,cantidad_stock,categoria});
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public  void mostrarunitario()throws SQLException{
        Connection conectar = conexion();
        String producto = buscarproductos.getText();
        String sql = "SELECT nombre,descripcion,precio,stock,categoria_id FROM Productos WHERE id = ? ";

        PreparedStatement stmt = conectar.prepareStatement(sql);
        stmt.setString(1,producto);

        ResultSet RS = stmt.executeQuery();
        StringBuilder datos = new StringBuilder("");
        if (RS.next()) {
            String nombre = RS.getString("nombre");
            String descripcion = RS.getString("descripcion");
            double precio = RS.getDouble("precio");
            String cantidad_stock = RS.getString("stock");
            String categoria = RS.getString("categoria_id");

            textField1.setText(nombre);
            textField2.setText(descripcion);
            textField3.setText(String.valueOf(precio));
            textField4.setText(cantidad_stock);
            textField5.setText(categoria);


        }else{

            JOptionPane.showMessageDialog(null,"No se encontro el producto");

        }




    }
    public void registrarCompra()throws SQLException {
        try{
            int customerId = Integer.parseInt(clienteid.getText());
            int userId = Integer.parseInt(cajeroid.getText());
            int productId = Integer.parseInt(productoid.getText());
            int quantity = Integer.parseInt(cant.getText());
            PreparedStatement pstmt = null;
            Connection conectar = conexion();
            String sqlInsertSale = "INSERT INTO Ventas (cliente_id, usuario_id, total) VALUES (?, ?, ?)";
            pstmt = conectar.prepareStatement(sqlInsertSale, PreparedStatement.RETURN_GENERATED_KEYS);
            pstmt.setInt(1, customerId);
            pstmt.setInt(2, userId);
            pstmt.setDouble(3, 0.0); // El total se calculará más tarde
            pstmt.executeUpdate();
            ResultSet rs = pstmt.getGeneratedKeys();
            int saleId = 0;
            if (rs.next()) {
            saleId = rs.getInt(1);
            }
            double subtotal = 0.0;
            String sqlInsertDetail = "INSERT INTO Detalles_Ventas (venta_id, producto_id, cantidad, precio_unitario, subtotal) VALUES (?, ?, ?, ?, ?)";
            pstmt = conectar.prepareStatement(sqlInsertDetail);
            pstmt.setInt(1, saleId);
            pstmt.setInt(2, productId);
            pstmt.setInt(3, quantity);
            // Aquí puedes calcular el precio unitario desde la tabla Productos si es necesario
            double unitPrice = getProductPrice(productId); // Método para obtener el precio del producto
            pstmt.setDouble(4, unitPrice);
            subtotal = unitPrice * quantity;
            pstmt.setDouble(5, subtotal);
            pstmt.executeUpdate();

            // 3. Actualizar el total en la tabla Ventas
            String sqlUpdateSale = "UPDATE Ventas SET total = total + ? WHERE id = ?";
            pstmt = conectar.prepareStatement(sqlUpdateSale);
            pstmt.setDouble(1, subtotal);
            pstmt.setInt(2, saleId);
            pstmt.executeUpdate();

            String sqlUpdateStock = "UPDATE Productos SET stock = stock - ? WHERE id = ?";
            pstmt = conectar.prepareStatement(sqlUpdateStock);
            pstmt.setInt(1, quantity);
            pstmt.setInt(2, productId);
            pstmt.executeUpdate();

            // 5. Registrar la factura en la tabla Facturas
            String sqlInsertInvoice = "INSERT INTO Facturas (venta_id, fecha_emision, cliente_id, total) VALUES (?, CURRENT_TIMESTAMP, ?, ?)";
            pstmt = conectar.prepareStatement(sqlInsertInvoice);
            pstmt.setInt(1, saleId);
            pstmt.setInt(2, customerId);
            pstmt.setDouble(3, subtotal); // Utiliza el total calculado
            pstmt.executeUpdate();

            // Confirmar transacción
            JOptionPane.showMessageDialog(null, "Compra registrada exitosamente.");
        }   catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error al registrar la compra: " + ex.getMessage());
        }
    }
    // Método para obtener el precio del producto desde la base de datos
    private double getProductPrice(int productId)throws SQLException {
        double price = 0.0;
        Connection conectar = conexion();

        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {

            // Consulta para obtener el precio del producto
            String sql = "SELECT precio FROM Productos WHERE id = ?";
            pstmt = conectar.prepareStatement(sql);
            pstmt.setInt(1, productId);

            // Ejecutar la consulta
            rs = pstmt.executeQuery();

            // Verificar si hay resultados
            if (rs.next()) {
                price = rs.getDouble("precio");
            } else {
                // Si no se encuentra el producto, puedes lanzar una excepción o mostrar un mensaje
                throw new Exception("Producto no encontrado");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // Cerrar recursos
            try {
                if (rs != null) rs.close();
                if (pstmt != null) pstmt.close();
                if (conectar != null) conectar.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return price;
    }



}
