package com.mycompany.corporatetalenhub;

import com.mycompany.corporatetalenthub.modelo.Empleado;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

/**
 * @author davidcarrascal
 */
public class App {

    private static final int CANTIDAD_TRIMESTRES = 3;
    private static final double NOTA_MINIMA = 0.0;
    private static final double NOTA_MAXIMA = 100.0;
    private static final double PROMEDIO_PARA_PROMOCION = 80.0;

    public static void main(String[] args) {
        try (var scanner = new Scanner(System.in)) {

            // Punto 1 y 2: ArrayList para mantener orden y HashMap para búsqueda rápida por ID (String)
            var listaEmpleados = new ArrayList<Empleado>();
            var mapaEmpleados = new HashMap<String, Empleado>();
            var sistemaActivo = true;

            do {
                mostrarMenu();

                try {
                    System.out.print("Seleccione una opción: ");
                    var opcion = scanner.nextInt();
                    scanner.nextLine(); // Consumir salto de línea

                    switch (opcion) {
                        case 1 -> registrarEmpleado(scanner, listaEmpleados, mapaEmpleados);
                        case 2 -> mostrarReporte(listaEmpleados);
                        case 3 -> buscarEmpleadoPorId(scanner, mapaEmpleados);
                        case 4 -> eliminarEmpleadoPorId(scanner, listaEmpleados, mapaEmpleados);
                        case 5 -> depurarEmpleadosBajoDesempeno(listaEmpleados, mapaEmpleados);
                        case 6 -> mostrarExtremosYInversoJava21(listaEmpleados);
                        case 7 -> mostrarCategoriasSalariales();
                        case 0 -> {
                            sistemaActivo = false;
                            System.out.println("Sesión finalizada.");
                        }
                        default -> System.out.println("Opción fuera del menú.");
                    }
                } catch (InputMismatchException excepcion) {
                    System.out.println("Entrada inválida. Debe escribir un valor numérico.");
                    scanner.nextLine();
                }
            } while (sistemaActivo);
        }
    }

    private static void mostrarMenu() {
        System.out.println("""

                =====================================
                    CORPORATE TALENT HUB (Java 21)
                =====================================
                1. Registrar empleado
                2. Mostrar reporte de desempeño y resumen
                3. Buscar empleado por ID
                4. Eliminar empleado por ID
                5. Depurar empleados con bajo promediO
                6. Demostrar Sequenced CollectionS
                7. Consultar categorías salariales
                0. Salir
                """);
    }

    // Punto 3: Agregar a ArrayList y HashMap
    private static void registrarEmpleado(Scanner scanner, List<Empleado> lista, Map<String, Empleado> mapa) {
        System.out.print("ID: ");
        var id = scanner.nextInt();
        scanner.nextLine();

        var claveId = String.valueOf(id);

        if (id <= 0) {
            System.out.println("El ID debe ser mayor que cero.");
            return;
        }

        // Búsqueda O(1) con HashMap en lugar de recorrer el arreglo
        if (mapa.containsKey(claveId)) {
            System.out.println("Ya existe un empleado con ese ID.");
            return;
        }

        System.out.print("Nombre: ");
        var nombre = scanner.nextLine().trim();

        if (nombre.isBlank()) {
            System.out.println("El nombre no puede estar vacío.");
            return;
        }

        System.out.print("Edad: ");
        var edadIngresada = scanner.nextInt();

        if (edadIngresada < 18 || edadIngresada > 100) {
            System.out.println("La edad está fuera del rango permitido.");
            scanner.nextLine();
            return;
        }
        var edad = (byte) edadIngresada;

        System.out.print("Salario: ");
        var salario = scanner.nextDouble();

        if (salario <= 0) {
            System.out.println("El salario debe ser mayor que cero.");
            scanner.nextLine();
            return;
        }

        var sumaNotas = 0.0;
        for (var trimestre = 0; trimestre < CANTIDAD_TRIMESTRES; trimestre++) {
            System.out.printf("Calificación del trimestre %d (0 a 100): ", trimestre + 1);
            var calificacion = scanner.nextDouble();

            if (calificacion < NOTA_MINIMA || calificacion > NOTA_MAXIMA) {
                System.out.println("Calificación fuera de rango.");
                scanner.nextLine();
                return;
            }
            sumaNotas += calificacion;
        }

        scanner.nextLine();

        var empleado = new Empleado(id, nombre, edad, salario);
        empleado.setPromedioDesempeno(sumaNotas / CANTIDAD_TRIMESTRES);

        // Agregamos en ambas colecciones
        lista.add(empleado);
        mapa.put(claveId, empleado);

        System.out.println("Empleado registrado correctamente.");
    }

    // Punto 3: Búsqueda instantánea usando la clave String en HashMap
    private static void buscarEmpleadoPorId(Scanner scanner, Map<String, Empleado> mapa) {
        System.out.print("Ingrese el ID a buscar: ");
        var idBuscado = scanner.nextLine().trim();

        var emp = mapa.get(idBuscado);
        if (emp != null) {
            System.out.printf("Encontrado -> ID: %d | Nombre: %s | Salario: %.2f | Promedio: %.2f%n",
                    emp.getId(), emp.getNombre(), emp.getSalario(), emp.getPromedioDesempeno());
        } else {
            System.out.println("No se encontró ningún empleado con el ID: " + idBuscado);
        }
    }

    // Punto 3: Eliminar de ArrayList y HashMap
    private static void eliminarEmpleadoPorId(Scanner scanner, List<Empleado> lista, Map<String, Empleado> mapa) {
        System.out.print("Ingrese el ID a eliminar: ");
        var idBuscado = scanner.nextLine().trim();

        var empleadoAEliminar = mapa.remove(idBuscado); // Elimina de HashMap
        if (empleadoAEliminar != null) {
            lista.remove(empleadoAEliminar);            // Elimina de ArrayList
            System.out.println("Empleado eliminado exitosamente.");
        } else {
            System.out.println("No existe empleado con el ID proporcionado.");
        }
    }

