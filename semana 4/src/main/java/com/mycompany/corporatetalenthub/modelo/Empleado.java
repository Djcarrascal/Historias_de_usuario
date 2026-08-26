package com.mycompany.corporatetalenthub.modelo;

import java.util.List;
import java.util.Map;

/**
 * @author davidcarrascal
 */
// Rama permitida por la sealed class Persona; queda "non-sealed" porque a su vez
// es extendida abiertamente por Desarrollador y Gerente (Task 3)
public non-sealed class Empleado extends Persona implements Promocionable {

    private final int id;
    private final double salario;
    private double promedioDesempeno;

    // Colecciones inmutables
    private final List<String> tecnologias;
    private final Map<String, String> sedes;

    public Empleado(int id, String nombre, byte edad, double salario) {
        super(nombre, edad);
        this.id = id;
        this.salario = salario;

        // Uso de métodos de factoría inmutables
        this.tecnologias = List.of("Java", "Spring Boot", "SQL");
        this.sedes = Map.of(
            "Principal", "Sede Central",
            "Secundaria", "Sede Norte"
        );
    }

    public int getId() {
        return id;
    }

    public double getSalario() {
        return salario;
    }

    public double getPromedioDesempeno() {
        return promedioDesempeno;
    }

    public void setPromedioDesempeno(double promedioDesempeno) {
        this.promedioDesempeno = promedioDesempeno;
    }

    public List<String> getTecnologias() {
        return tecnologias;
    }

    public Map<String, String> getSedes() {
        return sedes;
    }

    // Task 4: implementación base del bono; las subclases la sobrescriben (polimorfismo)
    @Override
    public double calcularBonoAscenso() {
        return salario * 0.05;
    }
}
