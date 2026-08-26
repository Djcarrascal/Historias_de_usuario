package com.mycompany.corporatetalenthub.modelo;

/**
 * @author davidcarrascal
 */
public final class Desarrollador extends Empleado {

    private final String lenguajePrincipal;

    public Desarrollador(int id, String nombre, byte edad, double salario, String lenguajePrincipal) {
        super(id, nombre, edad, salario);
        this.lenguajePrincipal = lenguajePrincipal;
    }

    public String getLenguajePrincipal() {
        return lenguajePrincipal;
    }

    // Task 4: bono especial para el perfil Desarrollador (polimorfismo)
    @Override
    public double calcularBonoAscenso() {
        return getSalario() * 0.10;
    }
}
