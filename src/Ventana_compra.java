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

/**
 * Clase Ventana_compra que representa la interfaz gráfica para realizar compras.
 * Extiende JFrame para crear una ventana donde los cajeros pueden registrar ventas y ver detalles de productos.
 */
public class Ventana_compra extends JFrame {

    private JButton button1;               // Botón para mostrar la imagen del producto
    private JButton volverButton;          // Botón para volver al menú del cajero
    private JPanel panelcompra;            // Panel principal de la ventana de compra
    private JTextField buscarproductos;    // Campo de texto para buscar productos
    private JTable table1111;              // Tabla para mostrar la información de los clientes
    private JTextField textField1;         // Campo para mostrar el nombre del producto
    private JTextField textField2;         // Campo para mostrar la descripción del producto
    private JTextField textField3;         // Campo para mostrar el precio del producto
    private JTextField textField4;         // Campo para mostrar la cantidad en stock del producto
    private JTextField nombcli;            // Campo para ingresar el nombre del cliente
    private JButton limpiarButton;          // Botón para limpiar los campos
    private JButton ingresarButton;         // Botón para registrar la compra
    private JTextField clienteid;           // Campo para mostrar el ID del cliente
    private JTextField cajeroid;            // Campo para mostrar el ID del cajero
    private JTextField productoid;          // Campo para ingresar el ID del producto
    private JTextField cant;                // Campo para ingresar la cantidad del producto
    private JLabel nombre;                  // Etiqueta para mostrar el nombre del producto
    private JLabel ca;                      // Etiqueta adicional (no se utiliza en este fragmento)
    private JLabel otro;                    // Etiqueta adicional (no se utiliza en este fragmento)
    private JButton cerrar;                 // Botón para cerrar la aplicación
    private JButton minimizar;              // Botón para minimizar la ventana
    private JPanel panel1;                  // Panel adicional
    private JLabel imagenprod;              // Etiqueta para mostrar la imagen del producto
    private JTextField apeliicli;          // Campo para ingresar el apellido del cliente
    private JTextField correocli;           // Campo para ingresar el correo del cliente
    private JTextField telecli;             // Campo para ingresar el teléfono del cliente
    private JTextField dircli;              // Campo para ingresar la dirección del cliente
    private JButton registrarButton;        // Botón para registrar un nuevo cliente
    private JScrollPane scrollPane1;        // Panel de desplazamiento para la tabla de clientes
    private JButton mostrarClientesButton;   // Botón para mostrar la lista de clientes
    private DefaultTableModel model1;      // Modelo de tabla para mostrar la información de los clientes

