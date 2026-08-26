package com.mycompany.corporatetalenthub.modelo;

/**
 * @author davidcarrascal
 */
public final class Gerente extends Empleado {

    private final double presupuestoMensual;

    public Gerente(int id, String nombre, byte edad, double salario, double presupuestoMensual) {
        super(id, nombre, edad, salario);
        this.presupuestoMensual = presupuestoMensual;
    }

    public double getPresupuestoMensual() {
        return presupuestoMensual;
    }

    // Task 4: bono especial para el perfil Gerente (polimorfismo)
    @Override
    public double calcularBonoAscenso() {
        return getSalario() * 0.15;
    }
}
