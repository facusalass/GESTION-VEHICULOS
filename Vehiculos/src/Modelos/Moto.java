package Modelos;

public class Moto extends Vehiculo {
    private int cilindrada;

    public Moto(String patente, String marca, String modelo, Integer kilometraje, int cilindrada) {
        super(patente, marca, modelo, kilometraje);
        this.cilindrada = cilindrada;
    }

    @Override
    public void realizarServicioBasico() {
        System.out.println("Lubricando cadena y ajustando frenos de la Moto " + getMarca());
    }

    @Override
    public String toString() {
        return super.toString() + " | Tipo: Moto (" + cilindrada + "cc)";
    }
}