package cipherchat.backend;

/**
 *
 * @author saien
 */
public class GenerarCodigo {

    private int contUsuarios=0;
    
    public GenerarCodigo() {
    }
    
    public String generadorCodigo(){
        contUsuarios++;
        String constante = "2024";
        String codigo = String.format("%s%04d", constante, contUsuarios);
        return  codigo;
    }
}