    // Punto 6: Limpieza dinámica usando removeIf
    private static void depurarEmpleadosBajoDesempeno(List<Empleado> lista, Map<String, Empleado> mapa) {
        if (lista.isEmpty()) {
            System.out.println("No hay empleados para evaluar.");
            return;
        }

        // removeIf evalúa una condición lambda para eliminar elementos que la cumplan
        var removidos = lista.removeIf(emp -> emp.getPromedioDesempeno() < PROMEDIO_PARA_PROMOCION);

        // Mantenemos sincronizado el HashMap eliminando las entradas obsoletas
        mapa.entrySet().removeIf(entry -> entry.getValue().getPromedioDesempeno() < PROMEDIO_PARA_PROMOCION);

        if (removidos) {
            System.out.println("Se eliminaron de la lista los empleados con promedio menor a " + PROMEDIO_PARA_PROMOCION);
        } else {
            System.out.println("Todos los empleados cumplen con el promedio mínimo requerido.");
        }
    }

    // Punto 5: Novedad Java 21 - Sequenced Collections
    private static void mostrarExtremosYInversoJava21(List<Empleado> lista) {
        if (lista.isEmpty()) {
            System.out.println("La lista está vacía.");
            return;
        }

        System.out.println("\n--- SINTAXIS LEGACY (Java 8/11) ---");
        // Para obtener extremos se usaban índices manuales propensos a errores de desbordamiento (IndexOutOfBoundsException)
        var primerEmpleadoLegacy = lista.get(0);
        var ultimoEmpleadoLegacy = lista.get(lista.size() - 1);
        System.out.println("Primero (Legacy): " + primerEmpleadoLegacy.getNombre());
        System.out.println("Último (Legacy): " + ultimoEmpleadoLegacy.getNombre());

        System.out.println("\n--- SINTAXIS MODERNA (Java 21 - Sequenced Collections) ---");
        /*
         * Comentario de mejora:
         * Java 21 introduce la interfaz SequencedCollection que proporciona métodos directos
         * como getFirst(), getLast() y reversed(). Esto evita operaciones aritméticas manuales
         * como (size() - 1), haciendo el código más legible, expresivo y previniendo errores de índice.
         */
        var primerEmpleadoJava21 = lista.getFirst();
        var ultimoEmpleadoJava21 = lista.getLast();
        System.out.println("Primero (Java 21): " + primerEmpleadoJava21.getNombre());
        System.out.println("Último (Java 21): " + ultimoEmpleadoJava21.getNombre());

        System.out.println("\n--- LISTA EN ORDEN INVERSO (reversed()) ---");
        // reversed() genera una vista invertida en O(1) sin modificar la lista original ni requerir ordenamientos manuales
        for (var emp : lista.reversed()) {
            System.out.printf("ID: %d | Nombre: %s%n", emp.getId(), emp.getNombre());
        }
    }

    // Punto 6: Métodos funcionales, inferencia de tipos con var y reporte final
    private static void mostrarReporte(List<Empleado> lista) {
        if (lista.isEmpty()) {
            System.out.println("Todavía no hay empleados registrados.");
            return;
        }

        System.out.println("\nREPORTE DE DESEMPEÑO");

        // Bucle simplificado con inferencia de tipos (var) introducido en Java 10/11
        for (var emp : lista) {
            var promedio = emp.getPromedioDesempeno();
            var puntajeSimplificado = (int) promedio;
            var estadoPromocion = promedio >= PROMEDIO_PARA_PROMOCION ? "PROMOVIDO" : "NO PROMOVIDO";
            var categoria = obtenerCategoriaSalarial(emp.getSalario());

            System.out.printf(
                    "ID: %d | Nombre: %s | Promedio: %.2f | Simplificado: %d | Estado: %s | Categoría: %s%n",
                    emp.getId(), emp.getNombre(), promedio, puntajeSimplificado, estadoPromocion, categoria);
        }

        // Generar reporte final con Stream API (Cálculo de total y promedio salarial)
        var totalEmpleados = lista.size();
        var promedioSalarios = lista.stream()
                .mapToDouble(Empleado::getSalario)
                .average()
                .orElse(0.0);

        System.out.println("\n=====================================");
        System.out.println("RESUMEN DE NOMINA Y REPORTE FINAL");
        System.out.println("=====================================");
        System.out.println("Total de empleados registrados: " + totalEmpleados);
        System.out.printf("Promedio general de salarios: $%.2f%n", promedioSalarios);
    }

    public static String obtenerCategoriaSalarial(double salario) {
        var rango = determinarRangoSalarial(salario);
        return switch (rango) {
            case 1 -> "JUNIOR";
            case 2 -> "SEMISENIOR";
            case 3 -> "SENIOR";
            case 4 -> "LÍDER";
            default -> throw new IllegalArgumentException("Rango salarial no reconocido: " + rango);
        };
    }

    private static int determinarRangoSalarial(double salario) {
        if (salario < 2_000_000.0) return 1;
        if (salario < 4_000_000.0) return 2;
        if (salario < 7_000_000.0) return 3;
        return 4;
    }

    private static void mostrarCategoriasSalariales() {
        System.out.println("""
                Categorías:
                - Menos de $2.000.000: JUNIOR
                - Desde $2.000.000 y menos de $4.000.000: SEMISENIOR
                - Desde $4.000.000 y menos de $7.000.000: SENIOR
                - Desde $7.000.000: LÍDER
                """);
    }
}