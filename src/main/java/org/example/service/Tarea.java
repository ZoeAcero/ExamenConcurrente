package org.example.service;

import org.springframework.stereotype.Component;

@Component
public class Tarea {

    /**
     * Calcula la energía potencial gravitatoria total de un conjunto de partículas.
     * El cálculo incluye control de división por cero cuando la distancia es 0.
     */
    public double calcularEnergiaPotencial(int numParticulas) {

        final double G = 6.67430e-11; // Constante de gravitación universal
        double[] masas = new double[numParticulas];
        int[] distancias = new int[numParticulas];
        double energiaTotal = 0.0;

        for (int i = 0; i < numParticulas; i++) {
            masas[i] = 1 + Math.random() * 9;          // Masa entre 1 y 10 kg
            distancias[i] = (int) (Math.random() * 10); // Distancia entre 0 y 10 m

            try {
                if (distancias[i] == 0) {
                    throw new ArithmeticException(
                            "División por cero: distancia nula entre partículas"
                    );
                }
                // Cálculo de energía: (G * m1 * m2) / r
                energiaTotal += (G * masas[i] * masas[i]) / distancias[i];

            } catch (ArithmeticException e) {
                // Se ignora la partícula con distancia nula según el enunciado
            }
        }
        return energiaTotal;
    }
}