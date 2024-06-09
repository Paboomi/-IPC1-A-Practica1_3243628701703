package cipherchat.backend;

public class Usuario {

    private String codeUser;
    private String nombre;
    private String apellido;
    private String edad;
    private String telefono;
    private String contraseña;
    private String genero;

    public Usuario(String nombre, String apellido, String edad, String telefono, String genero, String contraseña, String codeUser) {
        this.codeUser = codeUser;
        this.nombre = nombre;
        this.apellido = apellido;
        this.edad = edad;
        this.telefono = telefono;
        this.contraseña = contraseña;
        this.genero = genero;
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

}
