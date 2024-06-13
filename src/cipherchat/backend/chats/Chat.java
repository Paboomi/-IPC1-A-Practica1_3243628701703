package cipherchat.backend.chats;

import cipherchat.backend.Usuario;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author saien
 */
public class Chat {
    private Usuario usuario1;
    private Usuario usuario2;
    private List<Mensaje> mensajes;

    public Chat(Usuario usuario1, Usuario usuario2) {
        this.usuario1 = usuario1;
        this.usuario2 = usuario2;
        this.mensajes = new ArrayList<>();
    }

    public void enviarMensaje(Usuario remitente, String contenido) {
        Usuario destinatario = (remitente == usuario1) ? usuario2 : usuario1;
        Mensaje mensaje = new Mensaje(remitente, destinatario, contenido);
        mensajes.add(mensaje);
    }

    public List<Mensaje> getMensajes() {
        return mensajes;
    }
}
