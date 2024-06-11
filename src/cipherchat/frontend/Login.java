package cipherchat.frontend;

//Libraries
import cipherchat.backend.GenerarCodigo;
import cipherchat.backend.ListaUsuarios;
import cipherchat.backend.Usuario;
import cipherchat.frontend.dialog.ErrorFindUsuario;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

public class Login extends JFrame implements ActionListener, FocusListener {

    private JTextField codeField;
    private JPasswordField passwordField;
    private JButton btnLogin;
    private JButton btnRegistrarse;
    private JCheckBox cbPassword;
    private String PATH_IMAGES = "cipherchat/Images";
    private Font fuenteGenerica = new Font("Hack", Font.BOLD, 16);
    private Color color = new Color(245, 240, 255);
    private GenerarCodigo genCode = new GenerarCodigo();
    private ListaUsuarios usuarios = new ListaUsuarios();

    public Login() {
        initComponents();
    }

    private void initComponents() {
        JLabel titleLabel = new JLabel("Bienvenido");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 20)); // Establece el tipo de letra y el tama�o
        titleLabel.setBounds(200, 10, 100, 30); //pos x, pos y, ancho, alto
        this.add(titleLabel);

        // Carga la imagen
//        ImageIcon imageIcon = new ImageIcon(getClass().getClassLoader().getResource("/Images/login.png"));
        ImageIcon imageIcon = new ImageIcon(getClass().getClassLoader().getResource(PATH_IMAGES + "/login2.png"));
        // Ajusta el tama�o de la imagen (puedes cambiar los valores seg�n tus necesidades)
        Image imageDimension = imageIcon.getImage().getScaledInstance(120, 110, Image.SCALE_SMOOTH);
        // Crea un nuevo ImageIcon con la imagen ajustada
        ImageIcon adjustedImageIcon = new ImageIcon(imageDimension);
        // Crea un JLabel para mostrar la imagen
        JLabel imageLabel = new JLabel(adjustedImageIcon);
        imageLabel.setBounds(190, 30, 120, 110); // (x, y, width, height) aqui el ancho y la altura deben ser las mismas que cuando redimensionamos
        this.add(imageLabel);

        // Etiqueta de texto para el nombre de usuario
        JLabel lblCodigo = new JLabel("Codigo:");
        lblCodigo.setFont(fuenteGenerica); // Establece el tipo de letra y el tama�o
        lblCodigo.setBounds(50, 140, 80, 25);
        this.add(lblCodigo);

        // Campo de texto para el nombre de usuario
        codeField = new JTextField("Code");
        codeField.setBounds(160, 140, 260, 25);
        codeField.addFocusListener(this);
        this.add(codeField);

        // Etiqueta para la contrase�a
        JLabel passwordLabel = new JLabel("Contraseña:");
        passwordLabel.setFont(fuenteGenerica); // Establece el tipo de letra y el tama�o
        passwordLabel.setBounds(50, 180, 160, 25);
        this.add(passwordLabel);

        //Campo de texto para la contrase�a
        passwordField = new JPasswordField("Password");
        //passwordField.setEchoChar((char) 0);
        passwordField.setBounds(160, 180, 260, 25);
        passwordField.addFocusListener(this);
        this.add(passwordField);

        //Boton Ver contraseña
        cbPassword = new JCheckBox("Ver contraseña");
        cbPassword.setFont(new Font("Hack", Font.BOLD, 12));
        cbPassword.setBounds(160, 210, 150, 25);
        cbPassword.setVisible(true);
        cbPassword.addActionListener(this);
        this.add(cbPassword);

        // Boton de inicio de sesion
        btnLogin = new JButton("Iniciar Sesi�n");
        btnLogin.setBounds(275, 260, 150, 25); //Ajuste de posici�n
        btnLogin.setForeground(Color.BLACK); // Ajuste de color de letra
        btnLogin.setBackground(new Color(214, 225, 50)); // Ajuste de color de boton, con color en RGB
        btnLogin.addActionListener(this); // Agregamos un ActionListener al bot�n
        this.add(btnLogin);

        // Boton de Registro
        btnRegistrarse = new JButton("Registrarse");
        btnRegistrarse.setBounds(75, 260, 150, 25); //Ajuste de posici�n
        btnRegistrarse.setBackground(new Color(214, 225, 50)); // Ajuste de color de boton, con color en RGB
        btnRegistrarse.addActionListener(this); // Agregamos un ActionListener al bot�n
        this.add(btnRegistrarse);

        //Propiedades del JFrame
        this.setTitle("Login");
        this.setLocationRelativeTo(null);
        this.setSize(500, 350);
        getContentPane().setBackground(color);
        setLocationRelativeTo(null);
        /*
        x: La posici�n horizontal de la esquina superior izquierda de la ventana en p�xeles desde el borde izquierdo de la pantalla.
        y: La posici�n vertical de la esquina superior izquierda de la ventana en p�xeles desde la parte superior de la pantalla.
        width: El ancho de la ventana en p�xeles.
        height: La altura de la ventana en p�xeles.
         */
        this.setLayout(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        // Accion para ver la contraseña
        if (ae.getSource() == cbPassword) {
            if (cbPassword.isSelected()) {
                passwordField.setEchoChar((char) 0);
            } else {
                passwordField.setEchoChar('\u25CF');
            }
            //Accion para ingresar
        } else if (ae.getSource() == btnLogin) {
            String codeUser = codeField.getText();
            char[] password = passwordField.getPassword();
            String pwd = new String(password);

            //Buscamos al usuario en la lista
            try {
                if (usuarios.verificarCredenciales(codeUser, pwd)) {
                    ModuloUsuario modUser = new ModuloUsuario(usuarios);
                    System.out.println("Bienvenido al sistema");
                    this.dispose();
                }

            } catch (Exception e) {
                e.printStackTrace();
                ErrorFindUsuario errorUser = new ErrorFindUsuario(this);
                errorUser.mostrarDialog();
            }

            //Accion para registrarse
        } else if (ae.getSource() == btnRegistrarse) {
            this.setVisible(false);
            CrearUsuario user = new CrearUsuario(genCode, usuarios, this);
            System.out.println("Abrir la ventana para el registro");
        }
        System.out.println("================================================");
    }

    //Accion cuando el cursor se encuentra en el foco de los textfield
    @Override
    public void focusGained(FocusEvent fe) {
        if (fe.getSource() == codeField) {
            codeField.setText("");
        } else if (fe.getSource() == passwordField) {
            if (cbPassword.isSelected()) {
                passwordField.setEchoChar((char) 0);
            } else {
                passwordField.setEchoChar('\u25CF');
            }
            passwordField.setText("");
        }
    }

    //Sin acciones cuando se pierde el foco
    @Override
    public void focusLost(FocusEvent e) {

    }

    public void setUsuarios(ListaUsuarios usuarios) {
        this.usuarios = usuarios;
    }

    public void setGenCode(GenerarCodigo genCode) {
        this.genCode = genCode;
    }

}
