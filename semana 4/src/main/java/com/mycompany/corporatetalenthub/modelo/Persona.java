package com.mycompany.corporatetalenthub.modelo;

/**
 * @author davidcarrascal
 */
/*
 * Comentario técnico (Task 1 - Herencia sellada vs abierta):
 * En el estilo Legacy (Java 8/11) esta clase se hubiera declarado simplemente
 * como "public abstract class Persona", sin ninguna restricción. Eso permite
 * que cualquier otra clase del proyecto (o incluso de otro paquete) pueda
 * extenderla libremente, sin que el autor de la clase tenga control sobre
 * quién hereda de ella.
 *
 * A partir de Java 17 se puede usar "sealed" junto con "permits" para declarar
 * de forma explícita cuáles son las únicas clases autorizadas a extender
 * Persona (en este caso Empleado y ConsultorExterno). Esto protege el dominio
 * del negocio porque el compilador impide la creación de subclases no
 * previstas, y además ofrece más seguridad en el diseño de APIs: quien
 * consume esta clase sabe con certeza cuáles son todas sus posibles formas.
 */
public abstract sealed class Persona permits Empleado, ConsultorExterno {

    private final String nombre;
    private final byte edad;

    protected Persona(String nombre, byte edad) {
        this.nombre = nombre;
        this.edad = edad;
    }

    public String getNombre() {
        return nombre;
    }

    public byte getEdad() {
        return edad;
    }
}
