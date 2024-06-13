package cipherchat.frontend;

import cipherchat.backend.Usuario;
import cipherchat.backend.chats.Chat;
import cipherchat.backend.chats.Mensaje;
import cipherchat.backend.mensajes.CodificarMensaje;
import cipherchat.backend.mensajes.EncriptarMensaje;
import cipherchat.frontend.dialog.ErrorUploadFile;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

/**
 *
 * @author saien
 */
public class ChatFrame extends JFrame {

    private Usuario usuarioActual;
    private Usuario usuarioDestinatario;
    private Chat chat;

    private JTextArea chatArea;
    private JPanel chatPanel;
    private JTextField inputField;
    private JButton sendButton;
    private JButton backButton;
    private Font fuenteGenerica = new Font("Hack", Font.BOLD, 18);
    private ModuloUsuario modUser;
    private JLabel destinatarioLabel;
    private String PATH_DIRECTORY = "src/cipherchat/Arreglos";
    private boolean archivoACargado = false;
    private boolean archivoBCargado = false;
    private Color color = new Color(245, 240, 255);
    private int[][] matrizA;
    private int[][] matrizB;

    public ChatFrame(Usuario usuarioActual, Usuario usuarioDestinatario, Chat chat, ModuloUsuario modUser) {
        this.usuarioActual = usuarioActual;
        this.usuarioDestinatario = usuarioDestinatario;
        this.chat = chat;
        this.modUser = modUser;
        initComponents();
        setVisible(true);
    }

    private void initComponents() {

        setTitle("Chat con " + usuarioDestinatario.getNombre());
        setSize(700, 700);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        chatArea = new JTextArea();
        chatPanel = new JPanel();
        chatPanel.setLayout(new BoxLayout(chatPanel, BoxLayout.Y_AXIS));
        chatArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(chatPanel);

        inputField = new JTextField(25);

        sendButton = new JButton("Enviar");
        sendButton.setEnabled(false);

        backButton = new JButton("Regresar");
        backButton.setFont(fuenteGenerica);
        backButton.setBorderPainted(false);
        backButton.setFocusPainted(false);
        backButton.setForeground(Color.BLACK);
        backButton.setBackground(new Color(214, 225, 50));

        //Panel para el boton regresar y msotrar el destinatario
        JPanel pnlBack = new JPanel(new BorderLayout());
        destinatarioLabel = new JLabel("Chat con: " + usuarioDestinatario.getCodeUser(), SwingConstants.CENTER);
        pnlBack.add(backButton, BorderLayout.WEST);
        pnlBack.add(destinatarioLabel, BorderLayout.CENTER);

        JPanel panel = new JPanel();
        panel.add(inputField);
        panel.add(sendButton);

        add(pnlBack, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);
        add(panel, BorderLayout.SOUTH);

        sendButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String contenido = inputField.getText();
                if (!contenido.isEmpty() && archivoBCargado) {
                    try {
                        CodificarMensaje codificador = new CodificarMensaje();
                        int[] mensajeCodificado = codificador.codMensaje(contenido);

                        EncriptarMensaje encriptador = new EncriptarMensaje(matrizA, matrizB);
                        int[][] matrizC = encriptador.encriptar(mensajeCodificado);
                        encriptador.guardarMatriz(matrizC, "src/cipherchat/Arreglos/MatrizC.txt");

                        chat.enviarMensaje(usuarioActual, contenido);
                        inputField.setText("");
                        archivoBCargado = false;
                        sendButton.setEnabled(false);
                        actualizarChat();
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }

                }
            }
        });

        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                modUser.setVisible(true);
                ChatFrame.this.dispose();
            }

        });

        actualizarChat();

        //Panel para carga de archivos
        JPanel pnlFiles = new JPanel();
        pnlFiles.setLayout(new BoxLayout(pnlFiles, BoxLayout.Y_AXIS));

        //Agregamos los campos
        pnlFiles.add(createFileUploadPanel("Ingresar matriz A", true));
        pnlFiles.add(createFileUploadPanel("Ingresar matriz B", false));

        add(pnlFiles, BorderLayout.EAST);
    }
//Actualizamos el chat con los mensajes

    private void actualizarChat() {
        chatPanel.removeAll();
        List<Mensaje> mensajes = chat.getMensajes();
        chatArea.setText("");
        for (Mensaje mensaje : mensajes) {
            chatPanel.add(crearMensajePanel(mensaje));
        }
        chatPanel.revalidate();
        chatPanel.repaint();
    }

    private JPanel crearMensajePanel(Mensaje mensaje) {
        JPanel panel = new JPanel(new BorderLayout());
        JLabel contenidoLabel = new JLabel(mensaje.getContenido());

        JLabel timestampLabel = new JLabel(mensaje.getTimestamp().toString());

        JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        topPanel.add(contenidoLabel);

        //Boton reportar
        if (!mensaje.getRemitente().getCodeUser().equals(usuarioActual.getCodeUser())) {
            JButton reportarButton = new JButton("Reportar");
            reportarButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    JOptionPane.showMessageDialog(ChatFrame.this, "Mensaje reportado: " + mensaje.getContenido());
                }
            });
            topPanel.add(reportarButton);
        }

        JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        bottomPanel.add(timestampLabel);

        panel.add(topPanel, BorderLayout.CENTER);
        panel.add(bottomPanel, BorderLayout.SOUTH);

        panel.setBorder(BorderFactory.createLineBorder(Color.BLACK));

        return panel;
    }
//Metodo para cargar las matrices

    private JPanel createFileUploadPanel(String labelText, boolean isArchivoA) {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        JLabel label = new JLabel(labelText);
        label.setBorder(BorderFactory.createEmptyBorder(20, 0, 10, 0));
        JButton chooseButton = new JButton("Seleccionar archivo");
        chooseButton.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));
        JButton uploadButton = new JButton("Cargar");
        uploadButton.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));

        chooseButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    File file = new File(PATH_DIRECTORY);
                    JFileChooser fileChooser = new JFileChooser(file);
                    int returnValue = fileChooser.showOpenDialog(null);
                    if (returnValue == JFileChooser.APPROVE_OPTION) {
                        String filePath = fileChooser.getSelectedFile().getPath();
                        if (isArchivoA) {
                            matrizA = leerMatriz(filePath, 3, 3);
                        } else {
                            matrizB = leerMatriz(filePath, 3, 8);
                        }

                    }
                } catch (Exception ae) {
                    ErrorUploadFile errorFile = new ErrorUploadFile(ChatFrame.this);
                    ae.printStackTrace();

                }

            }
        });

        uploadButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (isArchivoA) {
                    archivoACargado = true;
                } else {
                    archivoBCargado = true;
                }
                checkEnviarButtonState();
            }
        });

        panel.add(label);
        panel.add(chooseButton);
        panel.add(uploadButton);
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));  // Añade un margen alrededor del panel

        return panel;
    }

    private void checkEnviarButtonState() {
        // Habilitar el botón de enviar solo si ambos archivos han sido cargados
        if (archivoACargado && archivoBCargado) {
            sendButton.setEnabled(true);
        } else {
            sendButton.setEnabled(false);
        }
    }
    
    private int[][] leerMatriz(String ruta, int filas, int columnas) throws IOException {
        int[][] matriz = new int[filas][columnas];
        List<String> lineas = Files.readAllLines(Paths.get(ruta));
        for (int i = 0; i < filas; i++) {
            String[] valores = lineas.get(i).split(",");
            for (int j = 0; j < columnas; j++) {
                matriz[i][j] = Integer.parseInt(valores[j].trim());
            }
        }
        return matriz;
    }

}
