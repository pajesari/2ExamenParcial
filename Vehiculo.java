public abstract class Vehiculo {
    protected String matricula;
    protected double capacidadMaximaKg;

    public Vehiculo(String matricula, double capacidadMaximaKg) {
        this.matricula = matricula;
        this.capacidadMaximaKg = capacidadMaximaKg;
    }

    // Getters y Setters
    public String getMatricula() {
         return matricula; 
     }
    public void setMatricula(String matricula) {
         this.matricula = matricula;
        
    }
    
    public double getCapacidadMaximaKg() {
        return capacidadMaximaKg;
    }
    public void setCapacidadMaximaKg(double capacidadMaximaKg) {
         this.capacidadMaximaKg = capacidadMaximaKg;
    }
}
