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
                System.out.println("\nCENTRO DE DISTRIBUCION Y TRANSPORTE ");
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
                        System.out.println("\nSaliendo del sistema");
                        break;
                    default:
                        System.out.println("\nopcion no valida intente de nuevo");
                }
            } catch (Exception e) {
                System.out.println("\nerrorÑ porfavor ingrese un valor valido ");
                teclado.nextLine(); // Limpiar la basura del teclado 
                opcion = -1;
            }
        }
        teclado.close();
    }

    private static void ingresarNuevoPaquete(Scanner teclado, List<Paquete> inventario) {
        System.out.println("\nIngrese nuevo Paquete");
        System.out.println("1. Paquete Estandar");
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
            System.out.println("Paquete Estandar guardado");
        } else if (tipo == 2) {
            System.out.print("Ingresa el valor declarado ($): ");
            double valorDeclarado = teclado.nextDouble();
            teclado.nextLine();

            PaqueteAltoValor paquete = new PaqueteAltoValor(idRastreo, pesoKg, valorDeclarado);
            inventario.add(paquete);
            System.out.println("Paquete de Alto Valor guardado!");
        } else {
            System.out.println("Tipo de paquete inexistente");
        }
    }

    private static void registrarVehiculo(Scanner teclado, List<Vehiculo> flotilla) {
        System.out.println("\nRegistrar vehiculo");
        System.out.println("1. Montacargas");
        System.out.println("2. Camion de Reparto");
        System.out.print("Selecciona el tipo de vehiculo: ");
        int tipo = teclado.nextInt();
        teclado.nextLine();

        System.out.print("Ingresa la matricula: ");
        String matricula = teclado.nextLine();

        System.out.print("Ingresa la capacidad maxima de carga en Kg: ");
        double capacidadMaximaKg = teclado.nextDouble();
        teclado.nextLine();

        if (tipo == 1) {
            System.out.print("Ingresa las horas iniciales de uso: ");
            int horasUso = teclado.nextInt();
            teclado.nextLine();

            Montacargas montacargas = new Montacargas(matricula, capacidadMaximaKg, horasUso);
            flotilla.add(montacargas);
            System.out.println("Montacargas registrado");
        } else if (tipo == 2) {
            System.out.print("Ingresa el valor del vehículo ($): ");
            double valorVehiculo = teclado.nextDouble();
            teclado.nextLine();

            CamionReparto camion = new CamionReparto(matricula, capacidadMaximaKg, valorVehiculo);
            flotilla.add(camion);
            System.out.println("Camion de Reparto registrado");
        } else {
            System.out.println("Tipo de vehiculo no existente.");
        }
    }

    private static void mostrarInventarioYTarifas(List<Paquete> inventario) {
        System.out.println("\nInventario y tarifas");
        if (inventario.isEmpty()) {
            System.out.println("El inventario est vacio");
            return;
        }

        for (Paquete p : inventario) {
            System.out.println("ID Rastreo: " + p.getIdRastreo() +
                               "  Peso: " + p.getPesoKg() + " Kg" +
                               "  Tarifa de Envío: $" + p.calcularTarifaEnvio() + " mxn");
        }
    }

    private static void auditoriaSeguros(List<Paquete> inventario, List<Vehiculo> flotilla) {
        System.out.println("\nAuditoria de Seguros");
        double costoTotalPolizas = 0.0;
        int cantidadAsegurables = 0;

        System.out.println("\n>Paquetes Asegurables:");
        for (Paquete p : inventario) {
            if (p instanceof Asegurable) {
                Asegurable asegurable = (Asegurable) p;
                double poliza = asegurable.calcularPolizaMensual();
                costoTotalPolizas += poliza;
                cantidadAsegurables++;
                System.out.println(" Paquete Alto Valor ID: " + p.getIdRastreo() +
                                   "  Póliza Mensual: $" + poliza + " mxn");
            }
        }

        System.out.println("\n Vehiculos Asegurables:");
        for (Vehiculo v : flotilla) {
            if (v instanceof Asegurable) {
                Asegurable asegurable = (Asegurable) v;
                double poliza = asegurable.calcularPolizaMensual();
                costoTotalPolizas += poliza;
                cantidadAsegurables++;
                System.out.println(" Camion de Reparto Matricula: " + v.getMatricula() +
                                   "  Poliza Mensual: $" + poliza + " mxn");
            }
        }

        if (cantidadAsegurables == 0) {
            System.out.println("No hay elementos asegurables .");
        } else {
            System.out.println("Costo Total a pagar a la aseguradora: $" + costoTotalPolizas + " MXN");
            
        }
    }

    private static void transportarPaquetes(Scanner teclado, List<Paquete> inventario, List<Vehiculo> flotilla) {
        System.out.println("\nTrsnaportar paquetes");

        if (inventario.isEmpty()) {
            System.out.println("No hay paquetes en el inventario ");
            return;
        }

        List<CamionReparto> camiones = new ArrayList<>();
        for (Vehiculo v : flotilla) {
            if (v instanceof CamionReparto) {
                camiones.add((CamionReparto) v);
            }
        }

        List<Montacargas> montacargasLista = new ArrayList<>();
        for (Vehiculo v : flotilla) {
            if (v instanceof Montacargas) {
                montacargasLista.add((Montacargas) v);
            }
        }

        if (camiones.isEmpty()) {
            System.out.println("error no hay montacargas registrados ");
            return;
        }

        if (montacargasLista.isEmpty()) {
            System.out.println("error no hay montacargas dosponibles");
            return;
        }

        System.out.println("\nSelecciona el Camion de Reparto:");
        for (int i = 0; i < camiones.size(); i++) {
            CamionReparto c = camiones.get(i);
            System.out.println((i + 1) + ". Matricula: " + c.getMatricula() +
                               " | Capacidad: " + c.getCapacidadMaximaKg() + " Kg");
        }
        System.out.print("Opcion: ");
        int indiceCamion = teclado.nextInt() - 1;
        teclado.nextLine();

        if (indiceCamion < 0 || indiceCamion >= camiones.size()) {
            System.out.println("Seleccion de camion invalida.");
            return;
        }
        CamionReparto camionSeleccionado = camiones.get(indiceCamion);

        System.out.println("\nSelecciona el Montacargas para la maniobra:");
        for (int i = 0; i < montacargasLista.size(); i++) {
            Montacargas m = montacargasLista.get(i);
            System.out.println((i + 1) + ". Matrícula: " + m.getMatricula() +
                               " | Horas de uso actuales: " + m.getHorasUso());
        }
        System.out.print("Opcion: ");
        int indiceMontacargas = teclado.nextInt() - 1;
        teclado.nextLine();

        if (indiceMontacargas < 0 || indiceMontacargas >= montacargasLista.size()) {
            System.out.println("Seleccion de montacargas invalida");
            return;
        }
        Montacargas montacargasSeleccionado = montacargasLista.get(indiceMontacargas);

        double capacidadRestante = camionSeleccionado.getCapacidadMaximaKg();
        int paquetesCargados = 0;

        System.out.println("Capacidad inicial del camión (" + camionSeleccionado.getMatricula() + "): " + capacidadRestante + " Kg");

        Iterator<Paquete> iterador = inventario.iterator();
        while (iterador.hasNext()) {
            Paquete p = iterador.next();
            if (p.getPesoKg() <= capacidadRestante) {
                capacidadRestante -= p.getPesoKg();
                iterador.remove();
                montacargasSeleccionado.incrementarHorasUso();
                paquetesCargados++;
                System.out.println(" Paquete ID: " + p.getIdRastreo() +
                                   " Peso: " + p.getPesoKg() + " Kg | Capacidad restante: " + capacidadRestante + " Kg");
            } else {
                System.out.println(" exedente d epeso Paquete ID: " + p.getIdRastreo() +
                                   "  Peso: " + p.getPesoKg() + " Kg - sipera la capacidad");
            }
        }

        System.out.println("\nresumen de las operaciones");
        System.out.println("Paquetes cargados con exito: " + paquetesCargados);
        System.out.println("Capacidad restante final del camión: " + capacidadRestante + " Kg");
        System.out.println("Nuevas horas de uso del Montacargas (" + montacargasSeleccionado.getMatricula() + "): " + montacargasSeleccionado.getHorasUso());
        System.out.println("Paquetes restantes en inventario: " + inventario.size());
    }
}

