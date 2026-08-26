package com.mycompany.corporatetalenthub.modelo;

/**
 * @author davidcarrascal
 */
/*
 * Comentario técnico (Task 2 - Modelado inmutable con records):
 * En el estilo Legacy (Java 8/11) esta misma estructura de datos se hubiera
 * tenido que construir como una clase POJO tradicional: escribiendo a mano
 * el constructor, un getter por cada atributo, y sobrescribiendo equals(),
 * hashCode() y toString() solo para poder comparar o imprimir el reporte.
 *
 * Con los "records" de Java 17/21 todo ese código repetitivo se genera
 * automáticamente a partir de la definición de sus componentes, y los
 * atributos quedan inmutables (final) por defecto, lo cual es ideal para
 * representar un reporte que no debe cambiar una vez emitido.
 */
public record DesempeñoReport(int idEmpleado, double promedio, String feedback) {
}
