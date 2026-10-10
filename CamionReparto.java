public class CamionReparto extends Vehiculo implements Asegurable {
    private double valorVehiculo;

    public CamionReparto(String matricula, double capacidadMaximaKg, double valorVehiculo) {
        super(matricula, capacidadMaximaKg);
        this.valorVehiculo = valorVehiculo;
    }

    @Override
    public double calcularPolizaMensual() {
     // Costo fijo de 2500 + 2por ciento del Valor del Vehículo
        return 2500 + (this.valorVehiculo * 0.02);
    }

    // Getters y Setters
    public double getValorVehiculo() {
         return valorVehiculo; 
        }
    public void setValorVehiculo(double valorVehiculo) {
         this.valorVehiculo = valorVehiculo; 
        }
}
