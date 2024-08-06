import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.sql.*;

public class Registrar_producto extends JFrame{
    private JButton ingresarButton;
    private JTextField nombre;
    private JTextField Des;
    private JTextField Prec;
    private JTextField cant;
    private JTextField imagen;
    private JLabel imagenlabel;
    private JPanel panelregistro;
    private JButton volverButton;
    private JButton registrarCajeroButton;
    private JTable table1;

    private JTextField nomcajero;
    private JTextField usercajero;
    private JButton seleccionarButton;
    private JButton mostrarproducto;
    private JTextField correocajero;
    private JButton mostrarCajerosButton;
    private JPasswordField passwordcajero;
    private JScrollPane scrooll;
    private JTable table2;
    private JScrollPane scroll2;
    private JPanel panel2;
    private JButton minimizar;
    private JButton cerrar;
    private String imagenRuta;
    private DefaultTableModel model1;
    private DefaultTableModel model2;


    public Registrar_producto() {
        super("Ventana Registro");
        setContentPane(panelregistro);
        setUndecorated(true);

        ingresarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try{Registrarproductos();
                } catch(SQLException ex) {
                    System.out.println(ex.getMessage());}
                 catch (FileNotFoundException ex) {
                    throw new RuntimeException(ex);
                }
            }});
        volverButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               Ventana_menu_administrador menu = new Ventana_menu_administrador();
               menu.ingresar();
               dispose();
            }
        });

        seleccionarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                seleccionarImagen();

            }
        });
        mostrarproducto.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    cargarDatoss();
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
        registrarCajeroButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    ingresarcajero();
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }

            }
        });

        mostrarCajerosButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {try {cargarDatos();} catch (SQLException ex) {throw new RuntimeException(ex);}}
        });
        minimizar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setState(Frame.ICONIFIED);
            }
        });
        cerrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
    }
    public void ingresar(){
        setVisible(true);
        setSize(900,600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public void Registrarproductos() throws SQLException, FileNotFoundException {
        Connection cone = null;
        PreparedStatement stmt = null;
        String nom = nombre.getText();
        String descripcion = Des.getText();
        Double precio = Double.parseDouble(Prec.getText());
        String cantidad = cant.getText();
        try {
            cone = new Conexion_base_de_datos().conexion();
            String sql = "INSERT  INTO Productos(nombre,descripcion,precio,stock,imagenes,fecha_creacion)values(?,?,?,?,?,NOW())";
            stmt = cone.prepareStatement(sql);
            stmt.setString(1,nom);
            stmt.setString(2,descripcion);
            stmt.setDouble(3,precio);
            stmt.setInt(4,Integer.parseInt(cantidad));
            if (imagenRuta != null) {
                FileInputStream fis = new FileInputStream(new File(imagenRuta));
                stmt.setBinaryStream(5, fis, (int) new File(imagenRuta).length());
            } else {
                stmt.setNull(5, Types.BLOB);
            }
            int columnas = stmt.executeUpdate();
            if (columnas>0){
                JOptionPane.showMessageDialog(null,"Datos ingresados exitosamente");
            }else{
                JOptionPane.showMessageDialog(null,"Datos no ingresados");
            }
        }catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            new Conexion_base_de_datos().cerrarRecursos(cone, stmt, null);
        }

    }
    private void seleccionarImagen() {
        JFileChooser fileChooser = new JFileChooser();
        int resultado = fileChooser.showOpenDialog(null);
        if (resultado == JFileChooser.APPROVE_OPTION) {
            File archivoSeleccionado = fileChooser.getSelectedFile();
            imagenRuta = archivoSeleccionado.getAbsolutePath();
            imagen.setText(imagenRuta);
        }
    }

    private void createUIComponents() throws SQLException {
        // TODO: place custom component creation code here
        volverButton = new JButton();
        personalizeButton(volverButton);
        panelregistro = new Conexion_base_de_datos.CustomPanel("./src/fondocajero.pmg.jpg");
        panel2 = new Conexion_base_de_datos.CustomPanel("./src/imagenes/descargar-removebg.png");
        imagenlabel = new JLabel();
        Image imagen = new ImageIcon("./src/imagenes/edit.png").getImage();
        ImageIcon img1 = new ImageIcon(imagen.getScaledInstance(70, 70, Image.SCALE_SMOOTH));
        imagenlabel.setIcon(img1);
        model1 = new DefaultTableModel();
        model2 = new DefaultTableModel();
        model2.addColumn("Cliente ID");model2.addColumn("Username");model2.addColumn("Cajero");model2.addColumn("Nombre completo");model1.addColumn("Cliente ID");model1.addColumn("Fecha venta");model1.addColumn("Cajero ID");model1.addColumn("Total venta");
        table1 = new JTable(model1);
        table2 = new JTable(model2);
        add(table1);
        add(table2);
        scroll2 = new JScrollPane(table2);
        scroll2.setPreferredSize(new Dimension(300, 400));
        scrooll = new JScrollPane(table1);
        scrooll.setPreferredSize(new Dimension(300, 400));
        cerrar = new JButton();
        minimizar = new JButton();
        ingresarButton = new JButton();
        seleccionarButton = new JButton();
        mostrarproducto = new JButton();
        mostrarCajerosButton = new JButton();
        registrarCajeroButton = new JButton();
        Conexion_base_de_datos.metodobotones(ingresarButton);
        Conexion_base_de_datos.metodobotones(seleccionarButton);
        Conexion_base_de_datos.metodobotones(mostrarproducto);
        Conexion_base_de_datos.metodobotones(mostrarCajerosButton);
        Conexion_base_de_datos.metodobotones(registrarCajeroButton);
        Conexion_base_de_datos.personalizeButton(minimizar);
        Conexion_base_de_datos.personalizeButton(cerrar);





    }

    public void ingresarcajero()throws SQLException{
        Connection conectar =null;
        PreparedStatement stmt=null;
        String nomb = nomcajero.getText();
        String user = usercajero.getText();
        String correo = correocajero.getText();
        String pass = passwordcajero.getText();
        String sql = " INSERT  INTO Cajeros(username,password,Correo,Nombre_y_Apellido)values(?,?,?,?)";
        try {
            conectar = new Conexion_base_de_datos().conexion();
            stmt = conectar.prepareStatement(sql);
            stmt.setString(1,user);
            stmt.setString(2,pass);
            stmt.setString(3,correo);
            stmt.setString(4,nomb);
            int columnas = stmt.executeUpdate();
            if (columnas>0){
                JOptionPane.showMessageDialog(null,"Datos ingresados exitosamente");
            }else{
                JOptionPane.showMessageDialog(null,"Datos no ingresados");
            }

        }catch (SQLException ex){
            System.out.println(ex.getMessage());
        }finally {
            new Conexion_base_de_datos().cerrarRecursos(conectar,stmt,null);
        }



    }
    private void cargarDatoss()throws SQLException {
        Connection conexion=null;
        Statement statement = null;
        ResultSet resultSet=null;

        try {
            conexion = new Conexion_base_de_datos().conexion();
            model1.setRowCount(0);
            Conexion_base_de_datos da = new Conexion_base_de_datos();
             conexion = da.conexion();
             statement = conexion.createStatement();
             resultSet = statement.executeQuery("SELECT * FROM Productos");
            while (resultSet.next()) {
                model1.addRow(new Object[]{
                        resultSet.getString("nombre"),
                        resultSet.getString("descripcion"),
                        resultSet.getString("precio"),
                        resultSet.getDouble("stock")
                });
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            new Conexion_base_de_datos().cerrarRecursos(conexion,statement,resultSet);
        }
    }
    private void cargarDatos()throws SQLException {
        Connection conexion=null;
        Statement statement = null;
        ResultSet resultSet=null;
        try {
            conexion=new Conexion_base_de_datos().conexion();
            model2.setRowCount(0);
             statement = conexion.createStatement();
             resultSet = statement.executeQuery("SELECT * FROM Cajeros");
            while (resultSet.next()) {
                model2.addRow(new Object[]{
                        resultSet.getString("codigo"),
                        resultSet.getString("username"),
                        resultSet.getString("Correo"),
                        resultSet.getString("Nombre_y_Apellido")
                });
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            new Conexion_base_de_datos().cerrarRecursos(conexion,statement,resultSet);
        }
    }

    private void personalizeButton(JButton button) {
        button.setFocusPainted(false);
        button.setBorderPainted(false);
        button.setContentAreaFilled(false);
        button.setFont(new Font("Arial", Font.BOLD, 16));
        button.setForeground(Color.CYAN);
        button.setBackground(Color.DARK_GRAY);
        button.setPreferredSize(new Dimension(20, 20)); // Tamaño personalizado
        button.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR)); // Cambiar el cursor
    }


}
