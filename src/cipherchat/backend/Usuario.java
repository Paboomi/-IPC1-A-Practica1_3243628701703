package cipherchat.backend;

import java.util.ArrayList;
import java.util.List;

public class Usuario {

    private String codeUser;
    private String nombre;
    private String apellido;
    private String edad;
    private String telefono;
    private String contraseña;
    private String genero;
    private List<Usuario> contactos;

    public Usuario(String nombre, String apellido, String edad, String telefono, String genero, String contraseña, String codeUser) {
        this.codeUser = codeUser;
        this.nombre = nombre;
        this.apellido = apellido;
        this.edad = edad;
        this.telefono = telefono;
        this.contraseña = contraseña;
        this.genero = genero;
        this.contactos = new ArrayList<>();
    }

    @Override
    public String toString() {
        return """
               Usuario:
               codeUser= """ + codeUser + "\n"
                + "nombre= " + nombre + "\n"
                + "apellido= " + apellido + "\n"
                + "edad= " + edad + "\n"
                + "telefono= " + telefono + "\n"
                + "contraseña= " + contraseña + "\n"
                + "genero= " + genero + "\n";
    }
    //Metodos para administrar contactos
    public void addContacto(Usuario contacto) {
        contactos.add(contacto);
    }

    public void deleteContacto(String codeUser) {
        contactos.removeIf(contacto -> contacto.getCodeUser().equals(codeUser));
    }

    public Usuario obtenerContacto(String codeUser) {
        try {
            for (Usuario contacto : contactos) {
                if (contacto.getCodeUser().equals(codeUser)) {
                    return contacto;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public String getCodeUser() {
        return codeUser;
    }

    public void setCodeUser(String codeUser) {
        this.codeUser = codeUser;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getEdad() {
        return edad;
    }

    public void setEdad(String edad) {
        this.edad = edad;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getContraseña() {
        return contraseña;
    }

    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public List<Usuario> getContactos() {
        return contactos;
    }

}
