package cipherchat.frontend;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author saien
 */
public class ModuloUsuario extends JFrame implements ActionListener {

    private CardLayout cardLayout;
    private JPanel panelContenido;
    private JButton btnContactList, btnNotifications, btnEditProfile;
    private JPanel pnlMenu;
    private Font fuenteGenerica = new Font("Hack", Font.BOLD, 16);
    private Color color = new Color(245, 240, 255);

    public ModuloUsuario() {
        initComponents();
    }

    private void initComponents() {

        //Personalizamos la ventana
        this.setTitle("Principal");
        this.setSize(900, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        this.setLayout(new BorderLayout());

        //Creamos el panel para los menus
        FlowLayout flowLayout = new FlowLayout();
        flowLayout.setHgap(20);
        pnlMenu = new JPanel();
        pnlMenu.setLayout(flowLayout);
        pnlMenu.setBackground(Color.BLACK);
        pnlMenu.setBorder(BorderFactory.createEmptyBorder(20,0,0,0));

        //Creamos los botones del menu y sus propiedades
        Cursor handCursor = new Cursor(Cursor.HAND_CURSOR);
       
        btnContactList = new JButton("Lista Contactos");
        btnContactList.setBackground(new Color(214, 225, 50));
        btnContactList.setFont(fuenteGenerica);
        btnContactList.setBorderPainted(false);
        btnContactList.setFocusPainted(false);
        btnContactList.setCursor(handCursor);
        
        btnNotifications = new JButton("Notificaciones");
        btnNotifications.setBackground(new Color(214, 225, 50));
        btnNotifications.setFont(fuenteGenerica);
        btnNotifications.setBorderPainted(false);
        btnNotifications.setFocusPainted(false);
        btnNotifications.setCursor(handCursor);
        
        btnEditProfile = new JButton("Editar Perfil");
        btnEditProfile.setBackground(new Color(214, 225, 50));
        btnEditProfile.setFont(fuenteGenerica);
        btnEditProfile.setBorderPainted(false);
        btnEditProfile.setFocusPainted(false);
        btnEditProfile.setCursor(handCursor);
        
        //Agregamos las escuchas a los botones
        btnContactList.addActionListener(this);
        btnNotifications.addActionListener(this);
        btnEditProfile.addActionListener(this);

        //Agreagamos los botones al panel del menu
        pnlMenu.add(btnContactList);
        pnlMenu.add(btnNotifications);
        pnlMenu.add(btnEditProfile);

        //Agregamos el panel del Menu al Frame
        add(pnlMenu, BorderLayout.NORTH);

        //Creamos nuestras tarjetas para intercambiar de paneles
        cardLayout = new CardLayout();
        panelContenido = new JPanel(cardLayout);
        panelContenido.setBackground(Color.BLACK);

        //Agreagamos los paneles a nuestro panel de contenido
        panelContenido.add(pnlContactList(), "Lista Contactos");
        panelContenido.add(pnlNotifications(), "Notificaciones");
        panelContenido.add(pnlEditProfile(), "Editar Perfil");

        //Agregamos el panel de contenido al Frame
        add(panelContenido, BorderLayout.CENTER);

        this.setVisible(true);
    }

    private JPanel pnlContactList() {
        JPanel panel = new JPanel();
        panel.setPreferredSize(new Dimension(830,550));
        panel.setBackground(Color.WHITE);
        panel.add(new JLabel("Este es el Panel 1"));
        
        JPanel pnlIntermedio = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
        pnlIntermedio.setPreferredSize(new Dimension(830,550));
        pnlIntermedio.setBorder(BorderFactory.createEmptyBorder(20,30,0,0));
        pnlIntermedio.setBackground(Color.BLACK);
        pnlIntermedio.add(panel);
        return pnlIntermedio;
    }

    private JPanel pnlNotifications() {
        JPanel panel = new JPanel();
        panel.setPreferredSize(new Dimension(830,550));
        panel.setBackground(Color.WHITE);
        panel.add(new JLabel("Este es el Panel 2"));
        
        JPanel pnlIntermedio = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
        pnlIntermedio.setPreferredSize(new Dimension(830,550));
        pnlIntermedio.setBorder(BorderFactory.createEmptyBorder(20,30,0,0));
        pnlIntermedio.setBackground(Color.BLACK);
        pnlIntermedio.add(panel);
        return pnlIntermedio;
    }

    private JPanel pnlEditProfile() {
       JPanel panel = new JPanel();
        panel.setPreferredSize(new Dimension(830,550));
        panel.setBackground(Color.WHITE);
        panel.add(new JLabel("Este es el Panel 3"));
        
        JPanel pnlIntermedio = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
        pnlIntermedio.setPreferredSize(new Dimension(830,550));
        pnlIntermedio.setBorder(BorderFactory.createEmptyBorder(20,30,0,0));
        pnlIntermedio.setBackground(Color.BLACK);
        pnlIntermedio.add(panel);
        return pnlIntermedio;
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        //Acciones cuando se presionan los botones del menu
        if (ae.getSource() == btnContactList) {
            cardLayout.show(panelContenido, "Lista Contactos");
        } else if (ae.getSource() == btnNotifications) {
            cardLayout.show(panelContenido, "Notificaciones");
        } else if (ae.getSource() == btnEditProfile) {
            cardLayout.show(panelContenido, "Editar Perfil");
        }
    }

}
