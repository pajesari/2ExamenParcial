import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner teclado = new Scanner(System.in);
        List<Paquete> inventario = new ArrayList<>();
        List<Vehiculo> flotilla = new ArrayList<>();

        int opcion = -1;

        while (opcion != 0) {
            try {
                System.out.println("\n=====> CEDIS: CENTRO DE DISTRIBUCION Y TRANSPORTE <=====");
                System.out.println("1. Ingresar nuevo paquete");
                System.out.println("2. Registrar vehículo");
                System.out.println("3. Mostrar inventario y calcular tarifas");
                System.out.println("4. Auditoría de Seguros");
                System.out.println("5. Transportar Paquetes (Despacho)");
                System.out.println("0. Salir");
                System.out.print("Elige una opción: ");
                
                opcion = teclado.nextInt();
                teclado.nextLine(); // Limpiar el buffer

                switch (opcion) {
                    case 1:
                        System.out.println("\n--- INGRESAR NUEVO PAQUETE ---");
                        // TODO: Implementar lógica de ingreso de paquetes
                        break;
                    case 2:
                        System.out.println("\n--- REGISTRAR VEHÍCULO ---");
                        // TODO: Implementar lógica de registro de vehículos
                        break;
                    case 3:
                        System.out.println("\n--- INVENTARIO Y TARIFAS ---");
                        // TODO: Implementar iteración y polimorfismo para calcular tarifas
                        break;
                    case 4:
                        System.out.println("\n--- AUDITORÍA DE SEGUROS ---");
                        // TODO: Identificar Asegurables, calcular pólizas y costo total
                        break;
                    case 5:
                        System.out.println("\n--- TRANSPORTAR PAQUETES (DESPACHO) ---");
                        // TODO: Implementar uso de Iterator, cargar paquetes al camión y reglas de montacargas
                        break;
                    case 0:
                        System.out.println("Saliendo del sistema...");
                        break;
                    default:
                        System.out.println("Opción no válida. Intenta de nuevo.");
                }
            } catch (Exception e) {
                System.out.println("\n[ERROR] Entrada inválida. Por favor ingresa un número válido.");
                teclado.nextLine(); // Limpiar la basura del teclado para que no se cicle
                opcion = -1;
            }
        }
        teclado.close();
    }
}
