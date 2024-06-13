package cipherchat.backend.buttons;


import cipherchat.backend.Usuario;
import cipherchat.backend.chats.Chat;
import cipherchat.backend.chats.ChatController;
import cipherchat.frontend.ChatFrame;
import cipherchat.frontend.ModuloUsuario;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.AbstractCellEditor;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellEditor;

public class ButtonEditor extends AbstractCellEditor implements TableCellEditor, ActionListener {
    private JButton button;
    private String label;
    private boolean isPushed;
    private JTable table;
    private Usuario usuario;
    private ChatController chatController;
    private ModuloUsuario modUser;

    public ButtonEditor(JTable table, Usuario usuario, ModuloUsuario modUser) {
        this.table = table;
        this.usuario = usuario;
        this.modUser = modUser;
        this.chatController = ChatController.getInstance();
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
        Usuario usuarioDestinatario = this.usuario.obtenerContacto(codeUser);
        if (usuarioDestinatario != null) {
            modUser.setVisible(false);
            Chat chat = chatController.getChat(usuario, usuarioDestinatario);
            ChatFrame chatFrame = new ChatFrame(usuario, usuarioDestinatario, chat, modUser);
        }
    }

    private void deleteContact(int row) {
        String codeUser = (String) table.getValueAt(row, 0);
        usuario.deleteContacto(codeUser);
        ((DefaultTableModel) table.getModel()).removeRow(row);
    }
}

