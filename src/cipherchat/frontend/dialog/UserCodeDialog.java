package cipherchat.frontend.dialog;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

/**
 *
 * @author saien
 */
public class UserCodeDialog extends JDialog {

    private String codeUser;
    private Color color = new Color(245, 240, 255);
    private Font fuenteGenerica = new Font("Hack", Font.BOLD, 18);

    public UserCodeDialog(Frame parent, String codeUser) {
        super(parent, "Sobre el desarrollador", true);
        //Obtenemos el codigo del usuario
        this.codeUser = codeUser;
        setSize(400, 250);
        setPreferredSize(new Dimension(400, 250));
        setLocationRelativeTo(null);
        getContentPane().setBackground(color);
        setLayout(new BorderLayout());
        add(formatoMensaje(), BorderLayout.CENTER);
        //Creamos un panel para el boton y definimos sus propiedades
        JPanel btnPanel = new JPanel();
        JButton btnAceptar = new JButton("Aceptar");
        btnAceptar.setFont(fuenteGenerica);
        btnAceptar.setForeground(Color.BLACK);
        btnAceptar.setBackground(new Color(214, 225, 50));
        btnAceptar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                dispose();
            }

        });
        btnPanel.add(btnAceptar);
        add(btnPanel, BorderLayout.SOUTH);
        pack();
        setVisible(true);

    }

    public JLabel formatoMensaje() {
        JLabel lblInfo = new JLabel("<html><font size='16'>Tu código de usuario es: " + codeUser + "</font></html>");
        Font font = lblInfo.getFont();
        Font nuevaFont = new Font(font.getName(), font.getStyle(), 20);
        lblInfo.setFont(nuevaFont);
        lblInfo.setForeground(Color.BLACK);
        lblInfo.setHorizontalAlignment(SwingConstants.CENTER);

        return lblInfo;
    }

}
