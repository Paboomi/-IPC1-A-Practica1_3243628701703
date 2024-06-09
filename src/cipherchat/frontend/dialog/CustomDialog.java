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

/**
 *
 * @author saien
 */
public abstract class CustomDialog extends JDialog {

    private Font fuenteGenerica = new Font("Hack", Font.BOLD, 18);
    private Color color = new Color(245, 240, 255);

    public CustomDialog(Frame parent, String title) {
        super(parent, title, true);
        setSize(400, 150);
        setPreferredSize(new Dimension(400, 150));
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
    }

    public abstract JLabel formatoMensaje();

    public void mostrarDialog() {
        setVisible(true);
    }
}
