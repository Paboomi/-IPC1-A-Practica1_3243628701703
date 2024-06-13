package cipherchat.backend.chats;

import cipherchat.backend.Usuario;
import java.time.LocalDateTime;

/**
 *
 * @author saien
 */
public class Mensaje {
     private Usuario remitente;
    private Usuario destinatario;
    private String contenido;
    private LocalDateTime timestamp;

    public Mensaje(Usuario remitente, Usuario destinatario, String contenido) {
        this.remitente = remitente;
        this.destinatario = destinatario;
        this.contenido = contenido;
        this.timestamp = LocalDateTime.now();
    }

    public Usuario getRemitente() {
        return remitente;
    }

    public Usuario getDestinatario() {
        return destinatario;
    }

    public String getContenido() {
        return contenido;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    @Override
    public String toString() {
        return "[" + timestamp + "] " + remitente.getNombre() + ": " + contenido;
    }
}
