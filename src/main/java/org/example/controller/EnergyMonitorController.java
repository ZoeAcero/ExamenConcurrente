package org.example.controller;

import org.example.service.Tarea;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.concurrent.*;
import java.util.stream.IntStream;

@Component
public class EnergyMonitorController {

    @Autowired
    private Tarea tarea;

    @Autowired
    private ExecutorService energyExecutor; // Inyectado desde ThreadConfig

    // Método limpio para ejecutar desde la terminal
    public String ejecutar(int particulas, int divisiones) {

        // --- MODO SECUENCIAL ---
        long startS = System.currentTimeMillis();
        double resultadoS = tarea.calcularEnergiaPotencial(particulas);
        long timeS = System.currentTimeMillis() - startS;

        // --- MODO CONCURRENTE (Paralelismo por división de carga) ---
        int cargaPorHilo = particulas / divisiones;
        long startC = System.currentTimeMillis();

        var futures = IntStream.range(0, divisiones)
                .mapToObj(i -> CompletableFuture.supplyAsync(
                        () -> tarea.calcularEnergiaPotencial(cargaPorHilo), energyExecutor))
                .toList();

        double resultadoC = futures.stream().mapToDouble(CompletableFuture::join).sum();
        long timeC = System.currentTimeMillis() - startC;

        double speedup = (double) timeS / timeC;

        return String.format(
                "--- BENCHMARK GRAVITATORIO ---\n" +
                        "Partículas Totales: %d | Hilos: %d\n" +
                        "Tiempo Secuencial: %d ms | Resultado: %.5e\n" +
                        "Tiempo Paralelo: %d ms | Resultado: %.5e\n" +
                        "Speedup: %.2f | Eficiencia: %.2f",
                particulas, divisiones, timeS, resultadoS, timeC, resultadoC, speedup, (speedup/divisiones)
        );
    }
}