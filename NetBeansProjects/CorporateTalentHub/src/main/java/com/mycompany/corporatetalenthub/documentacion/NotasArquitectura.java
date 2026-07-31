/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.corporatetalenthub.documentacion;

/**
 *
 * @author davidcarrascal
 */
public class NotasArquitectura {
        
    /* 
    La diferencia principal entre Java 8 y Java 17 LTS radica en su filosofía de diseño: 
    mientras Java 8 se enfocó en revolucionar el lenguaje mediante el paradigma funcional, 
    Java 17 LTS se centra en la productividad del desarrollador, la seguridad por defecto y 
    la optimización para entornos de nube.
    
    ===============/=================/=================/===================/=================/=
    
    1. División de la Memoria (El Heap)
    La JVM organiza la memoria donde viven los objetos en dos áreas principales según su esperanza de vida:
    Young Generation (Generación Joven): Aquí nacen los objetos (new). Se divide en Eden y Survivors. Se limpia muy rápido mediante Minor GC.
    Old Generation (Generación Vieja): Si un objeto sobrevive a varias limpiezas en la Young Generation, se "promociona" aquí (beans, caches, conexiones). Se limpia con Major/Full GC (más lenta).
    
    2. Detección de Basura (Reachability)
    La JVM busca qué objetos son alcanzables desde las fuentes principales (métodos en ejecución, variables estáticas, hilos activos = GC Roots).
    Regla: Si no hay ninguna ruta que conecte una GC Root con un objeto, se considera basura y se elimina.
    
    3. ¿Cómo Limpia el Garbage Collector?
    Mark (Marca): Identifica qué objetos están vivos.
    Sweep (Barre): Borra los objetos no marcados.
    Compact (Compacta): Junta los objetos sobrevivientes para evitar la memoria fragmentada.
    
    4. Optimizaciones Clave
    Escape Analysis (JIT): Si el compilador detecta que un objeto no sale de un método, lo crea en la pila (Stack) en lugar del Heap, evitando carga al GC.
    GCs Modernos (G1, ZGC): Limpian la memoria en paralelo o por regiones para evitar pausar la aplicación.
    
   
    */

}
