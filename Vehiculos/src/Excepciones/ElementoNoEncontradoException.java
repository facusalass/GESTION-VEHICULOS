package Excepciones;

public class ElementoNoEncontradoException extends Exception {


    public ElementoNoEncontradoException() {
        super("El elemento buscado no existe en el sistema.");
    }


    public ElementoNoEncontradoException(String mensaje) {
        super(mensaje);
    }
}