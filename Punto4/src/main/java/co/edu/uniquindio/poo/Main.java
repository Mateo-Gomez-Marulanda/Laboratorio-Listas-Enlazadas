package co.edu.uniquindio.poo;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Spliterator;
import java.util.function.Consumer;

public class Main {

    //Escenario 4: Juego por turnos de varios jugadores

    public static void main(String[] args) {
        ListaDobleCircular<String> listaJugadores = new ListaDobleCircular<>();

        listaJugadores.agregarFinal("Juan");
        listaJugadores.agregarFinal("Pedro");
        listaJugadores.agregarFinal("Ana");
        listaJugadores.agregarFinal("Maria");

        listaJugadores.imprimirLista();

        listaJugadores.remover("Pedro");

        listaJugadores.imprimirLista();
    }
}

class NodoDoble<T> {

    private NodoDoble<T> siguienteNodo;
    private NodoDoble<T> anteriorNodo;
    private T valorNodo;


    /**
     * Constructor de la clase Nodo
     * @param dato Elemento que se guarda en el Nodo
     */
    public NodoDoble(T valorNodo) {
        this.valorNodo = valorNodo;
    }


    /**
     * Constructor de la clase Nodo
     * @param dato Elemento que se guarda en el Nodo
     * @param siguiente Enlace al siguiente Nodo
     */
    public NodoDoble(T dato, NodoDoble<T> siguiente,NodoDoble<T> anterior) {
        super();
        this.valorNodo = dato;
        this.siguienteNodo = siguiente;
        this.anteriorNodo = anterior;
    }


    //Metodos get y set de la clase Nodo

    public NodoDoble<T> getSiguienteNodo() {
        return siguienteNodo;
    }


    public void setSiguienteNodo(NodoDoble<T> siguienteNodo) {
        this.siguienteNodo = siguienteNodo;
    }


    public T getValorNodo() {
        return valorNodo;
    }


    public void setValorNodo(T valorNodo) {
        this.valorNodo = valorNodo;
    }


    public NodoDoble<T> getAnteriorNodo() {
        return anteriorNodo;
    }


    public void setAnteriorNodo(NodoDoble<T> anteriorNodo) {
        this.anteriorNodo = anteriorNodo;
    }
}

class ListaDobleCircular<T> {

    private NodoDoble<T> nodoPrimero;
    private NodoDoble<T> nodoUltimo;
    private int tamanio;

    public ListaDobleCircular() {
        nodoPrimero = null;
        nodoUltimo = null;
        tamanio = 0;
    }

    /**
     * Agrega un nuevo nodo en la posici�n dada
     * @param valor
     * @param posicion
     */
    public void insertar( T valor, int posicion ) {
        int cont = 0;

        for( NodoDoble<T> aux = nodoPrimero; cont < tamanio; cont++, aux = aux.getSiguienteNodo() ) {
            if( cont == posicion ) {
                NodoDoble<T> aux2 = aux.getAnteriorNodo();
                NodoDoble<T> nuevo = new NodoDoble<>( valor );
                aux2.setSiguienteNodo( nuevo );
                nuevo.setSiguienteNodo( aux );
                nuevo.setAnteriorNodo( aux2 );
                aux.setAnteriorNodo( nuevo );
                tamanio ++;
            }
        }
    }

    /**
     * Busca y retorna la posici�n de un nodo que tenga el valor ingresado por par�metro
     * @param valor a buscar
     * @return posici�n donde se encontr� el nodo
     */
    public int buscar( T valor ) {
        int cont = 0;
        int pos = -1;

        for( NodoDoble<T> aux = nodoPrimero; cont < tamanio; cont++, aux = aux.getSiguienteNodo() ) {
            if( aux.getValorNodo().equals( valor ) ) {
                pos = cont;
            }
        }
        return pos;
    }

    public void agregarFinal(T valorNodo) {

        NodoDoble<T> nuevoNodo = new NodoDoble<>( valorNodo );

        if( estaVacia() ) {
            nodoPrimero = nodoUltimo = nuevoNodo;
        }
        else {
            nuevoNodo.setSiguienteNodo(nodoPrimero);
            nodoPrimero.setAnteriorNodo(nuevoNodo);
            nodoUltimo.setSiguienteNodo( nuevoNodo );
            nuevoNodo.setAnteriorNodo( nodoUltimo );
            nodoPrimero = nuevoNodo;
        }
        tamanio++;
    }

    //Verificar si la lista esta vacia
    public boolean estaVacia() {
        return nodoPrimero == null && nodoUltimo == null;
    }

    /**
     * Imprime en consola la lista enlazada
     */
    public void imprimirLista() {

        NodoDoble<T> aux = nodoPrimero;
        int cont = 0;

        while( aux!=null && cont != tamanio ) {
            System.out.print("Turno de: " + aux.getValorNodo()+"\t -> " );
            aux = aux.getSiguienteNodo();
            cont ++;
        }

        System.out.println("Turno de: " + nodoPrimero.getValorNodo());
    }

    public boolean remover( T valor ) {
        NodoDoble<T> aux = nodoPrimero;
        int cont = 0;
        while(cont < tamanio) {
            if( aux.getValorNodo().equals( valor ) ) {
                NodoDoble<T> aux2 = aux.getAnteriorNodo();
                NodoDoble<T> aux3 = aux.getSiguienteNodo();
                aux2.setSiguienteNodo( aux.getSiguienteNodo() );
                aux3.setAnteriorNodo(aux2);
                tamanio--;
                System.out.println("\n* " + valor + " ha sido eliminado");
                return true;
            } else {
                aux = aux.getSiguienteNodo();
            }
        }
        System.out.println("\n* " + valor + " No ha sido encontrado en la lista");
        return false;
    }
}
