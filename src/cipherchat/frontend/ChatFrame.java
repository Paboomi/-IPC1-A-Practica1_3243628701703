package cipherchat.frontend;

import cipherchat.backend.Usuario;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 *
 * @author saien
 */
public class ChatFrame extends JFrame{
    
    private Usuario usuario;

    public ChatFrame(Usuario usuario) {
        this.usuario= usuario;
        initComponents();
        
    }
    
    private void initComponents(){
        JOptionPane.showMessageDialog(this, "Hola");
        setVisible(true);
    }
    
}
