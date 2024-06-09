package cipherchat.backend;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author saien
 */
public class ListaUsuarios {
    private List<Usuario> usuarios;

    public ListaUsuarios() {
        this.usuarios = new ArrayList<>();
    }
    
    public void addUser(Usuario usuario){
        usuarios.add(usuario);
    }
    
    public void deleteUser(String codeUser){
        usuarios.removeIf(usuario -> usuario.getCodeUser().equals(codeUser));
    }
    
    public Usuario obtenerUsuario(String codeUser){
        try {
            for (Usuario usuario : usuarios) {
                if (usuario.getCodeUser().equals(codeUser)) {
                    return usuario;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    
    public void updateUser(String codeUser, Usuario upUsuario){
        for (int i = 0; i < usuarios.size(); i++) {
            if (usuarios.get(i).getCodeUser().equals(codeUser)) {
                usuarios.set(i, upUsuario);
                return;
            }
            
        }
    }
    
}
