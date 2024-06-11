package cipherchat.frontend;

import cipherchat.backend.GenerarCodigo;
import cipherchat.backend.ListaUsuarios;
import cipherchat.backend.Usuario;
import cipherchat.frontend.dialog.UserCodeDialog;
import javax.swing.*;
import java.awt.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CrearUsuario extends JFrame implements ActionListener {
    
    private JTextField nameField;
    private JTextField lastNameField;
    private JPasswordField passwordField;
    private JTextField ageField;
    private JTextField phoneNumberField;
    private JCheckBox cbPassword;
    private JButton btnRegresar;
    private JComboBox<String> genderComboBox;
    private Font fuenteGenerica = new Font("Hack", Font.BOLD, 16);
    private Color color = new Color(245, 240, 255);
    private String PATH_IMAGES = "cipherchat/Images";
//    private GenerarCodigo genCode = new GenerarCodigo();
//    private ListaUsuarios usuarios = new ListaUsuarios();
    private GenerarCodigo genCode;
    private ListaUsuarios usuarios;
    private Login login;
    
    private JButton registerButton;
    
    public CrearUsuario(GenerarCodigo genCode, ListaUsuarios usuarios, Login login) {
        this.genCode = genCode;
        this.usuarios = usuarios;
        this.login = login;
        initComponents();
        
    }
    
    private void initComponents() {

        //Boton para regresar
        ImageIcon imgIcon = new ImageIcon(getClass().getClassLoader().getResource(PATH_IMAGES + "/back.png"));
        btnRegresar = new JButton(imgIcon);
        btnRegresar.setBackground(color);
        btnRegresar.setBorderPainted(false);
        btnRegresar.setContentAreaFilled(false);
        btnRegresar.setFocusPainted(false);
        btnRegresar.setOpaque(false);
        btnRegresar.setBounds(10, 10, 40, 33);
        btnRegresar.addActionListener(this);
        this.add(btnRegresar);

        //Creamos los componentes del Frame
        JLabel titleLabel = new JLabel("Registrarse");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 22));
        titleLabel.setBounds(200, 10, 160, 30);
        this.add(titleLabel);
        
        JLabel lblName = new JLabel("Nombres:");
        lblName.setBounds(50, 50, 100, 25);
        lblName.setFont(fuenteGenerica);
        this.add(lblName);
        
        nameField = new JTextField();
        nameField.setBounds(170, 50, 260, 25);
        this.add(nameField);
        
        JLabel lblApellido = new JLabel("Apellidos:");
        lblApellido.setBounds(50, 90, 100, 25);
        lblApellido.setFont(fuenteGenerica);
        this.add(lblApellido);
        
        lastNameField = new JTextField();
        lastNameField.setBounds(170, 90, 260, 25);
        this.add(lastNameField);
        
        JLabel lblPassword = new JLabel("Contraseña:");
        lblPassword.setBounds(50, 130, 120, 25);
        lblPassword.setFont(fuenteGenerica);
        this.add(lblPassword);
        
        passwordField = new JPasswordField();
        passwordField.setBounds(170, 130, 260, 25);
        this.add(passwordField);

        //Mostrar contraseña
        cbPassword = new JCheckBox("Ver Contraseña");
        cbPassword.setFont(new Font("Kristen ITC", Font.BOLD, 12));
        cbPassword.setBounds(170, 150, 150, 25);
        cbPassword.setVisible(true);
        cbPassword.addActionListener(this);
        this.add(cbPassword);
        
        JLabel genderLabel = new JLabel("Género:");
        genderLabel.setBounds(50, 190, 80, 25);
        genderLabel.setFont(fuenteGenerica);
        this.add(genderLabel);
        
        String[] genders = {"Masculino", "Femenino"};
        genderComboBox = new JComboBox<>(genders);
        genderComboBox.setBounds(170, 190, 150, 25);
        this.add(genderComboBox);
        
        JLabel lblAge = new JLabel("Edad:");
        lblAge.setBounds(50, 230, 120, 25);
        lblAge.setFont(fuenteGenerica);
        this.add(lblAge);
        
        ageField = new JTextField();
        ageField.setBounds(170, 230, 260, 25);
        this.add(ageField);
        
        JLabel lblNumberPhone = new JLabel("Teléfono:");
        lblNumberPhone.setBounds(50, 270, 120, 25);
        lblNumberPhone.setFont(fuenteGenerica);
        this.add(lblNumberPhone);
        
        phoneNumberField = new JTextField();
        phoneNumberField.setBounds(170, 270, 260, 25);
        this.add(phoneNumberField);
        
        registerButton = new JButton("Registrarse");
        registerButton.setBounds(210, 310, 150, 25);
        registerButton.setForeground(Color.BLACK);
        registerButton.setFont(fuenteGenerica);
        registerButton.setBackground(new Color(214, 225, 50));
        registerButton.addActionListener(this);
        this.add(registerButton);
        
        this.setTitle("Registro");
        this.setBounds(650, 500, 450, 400);
        getContentPane().setBackground(color);
        this.setLayout(null);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setResizable(false);
        this.setVisible(true);
        this.setLocationRelativeTo(null);
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("Registrarse")) {
            String nombre = nameField.getText();
            String apellido = lastNameField.getText();
            char[] pass = passwordField.getPassword();
            String password = new String(pass);
            String genero = (String) genderComboBox.getSelectedItem();
            String edad = ageField.getText();
            String telefono = phoneNumberField.getText();

            //Generamos el codigo para el usuario
            String userCode = genCode.generadorCodigo();
            UserCodeDialog messageCode = new UserCodeDialog(this, userCode);

            //Creamos un nuevo usuario
            Usuario user = new Usuario(nombre, apellido, edad, telefono, genero, password, userCode);

            //Agregamos el usuario a la lista
            usuarios.addUser(user);
            System.out.println(usuarios.obtenerUsuario("20240001"));
            login.setVisible(true);
            this.dispose();
        } else if (e.getSource() == cbPassword) {
            if (cbPassword.isSelected()) {
                passwordField.setEchoChar((char) 0);
            } else {
                passwordField.setEchoChar('\u25CF');
            }
        } else if (e.getSource() == btnRegresar) {
            login.setVisible(true);
            this.dispose();
        }
        System.out.println("===================================================");
    }
    
}
