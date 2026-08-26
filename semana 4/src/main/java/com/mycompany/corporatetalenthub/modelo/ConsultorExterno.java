package com.mycompany.corporatetalenthub.modelo;

/**
 * @author davidcarrascal
 */
// Segunda rama permitida por la sealed class Persona (junto con Empleado)
public final class ConsultorExterno extends Persona {

    private final String empresaConsultora;
    private final double tarifaPorHora;

    public ConsultorExterno(String nombre, byte edad, String empresaConsultora, double tarifaPorHora) {
        super(nombre, edad);
        this.empresaConsultora = empresaConsultora;
        this.tarifaPorHora = tarifaPorHora;
    }

    public String getEmpresaConsultora() {
        return empresaConsultora;
    }

    public double getTarifaPorHora() {
        return tarifaPorHora;
    }
}
