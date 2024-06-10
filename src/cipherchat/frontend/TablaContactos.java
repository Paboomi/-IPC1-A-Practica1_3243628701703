package cipherchat.frontend;

import cipherchat.backend.ListaUsuarios;
import cipherchat.backend.Usuario;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author saien
 */
public class TablaContactos extends JPanel{
    private ListaUsuarios usuarios;
    private JTable table;
    public TablaContactos() {
        usuarios = new ListaUsuarios();
        setSize(new Dimension(830, 550));
        setLayout(new BorderLayout());
    }
    
    private JPanel mostrarContactos(){
        //Fuente para las columnas
        Font font = new Font("Arial", Font.BOLD, 24);
        
        //Creamos nuestro modelo de tabla
        DefaultTableModel tableModel = new DefaultTableModel();
        //Agregamos las columnas
        tableModel.addColumn("Codigo");
        tableModel.addColumn("Nombre");
        tableModel.addColumn("Chat");
        tableModel.addColumn("Eliminar");
        
        for (Usuario usuario : usuarios.getUsuarios()) {
            Object[] rowData = {usuario.getCodeUser(),
                usuario.getNombre() + " " + usuario.getApellido(), "Chat", " Eliminar"
            };
            tableModel.addRow(rowData);
        }
        
        table = new JTable(tableModel);
        table.getTableHeader().setReorderingAllowed(false);
        table.setRowHeight(20);
        table.setFont(new Font("Verdana", Font.ROMAN_BASELINE, 20));
        
       
        //Creamos un ScrollPane que contendra nuestra JTable
        JScrollPane scrollPane = new JScrollPane(table);
        
        //Creamos un nuevo panel que contendra nuestro boton y la JTable
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        panel.add(scrollPane, BorderLayout.CENTER);
        
        return panel;
    }
    
}
