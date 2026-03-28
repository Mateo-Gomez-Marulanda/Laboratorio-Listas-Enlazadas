package co.edu.uniquindio.poo.Unidad2.Simple.Laboratorio.Punto1;

public class Panaderia {
    public static void main(String[] args) {
        // Crear la lista de clientes
        SimpleList<Cliente> clientes = new SimpleList<>();

        // Agregar clientes a la lista
        clientes.add(new Cliente("Ana", "1"));
        clientes.add(new Cliente("Luis", "2"));
        clientes.add(new Cliente("Carlos", "3"));
        clientes.add(new Cliente("Marta", "4"));

        System.out.println("Lista de clientes al inicio:");
        clientes.showTurn();

        // Atender al primer cliente
        System.out.println("\nAtendiendo al primer cliente...");
        clientes.serve();

        System.out.println("Lista de clientes después de atender uno:");
        clientes.showTurn();

        // Mostrar el siguiente turno (el próximo cliente)
        if (!clientes.isEmptyList()) {
            System.out.println();
            clientes.obtainsNextTurn();
        } else {
            System.out.println("No hay más clientes en la lista.");
        }

        // Atender a otro cliente
        System.out.println("\nAtendiendo al siguiente cliente...");
        clientes.serve();

        System.out.println("Lista de clientes final:");
        clientes.showTurn();
    }
}

class Node<T> {
    private T data;
    private Node<T> Next;

    public T getdata() {
        return data;
    }

    public void setdata(T data) {
        this.data = data;
    }

    public Node<T> getNext() {
        return Next;
    }

    public void setNext(Node<T> Next) {
        this.Next = Next;
    }

    public Node(T data) {
        this.data = data;
        this.Next = null;
    }

}
class SimpleList<T>{
    private Node<T> first;
    private int size;

    public int size() {
        return size;
    }

    public SimpleList() {
        this.first = null;
        this.size = 0;
    }

    public boolean isEmptyList() {
        if (first == null && size == 0) {
            return true;
        }
        return false;

    }

    public void add(T data) {
        Node<T> newNode = new Node<>(data);
        if (isEmptyList()) {
            first = newNode;
        } else {
            Node<T> aux = first;
            while (aux.getNext() != null) {
                aux = aux.getNext();
            }
            aux.setNext(newNode);
        }
        size++;
    }

    public void serve() {
        if (isEmptyList()) {
            throw new RuntimeException("List is empty");
        } else {
            Node<T> next = first.getNext();
            first.setNext(null);
            first = next;
        }
        size--;

    }

    public void showTurn() {
        Node<T> aux = first;
        String message = "[";
        do {
            message += aux.getdata() + " ";
            aux = aux.getNext();
        } while (aux != null);

        message += "]";
        System.out.println(message);
    }

    public void obtainsNextTurn(){
        Node<T> aux = first;
        Node<T> next = aux.getNext();
        System.out.println("Turno actual:"+ first.getdata());
        System.out.println("Siguiente turno:"+ next.getdata());
    }

}

class Cliente {
    private String name;
    private String id;

    public Cliente(String name, String id){
        this.id = id;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return name;
    }
    
}
