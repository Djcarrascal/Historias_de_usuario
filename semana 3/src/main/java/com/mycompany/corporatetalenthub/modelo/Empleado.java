package com.mycompany.corporatetalenthub.modelo;

import java.util.List;
import java.util.Map;

/**
 * @author davidcarrascal
 */
public class Empleado {

    private final int id;
    private final String nombre;
    private final byte edad;
    private final double salario;
    private double promedioDesempeno;

    // Colecciones inmutables
    private final List<String> tecnologias;
    private final Map<String, String> sedes;

    public Empleado(int id, String nombre, byte edad, double salario) {
        this.id = id;
        this.nombre = nombre;
        this.edad = edad;
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

    public String getNombre() {
        return nombre;
    }

    public byte getEdad() {
        return edad;
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
}