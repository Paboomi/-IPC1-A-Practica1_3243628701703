package cipherchat.frontend;

import cipherchat.backend.ListaUsuarios;
import cipherchat.backend.Usuario;
import cipherchat.backend.buttons.ButtonEditor;
import cipherchat.backend.buttons.ButtonRenderer;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author saien
 */
public class TablaContactos extends JPanel {
//    private ListaUsuarios usuarios;

    private JTable table;
    private JButton btnChat;
    private JButton btnEliminar;
    private Usuario usuario;
    private DefaultTableModel tableModel;
    private ListaUsuarios usuarios;
    private Font fuenteGenerica = new Font("Hack", Font.BOLD, 18);

    public TablaContactos(Usuario usuario, ListaUsuarios usuarios) {
        this.usuario = usuario;
        this.usuarios = usuarios;
        setSize(new Dimension(830, 550));
        setBackground(Color.WHITE);
        setLayout(new BorderLayout());
        add(mostrarContactos(), BorderLayout.CENTER);
    }

    private JPanel mostrarContactos() {
        //Fuente para las columnas
        Font font = new Font("Arial", Font.BOLD, 24);

        //Creamos nuestro modelo de tabla
        tableModel = new DefaultTableModel();
        //Agregamos las columnas
        tableModel.addColumn("Codigo");
        tableModel.addColumn("Nombre");
        tableModel.addColumn("Chat");
        tableModel.addColumn("Eliminar");
        try {
            for (Usuario contacto : usuario.getContactos()) {

                Object[] rowData = {usuario.getCodeUser(),
                    usuario.getNombre() + " " + usuario.getApellido(), "Chat", " Eliminar"
                };
                tableModel.addRow(rowData);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        table = new JTable(tableModel);
        table.getTableHeader().setReorderingAllowed(false);
        table.setRowHeight(20);
        table.setFont(new Font("Verdana", Font.ROMAN_BASELINE, 20));

        table.getColumn("Chat").setCellRenderer(new ButtonRenderer());
        table.getColumn("Chat").setCellEditor(new ButtonEditor(table, usuario));
        
        table.getColumn("Eliminar").setCellRenderer(new ButtonRenderer());
        table.getColumn("Eliminar").setCellEditor(new ButtonEditor(table, usuario));

//        //Configuramos las columnas de la tabla
//        table.getColumnModel().getColumn(0).setPreferredWidth(80);
//        table.getColumnModel().getColumn(1).setPreferredWidth(100);
//        table.getColumnModel().getColumn(2).setPreferredWidth(10);
//        table.getColumnModel().getColumn(3).setPreferredWidth(10);

        //Creamos un ScrollPane que contendra nuestra JTable
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setPreferredSize(new Dimension(700, 550));

        //Creamos un nuevo panel que contendra nuestro boton y la JTable
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        panel.add(scrollPane, BorderLayout.CENTER);

        return panel;
    }

    public void agregarContacto(Usuario contacto) {

        // Agregar el nuevo contacto a la lista del primer usuario (ajustar según sea necesario)
        usuario.addContacto(contacto);

        //Creamos los botones para chat y eliminar
        btnChat = new JButton("Chat");
        btnChat.setFont(fuenteGenerica);
        btnChat.setBorderPainted(false);
        btnChat.setFocusPainted(false);
        btnChat.setForeground(Color.BLACK);
        btnChat.setBackground(new Color(214, 225, 50));

        btnEliminar = new JButton("Eliminar");
        btnEliminar.setFont(fuenteGenerica);
        btnEliminar.setBorderPainted(false);
        btnEliminar.setFocusPainted(false);
        btnEliminar.setForeground(Color.BLACK);
        btnEliminar.setBackground(new Color(214, 225, 50));

        // Actualizar la tabla
        Object[] rowData = {contacto.getCodeUser(),
            contacto.getNombre() + " " + contacto.getApellido(),
            "Chat",
            "Eliminar"};
        tableModel.addRow(rowData);
    }
}
