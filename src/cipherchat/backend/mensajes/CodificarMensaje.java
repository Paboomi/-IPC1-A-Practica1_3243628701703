package cipherchat.backend.mensajes;

import java.text.Normalizer;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author saien
 */
public class CodificarMensaje {

    public int[] codMensaje(String mensaje){
        // Convertir el mensaje a mayusculas y eliminar tildes, excepto la letra Ñ
        String msjRecibido = normalizeExceptN(mensaje.toUpperCase());
        List<Integer> msjEncriptado = new ArrayList<>();

        for (char c : msjRecibido.toCharArray()) {
            if (c >= 'A' && c <= 'N') { // Desde 'A' hasta 'N'
                int valor = c - 'A';
                msjEncriptado.add(valor);
            } else if (c == 'Ñ') {
                msjEncriptado.add(14); // 'Ñ' tiene un valor especial
            } else if (c >= 'O' && c <= 'Z') { // Desde 'O' hasta 'Z'
                int valor = c - 'A' + 1; // Ajusta el valor para 'Ñ'
                msjEncriptado.add(valor);
            } else if (c == ' ') {
                msjEncriptado.add(27); // Espacio
            }
        }

        // Asegurar que la longitud del mensaje encriptado sea múltiplo de 3
        while (msjEncriptado.size() % 3 != 0) {
            msjEncriptado.add(27);  // Añadir espacios representados como 27
        }

        // Convertir la lista a un arreglo de enteros
        int[] arregloEncriptado = new int[msjEncriptado.size()];
        for (int i = 0; i < msjEncriptado.size(); i++) {
            arregloEncriptado[i] = msjEncriptado.get(i);
        }

        return arregloEncriptado;
    }

    private String normalizeExceptN(String input) {
        StringBuilder sb = new StringBuilder();
        for (char c : input.toCharArray()) {
            if (c == 'Ñ') {
                sb.append(c); // Añadir la Ñ sin normalizar
            } else {
                String normalized = Normalizer.normalize(String.valueOf(c), Normalizer.Form.NFD);
                sb.append(normalized.replaceAll("[\\p{InCombiningDiacriticalMarks}]", ""));
            }
        }
        return sb.toString();
    }
    
}
