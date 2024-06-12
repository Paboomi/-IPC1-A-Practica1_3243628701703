package cipherchat.frontend;

import cipherchat.backend.ListaUsuarios;
import cipherchat.backend.Usuario;
import cipherchat.frontend.dialog.ErrorFindUsuario;
import cipherchat.frontend.dialog.UsuarioEncontrado;
import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

/**
 *
 * @author saien
 */
public class ModuloUsuario extends JFrame implements ActionListener {

    private CardLayout cardLayout;
    private JPanel panelContenido;
    private JButton btnContactList, btnNotifications, btnEditProfile, btnBuscar, btnAgregar;
    private JTextField contactField;
    private JPanel pnlMenu;
    private Font fuenteGenerica = new Font("Hack", Font.BOLD, 16);
    private Color color = new Color(245, 240, 255);
    private ListaUsuarios usuarios;
    private Usuario usuario;

    public ModuloUsuario(Usuario usuario, ListaUsuarios usuarios) {
        this.usuario = usuario;
        this.usuarios = usuarios;
        initComponents();
    }

    private void initComponents() {

        //Personalizamos la ventana
        this.setTitle("Principal");
        this.setSize(1000, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        this.setLayout(new BorderLayout());

        //Creamos el panel para los menus
        FlowLayout flowLayout = new FlowLayout();
        flowLayout.setHgap(20);
        pnlMenu = new JPanel();
        pnlMenu.setLayout(flowLayout);
        pnlMenu.setBackground(Color.BLACK);
        pnlMenu.setBorder(BorderFactory.createEmptyBorder(20, 0, 0, 0));

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

        //Instanciamos nuestra tabla de contactos
        TablaContactos tblContactos = new TablaContactos(usuario, usuarios);
        tblContactos.setPreferredSize(new Dimension(700, 550));

        //Creamos nuestro panel para almacenar los componentes
//        JPanel pnlIntermedio = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
        JPanel pnlIntermedio = new JPanel(new BorderLayout());
        pnlIntermedio.setPreferredSize(new Dimension(930, 550));
        pnlIntermedio.setBorder(BorderFactory.createEmptyBorder(20, 30, 0, 0));
        pnlIntermedio.setBackground(Color.BLACK);

        //Creamos los componentes para la busqueda de contactos y los personalizamos
        JPanel pnlFindContact = new JPanel();
        pnlFindContact.setLayout(new BoxLayout(pnlFindContact, BoxLayout.Y_AXIS));
        pnlFindContact.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); // Añade márgenes alrededor del panel

        JLabel lblBuscarContacto = new JLabel("Buscar Contacto");
        lblBuscarContacto.setHorizontalAlignment(SwingConstants.CENTER);
        lblBuscarContacto.setAlignmentX(Component.CENTER_ALIGNMENT); // Alinea al centro

        contactField = new JTextField();
        contactField.setPreferredSize(new Dimension(100, 25)); // Ancho y alto del JTextField
        contactField.setMaximumSize(new Dimension(Integer.MAX_VALUE, contactField.getPreferredSize().height)); // Permite que el JTextField se expanda horizontalmente

        btnAgregar = new JButton("Agregar Contacto");
        btnAgregar.setAlignmentX(Component.LEFT_ALIGNMENT); // Alinea a la izquierda
        btnAgregar.setFont(fuenteGenerica);
        btnAgregar.setBorderPainted(false);
        btnAgregar.setFocusPainted(false);
        btnAgregar.setForeground(Color.BLACK);
        btnAgregar.setBackground(new Color(214, 225, 50));

        btnBuscar = new JButton("Buscar");
        btnBuscar.setAlignmentX(Component.LEFT_ALIGNMENT); // Alinea a la izquierda
        btnBuscar.setFont(fuenteGenerica);
        btnAgregar.setBorderPainted(false);
        btnAgregar.setFocusPainted(false);
        btnBuscar.setForeground(Color.BLACK);
        btnBuscar.setBackground(new Color(214, 225, 50));

//        //Modificamos el boton buscar para saber si el usuario existe
        btnBuscar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                try {
                    String codeContact = contactField.getText();
                    if (usuarios.existeUsuario(codeContact)) {
                        System.out.println("usuario encontrado");
                        Usuario contactoBuscado = usuarios.obtenerUsuario(codeContact);
                        UsuarioEncontrado dialogEncontrado = new UsuarioEncontrado(ModuloUsuario.this, contactoBuscado);
                    } else {
                        ErrorFindUsuario userExist = new ErrorFindUsuario(ModuloUsuario.this);
                        userExist.mostrarDialog();
                    }
                } catch (Exception e) {
                    System.out.println("Ha ocurrido algo");
                }
            }
        });

        //Modificamos el boton agregar para agregar al usuario a la tabla de contactos
        btnAgregar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String codeContact = contactField.getText();
                Usuario newContact = usuarios.obtenerUsuario(codeContact);
                tblContactos.agregarContacto(newContact);
            }
        });

        //Personalizamos la estetica del panel
        pnlFindContact.add(lblBuscarContacto);
        pnlFindContact.add(Box.createRigidArea(new Dimension(0, 10))); // Añade espacio vertical

        pnlFindContact.add(contactField);
        pnlFindContact.add(Box.createRigidArea(new Dimension(0, 10))); // Añade espacio vertical

        pnlFindContact.add(btnBuscar);
        pnlFindContact.add(Box.createRigidArea(new Dimension(0, 10))); // Añade espacio vertical

        pnlFindContact.add(btnAgregar);

        pnlIntermedio.add(tblContactos, BorderLayout.WEST);
        pnlIntermedio.add(pnlFindContact, BorderLayout.EAST);

        return pnlIntermedio;
    }

    private JPanel pnlNotifications() {
        JPanel panel = new JPanel();
        panel.setPreferredSize(new Dimension(830, 550));
        panel.setBackground(Color.WHITE);
        panel.add(new JLabel("Este es el Panel 2"));

        JPanel pnlIntermedio = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
        pnlIntermedio.setPreferredSize(new Dimension(830, 550));
        pnlIntermedio.setBorder(BorderFactory.createEmptyBorder(20, 30, 0, 0));
        pnlIntermedio.setBackground(Color.BLACK);
        pnlIntermedio.add(panel);
        return pnlIntermedio;
    }

    private JPanel pnlEditProfile() {
        JPanel panel = new JPanel();
        panel.setPreferredSize(new Dimension(830, 550));
        panel.setBackground(Color.WHITE);
        panel.add(new JLabel("Este es el Panel 3"));

        JPanel pnlIntermedio = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
        pnlIntermedio.setPreferredSize(new Dimension(830, 550));
        pnlIntermedio.setBorder(BorderFactory.createEmptyBorder(20, 30, 0, 0));
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
