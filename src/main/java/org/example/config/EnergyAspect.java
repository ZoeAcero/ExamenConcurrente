package org.example.config;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class EnergyAspect {

    /**
     * ASPECTO 1
     * Join point: Ejecución del método calcularEnergiaPotencial en la clase Tarea.
     * Pointcut: execution(* org.example.service.Tarea.calcularEnergiaPotencial(..))
     * Tipo de advice: @Around
     * Lógica general: Mide el tiempo exacto que tarda el hilo en procesar su lote de partículas.
     */
    @Around("execution(* org.example.service.Tarea.calcularEnergiaPotencial(..))")
    public Object medirTiempoHilo(ProceedingJoinPoint joinPoint) throws Throwable {
        long inicio = System.nanoTime();
        Object resultado = joinPoint.proceed();
        long fin = System.nanoTime();
        // System.out.println("Hilo " + Thread.currentThread().getName() + " tardó: " + (fin - inicio) / 1e6 + " ms");
        return resultado;
    }

    /**
     * ASPECTO 2
     * Join point: Cualquier método del servicio Tarea.
     * Pointcut: within(org.example.service.Tarea)
     * Tipo de advice: @Before
     * Lógica general: Loguea el inicio de un cálculo pesado para trazabilidad.
     */
    @Before("within(org.example.service.Tarea)")
    public void logAntesDeTarea() {
        // Lógica de trazabilidad inicial
    }

    /**
     * ASPECTO 3
     * Join point: Al finalizar con éxito el cálculo en la clase Tarea.
     * Pointcut: execution(double org.example.service.Tarea.calcularEnergiaPotencial(int))
     * Tipo de advice: @AfterReturning
     * Lógica general: Notifica que un hilo ha terminado de calcular su energía gravitatoria.
     */
    @AfterReturning(pointcut = "execution(* org.example.service.Tarea.calcularEnergiaPotencial(..))", returning = "result")
    public void logDespuesDeTarea(Object result) {
        // Lógica de confirmación de cálculo finalizado
    }
}