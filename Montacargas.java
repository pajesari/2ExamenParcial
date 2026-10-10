public class Montacargas extends Vehiculo {
    private int horasUso;

    public Montacargas(String matricula, double capacidadMaximaKg, int horasUso) {
        super(matricula, capacidadMaximaKg);
        this.horasUso = horasUso;
    }

    // Getters y Setters
    public int getHorasUso() { return horasUso; }
    public void setHorasUso(int horasUso) { this.horasUso = horasUso; }
    
    // Método para incrementar las horas de uso cuando sube paquetes exitosamente
    public void incrementarHorasUso() {
        this.horasUso++;
    }
}
