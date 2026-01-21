package org.example;

import org.example.controller.EnergyMonitorController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class EnergyGridApplication implements CommandLineRunner {

    @Autowired
    private EnergyMonitorController monitor;

    public static void main(String[] args) {
        // Inicia la aplicación Spring Boot
        SpringApplication.run(EnergyGridApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        // Parámetros del Benchmark solicitados
        int numParticulas = 500000;
        int[] hilosAProbar = {1, 2, 4, 8};

        System.out.println("\n" + "=".repeat(70));
        System.out.println("   BENCHMARK AUTOMÁTICO: CENTRAL DE ENERGÍA INTELIGENTE (SMART GRID)");
        System.out.println("   Análisis de Escalabilidad - Examen 21-Enero-2026");
        System.out.println("=".repeat(70));

        // Ejecución secuencial y concurrente para comparar rendimiento
        for (int hilos : hilosAProbar) {
            System.out.println("\n>>> TEST DE RENDIMIENTO CON " + hilos + " HILO(S) EN EL POOL:");

            // Se ejecuta la lógica de cálculo gravitatorio y benchmark
            String informe = monitor.ejecutar(numParticulas, hilos);

            // Salida limpia por la terminal de IntelliJ
            System.out.println(informe);
            System.out.println("-".repeat(70));
        }

        System.out.println("\n[SISTEMA FINALIZADO]");
        System.out.println("Estudio de benchmark completado con éxito.");
        System.out.println("=".repeat(70));
    }
}