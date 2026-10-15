import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner teclado = new Scanner(System.in);
        List<Paquete> inventario = new ArrayList<>();
        List<Vehiculo> flotilla = new ArrayList<>();

        int opcion = -1;

        while (opcion != 6) {
            try {
                System.out.println("\n=====> CEDIS: CENTRO DE DISTRIBUCION Y TRANSPORTE <=====");
                System.out.println("1. Ingresar nuevo paquete");
                System.out.println("2. Registrar vehículo");
                System.out.println("3. Mostrar inventario y calcular tarifas");
                System.out.println("4. Auditoría de Seguros");
                System.out.println("5. Transportar Paquetes (Despacho)");
                System.out.println("6. Salir");
                System.out.print("Elige una opción: ");

                opcion = teclado.nextInt();
                teclado.nextLine(); // Limpiar el buffer

                switch (opcion) {
                    case 1:
                        ingresarNuevoPaquete(teclado, inventario);
                        break;
                    case 2:
                        registrarVehiculo(teclado, flotilla);
                        break;
                    case 3:
                        mostrarInventarioYTarifas(inventario);
                        break;
                    case 4:
                        auditoriaSeguros(inventario, flotilla);
                        break;
                    case 5:
                        transportarPaquetes(teclado, inventario, flotilla);
                        break;
                    case 6:
                        System.out.println("\nSaliendo del sistema...");
                        break;
                    default:
                        System.out.println("\nOpción no válida. Intenta de nuevo.");
                }
            } catch (Exception e) {
                System.out.println("\n[ERROR] Entrada inválida. Por favor ingresa un número válido.");
                teclado.nextLine(); // Limpiar la basura del teclado para que no se cicle
                opcion = -1;
            }
        }
        teclado.close();
    }

    // 1. Ingresar nuevo paquete
    private static void ingresarNuevoPaquete(Scanner teclado, List<Paquete> inventario) {
        System.out.println("\n--- INGRESAR NUEVO PAQUETE ---");
        System.out.println("1. Paquete Estándar");
        System.out.println("2. Paquete de Alto Valor");
        System.out.print("Selecciona el tipo de paquete: ");
        int tipo = teclado.nextInt();
        teclado.nextLine();

        System.out.print("Ingresa el ID de rastreo: ");
        String idRastreo = teclado.nextLine();

        System.out.print("Ingresa el peso en Kg: ");
        double pesoKg = teclado.nextDouble();
        teclado.nextLine();

        if (tipo == 1) {
            System.out.print("Ingresa la zona de entrega (Local, Nacional, Internacional): ");
            String zonaEntrega = teclado.nextLine();

            PaqueteEstandar paquete = new PaqueteEstandar(idRastreo, pesoKg, zonaEntrega);
            inventario.add(paquete);
            System.out.println("¡Paquete Estándar guardado exitosamente en el inventario!");
        } else if (tipo == 2) {
            System.out.print("Ingresa el valor declarado ($): ");
            double valorDeclarado = teclado.nextDouble();
            teclado.nextLine();

            PaqueteAltoValor paquete = new PaqueteAltoValor(idRastreo, pesoKg, valorDeclarado);
            inventario.add(paquete);
            System.out.println("¡Paquete de Alto Valor guardado exitosamente en el inventario!");
        } else {
            System.out.println("Tipo de paquete no válido. Operación cancelada.");
        }
    }

    // 2. Registrar vehículo
    private static void registrarVehiculo(Scanner teclado, List<Vehiculo> flotilla) {
        System.out.println("\n--- REGISTRAR VEHÍCULO ---");
        System.out.println("1. Montacargas");
        System.out.println("2. Camión de Reparto");
        System.out.print("Selecciona el tipo de vehículo: ");
        int tipo = teclado.nextInt();
        teclado.nextLine();

        System.out.print("Ingresa la matrícula: ");
        String matricula = teclado.nextLine();

        System.out.print("Ingresa la capacidad máxima de carga en Kg: ");
        double capacidadMaximaKg = teclado.nextDouble();
        teclado.nextLine();

        if (tipo == 1) {
            System.out.print("Ingresa las horas iniciales de uso: ");
            int horasUso = teclado.nextInt();
            teclado.nextLine();

            Montacargas montacargas = new Montacargas(matricula, capacidadMaximaKg, horasUso);
            flotilla.add(montacargas);
            System.out.println("¡Montacargas registrado exitosamente en la flotilla!");
        } else if (tipo == 2) {
            System.out.print("Ingresa el valor del vehículo ($): ");
            double valorVehiculo = teclado.nextDouble();
            teclado.nextLine();

            CamionReparto camion = new CamionReparto(matricula, capacidadMaximaKg, valorVehiculo);
            flotilla.add(camion);
            System.out.println("¡Camión de Reparto registrado exitosamente en la flotilla!");
        } else {
            System.out.println("Tipo de vehículo no válido. Operación cancelada.");
        }
    }

    // 3. Mostrar inventario y calcular tarifas (Polimorfismo)
    private static void mostrarInventarioYTarifas(List<Paquete> inventario) {
        System.out.println("\n--- INVENTARIO Y TARIFAS ---");
        if (inventario.isEmpty()) {
            System.out.println("El inventario está vacío.");
            return;
        }

        for (Paquete p : inventario) {
            System.out.println("ID Rastreo: " + p.getIdRastreo() +
                               " | Peso: " + p.getPesoKg() + " Kg" +
                               " | Tarifa de Envío: $" + p.calcularTarifaEnvio() + " MXN");
        }
    }

    // 4. Auditoría de Seguros (Interfaces)
    private static void auditoriaSeguros(List<Paquete> inventario, List<Vehiculo> flotilla) {
        System.out.println("\n--- AUDITORÍA DE SEGUROS ---");
        double costoTotalPolizas = 0.0;
        int cantidadAsegurables = 0;

        System.out.println("\n>> Paquetes Asegurables:");
        for (Paquete p : inventario) {
            if (p instanceof Asegurable) {
                Asegurable asegurable = (Asegurable) p;
                double poliza = asegurable.calcularPolizaMensual();
                costoTotalPolizas += poliza;
                cantidadAsegurables++;
                System.out.println(" - [Paquete Alto Valor] ID: " + p.getIdRastreo() +
                                   " | Póliza Mensual: $" + poliza + " MXN");
            }
        }

        System.out.println("\n>> Vehículos Asegurables:");
        for (Vehiculo v : flotilla) {
            if (v instanceof Asegurable) {
                Asegurable asegurable = (Asegurable) v;
                double poliza = asegurable.calcularPolizaMensual();
                costoTotalPolizas += poliza;
                cantidadAsegurables++;
                System.out.println(" - [Camión de Reparto] Matrícula: " + v.getMatricula() +
                                   " | Póliza Mensual: $" + poliza + " MXN");
            }
        }

        if (cantidadAsegurables == 0) {
            System.out.println("No hay elementos asegurables registrados.");
        } else {
            System.out.println("\n-------------------------------------------");
            System.out.println("Costo Total a pagar a la aseguradora: $" + costoTotalPolizas + " MXN");
            System.out.println("-------------------------------------------");
        }
    }

    // 5. Transportar Paquetes - Despacho (Uso de Iteradores)
    private static void transportarPaquetes(Scanner teclado, List<Paquete> inventario, List<Vehiculo> flotilla) {
        System.out.println("\n--- TRANSPORTAR PAQUETES (DESPACHO) ---");

        if (inventario.isEmpty()) {
            System.out.println("No hay paquetes en el inventario para despachar.");
            return;
        }

        // Filtrar y mostrar camiones
        List<CamionReparto> camiones = new ArrayList<>();
        for (Vehiculo v : flotilla) {
            if (v instanceof CamionReparto) {
                camiones.add((CamionReparto) v);
            }
        }

        // Filtrar y mostrar montacargas
        List<Montacargas> montacargasLista = new ArrayList<>();
        for (Vehiculo v : flotilla) {
            if (v instanceof Montacargas) {
                montacargasLista.add((Montacargas) v);
            }
        }

        if (camiones.isEmpty()) {
            System.out.println("Error: No hay camiones de reparto registrados para el viaje.");
            return;
        }

        if (montacargasLista.isEmpty()) {
            System.out.println("Error: No hay montacargas registrados para la maniobra.");
            return;
        }

        System.out.println("\nSelecciona el Camión de Reparto:");
        for (int i = 0; i < camiones.size(); i++) {
            CamionReparto c = camiones.get(i);
            System.out.println((i + 1) + ". Matrícula: " + c.getMatricula() +
                               " | Capacidad: " + c.getCapacidadMaximaKg() + " Kg");
        }
        System.out.print("Opción: ");
        int indiceCamion = teclado.nextInt() - 1;
        teclado.nextLine();

        if (indiceCamion < 0 || indiceCamion >= camiones.size()) {
            System.out.println("Selección de camión inválida.");
            return;
        }
        CamionReparto camionSeleccionado = camiones.get(indiceCamion);

        System.out.println("\nSelecciona el Montacargas para la maniobra:");
        for (int i = 0; i < montacargasLista.size(); i++) {
            Montacargas m = montacargasLista.get(i);
            System.out.println((i + 1) + ". Matrícula: " + m.getMatricula() +
                               " | Horas de uso actuales: " + m.getHorasUso());
        }
        System.out.print("Opción: ");
        int indiceMontacargas = teclado.nextInt() - 1;
        teclado.nextLine();

        if (indiceMontacargas < 0 || indiceMontacargas >= montacargasLista.size()) {
            System.out.println("Selección de montacargas inválida.");
            return;
        }
        Montacargas montacargasSeleccionado = montacargasLista.get(indiceMontacargas);

        double capacidadRestante = camionSeleccionado.getCapacidadMaximaKg();
        int paquetesCargados = 0;

        System.out.println("\nIniciando proceso de carga con Iterator...");
        System.out.println("Capacidad inicial del camión (" + camionSeleccionado.getMatricula() + "): " + capacidadRestante + " Kg");

        // Uso estricto de Iterator
        Iterator<Paquete> iterador = inventario.iterator();
        while (iterador.hasNext()) {
            Paquete p = iterador.next();
            if (p.getPesoKg() <= capacidadRestante) {
                // Se carga al camión y se retira del inventario (almacén)
                capacidadRestante -= p.getPesoKg();
                iterador.remove();
                montacargasSeleccionado.incrementarHorasUso();
                paquetesCargados++;
                System.out.println(" [CARGADO] Paquete ID: " + p.getIdRastreo() +
                                   " | Peso: " + p.getPesoKg() + " Kg | Capacidad restante: " + capacidadRestante + " Kg");
            } else {
                System.out.println(" [EXCEDENTE / NO CABE] Paquete ID: " + p.getIdRastreo() +
                                   " | Peso: " + p.getPesoKg() + " Kg (Supera la capacidad restante)");
            }
        }

        System.out.println("\n--- RESUMEN DEL DESPACHO ---");
        System.out.println("Paquetes cargados con éxito: " + paquetesCargados);
        System.out.println("Capacidad restante final del camión: " + capacidadRestante + " Kg");
        System.out.println("Nuevas horas de uso del Montacargas (" + montacargasSeleccionado.getMatricula() + "): " + montacargasSeleccionado.getHorasUso());
        System.out.println("Paquetes restantes en inventario: " + inventario.size());
    }
}

