package cipherchat.backend.mensajes;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

/**
 *
 * @author saien
 */
public class EncriptarMensaje {
    private int[][] matrizA;
    private int[][] matrizB;

    public EncriptarMensaje(int[][] matrizA, int[][] matrizB) {
        this.matrizA = matrizA;
        this.matrizB = matrizB;
    }

    public int[][] encriptar(int[] mensajeCodificado) {
        int[][] matrizC = new int[3][mensajeCodificado.length / 3];

        for (int i = 0; i < mensajeCodificado.length; i += 3) {
            int[] vector = {mensajeCodificado[i], mensajeCodificado[i + 1], mensajeCodificado[i + 2]};
            int[] resultado = multiplicarMatrices(matrizA, vector);
            matrizC[0][i / 3] = resultado[0];
            matrizC[1][i / 3] = resultado[1];
            matrizC[2][i / 3] = resultado[2];
        }

        return matrizC;
    }

    private int[] multiplicarMatrices(int[][] matriz, int[] vector) {
        int[] resultado = new int[3];
        for (int i = 0; i < 3; i++) {
            resultado[i] = 0;
            for (int j = 0; j < 3; j++) {
                resultado[i] += matriz[i][j] * vector[j];
            }
        }
        return resultado;
    }

    public void guardarMatriz(int[][] matriz, String ruta) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(ruta));
        for (int[] fila : matriz) {
            for (int valor : fila) {
                writer.write(valor + ",");
            }
            writer.newLine();
        }
        writer.close();
    }
}
