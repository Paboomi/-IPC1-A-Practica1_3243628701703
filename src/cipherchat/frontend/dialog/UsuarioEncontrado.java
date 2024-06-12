package cipherchat.frontend.dialog;

import cipherchat.backend.Usuario;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

/**
 *
 * @author saien
 */
public class UsuarioEncontrado extends JDialog{
    private Usuario usuario;
    private Color color = new Color(245, 240, 255);
    private Font fuenteGenerica = new Font("Hack", Font.BOLD, 18);

    public UsuarioEncontrado(Frame parent, Usuario usuario) {
        super(parent, "Usuario encontrado", true);
        //Obtenemos el usuario
        this.usuario = usuario;
        setSize(600, 250);
        setPreferredSize(new Dimension(600, 250));
        setLocationRelativeTo(null);
        getContentPane().setBackground(color);
        setLayout(new BorderLayout());
        add(formatoMensaje(), BorderLayout.CENTER);
        //Creamos un panel para el boton y definimos sus propiedades
        JPanel btnPanel = new JPanel();
        
        JButton btnAceptar = new JButton("Aceptar");
        btnAceptar.setFont(fuenteGenerica);
        btnAceptar.setBorderPainted(false);
        btnAceptar.setFocusPainted(false);
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
        JLabel lblInfo = new JLabel("<html><font size='16'>Nombre: " + usuario.getNombre() + " " + usuario.getApellido()+"</font></html>");
        Font font = lblInfo.getFont();
        Font nuevaFont = new Font(font.getName(), font.getStyle(), 16);
        lblInfo.setFont(nuevaFont);
        lblInfo.setForeground(Color.BLACK);
        lblInfo.setHorizontalAlignment(SwingConstants.CENTER);

        return lblInfo;
    }


    
}
