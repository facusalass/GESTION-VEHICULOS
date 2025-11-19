package Modelos;

public class ListaOperaciones {

    private NodoOperacion cabeza;

    public void agregar(String descripcion) {
        NodoOperacion nuevo = new NodoOperacion(descripcion);
        nuevo.siguiente = cabeza;
        cabeza = nuevo;
    }

    public void mostrarHistorial() {
        NodoOperacion actual = cabeza;
        while (actual != null) {
            System.out.println(actual.descripcion);
            actual = actual.siguiente;
        }
    }
}