    /**
     * Constructor de la clase Ventana_compra.
     *
     * @param idcajero El ID del cajero.
     */
    public Ventana_compra(int idcajero) {
        super("Ventana Compra");
        setContentPane(panelcompra);
        setUndecorated(true); // Desactiva el decorado de la ventana
        cajeroid.setText(String.valueOf(idcajero)); // Asigna el ID del cajero al campo correspondiente

        // Acción para volver al menú del cajero
        volverButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Ventana_menu_cajero cajero = new Ventana_menu_cajero(idcajero);
                cajero.ingresar(); // Muestra el menú del cajero
                dispose(); // Cierra la ventana actual
            }
        });

        // Acción para mostrar la imagen del producto
        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    mostrarunitario(imagenprod); // Muestra la imagen del producto
                } catch (SQLException | IOException ex) {
                    System.out.println(ex.getMessage());
                }
            }
        });

        // Acción para limpiar los campos
        limpiarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String espacioenblanco = "";
                // Limpia todos los campos de texto
                textField1.setText(espacioenblanco);
                textField2.setText(espacioenblanco);
                textField3.setText(espacioenblanco);
                textField4.setText(espacioenblanco);
                nombcli.setText(espacioenblanco);
                buscarproductos.setText(""); // Limpia el campo de búsqueda de productos
            }
        });

        // Acción para registrar la compra
        ingresarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    registrarCompra(); // Registra la compra
                } catch (SQLException ex) {
                    System.out.println(ex.getMessage());
                }
            }
        });

        // Acción para cerrar la aplicación
        cerrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0); // Cierra la aplicación
            }
        });

        // Acción para minimizar la ventana
        minimizar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setState(Frame.ICONIFIED); // Minimiza la ventana
            }
        });

        // Acción para registrar un nuevo cliente
        registrarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    ingresarusuario(); // Ingresa un nuevo usuario
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });

        // Acción para mostrar la lista de clientes
        mostrarClientesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    cargarDatoss(); // Carga los datos de los clientes
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
    }

    /**
     * Método para mostrar la ventana de compra.
     */
    public void ingresar() {
        setVisible(true); // Hace visible la ventana
        setSize(900, 600); // Establece el tamaño de la ventana
        setLocationRelativeTo(null); // Centra la ventana en la pantalla
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Cierra la aplicación al cerrar la ventana
    }

    /**
     * Método para crear los componentes de la interfaz.
     */
    private void createUIComponents() {
        panelcompra = new Conexion_base_de_datos.CustomPanel("./src/fondoadministrador.jpeg"); // Panel principal
        panel1 = new Conexion_base_de_datos.CustomPanel("./src/imagenes/descargar-removebg.png"); // Panel adicional
        model1 = new DefaultTableModel();
        // Agrega columnas al modelo de la tabla
        model1.addColumn("id");
        model1.addColumn("Nombre");
        model1.addColumn("Apellido");
        model1.addColumn("Correo");
        model1.addColumn("Telefono");
        model1.addColumn("Direccion");

        table1111 = new JTable(); // Crea una nueva tabla
        table1111.setModel(model1); // Asigna el modelo a la tabla
        table1111.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS); // Ajusta el tamaño de las columnas automáticamente
        table1111.setShowGrid(true); // Muestra la cuadrícula en la tabla
        table1111.setGridColor(Color.GRAY); // Establece el color de la cuadrícula
        table1111.getTableHeader().setReorderingAllowed(false); // Desactiva el reordenamiento de las columnas

        scrollPane1 = new JScrollPane(table1111); // Crea un panel de desplazamiento para la tabla
        scrollPane1.setPreferredSize(new Dimension(300, 400)); // Establece el tamaño preferido del panel de desplazamiento

        // Inicializa los botones
        button1 = new JButton();
        volverButton = new JButton();
        limpiarButton = new JButton();
        ingresarButton = new JButton();
        registrarButton = new JButton();
        mostrarClientesButton = new JButton();

        // Personaliza los botones utilizando métodos de la clase Conexion_base_de_datos
        Conexion_base_de_datos.metodobotones(button1);
        Conexion_base_de_datos.metodobotones(volverButton);
        Conexion_base_de_datos.metodobotones(limpiarButton);
        Conexion_base_de_datos.metodobotones(ingresarButton);
        Conexion_base_de_datos.metodobotones(registrarButton);
        Conexion_base_de_datos.metodobotones(mostrarClientesButton);

        // Inicializa los botones para cerrar y minimizar
        cerrar = new JButton();
        minimizar = new JButton();
        Conexion_base_de_datos.personalizeButton(volverButton);
        Conexion_base_de_datos.personalizeButton(cerrar);
        Conexion_base_de_datos.personalizeButton(minimizar);
    }

    /**
     * Método para ingresar un nuevo usuario en la base de datos.
     *
     * @throws SQLException si ocurre un error al realizar la operación.
     */
    public void ingresarusuario() throws SQLException {
        Connection conectar = null;
        PreparedStatement stmt = null;
        String nomb = nombcli.getText(); // Obtiene el nombre del cliente
        String apellido = apeliicli.getText(); // Obtiene el apellido del cliente
        String correo = correocli.getText(); // Obtiene el correo del cliente
        String telefono = telecli.getText(); // Obtiene el teléfono del cliente
        String direccion = dircli.getText(); // Obtiene la dirección del cliente
        String sql = "INSERT INTO Clientes(nombre, apellido, correo, telefono, direccion) VALUES (?, ?, ?, ?, ?)"; // SQL para insertar cliente

        try {
            conectar = new Conexion_base_de_datos().conexion(); // Establece la conexión a la base de datos
            stmt = conectar.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS); // Prepara la sentencia
            stmt.setString(1, nomb);
            stmt.setString(2, apellido);
            stmt.setString(3, correo);
            stmt.setInt(4, Integer.parseInt(telefono)); // Convierte el teléfono a entero
            stmt.setString(5, direccion);
            int columnas = stmt.executeUpdate(); // Ejecuta la inserción
            if (columnas > 0) {
                ResultSet generatedKeys = stmt.getGeneratedKeys(); // Obtiene las claves generadas
                if (generatedKeys.next()) {
                    int idGenerado = generatedKeys.getInt(1); // Obtiene el ID del cliente generado
                    clienteid.setText(String.valueOf(idGenerado)); // Muestra el ID en el campo correspondiente
                }
                JOptionPane.showMessageDialog(null, "Datos ingresados exitosamente"); // Mensaje de éxito
            } else {
                JOptionPane.showMessageDialog(null, "Datos no ingresados"); // Mensaje de error
            }
        } catch (SQLException ex) {
            ex.printStackTrace(); // Muestra la traza de la excepción
        } finally {
            new Conexion_base_de_datos().cerrarRecursos(conectar, stmt, null); // Cierra recursos
        }
    }

    /**
     * Método para cargar los datos de los clientes en la tabla.
     *
     * @throws SQLException si ocurre un error al realizar la operación.
     */
    private void cargarDatoss() throws SQLException {
        Connection conexion = null;
        Statement statement = null;
        ResultSet resultSet = null;
        try {
            model1.setRowCount(0); // Limpia la tabla antes de cargar nuevos datos
            Conexion_base_de_datos da = new Conexion_base_de_datos();
            conexion = new Conexion_base_de_datos().conexion(); // Establece la conexión a la base de datos
            statement = conexion.createStatement(); // Crea un objeto Statement
            resultSet = statement.executeQuery("SELECT * FROM Clientes"); // Ejecuta la consulta para obtener clientes
            while (resultSet.next()) {
                model1.addRow(new Object[]{
                        resultSet.getString("id"),
                        resultSet.getString("nombre"),
                        resultSet.getString("apellido"),
                        resultSet.getString("correo"),
                        resultSet.getInt("telefono"),
                        resultSet.getString("direccion")
                }); // Agrega los datos a la tabla
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Muestra la traza de la excepción
        } finally {
            new Conexion_base_de_datos().cerrarRecursos(conexion, statement, resultSet); // Cierra recursos
        }
    }

    /**
     * Método para cargar los productos en la tabla (no se utiliza en este fragmento).
     *
     * @param model Modelo de tabla al que se agregarán los productos.
     * @throws SQLException si ocurre un error al realizar la operación.
     */
    private static void cargarProductos(DefaultTableModel model) throws SQLException {
        Connection conexion = null;
        Statement statement = null;
        ResultSet resultSet = null;
        try {
            conexion = new Conexion_base_de_datos().conexion(); // Establece la conexión a la base de datos
            statement = conexion.createStatement(); // Crea un objeto Statement
            resultSet = statement.executeQuery("SELECT * FROM Productos"); // Ejecuta la consulta para obtener productos
            while (resultSet.next()) {
                String codigo = resultSet.getString("id");
                String nombre = resultSet.getString("nombre");
                double precio = resultSet.getDouble("precio");
                String descripcion = resultSet.getString("descripcion");
                String cantidad_stock = resultSet.getString("stock");
                model.addRow(new Object[]{codigo, nombre, precio, descripcion, cantidad_stock}); // Agrega los datos a la tabla
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Muestra la traza de la excepción
        } finally {
            new Conexion_base_de_datos().cerrarRecursos(conexion, statement, resultSet); // Cierra recursos
        }
    }

    /**
     * Método para mostrar la información de un producto específico.
     *
     * @param imagenproducto Etiqueta donde se mostrará la imagen del producto.
     * @throws SQLException si ocurre un error al realizar la operación.
     * @throws IOException  si ocurre un error al manejar la imagen.
     */
    public void mostrarunitario(JLabel imagenproducto) throws SQLException, IOException {
        Connection conexion = null;
        PreparedStatement stmt = null;
        ResultSet RS = null;
        String producto = buscarproductos.getText(); // Obtiene el ID del producto del campo de búsqueda
        String sql = "SELECT nombre, descripcion, precio, stock, imagenes FROM Productos WHERE id = ?"; // Consulta para obtener detalles del producto

        try {
            conexion = new Conexion_base_de_datos().conexion(); // Establece la conexión a la base de datos
            stmt = conexion.prepareStatement(sql); // Prepara la sentencia
            stmt.setString(1, producto); // Establece el ID del producto
            RS = stmt.executeQuery(); // Ejecuta la consulta
            if (RS.next()) { // Si se encuentra el producto
                String nombre = RS.getString("nombre");
                String descripcion = RS.getString("descripcion");
                double precio = RS.getDouble("precio");
                String cantidad_stock = RS.getString("stock");
                byte[] imgBytes = RS.getBytes("imagenes"); // Obtiene la imagen del producto

                if (imgBytes != null) {
                    ByteArrayInputStream bais = new ByteArrayInputStream(imgBytes);
                    BufferedImage originalImage = ImageIO.read(bais); // Lee la imagen desde los bytes
                    // Redimensiona la imagen
                    Image scaledImage = originalImage.getScaledInstance(100, 100, Image.SCALE_SMOOTH);
                    imagenproducto.setIcon(new ImageIcon(scaledImage)); // Establece la imagen en la etiqueta
                } else {
                    imagenproducto.setIcon(null); // Si no hay imagen, establece el icono a null
                }
                // Muestra los detalles del producto en los campos correspondientes
                textField1.setText(nombre);
                textField2.setText(descripcion);
                textField3.setText(String.valueOf(precio));
                textField4.setText(cantidad_stock);
            } else {
                JOptionPane.showMessageDialog(null, "No se encontró el producto"); // Mensaje si no se encuentra el producto
            }
        } catch (SQLException ex) {
            ex.printStackTrace(); // Muestra la traza de la excepción
        } finally {
            new Conexion_base_de_datos().cerrarRecursos(conexion, stmt, RS); // Cierra recursos
        }
    }

    /**
     * Método para registrar una compra en la base de datos.
     *
     * @throws SQLException si ocurre un error al realizar la operación.
     */
    public void registrarCompra() throws SQLException {
        Connection conexion = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            conexion = new Conexion_base_de_datos().conexion(); // Establece la conexión a la base de datos
            int customerId = Integer.parseInt(clienteid.getText()); // Obtiene el ID del cliente
            int userId = Integer.parseInt(cajeroid.getText()); // Obtiene el ID del cajero
            int productId = Integer.parseInt(productoid.getText()); // Obtiene el ID del producto
            int quantity = Integer.parseInt(cant.getText()); // Obtiene la cantidad del producto

            // Inserta la venta en la tabla Ventas
            String sql = "INSERT INTO Ventas (cliente_id, usuario_id, total) VALUES (?, ?, ?)";
            pstmt = conexion.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
            pstmt.setInt(1, customerId);
            pstmt.setInt(2, userId);
            pstmt.setDouble(3, 0.0); // Total inicial es 0.0
            pstmt.executeUpdate();
            rs = pstmt.getGeneratedKeys(); // Obtiene el ID de la venta generada
            int saleId = 0;
            if (rs.next()) {
                saleId = rs.getInt(1); // Almacena el ID de la venta
            }

            // Inserta los detalles de la venta en la tabla Detalles_Ventas
            double subtotal = 0.0;
            String sqlsub = "INSERT INTO Detalles_Ventas (venta_id, producto_id, cantidad, precio_unitario, subtotal) VALUES (?, ?, ?, ?, ?)";
            pstmt = conexion.prepareStatement(sqlsub);
            pstmt.setInt(1, saleId);
            pstmt.setInt(2, productId);
            pstmt.setInt(3, quantity);
            double unitPrice = obtenerprecio(productId); // Obtiene el precio unitario del producto
            pstmt.setDouble(4, unitPrice);
            subtotal = unitPrice * quantity; // Calcula el subtotal
            pstmt.setDouble(5, subtotal);
            pstmt.executeUpdate();

            // Actualiza el total de la venta
            String sqlactualizar = "UPDATE Ventas SET total = total + ? WHERE id = ?";
            pstmt = conexion.prepareStatement(sqlactualizar);
            pstmt.setDouble(1, subtotal);
            pstmt.setInt(2, saleId);
            pstmt.executeUpdate();

            // Actualiza el stock del producto
            String sqlactualizarStock = "UPDATE Productos SET stock = stock - ? WHERE id = ?";
            pstmt = conexion.prepareStatement(sqlactualizarStock);
            pstmt.setInt(1, quantity);
            pstmt.setInt(2, productId);
            pstmt.executeUpdate();

            // Inserta la factura correspondiente a la venta
            String sqlInsert = "INSERT INTO Facturas (venta_id, fecha_emision, cliente_id, total) VALUES (?, CURRENT_TIMESTAMP, ?, ?)";
            pstmt = conexion.prepareStatement(sqlInsert);
            pstmt.setInt(1, saleId);
            pstmt.setInt(2, customerId);
            pstmt.setDouble(3, subtotal); // Utiliza el total calculado
            pstmt.executeUpdate();

            JOptionPane.showMessageDialog(null, "Compra registrada exitosamente."); // Mensaje de éxito
        } catch (SQLException ex) {
            ex.printStackTrace(); // Muestra la traza de la excepción
            JOptionPane.showMessageDialog(null, "Error al registrar la compra: " + ex.getMessage()); // Mensaje de error
        } finally {
            new Conexion_base_de_datos().cerrarRecursos(conexion, pstmt, rs); // Cierra recursos
        }
    }

    /**
     * Método para obtener el precio de un producto por su ID.
     *
     * @param productId ID del producto.
     * @return Precio del producto.
     * @throws SQLException si ocurre un error al realizar la operación.
     */
    private double obtenerprecio(int productId) throws SQLException {
        Connection conexion = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        double price = 0.0;

        try {
            conexion = new Conexion_base_de_datos().conexion(); // Establece la conexión a la base de datos
            String sql = "SELECT precio FROM Productos WHERE id = ?"; // Consulta para obtener el precio del producto
            pstmt = conexion.prepareStatement(sql);
            pstmt.setInt(1, productId); // Establece el ID del producto
            rs = pstmt.executeQuery(); // Ejecuta la consulta
            if (rs.next()) {
                price = rs.getDouble("precio"); // Obtiene el precio
            } else {
                throw new Exception("Producto no encontrado"); // Lanza una excepción si no se encuentra el producto
            }
        } catch (SQLException ex) {
            ex.printStackTrace(); // Muestra la traza de la excepción
        } catch (Exception e) {
            e.printStackTrace(); // Muestra la traza de la excepción
        } finally {
            new Conexion_base_de_datos().cerrarRecursos(conexion, pstmt, rs); // Cierra recursos
        }

        return price; // Devuelve el precio del producto
    }
}

