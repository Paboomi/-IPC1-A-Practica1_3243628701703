package cipherchat.frontend.dialog;

import java.awt.Color;
import java.awt.Font;
import java.awt.Frame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

/**
 *
 * @author saien
 */
public class ErrorFindUsuario extends CustomDialog{

    public ErrorFindUsuario(Frame parent) {
        super(parent, "Error de Inicio de Sesion");
    }

    @Override
    public JLabel formatoMensaje() {
        JLabel lblInfo = new JLabel("<html><font size='16'>El usuario no existe</font></html>");
        Font font = lblInfo.getFont();
        Font nuevaFont = new Font(font.getName(), font.getStyle(), 20);
        lblInfo.setFont(nuevaFont);
        lblInfo.setForeground(Color.BLACK);
        lblInfo.setHorizontalAlignment(SwingConstants.CENTER);
        return lblInfo;
    }
    
}
