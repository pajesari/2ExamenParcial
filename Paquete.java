public abstract class Paquete {
    protected String idRastreo;
    protected double pesoKg;

    public Paquete(String idRastreo, double pesoKg) {
        this.idRastreo = idRastreo;
        this.pesoKg = pesoKg;
    }

    // Método abstracto que obliga a las clases hijas a definir cómo calculan su tarifa
    public abstract double calcularTarifaEnvio();

    // Getters y Setters
    public String getIdRastreo() { return idRastreo; }
    public void setIdRastreo(String idRastreo) { this.idRastreo = idRastreo; }
    
    public double getPesoKg() { return pesoKg; }
    public void setPesoKg(double pesoKg) { this.pesoKg = pesoKg; }
}
