public class PaqueteAltoValor extends Paquete implements Asegurable {
    private double valorDeclarado;

    public PaqueteAltoValor(String idRastreo, double pesoKg, double valorDeclarado) {
        super(idRastreo, pesoKg);
        this.valorDeclarado = valorDeclarado;
    }

    @Override
    public double calcularTarifaEnvio() {
        // Regla: $150.00 MXN + ($25.00 MXN por cada Kg)
        return 150.0 + (25.0 * pesoKg);
    }

    @Override
    public double calcularPolizaMensual() {
        // Regla: Es igual al 5% de su Valor Declarado
        return this.valorDeclarado * 0.05;
    }

    // Getters y Setters
    public double getValorDeclarado() { return valorDeclarado; }
    public void setValorDeclarado(double valorDeclarado) { this.valorDeclarado = valorDeclarado; }
}
