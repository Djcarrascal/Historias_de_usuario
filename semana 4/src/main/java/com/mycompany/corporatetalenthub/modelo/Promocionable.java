package com.mycompany.corporatetalenthub.modelo;

/**
 * @author davidcarrascal
 */
public interface Promocionable {

    // Método abstracto: cada perfil que implemente esta interfaz decide cómo calcular su propio bono
    double calcularBonoAscenso();

    /*
     * Comentario técnico (Task 4 - Evolución de interfaces):
     * Antes de Java 8, si se quería agregar un nuevo comportamiento a una interfaz
     * que ya tenía clases implementándola, era obligatorio modificar TODAS esas clases
     * o el código dejaba de compilar. Desde Java 8, los métodos "default" permiten
     * añadir funcionalidad nueva directamente en la interfaz, con una implementación
     * por defecto, sin romper el código de las clases que ya la usaban.
     */
    default void registrarLogPromocion(String nombre, double bono) {
        System.out.printf("[LOG] Bono de ascenso calculado -> Empleado: %s | Bono: $%.2f%n", nombre, bono);
    }
}
