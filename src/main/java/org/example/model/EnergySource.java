package org.example.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EnergySource {
    private String id;
    private double valorBase;
    private double divisor; // Para simular la excepción de división por cero
    private double resultado;
}