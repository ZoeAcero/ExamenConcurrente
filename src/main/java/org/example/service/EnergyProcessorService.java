package org.example.service;

import org.example.model.EnergySource;
import org.springframework.stereotype.Service;
import java.util.concurrent.atomic.AtomicInteger;

@Service
public class EnergyProcessorService {

    public double realizarCalculoPesado(EnergySource fuente, AtomicInteger errorCounter) {
        double calculo = 0;
        try {
            if (fuente.getDivisor() == 0) throw new ArithmeticException("Cero");

            // Simulación de carga intensa para el Benchmark
            for (int i = 0; i < 50000; i++) {
                calculo += (fuente.getValorBase() * i) / fuente.getDivisor();
            }
        } catch (ArithmeticException e) {
            errorCounter.incrementAndGet();
            return 0;
        }
        return calculo;
    }
}