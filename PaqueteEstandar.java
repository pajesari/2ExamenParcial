public class PaqueteEstandar extends Paquete {
    private String zonaEntrega; // Local, Nacional, Internacional

    public PaqueteEstandar(String idRastreo, double pesoKg, String zonaEntrega) {
        super(idRastreo, pesoKg);
        this.zonaEntrega = zonaEntrega;
    }

    @Override
    public double calcularTarifaEnvio() {
        // Regla: $50.00 MXN + ($10.00 MXN por cada Kg)
        return 50.0 + (10.0 * pesoKg);
    }

    // Getters y Setters
    public String getZonaEntrega() { return zonaEntrega; }
    public void setZonaEntrega(String zonaEntrega) { this.zonaEntrega = zonaEntrega; }
}
