import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
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
    private JTextField nombcli;
    private JButton limpiarButton;
    private JButton ingresarButton;
    private JTextField clienteid;
    private JTextField cajeroid;
    private JTextField productoid;
    private JTextField cant;
    private JLabel nombre;
    private JLabel ca;
    private JLabel otro;
    private JButton cerrar;
    private JButton minimizar;
    private JPanel panel1;
    private JLabel imagenprod;
    private JTextField apeliicli;
    private JTextField correocli;
    private JTextField telecli;
    private JTextField dircli;
    private JButton registrarButton;
    private JScrollPane scrollPane1;
    private JButton mostrarClientesButton;
    private DefaultTableModel model1;



    public Ventana_compra(int idcajero) {
        super("Ventana Compra");
        setContentPane(panelcompra);
        setUndecorated(true);
        cajeroid.setText(String.valueOf(idcajero));
        volverButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               Ventana_menu_cajero cajero = new Ventana_menu_cajero(idcajero);
                cajero.ingresar();
                dispose();
            }
        });
        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                    mostrarunitario(imagenprod);
                }catch (SQLException ex){
                    System.out.println(ex.getMessage());
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
        limpiarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String espacioenblanco = "";
                textField1.setText(espacioenblanco);textField2.setText(espacioenblanco);textField3.setText(espacioenblanco);textField4.setText(espacioenblanco);
                nombcli.setText(espacioenblanco);buscarproductos.setText("");
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
        registrarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    ingresarusuario();
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }

            }
        });
        mostrarClientesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    cargarDatoss();
                } catch (SQLException ex) {
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
        panelcompra = new Conexion_base_de_datos.CustomPanel("./src/fondoadministrador.jpeg");
        panel1 = new Conexion_base_de_datos.CustomPanel("./src/imagenes/descargar-removebg.png");
        model1 = new DefaultTableModel();
        model1.addColumn("id");model1.addColumn("Nombre");model1.addColumn("Apellido");model1.addColumn("Correo");model1.addColumn("Telefono");model1.addColumn("Direccion");
        table1111 = new JTable();
        table1111.setModel(model1);
        table1111.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        table1111.setShowGrid(true);
        table1111.setGridColor(Color.GRAY);
        table1111 = new JTable(model1);
        table1111.setShowGrid(true);
        table1111.setGridColor(Color.GRAY);
        table1111.getTableHeader().setReorderingAllowed(false);
        add(table1111);
        scrollPane1 = new JScrollPane(table1111);
        scrollPane1.setPreferredSize(new Dimension(300, 400));
        button1 = new JButton();
        volverButton = new JButton();
        limpiarButton = new JButton();
        ingresarButton = new JButton();
        registrarButton = new JButton();
        mostrarClientesButton = new JButton();
        Conexion_base_de_datos.metodobotones(button1);
        Conexion_base_de_datos.metodobotones(volverButton);
        Conexion_base_de_datos.metodobotones(limpiarButton);
        Conexion_base_de_datos.metodobotones(ingresarButton);
        Conexion_base_de_datos.metodobotones(registrarButton);
        Conexion_base_de_datos.metodobotones(mostrarClientesButton);
        volverButton = new JButton();
        cerrar = new JButton();
        minimizar = new JButton();
        Conexion_base_de_datos.personalizeButton(volverButton);
        Conexion_base_de_datos.personalizeButton(cerrar);
        Conexion_base_de_datos.personalizeButton(minimizar);


    }
    public void ingresarusuario()throws SQLException{

        Connection conectar = null;
        PreparedStatement stmt =null;
        String nomb = nombcli.getText();
        String apellido = apeliicli.getText();
        String correo = correocli.getText();
        String telefono = telecli.getText();
        String direccion = dircli.getText();
        String sql = " INSERT  INTO Clientes(nombre,apellido,correo,telefono,direccion)values(?,?,?,?,?)";
        try {
            conectar = new Conexion_base_de_datos().conexion();
            stmt = conectar.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            stmt.setString(1,nomb);
            stmt.setString(2,apellido);
            stmt.setString(3,correo);
            stmt.setInt(4, Integer.parseInt(telefono));
            stmt.setString(5,direccion);
            int columnas = stmt.executeUpdate();
            if (columnas>0){
                ResultSet generatedKeys = stmt.getGeneratedKeys();
                if (generatedKeys.next()) {
                    int idGenerado = generatedKeys.getInt(1);
                    clienteid.setText(String.valueOf(idGenerado));
                }
                JOptionPane.showMessageDialog(null,"Datos ingresados exitosamente");
            }else{
                JOptionPane.showMessageDialog(null,"Datos no ingresados");
            }

        }catch (SQLException ex){
            ex.printStackTrace();
        }finally {
            new Conexion_base_de_datos().cerrarRecursos(conectar,stmt,null);
        }

    }
    private void cargarDatoss()throws SQLException {
        Connection conexion = null;
        Statement statement =null;
        ResultSet resultSet =null;
        try {
            model1.setRowCount(0);
            Conexion_base_de_datos da = new Conexion_base_de_datos();
             conexion = new Conexion_base_de_datos().conexion();
             statement = conexion.createStatement();
             resultSet = statement.executeQuery("SELECT * FROM Clientes");
            while (resultSet.next()) {
                model1.addRow(new Object[]{
                        resultSet.getString("id"),
                        resultSet.getString("nombre"),
                        resultSet.getString("apellido"),
                        resultSet.getString("correo"),
                        resultSet.getInt("telefono"),
                        resultSet.getString("direccion")
                });
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            new Conexion_base_de_datos().cerrarRecursos(conexion,statement,resultSet);
        }
    }

    private static void cargarProductos(DefaultTableModel model)throws SQLException {

        Connection conexion = null;
        Statement statement =null;
        ResultSet resultSet =null;
        try {
            conexion = new Conexion_base_de_datos().conexion();
             statement = conexion.createStatement();
             resultSet = statement.executeQuery("SELECT * FROM Productos");
            while (resultSet.next()) {
                String codigo = resultSet.getString("id");
                String nombre = resultSet.getString("nombre");
                double precio = resultSet.getDouble("precio");
                String descripcion = resultSet.getString("descripcion");
                String cantidad_stock = resultSet.getString("stock");
                model.addRow(new Object[]{codigo, nombre, precio, descripcion,cantidad_stock});
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            new Conexion_base_de_datos().cerrarRecursos(conexion,statement,resultSet);
        }
    }
    public  void mostrarunitario(JLabel imagenproducto) throws SQLException, IOException {
        Connection conexion = null;
        PreparedStatement stmt =null;
        ResultSet RS =null;
        String producto = buscarproductos.getText();
        String sql = "SELECT nombre,descripcion,precio,stock,imagenes FROM Productos WHERE id = ? ";
        try {
            conexion = new Conexion_base_de_datos().conexion();
            stmt = conexion.prepareStatement(sql);
            stmt.setString(1,producto);
            RS = stmt.executeQuery();
            StringBuilder datos = new StringBuilder("");
            if (RS.next()) {
                String nombre = RS.getString("nombre");
                String descripcion = RS.getString("descripcion");
                double precio = RS.getDouble("precio");
                String cantidad_stock = RS.getString("stock");
                byte[] imgBytes = RS.getBytes("imagenes");
                if (imgBytes != null) {
                    ByteArrayInputStream bais = new ByteArrayInputStream(imgBytes);
                    BufferedImage originalImage = ImageIO.read(bais);
                    // Redimensionar la imagen
                    Image scaledImage = originalImage.getScaledInstance(100, 100, Image.SCALE_SMOOTH);
                    imagenproducto.setIcon(new ImageIcon(scaledImage));
                } else {
                    imagenproducto.setIcon(null);
                }
                textField1.setText(nombre);
                textField2.setText(descripcion);
                textField3.setText(String.valueOf(precio));
                textField4.setText(cantidad_stock);
            }else{
                JOptionPane.showMessageDialog(null,"No se encontro el producto");
            }
        }catch (SQLException ex){
            ex.printStackTrace();
        }finally {
            new Conexion_base_de_datos().cerrarRecursos(conexion,stmt,RS);
        }

    }
    public void registrarCompra()throws SQLException {
        Connection conexion = null;
        PreparedStatement pstmt =null;
        ResultSet rs =null;
        try{
            conexion = new Conexion_base_de_datos().conexion();
            int customerId = Integer.parseInt(clienteid.getText());
            int userId = Integer.parseInt(cajeroid.getText());
            int productId = Integer.parseInt(productoid.getText());
            int quantity = Integer.parseInt(cant.getText());
            pstmt = null;
            String sql = "INSERT INTO Ventas (cliente_id, usuario_id, total) VALUES (?, ?, ?)";
            pstmt = conexion.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
            pstmt.setInt(1, customerId);
            pstmt.setInt(2, userId);
            pstmt.setDouble(3, 0.0);
            pstmt.executeUpdate();
            rs = pstmt.getGeneratedKeys();
            int saleId = 0;
            if (rs.next()) {
            saleId = rs.getInt(1);
            }
            double subtotal = 0.0;
            String sqlsub = "INSERT INTO Detalles_Ventas (venta_id, producto_id, cantidad, precio_unitario, subtotal) VALUES (?, ?, ?, ?, ?)";
            pstmt = conexion.prepareStatement(sqlsub);
            pstmt.setInt(1, saleId);
            pstmt.setInt(2, productId);
            pstmt.setInt(3, quantity);
            double unitPrice = obtenerprecio(productId);
            pstmt.setDouble(4, unitPrice);
            subtotal = unitPrice * quantity;
            pstmt.setDouble(5, subtotal);
            pstmt.executeUpdate();
            String sqlactualizar = "UPDATE Ventas SET total = total + ? WHERE id = ?";
            pstmt = conexion.prepareStatement(sqlactualizar);
            pstmt.setDouble(1, subtotal);
            pstmt.setInt(2, saleId);
            pstmt.executeUpdate();

            String sqlactualizarStock = "UPDATE Productos SET stock = stock - ? WHERE id = ?";
            pstmt = conexion.prepareStatement(sqlactualizarStock);
            pstmt.setInt(1, quantity);
            pstmt.setInt(2, productId);
            pstmt.executeUpdate();

            String sqlInsert = "INSERT INTO Facturas (venta_id, fecha_emision, cliente_id, total) VALUES (?, CURRENT_TIMESTAMP, ?, ?)";
            pstmt = conexion.prepareStatement(sqlInsert);
            pstmt.setInt(1, saleId);
            pstmt.setInt(2, customerId);
            pstmt.setDouble(3, subtotal); // Utiliza el total calculado
            pstmt.executeUpdate();

            JOptionPane.showMessageDialog(null, "Compra registrada exitosamente.");
        }   catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error al registrar la compra: " + ex.getMessage());
        }finally {
            new Conexion_base_de_datos().cerrarRecursos(conexion,pstmt,rs);
        }
    }

    private double obtenerprecio(int productId)throws SQLException {
        Connection conexion = null;
        PreparedStatement pstmt =null;
        ResultSet rs =null;
        double price = 0.0;
        try {
            conexion= new Conexion_base_de_datos().conexion();
            String sql = "SELECT precio FROM Productos WHERE id = ?";
            pstmt = conexion.prepareStatement(sql);
            pstmt.setInt(1, productId);
             rs = pstmt.executeQuery();
            if (rs.next()) {
                price = rs.getDouble("precio");
            } else {
                throw new Exception("Producto no encontrado");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
           new Conexion_base_de_datos().cerrarRecursos(conexion,pstmt,rs);
        }

        return price;
    }
}
