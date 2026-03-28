/*
Escenario 2. Historial de navegación de un navegador web
Un navegador web necesita almacenar las páginas visitadas por un usuario.
El sistema debe permitir visitar una nueva página, regresar a la página anterior, 
avanzar nuevamente si ya había retrocedido, 
eliminar las páginas futuras cuando el usuario abre una nueva página después de retroceder, 
y mostrar la página actual. El sistema necesita moverse en ambos sentidos dentro del historial.
*/

//se usan las listas creadas por el profesor

public class Main {
    public static void main(String[] args) {
        WebSistem webSistem = new WebSistem();

        webSistem.visitarPaginaWeb(new PaginaWeb("Google", "www.google.com"));
        webSistem.visitarPaginaWeb(new PaginaWeb("Facebook", "www.facebook.com"));
        webSistem.visitarPaginaWeb(new PaginaWeb("Twitter", "www.twitter.com"));
        webSistem.visitarPaginaWeb(new PaginaWeb("LinkedIn", "www.linkedin.com"));

        webSistem.regresarPagina();
        webSistem.regresarPagina();
        webSistem.avanzarPagina();
        webSistem.visitarPaginaWeb(new PaginaWeb("Instagram", "www.instagram.com"));
        webSistem.mostrarHistorial();
        webSistem.cerrarPaginaWebActual();
        webSistem.mostrarHistorial();
    }
}

class WebSistem {
    private ListaDoble<PaginaWeb> historial;
    private NodoDoble<PaginaWeb> paginaActual;

    public WebSistem() {
        historial = new ListaDoble<>();
        paginaActual = null;
    }

    public void visitarPaginaWeb(PaginaWeb paginaWeb) {
        // Si estamos en medio del historial, eliminar todas las páginas futuras
        if (paginaActual != null && paginaActual != historial.getNodoUltimo()) {
            eliminarPaginasFuturas();
        }

        historial.agregarfinal(paginaWeb);
        paginaActual = historial.getNodoUltimo();
        mostrarPaginaActual();
    }

    public void cerrarPaginaWebActual() {
        historial.eliminar(paginaActual.getValorNodo());
    }

    private void eliminarPaginasFuturas() {
        NodoDoble<PaginaWeb> nodoActual = paginaActual.getSiguienteNodo();

        while (nodoActual != null) {
            NodoDoble<PaginaWeb> siguiente = nodoActual.getSiguienteNodo();
            historial.eliminar(nodoActual.getValorNodo());
            nodoActual = siguiente;
        }
    }

    public void avanzarPagina() {

        if (paginaActual == null || paginaActual.getSiguienteNodo() == null) {
            throw new IllegalStateException("No hay páginas futuras para avanzar.");
        }

        paginaActual = paginaActual.getSiguienteNodo();
        System.out.println(paginaActual);
        mostrarPaginaActual();

    }

    public void regresarPagina() {
        if (paginaActual == null || paginaActual.getAnteriorNodo() == null) {
            throw new IllegalStateException("No hay páginas anteriores para regresar.");
        }

        paginaActual = paginaActual.getAnteriorNodo();
        System.out.println(paginaActual);
        mostrarPaginaActual();
    }

    public NodoDoble<PaginaWeb> mostrarPaginaActual() {
        return paginaActual;
    }

    public void mostrarHistorial() {
        historial.imprimirLista();
    }
}

class PaginaWeb {
    private String nombre;
    private String enlace;

    public PaginaWeb(String nombre, String enlace) {
        this.nombre = nombre;
        this.enlace = enlace;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEnlace() {
        return enlace;
    }

    public void setEnlace(String enlace) {
        this.enlace = enlace;
    }

    @Override
    public String toString() {
        return "PaginaWeb [nombre=" + nombre + ", enlace=" + enlace + "]";
    }
}