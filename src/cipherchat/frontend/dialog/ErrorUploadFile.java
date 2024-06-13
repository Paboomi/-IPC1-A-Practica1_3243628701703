package cipherchat.frontend.dialog;

import java.awt.Color;
import java.awt.Font;
import java.awt.Frame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;


public class ErrorUploadFile extends CustomDialog{

    public ErrorUploadFile(Frame parent) {
        super(parent, "Error al cargar Archivo");
    }

    @Override
    public JLabel formatoMensaje() {
        JLabel lblInfo = new JLabel("<html><font size='16'>Error al cargar el archivo</font></html>");
        Font font = lblInfo.getFont();
        Font nuevaFont = new Font(font.getName(), font.getStyle(), 20);
        lblInfo.setFont(nuevaFont);
        lblInfo.setForeground(Color.BLACK);
        lblInfo.setHorizontalAlignment(SwingConstants.CENTER);
        return lblInfo;
    }
    
}
