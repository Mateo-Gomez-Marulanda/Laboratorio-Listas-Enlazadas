package co.edu.uniquindio.poo.Unidad2.Simple.Laboratorio.Punto3;

public class PlayList {
    public static void main(String[] args) {

        CircularSimpleList<String> playlist = new CircularSimpleList<>();

        playlist.add("Shape of You");
        playlist.add("Blinding Lights");
        playlist.add("Levitating");
        playlist.add("As It Was");

        System.out.println("Playlist inicial:");
        playlist.show();

        System.out.println("\nCanción actual:");
        playlist.currentSong();

        // simula reproducción continua
        System.out.println("\n⏭ Avanzando canciones:");
        for (int i = 0; i < 6; i++) {
            playlist.nextSong();
        }

        System.out.println("\n Eliminando 'Levitating':");
        playlist.remove("Levitating");
        playlist.show();

        System.out.println("\n Continuando reproducción:");
        for (int i = 0; i < 5; i++) {
            playlist.nextSong();
        }

        System.out.println("\nEliminando canción actual:");
        playlist.remove("As It Was");
        playlist.show();

        System.out.println("\nReproducción final:");
        for (int i = 0; i < 4; i++) {
            playlist.nextSong();
        }
    }
}

class CircularSimpleList<T> {
    private Node<T> first;
    private Node<T> current;
    private int size;

    public CircularSimpleList() {
        first = null;
        current = null;
        size = 0;
    }

    public void add(T data) {

        Node<T> newNode = new Node<>(data);

        if (isEmpty()) {
             first = current = newNode;
            newNode.setNext(newNode);
        } else {
            Node<T> aux = current.getNext();
            current.setNext(newNode);
            newNode.setNext(aux);
            current = newNode;
        }
        size++;
    }

    public boolean isEmpty() {
        return (first == null) ? true : false;
    }

    public void nextSong() {
        if (current != null) {
            current = current.getNext();
            System.out.println("Reproduciendo: " + current.getData());
        }
    }

    public void remove(T data) {
        if (first == null)
            return;

        Node<T> aux = first;
        Node<T> prev = null;

        do {
            if (aux.getData().equals(data)) {

                if (aux == first && aux.getNext() == first) {
                    first = null;
                    current = null;
                    return;
                }

                if (aux == first) {
                    Node<T> last = first;

                    while (last.getNext() != first) {
                        last = last.getNext();
                    }

                    first = first.getNext();
                    last.setNext(first);
                    if (current == aux)
                        current = first;
                    return;
                }

                prev.setNext(aux.getNext());
                if (current == aux)
                    current = aux.getNext();
                return;
            }

            prev = aux;
            aux = aux.getNext();

        } while (aux != first);
    }

    public void show() {
        if (first == null) {
            System.out.println("Lista vacía");
            return;
        }

        Node<T> aux = first;

        do {
            System.out.print(aux.getData());
            aux = aux.getNext();
        } while (aux != first);

        System.out.println("(vuelve al inicio)");
    }

    public void currentSong() {
        if (current != null) {
            System.out.println("Actual: " + current.getData());
        }
    }
}

class Node<T> {
    private T data;
    private Node<T> next;
    private Node<T> previous;

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public Node<T> getNext() {
        return next;
    }

    public void setNext(Node<T> next) {
        this.next = next;
    }

    public Node<T> getPrevious() {
        return previous;
    }

    public void setPrevious(Node<T> previous) {
        this.previous = previous;
    }

    public Node(T data) {
        this.data = data;
        this.next = null;
        this.previous = null;
    }

}