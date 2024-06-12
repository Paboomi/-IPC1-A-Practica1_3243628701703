package cipherchat.backend.buttons;


import cipherchat.backend.Usuario;
import cipherchat.frontend.ChatFrame;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.AbstractCellEditor;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellEditor;

public class ButtonEditor extends AbstractCellEditor implements TableCellEditor, ActionListener {
    private JButton button;
    private String label;
    private boolean isPushed;
    private JTable table;
    private Usuario usuario;

    public ButtonEditor(JTable table, Usuario usuario) {
        this.table = table;
        this.usuario = usuario;
        button = new JButton();
        button.setOpaque(true);
        button.addActionListener(this);
    }

    @Override
    public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
        label = (value == null) ? "" : value.toString();
        button.setText(label);
        isPushed = true;
        return button;
    }

    @Override
    public Object getCellEditorValue() {
        isPushed = false;
        return label;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        fireEditingStopped();
        int row = table.getSelectedRow();
        if (label.equals("Chat")) {
            openChat(row);
        } else if (label.equals("Eliminar")) {
            deleteContact(row);
        }
    }

    private void openChat(int row) {
        String codeUser = (String) table.getValueAt(row, 0);
        Usuario usuario = this.usuario.obtenerContacto(codeUser);
        if (usuario != null) {
            ChatFrame chatFrame = new ChatFrame(usuario);
            chatFrame.setVisible(true);
        }
    }

    private void deleteContact(int row) {
        String codeUser = (String) table.getValueAt(row, 0);
        usuario.deleteContacto(codeUser);
        ((DefaultTableModel) table.getModel()).removeRow(row);
    }
}

