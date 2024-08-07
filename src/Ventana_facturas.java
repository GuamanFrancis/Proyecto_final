import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDFont;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.apache.pdfbox.pdmodel.graphics.image.PDImageXObject;

public class Ventana_facturas extends JFrame{
    private JPanel panelfacturas;
    private JButton cerrar;
    private JButton minimizar;
    private JButton volverButton;
    private JButton mostrarFacturasButton;
    private JButton imprimirButton;
    private JTable table1;
    private DefaultTableModel model1;
    private JPanel panel1;
    private JScrollPane scrollPane1;

    public Ventana_facturas(int idCajero) {
        super("Ventana facturas");
        setContentPane(panelfacturas);
        setUndecorated(true);
        mostrarFacturasButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    cargarDatos();
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }

            }
        });
        cerrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {System.exit(0);}
        });
        minimizar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {setState(Frame.ICONIFIED);}
        });
        volverButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Ventana_menu_cajero menu = new Ventana_menu_cajero(idCajero);
                menu.ingresar();
                dispose();
            }
        });
        imprimirButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = table1.getSelectedRow();
                if (selectedRow >= 0) {
                    String ventaId = model1.getValueAt(selectedRow, 1).toString(); // ID de la venta
                    String rutaSalida = "Factura_" + ventaId + ".pdf"; // Nombre del archivo PDF
                    generarPDF(ventaId, rutaSalida);
                } else {
                    JOptionPane.showMessageDialog(null, "Por favor, selecciona una fila de la tabla.");
                }

            }
        });
    }
    private void createUIComponents() {
        panelfacturas = new Conexion_base_de_datos.CustomPanel("./src/fondoadministrador.jpeg");
        panel1 = new Conexion_base_de_datos.CustomPanel("./src/imagenes/descargar-removebg.png");
        volverButton = new JButton();
        cerrar = new JButton();
        minimizar = new JButton();
        mostrarFacturasButton = new JButton();
        imprimirButton = new JButton();
        Conexion_base_de_datos.metodobotones(mostrarFacturasButton);
        Conexion_base_de_datos.metodobotones(imprimirButton);
        Conexion_base_de_datos.personalizeButton(volverButton);
        Conexion_base_de_datos.personalizeButton(cerrar);
        Conexion_base_de_datos.personalizeButton(minimizar);
        model1 = new DefaultTableModel();
        model1.addColumn("ID Factura");model1.addColumn("ID Venta");model1.addColumn("ID Cliente ");model1.addColumn("Total");model1.addColumn("Fecha");
        table1 = new JTable();
        table1.setModel(model1);
        table1.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        table1.setShowGrid(true);
        table1.setGridColor(Color.GRAY);
        table1 = new JTable(model1);
        table1.setShowGrid(true);
        table1.setGridColor(Color.GRAY);
        table1.getTableHeader().setReorderingAllowed(false);
        add(table1);
        scrollPane1 = new JScrollPane(table1);
        scrollPane1.setPreferredSize(new Dimension(300, 400));

    }
    public void ingresar(){
        setVisible(true);
        setSize(900,600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }
    private void cargarDatos()throws SQLException {
        Connection conexion = null;
        Statement statement =null;
        ResultSet resultSet =null;
        try {
            model1.setRowCount(0);
            conexion = new Conexion_base_de_datos().conexion();
            statement = conexion.createStatement();
            resultSet = statement.executeQuery("SELECT * FROM Facturas");
            while (resultSet.next()) {
                model1.addRow(new Object[]{
                        resultSet.getString("id"),
                        resultSet.getString("venta_id"),
                        resultSet.getString("cliente_id"),
                        resultSet.getString("total"),
                        resultSet.getDate("fecha_emision")
                });
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            new Conexion_base_de_datos().cerrarRecursos(conexion,statement,resultSet);
        }
    }
    private void generarPDF(String ventaId, String rutaSalida) {
        Connection conexion = null;
        Statement statement =null;
        ResultSet facturaResultSet =null;
        ResultSet detallesResultSet =null;
        try (PDDocument document = new PDDocument()) {
            PDPage page = new PDPage();
            document.addPage(page);
            PDPageContentStream contentStream = new PDPageContentStream(document, page);


            PDFont boldFont = PDType1Font.HELVETICA_BOLD;
            PDFont regularFont = PDType1Font.HELVETICA;


            PDImageXObject logo = PDImageXObject.createFromFile("./src/imagenes/logoa-fotor-bg-remover-2024073116374.png", document);
            contentStream.drawImage(logo, 80, 730, 100, 50);


            contentStream.beginText();
            contentStream.setFont(boldFont, 16);
            contentStream.newLineAtOffset(220, 750);
            contentStream.showText("FACTURA VENTA");
            contentStream.endText();
            contentStream.moveTo(50, 740);
            contentStream.lineTo(550, 740);
            contentStream.stroke();


            contentStream.beginText();
            contentStream.setFont(boldFont, 12);
            contentStream.newLineAtOffset(50, 710);
            contentStream.showText("AccesoTech");
            contentStream.newLineAtOffset(0, -15);
            contentStream.setFont(regularFont, 12);
            contentStream.showText("RUC: (0102030456123)");
            contentStream.newLineAtOffset(0, -15);
            contentStream.showText("Dirección: San Juan de Calderon ");
            contentStream.newLineAtOffset(0, -15);
            contentStream.showText("Teléfono / email: 0986263165");
            contentStream.endText();

            conexion= new Conexion_base_de_datos().conexion();
             statement = conexion.createStatement();


            String facturasql = "SELECT f.id AS factura_id, f.fecha_emision, f.total AS total_factura, " +
                    "c.nombre AS cliente_nombre, c.apellido AS cliente_apellido, c.correo AS cliente_correo, " +
                    "c.telefono AS cliente_telefono, c.direccion AS cliente_direccion " +
                    "FROM Facturas f " +
                    "JOIN Clientes c ON f.cliente_id = c.id " +
                    "WHERE f.venta_id = " + ventaId;

             facturaResultSet = statement.executeQuery(facturasql);
            System.out.println("Consulta para ventaId: " + ventaId);

            if (facturaResultSet.next()) {
                contentStream.beginText();
                contentStream.setFont(boldFont, 12);
                contentStream.newLineAtOffset(50, 640);
                contentStream.showText("Datos del cliente:");
                contentStream.endText();


                contentStream.setNonStrokingColor(0, 0, 0);
                contentStream.beginText();
                contentStream.setFont(regularFont, 12);
                contentStream.newLineAtOffset(50, 620);
                contentStream.showText("Nombre y Apellido: " + facturaResultSet.getString("cliente_nombre") + " " + facturaResultSet.getString("cliente_apellido"));
                contentStream.newLineAtOffset(0, -15);
                contentStream.showText("Correo: " + facturaResultSet.getString("cliente_correo"));
                contentStream.newLineAtOffset(0, -15);
                contentStream.showText("Teléfono: " + facturaResultSet.getString("cliente_telefono"));
                contentStream.newLineAtOffset(0, -15);
                contentStream.showText("Dirección: " + facturaResultSet.getString("cliente_direccion"));
                contentStream.endText();


                contentStream.beginText();
                contentStream.setFont(regularFont, 12);
                contentStream.newLineAtOffset(50, 560);
                contentStream.showText("ID Factura: " + facturaResultSet.getString("factura_id"));
                contentStream.newLineAtOffset(0, -15);
                contentStream.showText("Total: $" + facturaResultSet.getString("total_factura"));
                contentStream.newLineAtOffset(0, -15);
                contentStream.showText("Fecha de Emisión: " + facturaResultSet.getDate("fecha_emision"));
                contentStream.endText();
            } else {
                JOptionPane.showMessageDialog(null, "No se encontraron datos para la factura con ID: " + ventaId);
                return;
            }

            String detallessql = "SELECT dv.cantidad, dv.precio_unitario, dv.subtotal, " +
                    "p.nombre AS producto_nombre, p.descripcion AS producto_descripcion " +
                    "FROM Detalles_Ventas dv " +
                    "JOIN Productos p ON dv.producto_id = p.id " +
                    "WHERE dv.venta_id = " + ventaId;
            try {
                 detallesResultSet = statement.executeQuery(detallessql);

                contentStream.moveTo(50, 530);
                contentStream.lineTo(550, 530);
                contentStream.stroke();

                contentStream.beginText();
                contentStream.setFont(boldFont, 12);
                contentStream.newLineAtOffset(50, 490);
                contentStream.showText("Descripción");
                contentStream.newLineAtOffset(250, 0);
                contentStream.showText("Unidades");
                contentStream.newLineAtOffset(100, 0);
                contentStream.showText("Precio unitario");
                contentStream.newLineAtOffset(100, 0);
                contentStream.showText("Importe");
                contentStream.endText();

                contentStream.moveTo(50, 510);
                contentStream.lineTo(550, 510);
                contentStream.stroke();


                int yOffset = 450;
                while (detallesResultSet.next()) {
                    if (yOffset < 50) {
                        contentStream.close();
                        page = new PDPage();
                        document.addPage(page);
                        contentStream = new PDPageContentStream(document, page);
                        yOffset = 750;
                    }
                    String descripcion = detallesResultSet.getString("producto_nombre") + " - " + detallesResultSet.getString("producto_descripcion");
                    List<String> descripcionLines = splitTextIntoLines(descripcion, 50);

                    for (String line : descripcionLines) {
                        contentStream.beginText();
                        contentStream.setFont(regularFont, 12);
                        contentStream.newLineAtOffset(40, yOffset);
                        contentStream.showText(line);
                        contentStream.endText();
                        yOffset -= 15;
                    }

                    contentStream.beginText();
                    contentStream.setFont(regularFont, 12);
                    contentStream.newLineAtOffset(300, yOffset + 20 * descripcionLines.size());
                    contentStream.showText(detallesResultSet.getString("cantidad"));
                    contentStream.newLineAtOffset(100, 0);
                    contentStream.showText(detallesResultSet.getString("precio_unitario") + " $");
                    contentStream.newLineAtOffset(100, 0);
                    contentStream.showText(detallesResultSet.getString("subtotal") + " $");
                    contentStream.endText();

                    yOffset -= 20;
                }

                contentStream.close();
                document.save(rutaSalida);
                JOptionPane.showMessageDialog(null, "Se creó el archivo '" + rutaSalida + "' en la carpeta del proyecto");


            }catch (SQLException ex) {
                ex.printStackTrace();
            }finally {
                new Conexion_base_de_datos().cerrarRecursos(null,null,detallesResultSet);
            }

        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error al generar el PDF: " + e.getMessage());
        }finally {
            new Conexion_base_de_datos().cerrarRecursos(conexion,statement,facturaResultSet);
        }
    }
    private List<String> splitTextIntoLines(String text, int maxLength) {
        List<String> lines = new ArrayList<>();
        String[] words = text.split(" ");
        StringBuilder currentLine = new StringBuilder();

        for (String word : words) {
            if (currentLine.length() + word.length() + 1 > maxLength) {
                lines.add(currentLine.toString());
                currentLine = new StringBuilder();
            }
            if (currentLine.length() > 0) {
                currentLine.append(" ");
            }
            currentLine.append(word);
        }

        if (currentLine.length() > 0) {
            lines.add(currentLine.toString());
        }

        return lines;
    }


}
