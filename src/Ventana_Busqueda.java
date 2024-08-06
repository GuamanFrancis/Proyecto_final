import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class Ventana_Busqueda extends JFrame{

    private JPanel panelprincipa;
    private JPanel panel1;
    private JLabel imageniz;
    private JLabel imagendere;
    private JTextField busccajero;
    private JButton buscarButton;
    private JTable table1;
    private JScrollPane scrooll;
    private JButton mostrarButton;
    private JPanel paneljtable;
    private JButton cerrar;
    private JButton minimizar;
    private JButton volverButton;
    private DefaultTableModel model;


    public Ventana_Busqueda() {
        super("Ventana Busqueda");
        setContentPane(panelprincipa);
        setUndecorated(true);





        buscarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nombre = busccajero.getText();
                try {
                    cargarDatosporbusqueda(nombre);
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }


            }
        });
        mostrarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    cargarDatos();
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }

            }
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
        volverButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Ventana_menu_administrador ventana = new Ventana_menu_administrador();
                ventana.ingresar();
                dispose();
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
        panelprincipa = new Conexion_base_de_datos.CustomPanel("./src/fondocajero.pmg.jpg");
        panel1 = new Conexion_base_de_datos.CustomPanel("./src/imagenes/descargar-removebg.png");
        imageniz = new JLabel();
        Image imagen = new ImageIcon("./src/imagenes/user.png").getImage();
        ImageIcon img1 = new ImageIcon(imagen.getScaledInstance(70, 70, Image.SCALE_SMOOTH));
        imageniz.setIcon(img1);
        imagendere = new JLabel();
        Image imagen1 = new ImageIcon("./src/imagenes/search.png").getImage();
        ImageIcon img2 = new ImageIcon(imagen1.getScaledInstance(70, 70, Image.SCALE_SMOOTH));
        imagendere.setIcon(img2);
        model = new DefaultTableModel();
        model.addColumn("Cliente ID");
        model.addColumn("Fecha venta");
        model.addColumn("Cajero ID");
        model.addColumn("Total venta");
        table1 = new JTable(model);
        table1.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        table1.setShowGrid(true); // Mostrar la cuadrícula
        table1.setGridColor(Color.GRAY); // Color de la cuadrícula
        table1.getTableHeader().setReorderingAllowed(false);
        add(table1);
        scrooll = new JScrollPane(table1);
        scrooll.setPreferredSize(new Dimension(300, 400));
        cerrar = new JButton();
        minimizar = new JButton();
        volverButton = new JButton();
        personalizeButton(volverButton);
        personalizeButton(cerrar);
        personalizeButton(minimizar);




    }
    private void cargarDatos()throws SQLException {
        Connection conexion=null;
        Statement statement = null;
        ResultSet resultSet=null;
        try {
            conexion = new Conexion_base_de_datos().conexion();
            model.setRowCount(0);
             statement = conexion.createStatement();
             resultSet = statement.executeQuery("SELECT * FROM Ventas");
            while (resultSet.next()) {
                model.addRow(new Object[]{
                        resultSet.getString("cliente_id"),
                        resultSet.getString("fecha_venta"),
                        resultSet.getString("usuario_id"),
                        resultSet.getDouble("total")
                });
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
                new Conexion_base_de_datos().cerrarRecursos(conexion,statement,resultSet);
        }
    }


    private void cargarDatosporbusqueda(String name)throws SQLException {
        Connection conexion=null;
        PreparedStatement stmt = null;
        ResultSet resultSet=null;
        try {
            conexion = new Conexion_base_de_datos().conexion();
            String sql ="SELECT codigo FROM Cajeros WHERE username = ?";
            stmt = conexion.prepareStatement(sql);
            stmt.setString(1, name);
            resultSet = stmt.executeQuery();
            if (resultSet.next()) {
                int idCajero = resultSet.getInt("codigo");
                Connection conexion2=null;
                PreparedStatement stmtVentas = null;
                ResultSet ventasResultSet=null;
                String sqlVentas = "SELECT cliente_id, fecha_venta,usuario_id,total FROM Ventas WHERE usuario_id = ?";
                    try {
                        conexion2= new Conexion_base_de_datos().conexion();
                        model.setRowCount(0);
                        stmtVentas = conexion2.prepareStatement(sqlVentas);
                        stmtVentas.setInt(1, idCajero);
                         ventasResultSet = stmtVentas.executeQuery();
                        while (ventasResultSet.next()) {
                            model.addRow(new Object[]{
                                ventasResultSet.getString("cliente_id"),
                                ventasResultSet.getString("fecha_venta"),
                                ventasResultSet.getString("usuario_id"),
                                ventasResultSet.getDouble("total")
                            });
                        }
                    }catch (SQLException ex){
                        ex.printStackTrace();
                    } finally {
                        new Conexion_base_de_datos().cerrarRecursos(conexion,stmtVentas,ventasResultSet);
                    }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            new Conexion_base_de_datos().cerrarRecursos(conexion,stmt,resultSet);
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
